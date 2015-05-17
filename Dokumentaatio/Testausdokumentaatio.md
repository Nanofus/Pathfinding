# Testausdokumentaatio

## JUnit-testaus

Ohjelman JUnit-testaus sisältää ohjelman perustoimintojen oikean toiminnan tarkistamisen (ovet toimivat kuten pitääkin, tietorakenteet toimivat odotetusti, ne algoritmit joiden kuuluu löytää lyhin reitti todellakin löytävät lyhyimmän reitin jne.)

### Algoritmien testaus

### Tietorakenteiden testaus

## Algoritmien manuaalinen testaus

Testauksessa ajetaan kaikki hakualgoritmit samanlaisessa verkossa. Nämä testit on ajettu käsin tulosten ohessa mainittujen parametrien avulla.

Testaus keskittyy algoritmien nopeuden ja käytetyn askelmäärän mittaamiseen. Yleiseen käyttöön optimaalisin algoritmi on nopea ja löytää parhaan reitin. Testeissä käytetään 105x105 ruudun sokkeloa (eli sen kokoista, että siihen kykeni vielä järkevästi manuaalisesti sijoittamaan erikoisruudut).

Tulos on keskiarvo kolmen ajamiskerran tuloksista, pyöristettynä millisekunteihin.

### Suoritettavat testit

* Algoritmien toiminta yksinkertaisessa sokkelossa (tässä käytetään 401x401-sokkeloa)
  * Sokkelotyypit: labyrintti ja avoin alue, jossa esteitä
  * Labyrinttityyppisen sokkelon läpi kulkee vain yksi oikea reitti
* Algoritmien toiminta sokkelossa, joka muuttuu
* Algoritmien toiminta kohderuudun liikkuessa
* Suurikokoinen yhdistelmäsokkelo, jossa esiintyy kaikkia edellämainittuja sekä lisäksi solmuja, joiden kaarilla on eri painot ("suo" ja "jää")
  * Eripainoisten kaarien sokkeloita ei näissä testeissä ole testattu erikseen, sillä ne eivät vaikuta merkittävästi ohjelman suoritusnopeuteen, ja muissa tilanteissa hyvä leveyshaku ei osaa ottaa painoja huomioon. Niiden toiminta on kuitenkin testattu JUnit-testeissä.

Kaikista testeistä ajetaan versiot, joissa vinottainen liike on sallittu ja kielletty. Vinottaisten liikkeiden tapauksessa käytetään vain A*:ä ja Dijkstran algoritmia, sillä vain ne osaavat ottaa huomioon vinottaisten liikkeiden hinnan.

### Tulokset

#### Yksinkertainen sokkelo
* Labyrintti 401x401
  * Ei vinottaisia liikkeitä
     * A*
       * 65ms
       * 2877 askelta
       * `java -jar pathfinding.jar 401x401 false A* 5 20 10 false false false 0`
     * Dijkstra
       * 59ms
       * 2877 askelta
       * `java -jar pathfinding.jar 401x401 false Dijkstra 5 20 10 false false false 0`
     * Leveyshaku
       * 30ms
       * 2877 askelta
       * `java -jar pathfinding.jar 401x401 false Breadth-first 5 20 10 false false false 0`
     * Syvyyshaku
       * 25ms (noin joka toinen kerta stack overflow)
       * 7753 askelta
       * `java -jar pathfinding.jar 401x401 false Depth-first 5 20 10 false false false 0`
  * Vinottaiset liikkeet
     * A*
       * 84ms
       * 2029 askelta
       * `java -jar pathfinding.jar 401x401 true A* 5 20 10 false false false 0`
     * Dijkstra
       * 67ms
       * 2029 askelta
       * `java -jar pathfinding.jar 401x401 true Dijkstra 5 20 10 false false false 0`
* Avoin tila 401x401
  * Ei vinottaisia liikkeitä
     * A*
       * 85ms
       * 797 askelta
       * `java -jar pathfinding.jar 401x401_open false A* 5 20 10 false false false 0`
     * Dijkstra
       * 72ms
       * 797 askelta
       * `java -jar pathfinding.jar 401x401_open false Dijkstra 5 20 10 false false false 0`
     * Leveyshaku
       * 31ms 
       * 797 askelta
       * `java -jar pathfinding.jar 401x401_open false Breadth-first 5 20 10 false false false 0`
     * Syvyyshaku
       * stack overflow
       * `java -jar pathfinding.jar 401x401_open false Depth-first 5 20 10 false false false 0`
  * Vinottaiset liikkeet
     * A*
       * 98ms
       * 468 askelta
       * `java -jar pathfinding.jar 401x401_open true A* 5 20 10 false false false 0`
     * Dijkstra
       * 87ms
       * 468 askelta
       * `java -jar pathfinding.jar 401x401_open true Dijkstra 5 20 10 false false false 0`

