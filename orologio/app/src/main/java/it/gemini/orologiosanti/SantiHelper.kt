package it.gemini.orologiosanti

import java.util.Calendar

object SantiHelper {

    private val MESI_BREV = arrayOf("Gen", "Feb", "Mar", "Apr", "Mag", "Giu", "Lug", "Ago", "Set", "Ott", "Nov", "Dic")
    private val GIORNI_BREV = arrayOf("Dom", "Lun", "Mar", "Mer", "Gio", "Ven", "Sab")

    private val FESTIVITA_FISSE = mapOf(
        "1-1" to "Capodanno",
        "1-6" to "Epifania",
        "4-25" to "Festa della Liberazione",
        "5-1" to "Festa dei Lavoratori",
        "6-2" to "Festa della Repubblica",
        "8-15" to "Ferragosto",
        "11-1" to "Tutti i Santi",
        "12-8" to "Immacolata Concezione",
        "12-25" to "Natale",
        "12-26" to "Santo Stefano"
    )

    private val SANTI = arrayOf(
        // 1 - Gennaio
        arrayOf(
            "SS. Maria Madre di Dio", "San Basilio • San Defendente", "Santa Genoveffa", "SS. Nome del Signore",
            "Santa Amelia", "Epifania del Signore", "San Luciano • San Raimondo", "San Severino • San Massimo",
            "San Giuliano, martire", "Sant'Agatone • Sant'Aldo", "Battesimo del Signore", "San Modesto",
            "Sant'Ilario", "San Felice", "San Mauro", "San Marcello, papa", "Sant'Antonio, abate",
            "Santa Liberata • Santa Prisca", "San Mario • San Canuto", "San Sebastiano • San Fabiano",
            "Sant'Agnese", "San Vincenzo • Sant'Anastasio", "Santa Emerenziana", "San Francesco di Sales",
            "Conversione di San Paolo", "San Tito • San Timoteo", "Santa Angela Merici", "San Tommaso d'Aquino",
            "San Costanzo", "Santa Martina", "San Giovanni Bosco"
        ),
        // 2 - Febbraio
        arrayOf(
            "San Severo • Santa Verdiana", "Presentazione del Signore (Candelora)", "San Biagio", "San Gilberto",
            "Sant'Agata", "San Paolo Miki", "San Romualdo", "San Girolamo Emiliani", "Santa Apollonia",
            "Santa Scolastica", "Nostra Signora di Lourdes", "Santa Eulalia", "Santa Fosca • Santa Maura",
            "San Valentino, martire", "SS. Faustino e Giovita", "Santa Giuliana", "San Donato, martire",
            "San Simeone", "Sant'Alvaro • San Mansueto", "Sant'Eleuterio • San Zenobio",
            "Santa Eleonora • San Pier Damiani", "Cattedra di San Pietro", "San Policarpo",
            "Sant'Edilberto, re • San Sergio", "San Cesario", "San Nestore", "San Leandro",
            "San Romano, abate", "Sant'Ilario, papa"
        ),
        // 3 - Marzo
        arrayOf(
            "Sant'Albino", "Sant'Eraclio • San Basilio, martire", "Santa Cunegonda • San Tiziano",
            "San Casimiro • San Lucio", "Sant'Adriano", "San Marciano", "Sante Perpetua e Felicita",
            "San Giovanni di Dio", "Santa Francesca Romana", "San Simplicio, papa", "San Costantino",
            "San Massimiliano", "Sant'Arrigo • Santa Eufrasia", "Santa Matilde, regina",
            "Santa Luisa de Marillac", "Sant'Ariberto", "San Patrizio", "San Cirillo di Gerusalemme",
            "San Giuseppe", "Santa Claudia • Santa Alessandra, martire", "San Benedetto da Norcia",
            "Santa Lea", "San Turibio de Mogrovejo", "San Romolo • Santa Caterina di Svezia",
            "Annunciazione del Signore", "Sant'Emanuele • San Teodoro", "Sant'Augusto",
            "San Sisto III, papa", "San Secondo", "Sant'Amedeo", "San Beniamino, martire"
        ),
        // 4 - Aprile
        arrayOf(
            "Sant'Ugo di Grenoble, vescovo", "San Francesco di Paola", "San Riccardo, vescovo",
            "Sant'Isidoro", "San Vincenzo Ferreri", "San Pietro da Verona",
            "Sant'Ermanno • San Giovanni B. de La Salle", "Sant'Alberto Dionigi",
            "San Gualtiero • Santa Maria Cleofe", "San Terenzio, martire", "San Stanislao, vescovo",
            "San Zenone • San Giulio, papa", "Sant'Ermenegildo • San Martino, papa", "Sant'Abbondio",
            "Sant'Annibale, martire", "Santa Bernadetta • San Lamberto", "Sant'Aniceto, papa • San Roberto",
            "San Galdino, vescovo", "Santa Emma • San Timone", "Santa Adalgisa • San Teotimo",
            "Sant'Anselmo", "SS. Sotero e Caio", "Sant'Adalberto • San Giorgio, martire",
            "San Fedele da Sigmaringa", "San Marco, evangelista", "SS. Cleto e Marcellino",
            "SS. Ida e Zita", "Santa Valeria", "Santa Caterina da Siena", "San Pio V, papa"
        ),
        // 5 - Maggio
        arrayOf(
            "San Giuseppe artigiano", "Sant'Atanasio", "SS. Filippo e Giacomo, apostoli", "San Silvano",
            "San Gottardo • San Pio V", "Santa Giuditta", "Santa Flavia", "San Vittore", "San Gregorio",
            "Sant'Antonino", "San Fabio, martire", "SS. Nereo e Achilleo", "Beata Vergine Maria di Fatima",
            "San Mattia, apostolo", "San Torquato", "Sant'Ubaldo", "San Pasquale Baylon",
            "San Giovanni I, papa", "San Celestino V, papa", "San Bernardino da Siena",
            "San Vittorio, martire", "Santa Rita da Cascia", "San Desiderio • San Giorgio",
            "Beata Vergine Maria Ausiliatrice", "San Gregorio VII, papa", "San Filippo Neri",
            "Sant'Agostino di Canterbury", "Sant'Emilio, martire", "San Massimino, vescovo",
            "Santa Giovanna d'Arco", "Visitazione Beata Vergine Maria"
        ),
        // 6 - Giugno
        arrayOf(
            "San Giustino", "SS. Marcellino e Pietro", "San Carlo Lwanga e compagni",
            "San Quirino, vescovo", "San Bonifacio, vescovo", "San Norberto", "San Roberto",
            "San Medardo, vescovo", "Sant'Efrem Siro • SS. Primo e Feliciano",
            "Santa Diana • San Zaccaria", "San Barnaba, apostolo", "San Guido",
            "Sant'Antonio di Padova", "Sant'Eliseo", "Santa Germana", "Sant'Aureliano",
            "San Ranieri • San Gregorio Barbarigo", "Santa Marina",
            "SS. Gervasio e Protasio • San Romualdo", "San Silverio, papa", "San Luigi Gonzaga",
            "San Paolino da Nola", "San Lanfranco, vescovo", "Natività di San Giovanni Battista",
            "San Guglielmo", "SS. Giovanni e Paolo, martiri • San Rodolfo",
            "San Cirillo d'Alessandria, vescovo", "Sant'Attilio, confessore • Sant'Ireneo",
            "SS. Pietro e Paolo", "SS. Primi Martiri"
        ),
        // 7 - Luglio
        arrayOf(
            "San Teobaldo • Sant'Aronne", "Sant'Ottone • Sant'Urbano", "San Tommaso, apostolo",
            "Santa Elisabetta, regina", "Sant'Antonio Maria Zaccaria", "Santa Maria Goretti",
            "San Claudio • Santa Edda", "Sant'Adriano", "San Fabrizio • Santa Veronica Giuliani",
            "Santa Felicita • Santa Vittoria", "San Benedetto da Norcia", "SS. Felice e Nabore, martiri",
            "Sant'Enrico, imperatore", "San Camillo De Lellis", "San Bonaventura",
            "Beata Vergine del Carmine", "Sant'Alessio, confessore", "San Federico", "Santa Giusta",
            "Sant'Elia, profeta • Sant'Apollinare", "San Lorenzo da Brindisi", "Santa Maria Maddalena",
            "Santa Brigida", "Santa Cristina", "San Giacomo, apostolo", "SS. Anna e Gioacchino",
            "Santa Liliana", "SS. Nazario e Celso, martiri", "Santa Marta",
            "San Pietro Crisologo, vescovo", "Sant'Ignazio di Loyola"
        ),
        // 8 - Agosto
        arrayOf(
            "Sant'Alfonso M. de Liguori", "Sant'Eusebio", "Santa Lidia", "San Domenico di Guzman",
            "San Giovanni Maria Vianney", "Trasfigurazione del Signore", "San Gaetano da Thiene",
            "San Domenico", "San Romano", "San Lorenzo, martire", "Santa Chiara d'Assisi",
            "Sant'Ercolano", "SS. Ippolito e Cassiano, martiri",
            "Sant'Alfredo • San Massimiliano Kolbe", "Assunzione di Maria Vergine",
            "San Stefano d'Ungheria", "San Giacinto", "Santa Elena, imperatrice",
            "San Giovanni Eudes • San Ludovico", "San Bernardo", "San Pio X, papa",
            "Beata Vergine Maria, regina", "Santa Rosa da Lima", "San Bartolomeo, apostolo",
            "San Lodovico, re", "Sant'Alessandro, martire", "Santa Monica", "Sant'Agostino",
            "Martirio di San Giovanni Battista", "Santa Faustina", "Sant'Aristide"
        ),
        // 9 - Settembre
        arrayOf(
            "Sant'Egidio", "Sant'Elpidio, vescovo", "San Gregorio Magno, papa", "Santa Rosalia",
            "San Vittorino", "Sant'Umberto", "Santa Regina", "Natività della Beata V. Maria",
            "San Sergio, papa", "San Nicola da Tolentino", "SS. Proto e Giacinto, martiri",
            "SS.mo Nome di Maria", "San Giovanni Crisostomo", "Esaltazione della Santa Croce",
            "Beata V. Maria Addolorata", "SS. Cornelio e Cipriano", "San Roberto Bellarmino",
            "Santa Sofia, martire", "San Gennaro, vescovo", "Sant'Eustachio",
            "San Matteo, apostolo ed evangelista", "San Maurizio, martire", "San Pio da Pietrelcina",
            "San Pacifico, prete", "Santa Aurelia", "SS. Cosma e Damiano", "San Vincenzo de' Paoli",
            "San Venceslao, martire", "SS. Michele, Gabriele, Raffaele arcangeli", "San Girolamo"
        ),
        // 10 - Ottobre
        arrayOf(
            "Santa Teresa di Gesù Bambino", "SS. Angeli Custodi", "San Gerardo, abate • Sant'Alfonso",
            "San Francesco d'Assisi", "San Placido, martire", "San Bruno di Calabria, abate",
            "Beata Vergine Maria del Rosario", "Santa Pelagia • Santa Reparata", "San Dionigi",
            "San Daniele, vescovo", "Maternità di Maria • San Firmino", "San Serafino",
            "Sant'Edoardo, re", "San Callisto, papa", "Santa Teresa d'Avila", "Santa Edvige",
            "Sant'Ignazio d'Antiochia", "San Luca, evangelista", "Sant'Isacco, martire",
            "Santa Irene", "Sant'Orsola e compagne", "San Donato • Santa Maria Salomè",
            "San Giovanni da Capestrano", "Sant'Antonio Maria Claret, vescovo", "San Crispino",
            "Sant'Evaristo, papa", "San Fiorenzo", "SS. Simone e Giuda", "Sant'Ermelinda",
            "San Germano, vescovo", "Santa Lucilla"
        ),
        // 11 - Novembre
        arrayOf(
            "Tutti i Santi", "Commemorazione dei defunti", "Santa Silvia", "San Carlo Borromeo",
            "San Zaccaria, profeta", "San Leonardo", "Sant'Ernesto", "San Goffredo, vescovo",
            "Sant'Oreste", "San Leone Magno", "San Martino", "San Renato", "San Diego, confessore",
            "San Giocondo, vescovo", "Sant'Alberto Magno", "Santa Margherita di Scozia",
            "Santa Elisabetta di Ungheria", "Sant'Oddone", "San Fausto",
            "San Felice di Valois • Sant'Ottavio", "Presentazione di Maria Vergine", "Santa Cecilia",
            "San Clemente, papa", "San Giovanni della Croce", "Santa Caterina di Alessandria",
            "San Corrado, vescovo • Santa Delfina", "San Virgilio", "Santa Livia • San Demetrio",
            "San Saturnino", "Sant'Andrea, apostolo"
        ),
        // 12 - Dicembre
        arrayOf(
            "Sant'Eligio", "Santa Bibiana", "San Francesco Saverio", "Santa Barbara", "San Dalmazio",
            "San Nicola, vescovo", "Sant'Ambrogio, vescovo", "Immacolata Concezione", "San Siro",
            "Nostra Signora di Loreto", "San Damaso I, papa", "Santa Giovanna",
            "Santa Lucia, martire", "San Giovanni della Croce", "San Valeriano", "Santa Adelaide",
            "San Lazzaro", "San Graziano, vescovo", "San Dario • Santa Fausta", "San Liberato",
            "San Pietro Canisio", "San Flaviano", "San Giovanni di Kenty", "Santa Irma",
            "Natale del Signore", "Santo Stefano, protomartire", "San Giovanni, evangelista",
            "SS. Innocenti, martiri", "San Tommaso Becket", "Sant'Eugenio, vescovo",
            "San Silvestro, papa"
        )
    )

