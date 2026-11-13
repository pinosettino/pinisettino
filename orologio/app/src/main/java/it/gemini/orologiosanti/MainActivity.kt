package it.gemini.orologiosanti

import android.app.Activity
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(64, 64, 64, 64)
            setBackgroundColor(Color.parseColor("#121212"))
        }

        val title = TextView(this).apply {
            text = "Widget Orologio & Santi"
            textSize = 22f
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER
        }

        val info = TextView(this).apply {
            text = "\n1. Torna alla schermata Home del telefono.\n" +
                   "2. Tieni premuto su uno spazio vuoto.\n" +
                   "3. Tocca 'Widget' e trascina 'Orologio e Santi' sulla Home.\n\n" +
                   "Suggerimento: tocca il widget sulla Home per cambiare colore (Bianco / Nero / Sfondo scuro / Sfondo chiaro)."
            textSize = 15f
            setTextColor(Color.LTGRAY)
            gravity = Gravity.CENTER
        }

        val btn = Button(this).apply {
            text = "Cambia Tema Widget Ora"
            setOnClickListener {
                val intent = Intent(this@MainActivity, ClockWidgetProvider::class.java).apply {
                    action = ClockWidgetProvider.ACTION_TOGGLE_THEME
                }
                sendBroadcast(intent)
            }
        }

        layout.addView(title)
        layout.addView(info)
        layout.addView(btn)
        setContentView(layout)

        val updateIntent = Intent(this, ClockWidgetProvider::class.java).apply {
            action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
            val ids = AppWidgetManager.getInstance(application)
                .getAppWidgetIds(ComponentName(application, ClockWidgetProvider::class.java))
            putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids)
        }
        sendBroadcast(updateIntent)
    }
}