* Labyrintti 105x105
  * Ei vinottaisia liikkeitä
     * A*
       * 15ms
       * 265 askelta
       * `java -jar pathfinding.jar t105x105 false A* 5 20 10 false false false 0`
     * Dijkstra
       * 13ms
       * 265 askelta
       * `java -jar pathfinding.jar t105x105 false Dijkstra 5 20 10 false false false 0`
     * Leveyshaku
       * 7ms
       * 265 askelta
       * `java -jar pathfinding.jar t105x105 false Breadth-first 5 20 10 false false false 0`
     * Syvyyshaku
       * 2ms
       * 2805 askelta
       * `java -jar pathfinding.jar t105x105 false Depth-first 5 20 10 false false false 0`
  * Vinottaiset liikkeet
     * A*
       * 20ms
       * 189 askelta
       * `java -jar pathfinding.jar t105x105 true A* 5 20 10 false false false 0`
     * Dijkstra
       * 15ms
       * 189 askelta
       * `java -jar pathfinding.jar t105x105 true Dijkstra 5 20 10 false false false 0`

Tuloksista huomataan, että Dijkstran algoritmi toimii testatuissa sokkeloissa hieman A*:eä nopeammin. A*, Dijkstran algoritmi ja leveyshaku tuottavat kaikki lyhimmän mahdollisen reitin. Kun kaarten painoja ei tarvitse ottaa huomioon, on leveyshaku algoritmeista paras. Syvyyshakua puolestaan ei voi käyttää varsinaisesti polunetsintään, sillä sen tuottama reitti on käyttökelvottoman pitkä. Syvyyshaku on kuitenkin merkittävästi nopeampi kuin muut, jos halutaan vain tarkistaa reitin olemassaolo.

#### Muuttuva sokkelo (ovet)

  * A*
   * 46ms
   * 302 askelta
   * 16 laskentakertaa
   * `java -jar pathfinding.jar t105x105_doors false A* 5 20 10 false false false 0`
 * Dijkstra
   * 36ms
   * 302 askelta
   * 16 laskentakertaa
   * `java -jar pathfinding.jar t105x105_doors false Dijkstra 5 20 10 false false false 0`
 * Leveyshaku
   * 20ms
   * 302 askelta
   * 16 laskentakertaa
   * `java -jar pathfinding.jar t105x105_doors false Breadth-first 5 20 10 false false false 0`
 * Syvyyshaku
   * Jäi jumiin loputtomaan looppiin
     * Algoritmi ei ehtinyt ovelle ennen sen sulkeutumista, lähti etsimään toista reittiä jota se ei myöskään ehtinyt käydä ja sulkeutuessaan toinen reitti vapautti ensimmäisen reitin uudestaan.
   * `java -jar pathfinding.jar t105x105_doors false Depth-first 5 20 10 false false false 0`

Vinottaiset liikkeet saattoivat ohjelman samanlaiseen looppiin kuin syvyyshaku ei-vinottaisilla testisokkelossa.

Muuttuvan sokkelon tilanteessa ei tapahtunut mitään erityisen yllättävää. Suoritusajoista kuitenkin huomaa, ettei aikaa kulunut kovin paljoa enempää kuin yksinkertaisessa polunetsinnässä, vaikka laskentakertoja oli 16-kertainen määrä. Syy tähän on siinä, että etsijän lähestyessä kohdettaan tarvittavan uuden polun pituus lyhenee. Pienemmässä sokkelossa reitti on eksponentiaalisesti nopeampi laskea.

#### Liikkuva kohde

* Ei vinottaisia liikkeitä
   * A*
     * 90ms
     * 299 askelta
     * 60 laskentakertaa
     * `java -jar pathfinding.jar t105x105_moving false A* 5 20 10 false false false 0`
   * Dijkstra
     * 70ms
     * 299 askelta
     * 60 laskentakertaa
     * `java -jar pathfinding.jar t105x105_moving false Dijkstra 5 20 10 false false false 0`
   * Leveyshaku
     * 30ms 
     * 299 askelta 
     * 60 laskentakertaa
     * `java -jar pathfinding.jar t105x105_moving false Breadth-first 5 20 10 false false false 0`
   * Syvyyshaku
     * 27ms
     * 3047 askelta
     * 67 laskentakertaa
     * `java -jar pathfinding.jar t105x105_moving false Depth-first 5 20 10 false false false 0`
