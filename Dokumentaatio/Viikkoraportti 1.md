#Viikkoraportti 1

Viikolla aloitin A*-algoritmin toteutuksen ja rakensin ohjelman ydinalueen.

* Tiedostonlukuluokka **MazeReader** on aika peruskauraa: se lukee tekstitiedostosta labyrintin. Labyrinttien generointiin olen tähän asti käyttänyt tätä avuliasta nettisivua: http://www.delorie.com/game-room/mazes/genmaze.cgi Labyrinttitiedosto koostuu siis seinistä ja lattioista. Algoritmi aloittaa ruudusta (0,1) vasemmasta ylänurkasta ja etsii tien ruutuun (x, y-1) jossa x on sokkelon leveys ja y korkeus. Luokka hakee myös ovien sijainnit itsestään muutaman siirron välein aukeavia ja sulkeutuvia ovia varten, mutta sitä toiminnallisuutta en ole vielä toteuttanut. Hoidan oleellisemmat pois alta ensin.
* **AlgorithmRunner**-luokka muuttaa MazeReaderista tulevan data **Node**-olioista koostuvaksi verkoksi ja ajaa halutun algoritmin verkossa. Node sisältää tiedot naapuri-Nodeista, tiedon onko kyseinen node osa seinää sekä algoritmien omia muuttujia. Verkon luova metodi ottaa lopulliseen verkkoon mukaan vain ne Nodet jotka eivät ole seinää; näin varsinaiset algoritmit eivät kuluta turhaa aikaa seinyyden tarkistamiseen. Luokalle voidaan myös kertoa parametrina, sallitaanko ruudukossa liikkuminen vinottain. Vinottain liikkuminen lisää solmujen naapurien määrää mutta tuottaa huomattavasti lyhyemmän reitin.
* **Algorithm-interface** tarjoaa rajapinnan varsinaisten algoritmien käyttöön. Se sisältää metodin FindPath, jolle annetaan alku- ja loppusolmu ja joka palauttaa kuljetun reitin.
* **AStar**-luokka on A*-polunetsintäalgoritmin toteutus. Se käyttää HashSetiä, PriorityQueuea ja ArrayListia joista seuraavalla viikolla aion tehdä omat toteutukset. Algoritmi itsessään oli yllättävän helppo toteuttaa; aiemmin en ollut moista tehnyt, lukenut vain hieman sen teoriaa, mutta sain toimivan (joskin luultavasti vielä epäoptimaalisen) ratkaisun toimimaan melko nopeasti. Algoritmi tulkitsee vierekkäin olevien solmujen välisen kaaren painoksi 1 ja vinottain vierekkäin olevien välisen kaaren painoksi 1,4. Unohdin ensimmäisessä versiossa tehdä tämän, ja kun sallin vinottain liikkumisen kummastelin hetken aikaa hassua siksakkia jota polku kulki suoralla reitillä.

Seuraavana luvassa: Dijkstran algoritmi, leveyshaku ja omat tietorakenteet.