    private fun getEaster(year: Int): Calendar {
        val a = year % 19
        val b = year / 100
        val c = year % 100
        val d = b / 4
        val e = b % 4
        val f = (b + 8) / 25
        val g = (b - f + 1) / 3
        val h = (19 * a + b - d - g + 15) % 30
        val i = c / 4
        val k = c % 4
        val l = (32 + 2 * e + 2 * i - h - k) % 7
        val m = (a + 11 * h + 22 * l) / 451
        val month = (h + l - 7 * m + 114) / 31 - 1
        val day = ((h + l - 7 * m + 114) % 31) + 1
        return Calendar.getInstance().apply {
            set(year, month, day, 0, 0, 0)
            set(Calendar.MILLISECOND, 0)
        }
    }

    private fun addDays(base: Calendar, days: Int): Calendar {
        return (base.clone() as Calendar).apply { add(Calendar.DAY_OF_YEAR, days) }
    }

    private fun isSameDay(c1: Calendar, c2: Calendar): Boolean {
        return c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR) &&
               c1.get(Calendar.DAY_OF_YEAR) == c2.get(Calendar.DAY_OF_YEAR)
    }

    fun getFormattedDate(cal: Calendar): String {
        val dow = GIORNI_BREV[cal.get(Calendar.DAY_OF_WEEK) - 1]
        val day = cal.get(Calendar.DAY_OF_MONTH)
        val mon = MESI_BREV[cal.get(Calendar.MONTH)]
        return "$dow $day $mon"
    }

    fun getDayDescription(cal: Calendar): String {
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)

        val parts = mutableListOf<String>()

        val fixedKey = "${month + 1}-$day"
        FESTIVITA_FISSE[fixedKey]?.let { parts.add(it) }

        val easter = getEaster(year)
        val may1 = Calendar.getInstance().apply { set(year, Calendar.MAY, 1, 0, 0, 0) }
        val offset = (7 - (may1.get(Calendar.DAY_OF_WEEK) - 1)) % 7
        val mothersDay = addDays(may1, offset + 7)

        val movables = listOf(
            addDays(easter, -67) to "Giovedì Grasso",
            addDays(easter, -63) to "Domenica di Carnevale",
            addDays(easter, -47) to "Martedì Grasso",
            addDays(easter, -46) to "Mercoledì delle Ceneri",
            addDays(easter, -7) to "Domenica delle Palme",
            easter to "Pasqua",
            addDays(easter, 1) to "Lunedì dell'Angelo (Pasquetta)",
            addDays(easter, 39) to "Ascensione",
            addDays(easter, 49) to "Pentecoste",
            addDays(easter, 60) to "Corpus Domini",
            mothersDay to "Festa della Mamma"
        )

        for ((mDate, mName) in movables) {
            if (isSameDay(cal, mDate) && !parts.contains(mName)) {
                parts.add(mName)
            }
        }

        val saintText = SANTI.getOrNull(month)?.getOrNull(day - 1)
        if (!saintText.isNullOrBlank()) {
            parts.add(saintText)
        }

        return parts.joinToString(" • ")
    }
}