* Vinottaiset liikkeet
   * A*
     * 76ms
     * 194 askelta
     * 39 laskentakertaa
     * `java -jar pathfinding.jar t105x105_moving true A* 5 20 10 false false false 0`
   * Dijkstra
     * 65ms
     * 194 askelta
     * 39 laskentakertaa
     * `java -jar pathfinding.jar t105x105_moving true Dijkstra 5 20 10 false false false 0`

Tulokset ei-vinottaisilla liikkeillä vastaavat suurin piirtein edellisten testien perusteella odotettuja.  Syvyyshaku teki kuitenkin hassun tempun liikkuvan kohteen kanssa; se käytti lähes saman ajan kuin leveyshaku, vaikka aiemmin se on ollut merkittävästi nopeampi. Huomattavasti huonomman reitin löytävä syvyyshaku joutui tekemään enemmän laskentakertoja, sillä etsijä ei pysynyt läheskään yhtä hyvin maalin perässä. Todennäköisesti syvyyshaulla olisi kuulunut kestää vielä paljon pidempään, mutta maaliruudulta loppuivat siirrot kesken 67 siirron jälkeen.

Vinottaisten liikkeiden tapauksessa huomataan päinvastainen ilmiö; vinottaisten liikkeiden avulla reitistä saadaan paljon lyhyempi, jolloin maaliruutu ei ehdi liikkua yhtä paljon ennen kuin se saadaan kiinni ja laskentakertoja tarvitaan paljon vähemmän. Tällöin ohjelman suoritus on huomattavasti nopeampi.

#### Käytännön peliympäristön simulaatio

Testataan siis sokkeloa, jossa esiintyvät kaikkien aiempien testien erityispiirteet yhtä aikaa, sekä ruutuja, joilla on vaihtelevat painot. Tämä tilanne muistuttaa eniten polunetsintäalgoritmin käytännön sovellutusta videopelissä.

* Ei vinottaisia liikkeitä
   * A*
     * 115ms
     * 333 askelta
     * 71 laskentakertaa
     * 5 epäonnistunutta laskentakertaa
     * `java -jar pathfinding.jar t105x105_all false A* 5 20 10 false false false 0`
   * Dijkstra
     * 82ms
     * 333 askelta
     * 71 laskentakertaa
     * 5 epäonnistunutta laskentakertaa
     * `java -jar pathfinding.jar t105x105_all false Dijkstra 5 20 10 false false false 0`
   * Leveyshaku
     * 36ms 
     * 352 askelta 
     * 72 laskentakertaa
     * 5 epäonnistunutta laskentakertaa
     * `java -jar pathfinding.jar t105x105_all false Breadth-first 5 20 10 false false false 0`
   * Syvyyshaku
     * Jumittui loputtomaan looppiin samasta syystä kuin aiemmin
     * `java -jar pathfinding.jar t105x105_all false Depth-first 5 20 10 false false false 0`
* Vinottaiset liikkeet
   * A*
     * 105ms
     * 267 askelta
     * 58 laskentakertaa
     * 5 epäonnistunutta laskentakertaa
     * `java -jar pathfinding.jar t105x105_all true A* 5 20 10 false false false 0`
   * Dijkstra
     * 86ms
     * 267 askelta
     * 58 laskentakertaa
     * 5 epäonnistunutta laskentakertaa
     * `java -jar pathfinding.jar t105x105_all true Dijkstra 5 20 10 false false false 0`

Näissäkin testeissä leveyshaku osoittautui nopeimmaksi, Dijkstra toiseksi nopeimmaksi ja A* hitaimmaksi. Leveyshaku kuitenkin jostain syystä päätyi kulkemaan pidempää reittiä kuin kaksi muuta. Tämä johtuu siitä, että se ei huomioi solmujen painoja, kun taas A* ja Dijkstra pyrkivät kulkemaan nopeampia jääruutuja pitkin - ja sinänsä siis kulkivat siirtomäärällisesti pidempää reittiä, vaikka reitin paino oli matalampi. Leveyshaku posotti suoraan suoruuduista läpi.

Epäonnistuneet laskentakerrat seurasivat viiden siirron tauosta, jonka aikana yksikään reitti ei ollut etsijälle auki.

Vinottaisilla liikkeillä kului suurinpiirtein sama aika kuin ei-vinottaisilla; lyhyemmästä reitistä seuraavat vähemmät laskentakerrat kompensoivat solmujen naapurien suurempaa määrää.
