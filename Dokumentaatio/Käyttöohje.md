# Käyttöohje

Ohjelma löytyy projektin juuresta. Se vaatii oheensa kansiot mazes ja graphics.

Ohjelma ajetaan komentoriviltä seuraavilla argumenteilla. Jos kaikkia argumentteja ei määritellä, ajetaan ohjelma oletusasetuksilla (määritetty tiedostossa main.java).

* maze
  * Käytettävä sokkelo. Mazes-kansiossa olevan kansion nimi. Kansion tulee sisältää kolme tiedostoa:
    * maze.txt - Sokkelo tekstitiedostona
	* startPosition.txt - Aloitussijainti koordinaatteina
	* targetMovement.txt - Maalisijainti koordinaatteina sekä tämän jälkeen maalipisteen mahdollinen liike.
* allowDiagonal
  * Onko vinottainen liike ruudukossa sallittu. True tai false.
* algo
  * Käytettävän algoritmin nimi. "A*", "Dijkstra", "Breadth-first" tai "Depth-first".
* moveDelay
  * Monenko siirron välein maalipiste tekee oman siirtonsa, kokonaisluku.
  * Oletusarvo 5.
* doorDelay
  * Monenko siirron välein ovet avautuvat tai sulkeutuvat, kokonaisluku.
  * Oletusarvo 20.
* waitBeforeFail
  * Montako epäonnistunutta reitin löytämistä sallitaan peräkkäin ennen kuin operaatio todetaan toivottomaksi ja luovutetaan.
  * Tälle on tarvetta, jos sulkeutuvat ovet voivat aiheuttaa tilanteen jossa reittiä ei löydy tai jos maalipiste menee liikkuessaan seinän sisään. Tällöin odotetaan yhden siirron verran ja yritetään sitten uudestaan.
  * Oletusarvo 10.
* logEnabled
  * Tulostetaanko jokaiseen polun uudelleenlaskentaan liittyvät tiedot komentoriville.
  * Etsimiseen käytetty aika ja askelten määrä.
  * True tai false, optimaalisten olosuhteiden tuottamiseksi false.
* windowEnabled
  * Käytetäänkö polunetsinnän visualisointia. True tai false.
  * Optimaalisten olosuhteiden tuottamiseksi false, sillä ikkuna hidastaa ohjelman toimintaa jonkin verran.
* smallTiles
  * Käytetäänkö visualisoinnissa 2x2 pikselin vai 10x10 pikselin ruutuja. True tai false.
  * Pienemmät ruudut ovat isompia sokkeloita varten.
* waitInMillis
  * Monenko millisekunnin välein uusi siirto tehdään, kokonaisluku.
  * Käytetään vain jos visualisointi on päällä. Ei vaikuta laskenta-ajan mittauksiin.
  * Oletusarvo 200.
  
Tämän jälkeen ohjelma käynnistyy ja aloittaa välittömästi valitun algoritmin suorittamisen valituilla parametreilla. Ohjelman suorituksen loputtua se kertoo tulokset:
* Löytyikö reitti
* Laskemiseen käytetty aika yhteensä
* Käytettyjen askelten määrä (mukaanlukien odottamiset jos reittiä ei löydy)
* Yhteen askeleeseen käytetty keskimääräinen aika (ei välttämättä kovin hyödyllinen)
* Solmujen siivoamiseen käytetty aika (solmut siivotaan aina uudelleenlaskennan yhteydessä)
* Solmujen siivoamiseen käytetty keskimääräinen aika
* Uudelleenlaskentakertojen määrä
* Keskimääräinen uudelleenlaskentaan käytetty aika
* Epäonnistuneet polunetsintäkerrat
* Pisin putki epäonnistuneita polunetsintöjä
* Kohdepisteen tekemät siirrot
* Ovien aukeamiset/sulkeutumiset
* Askeleet jääruuduissa
* Askeleet suoruuduissa

Kaikki ajat ovat millisekunneissa.

Jos komentoriviloki on päällä, tulostetaan joka uudelleenlaskentakerta:
* Laskentaan käytetty aika
* Tähänastinen laskentaan käytetty kokonaisaika
* Uuden polun pituus
* Laskemiseen käytetty aika per uuden polun askel (ei välttämättä hyödyllinen)

Optimaalisessa tilanteessa ohjelma ajetaan ilman ylimääräisiä komentorivitulostuksia (logEnabled false) ja ilman visualisointia (windowEnabled false).