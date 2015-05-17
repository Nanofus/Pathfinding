# Testausdokumentaatio

Testauksessa ajetaan kaikki hakualgoritmit samanlaisessa verkossa.

Testaus keskittyy algoritmien nopeuden ja käytetyn askelmäärän mittaamiseen. Yleiseen käyttöön optimaalisin algoritmi on nopea ja löytää parhaan reitin. Testeissä käytetään suurta 401x401 solmun sokkeloa.

Tulos on keskiarvo kolmen ajamiskerran tuloksista, pyöristettynä millisekunteihin.

## Suoritettavat testit

* Algoritmien toiminta yksinkertaisessa sokkelossa
  * Sokkelotyypit: labyrintti ja avoin alue, jossa esteitä
* Algoritmien toiminta sokkelossa, joka muuttuu
* Algoritmien toiminta kohderuudun liikkuessa
* Algoritmien toiminta verkossa, jonka kaarilla on eri painot ("jää" ja "suo")
  * Vain A* ja Dijkstra
* Suurikokoinen yhdistelmäsokkelo, jossa esiintyy kaikkia edellämainittuja

Kaikista testeistä ajetaan versiot, joissa vinottainen liike on sallittu ja kielletty. Vinottaisten liikkeiden tapauksessa käytetään vain A*:ä ja Dijkstran algoritmia, sillä vain ne osaavat ottaa huomioon vinottaisten liikkeiden hinnan.

## Tulokset

### Yksinkertainen sokkelo
* Labyrintti
  * Ei vinottaisia liikkeitä
     * A*: 65ms
       * `java -jar pathfinding.jar 401x401 false A* 5 20 10 false false false 0`
     * Dijkstra: 59ms
       * `java -jar pathfinding.jar 401x401 false Dijkstra 5 20 10 false false false 0`
     * Leveyshaku: 30ms
       * `java -jar pathfinding.jar 401x401 false Breadth-first 5 20 10 false false false 0`
     * Syvyyshaku: 25ms (joka toinen kerta stack overflow)
       * `java -jar pathfinding.jar 401x401 false Depth-first 5 20 10 false false false 0`
  * Vinottaiset liikkeet
     * A*: 84ms
       * `java -jar pathfinding.jar 401x401 true A* 5 20 10 false false false 0`
     * Dijkstra: 67ms
       * `java -jar pathfinding.jar 401x401 true Dijkstra 5 20 10 false false false 0`
* Avoin tila
  * Ei vinottaisia liikkeitä
     * A*: 85ms
       * `java -jar pathfinding.jar 401x401_open false A* 5 20 10 false false false 0`
     * Dijkstra: 72ms
       * `java -jar pathfinding.jar 401x401_open false Dijkstra 5 20 10 false false false 0`
     * Leveyshaku: 31ms 
       * `java -jar pathfinding.jar 401x401_open false Breadth-first 5 20 10 false false false 0`
     * Syvyyshaku: stack overflow
       * `java -jar pathfinding.jar 401x401_open false Depth-first 5 20 10 false false false 0`
  * Vinottaiset liikkeet
     * A*: 98ms
       * `java -jar pathfinding.jar 401x401_open true A* 5 20 10 false false false 0`
     * Dijkstra: 87ms
       * `java -jar pathfinding.jar 401x401_open true Dijkstra 5 20 10 false false false 0`
