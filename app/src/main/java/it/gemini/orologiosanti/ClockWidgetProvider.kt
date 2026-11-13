package it.gemini.orologiosanti

import android.app.AlarmManager
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.widget.RemoteViews
import java.util.Calendar

class ClockWidgetProvider : AppWidgetProvider() {

    companion object {
        const val ACTION_TOGGLE_THEME = "it.gemini.orologiosanti.ACTION_TOGGLE_THEME"
        private const val PREFS = "widget_prefs"
        private const val KEY_THEME = "theme_idx"

        fun updateAllWidgets(context: Context) {
            val manager = AppWidgetManager.getInstance(context)
            val ids = manager.getAppWidgetIds(ComponentName(context, ClockWidgetProvider::class.java))
            for (id in ids) {
                updateSingleWidget(context, manager, id)
            }
        }

        fun updateSingleWidget(context: Context, manager: AppWidgetManager, widgetId: Int) {
            val views = RemoteViews(context.packageName, R.layout.widget_clock)
            val now = Calendar.getInstance()

            views.setTextViewText(R.id.date_text, SantiHelper.getFormattedDate(now))
            views.setTextViewText(R.id.saint_text, SantiHelper.getDayDescription(now))

            // Tema
            val prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
            val themeIdx = prefs.getInt(KEY_THEME, 0) % 4
            when (themeIdx) {
                0 -> { // Trasparente testo bianco
                    views.setInt(R.id.widget_root, "setBackgroundColor", Color.TRANSPARENT)
                    views.setTextColor(R.id.clock_hm, Color.WHITE)
                    views.setTextColor(R.id.clock_sec, Color.parseColor("#E6FFFFFF"))
                    views.setTextColor(R.id.date_text, Color.WHITE)
                    views.setTextColor(R.id.saint_text, Color.parseColor("#F2FFFFFF"))
                }
                1 -> { // Trasparente testo scuro
                    views.setInt(R.id.widget_root, "setBackgroundColor", Color.TRANSPARENT)
                    views.setTextColor(R.id.clock_hm, Color.parseColor("#111111"))
                    views.setTextColor(R.id.clock_sec, Color.parseColor("#CC111111"))
                    views.setTextColor(R.id.date_text, Color.parseColor("#111111"))
                    views.setTextColor(R.id.saint_text, Color.parseColor("#E6111111"))
                }
                2 -> { // Sfondo nero
                    views.setInt(R.id.widget_root, "setBackgroundColor", Color.parseColor("#CC000000"))
                    views.setTextColor(R.id.clock_hm, Color.WHITE)
                    views.setTextColor(R.id.clock_sec, Color.parseColor("#E6FFFFFF"))
                    views.setTextColor(R.id.date_text, Color.WHITE)
                    views.setTextColor(R.id.saint_text, Color.parseColor("#F2FFFFFF"))
                }
                3 -> { // Sfondo bianco
                    views.setInt(R.id.widget_root, "setBackgroundColor", Color.parseColor("#E6FFFFFF"))
                    views.setTextColor(R.id.clock_hm, Color.parseColor("#111111"))
                    views.setTextColor(R.id.clock_sec, Color.parseColor("#CC111111"))
                    views.setTextColor(R.id.date_text, Color.parseColor("#111111"))
                    views.setTextColor(R.id.saint_text, Color.parseColor("#E6111111"))
                }
            }

            // Ridimensionamento fluido in base alla larghezza reale del widget
            val options = manager.getAppWidgetOptions(widgetId)
            val minWidthDp = options.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_WIDTH, 250)
            val scale = (minWidthDp / 280f).coerceIn(0.65f, 1.85f)

            views.setTextViewTextSize(R.id.clock_hm, TypedValue.COMPLEX_UNIT_SP, 44f * scale)
            views.setTextViewTextSize(R.id.clock_sec, TypedValue.COMPLEX_UNIT_SP, 24f * scale)
            views.setTextViewTextSize(R.id.date_text, TypedValue.COMPLEX_UNIT_SP, 22f * scale)
            views.setTextViewTextSize(R.id.saint_text, TypedValue.COMPLEX_UNIT_SP, 14f * scale)

            // Tap sul widget per ciclare i temi
            val toggleIntent = Intent(context, ClockWidgetProvider::class.java).apply {
                action = ACTION_TOGGLE_THEME
            }
            val pendingIntent = PendingIntent.getBroadcast(
                context, 0, toggleIntent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
            views.setOnClickPendingIntent(R.id.widget_root, pendingIntent)

            manager.updateAppWidget(widgetId, views)
        }

        fun scheduleMidnightAlarm(context: Context) {
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent = Intent(context, ClockWidgetProvider::class.java).apply {
                action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
            }
            val pi = PendingIntent.getBroadcast(
                context, 1, intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
            val nextMidnight = Calendar.getInstance().apply {
                add(Calendar.DAY_OF_YEAR, 1)
                set(Calendar.HOUR_OF_DAY, 0)
                set(Calendar.MINUTE, 0)
                set(Calendar.SECOND, 5)
            }
            alarmManager.setInexactRepeating(
                AlarmManager.RTC,
                nextMidnight.timeInMillis,
                AlarmManager.INTERVAL_DAY,
                pi
            )
        }
    }

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        for (id in appWidgetIds) {
            updateSingleWidget(context, appWidgetManager, id)
        }
        scheduleMidnightAlarm(context)
    }

    override fun onAppWidgetOptionsChanged(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int,
        newOptions: Bundle
    ) {
        updateSingleWidget(context, appWidgetManager, appWidgetId)
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        when (intent.action) {
            ACTION_TOGGLE_THEME -> {
                val prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
                val next = (prefs.getInt(KEY_THEME, 0) + 1) % 4
                prefs.edit().putInt(KEY_THEME, next).apply()
                updateAllWidgets(context)
            }
            Intent.ACTION_DATE_CHANGED,
            Intent.ACTION_TIME_SET,
            Intent.ACTION_TIMEZONE_CHANGED,
            AppWidgetManager.ACTION_APPWIDGET_UPDATE -> {
                updateAllWidgets(context)
            }
        }
    }
}
