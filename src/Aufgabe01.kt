// Uhrzeit Klasse
class Uhrzeit ( val std : Int, val min : Int)

// Parkschein Klasse
class Parkschein (val einfahrtZeit : Uhrzeit, var ausfahrtZeit : Uhrzeit) {

    // Parkzeit in Minuten
    fun parkzeit () : Int {
        var stunden = ausfahrtZeit.std - einfahrtZeit.std
        var minuten = ausfahrtZeit.min - einfahrtZeit.min
        return minuten + stunden * 60
    }

    fun angefangeneStunden () : Int {
        var stunden = ausfahrtZeit.std - einfahrtZeit.std
        return stunden + 1
    }
}

// Parkhausklasse
class Parkhaus {
    val parkscheine = arrayListOf<Parkschein>( )
    val parktarif = 3.5

    fun kuerzesteParkzeit () : Int {
        var kuerzeste = parkscheine.first().parkzeit()
        for ( p in parkscheine) {
            if (p.parkzeit() < kuerzeste) {
                kuerzeste = p.parkzeit()
            }
        }
        return kuerzeste
    }

    fun durchschnittlicheParkzeit () : Double  {
        var summeMins = 0.0
        for ( p in parkscheine) {
            summeMins += p.parkzeit()
        }
        return summeMins / parkscheine.size
    }

    fun einnahme () : Double {
        var einnahmen = 0.0
        for ( p in parkscheine) {
            einnahmen += p.angefangeneStunden() * parktarif
        }
        return einnahmen
    }

    // Überprüft, ob die Zeiten des Parkscheines realistisch sind
    fun ueberpruefen () : Boolean {
        for ( p in parkscheine) {
            if(p.parkzeit() < 0) {
                return false
            }
        }
        return true
    }

    fun getParkscheinAnzahlUm(uhrzeit: Uhrzeit) : Int{
        var anzahl = 0
        for (p in parkscheine) {
            if (uhrzeit.std*60+uhrzeit.min >= p.einfahrtZeit.std*60+p.einfahrtZeit.min &&
                uhrzeit.std*60+uhrzeit.min <= p.ausfahrtZeit.std*60+p.ausfahrtZeit.min) {
                anzahl++
            }
        }
        return anzahl
    }
}

fun main() {

    // Uhrzeiten erstellen
    val u1515 = Uhrzeit(15,15)
    val u1600 = Uhrzeit(16,0)
    val u1700 = Uhrzeit(17,0)
    val u1830 = Uhrzeit(18,30)
    val u1910 = Uhrzeit(19,10)

    val u1800 = Uhrzeit(18,0)

    // Parkscheine erstellen
    val p1 = Parkschein(u1515, u1600)
    val p2 = Parkschein(u1600, u1700)
    val p3 = Parkschein(u1700, u1830)
    val p4 = Parkschein(u1515, u1830)
    val p5 = Parkschein(u1515, u1910)
    val p6 = Parkschein(u1600, u1515)

    // Parkhaus erstellen
    val gummersbach = Parkhaus()
    // Parkscheine dem Array hinzufügen
    gummersbach.parkscheine.add(p1)
    gummersbach.parkscheine.add(p2)
    gummersbach.parkscheine.add(p3)
    gummersbach.parkscheine.add(p4)
    gummersbach.parkscheine.add(p5)

    var summe = gummersbach.getParkscheinAnzahlUm(u1800)
    println("Die Anzahl der Parkscheine für die Uhrzeit ist : $summe")

    println("Kürzeste Parkzeit: ${gummersbach.kuerzesteParkzeit()}")
    println("Durchschnittliche Parkzeit: ${gummersbach.durchschnittlicheParkzeit()}")
    println("Einnahmen: ${gummersbach.einnahme()}")

    // Überprüfung
    if (gummersbach.ueberpruefen() == true) {
        println("Alles in Ordnung!")
    } else {
        println("Mind. ein Parkschein ist nicht in Ordnung")
    }

    // Der falschen Parkschein wird zum Array hinzugefügt
    gummersbach.parkscheine.add(p6)

    // Erneuert Überprüfung
    if (gummersbach.ueberpruefen() == true) {
        println("Alles in Ordnung!")
    } else {
        println("Mind. ein Parkschein ist nicht in Ordnung")
    }
}
