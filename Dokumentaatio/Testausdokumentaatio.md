# Testausdokumentaatio

## JUnit-testaus

Ohjelman JUnit-testaus sisältää ohjelman perustoimintojen oikean toiminnan tarkistamisen (ovet toimivat kuten pitääkin, tietorakenteet toimivat odotetusti, ne algoritmit joiden kuuluu löytää lyhin reitti todellakin löytävät lyhyimmän reitin jne.)

### Algoritmien testaus

JUnit-testeillä testasin, että A*, Dijkstran algoritmi ja leveyshaku löytävät aina lyhyimmän reitin tilanteesta riippumatta, että ne reagoivat oikein tilanteessa, jossa reittiä ei löydy sekä sen, että kaikki käyttäytyvät järkevästi sokkelossa jossa on ovia. Testien pohjana käytin visualisointi päällä ajettuja yksinkertaisia sokkeloita, joista laskin manuaalisesti todellisen lyhimmän reitin ja optimaalisen käytöksen ovien tullessa vastaan. Testeistä on sekä versiot jotka sallivat vinottaiset liikkeet että versiot jotka eivät salli. Syvyyshaulle on koodissa läpimenevät testit, mutta niiden odottamat tulokset eivät perustu juuri mihinkään, sillä en pitänyt syvyyshakua erityisen tärkeänä.

A*:lle ja Dijkstralle on lisäksi erilliset testit, joissa testataan, ymmärtävätkö ne kaarten painot oikein (suo- ja jääruudut).

### Tietorakenteiden testaus

Jokaiselta tietorakenteelta testasin JUnit-testeillä julkisten metodien oikean toiminnan, sekä vertasin niiden nopeutta vastaaviin Javan omiin tietorakenteisiin.

#### Tietorakenteiden nopeus verrattuna Javan vastaaviin

##### OwnBinaryHeap

Testissä täytin OwnBinaryHeapin ja PriorityQueuen ensin yksittäisellä ja sitten 100 solmulla, joilla on arvot välillä 0-299. Kumpikin tietorakenne käytti samaa komparaattoria solmujen vertailuun (NodeComparator). Valitsin solmumäärän 100 nimenomaan siksi, että tuolla arvolla molemmat tietorakenteet ylittävät oletuskapasiteettinsa (11) ja joutuvat kasvattamaan kokoaan. 11 on kuulemma jostain syystä optimaalinen oletuskapasiteetti jos etukäteen ei ole tietoa lisättävien alkioiden määrästä, en vain saanut selville, miksi.

```
OwnBinaryHeap:
Add: 2994ns
Add x 100: 49608ns
Poll: 1711ns
IsEmpty: 7271ns
Size: 855ns

PriorityQueue:
Add: 27797ns
Add x 100: 526015ns
Poll: 21810ns
IsEmpty: 8125ns
Size: 4705ns
```

OwnBinaryHeap osoittautui PriorityQueuea huomattavasti nopeammaksi. Sen toimintaa kuitenkin rajoittaa pelkkien Node-tyyppisten olioiden salliminen, eikä se myöskään sisällä kaikkia PriorityQueuen ominaisuuksia, kuten `contains()` ja `remove()`.

##### OwnQueue

Testissä täytin OwnQueuen ja ArrayDequen ensin yhdellä ja sitten 200 solmulla ja otin eri operaatioihin käytetyt ajat ylös. Kummankin tietorakenteen oletuskapasiteetti on 16, joten kumpikin joutui laajentamaan taulukkoaan.

```
OwnQueue:
Push: 855ns
Push x 200: 38916ns
Peek: 1283ns
Pop: 855ns
IsEmpty: 428ns

ArrayDeque:
Push: 13685ns
Push x 200: 61582ns
Peek: 14540ns
Pop: 13258ns
IsEmpty: 5560ns
```

Jononi on huomattavasti nopeampi kuin Javan ArrayDeque. Toiminnallisuudeltaan se on kuitenkin sekä yksinkertaisempi että rajoittuneempi, kuten OwnBinaryHeapkin; se hyväksyy vain Node-tyyppisiä olioita.

##### OwnArrayList

OwnArrayList on, toisin kuin muut tietorakenteeni, toiminnallisuudeltaan lähes samaa tasoa Javan oman ArrayListin kanssa, ja hyväksyy kaikentyyppisiä olioita. Vertasin sitä ArrayListiin sekä solmuilla, liukuluvuilla että kokonaisluvuilla. ArrayListin aloituskapasiteetti on 10, joten annoin OwnArrayListille saman. Kaikissa tapauksessa käytin ensin yhtä ja sitten 200 lisättyä alkiota (välissä tyhjennys), jolloin lista joutui kasvattamaan itseään useaan kertaan. Kaikissa contains-testeissä etsitty alkio on taulukossa, joko ensimmäisenä tai viimeisenä. IndexOf-testeissä testataan ensimmäisen ja viimeisen alkion indeksin löytämistä.

###### Liukuluvuilla

Täytin kummankin tietorakenteen 200 satunnaisella liukuluvulla väliltä 0-1.

```
OwnArrayList:
Add: 64148ns
Add x 200: 108197ns
Contains (chosen one last): 11119ns
Contains (chosen one first): 10264ns
IndexOf (chosen one last): 11119ns
IndexOf (chosen one first): 13258ns
Get (101st): 428ns
Remove (101st): 16678ns
IsEmpty: 427ns
Size: 428ns

ArrayList:
Add: 2138ns
Add x 200: 126158ns
Contains (chosen one last): 75695ns
Contains (chosen one first): 2566ns
IndexOf (chosen one last): 1910334ns
IndexOf (chosen one first): 2566ns
Get (101st): 1283ns
Remove (101st): 5131ns
IsEmpty: 855ns
Size: 855ns
```

Liukuluvuilla Javan oma ArrayList toimi paljon nopeammin kuin OwnArrayList kaikissa tilanteissa, paitsi `isEmpty()`- ja `size()`-kyselyissä. ArrayListin `size()` on monimutkaisempi, kun taas kummassakin `isEmpty()` perustuu suoraan siihen, onko `size()`:n arvo `0`.

###### Kokonaisluvuilla

Täytin kummankin tietorakenteen 200 järjestyksessä olevalla kokonaisluvulla. 

```
OwnArrayList:
Add: 2138ns
Add x 200: 44049ns
Contains (chosen one last): 12829ns
Contains (chosen one first): 2138ns
IndexOf (chosen one last): 11546ns
IndexOf (chosen one first): 1283ns
Get (101st): 428ns
Remove (101st): 18389ns
IsEmpty: 855ns
Size: 427ns

ArrayList:
Add: 2566ns
Add x 200: 649180ns
Contains (chosen one last): 89380ns
Contains (chosen one first): 4704ns
IndexOf (chosen one last): 36350ns
IndexOf (chosen one first): 2138ns
Get (101st): 855ns
Remove (101st): 4705ns
IsEmpty: 855ns
Size: 428ns
```

Kokonaisluvuilla OwnArrayList oli jälleen hitaampi kuin ArrayList, mutta ei läheskään yhtä pahasti kuin liukuluvuilla. Yksittäisessä lisäyksessä päästiin lähes samaan aikaan, ja `contains()` ja `indexOf()` jäivät vain puolta hitaammiksi.

###### Solmuilla

Täytin kummankin listan 200 solmulla.

```
OwnArrayList:
Add: 4705ns
Add x 200: 32074ns
Contains (chosen one last): 11975ns
Contains (chosen one first): 428ns
IndexOf (chosen one last): 11119ns
IndexOf (chosen one first): 428ns
Get (101st): 3422ns
Remove (101st): 17106ns
IsEmpty: 427ns
Size: 428ns

ArrayList:
Add: 6843ns
Add x 200: 39772ns
Contains (chosen one last): 17106ns
Contains (chosen one first): 427ns
IndexOf (chosen one last): 14968ns
IndexOf (chosen one first): 428ns
Get (101st): 3849ns
Remove (101st): 6842ns
IsEmpty: 3421ns
Size: 2566ns
```

Monimutkaisemmalla oliolla, tässä tapauksessa Nodella, OwnArrayList pääsi nopeudeltaan vieläkin lähemmäksi ArrayListia, ja aikaerot jäivät kaikissa operaatioissa `remove()`:a lukuunottamatta suhteessa paljon pienemmiksi kuin millään muilla alkiotyypeillä. `remove()` on siis ArrayListissa selvästi tehokkaampi kuin OwnArrayListissa. Kummassakin tietorakenteessa metodi kopioi vanhan taulukon uuteen taulukkoon lukuunottamatta pois pudotettavaa alkiota, mutta ArrayList käyttää `System.arraycopy`-metodia, joka on huomattavasti nopeampi kuin manuaalinen taulukon kopiointi.

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

##### Labyrintti 401x401

| Algoritmi | A* | Dijkstra | Leveyshaku | Syvyyshaku | A* (vinottaiset) | Dijkstra (vinottaiset |
|-----------|------|----------|------------|----------------------------------------------|------------------|-----------------------|
| **Aika** | 65ms | 59ms | 30ms | 25ms (noin joka toinen kerta stack overflow) | 84ms | 67ms |
| **Askeleet** | 2877 | 2877 | 2877 | 7753 | 2029 | 2029 |
| **Ajoparametrit** | `java -jar pathfinding.jar 401x401 false A* 5 20 10 false false false 0` | `java -jar pathfinding.jar 401x401 false Dijkstra 5 20 10 false false false 0` | `java -jar pathfinding.jar 401x401 false Breadth-first 5 20 10 false false false 0` | `java -jar pathfinding.jar 401x401 false Depth-first 5 20 10 false false false 0` | `java -jar pathfinding.jar 401x401 true A* 5 20 10 false false false 0` | `java -jar pathfinding.jar 401x401 true Dijkstra 5 20 10 false false false 0` |

##### Avoin tila 401x401

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

| Algoritmi | A* | Dijkstra | Leveyshaku |
|----------------|---------------------------------------------------------------------------------|---------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------|
| **Aika** | 46ms | 36ms | 20ms |
| **Askeleet** | 302 | 302 | 302 |
| **Laskentakerrat** | 16 | 16 | 16 |
| **Ajoparametrit** | `java -jar pathfinding.jar t105x105_doors false A* 5 20 10 false false false 0` | `java -jar pathfinding.jar t105x105_doors false Dijkstra 5 20 10 false false false 0` | `java -jar pathfinding.jar t105x105_doors false Breadth-first 5 20 10 false false false 0` |

Syvyyshaku jäi loputtomaan looppiin. Etsijä ei ehtinyt ovelle ennen sen sulkeutumista ja se lähti etsimään uutta reittiä. Myös uusi reitti sulkeutui ennen kuin etsijä ehti ovelle, ja vanha reitti aukesi uudestaan. Tämän jälkeen etsijä yritti uudestaan vanhaa reittiä. Ajoparametrit: `java -jar pathfinding.jar t105x105_doors false Depth-first 5 20 10 false false false 0`.

Vinottaiset liikkeet saattoivat ohjelman samanlaiseen looppiin kuin syvyyshaku.

Muuttuvan sokkelon tilanteessa ei tapahtunut mitään erityisen yllättävää. Suoritusajoista kuitenkin huomaa, ettei aikaa kulunut kovin paljoa enempää kuin yksinkertaisessa polunetsinnässä, vaikka laskentakertoja oli 16-kertainen määrä. Syy tähän on siinä, että etsijän lähestyessä kohdettaan tarvittavan uuden polun pituus lyhenee. Pienemmässä sokkelossa reitti on eksponentiaalisesti nopeampi laskea.

#### Liikkuva kohde

| Algoritmi | A* | Dijkstra | Leveyshaku | Syvyyshaku | A* (vinottaiset) | Dijkstra (vinottaiset) |
|----------------|----------------------------------------------------------------------------------|----------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------|
| **Aika** | 90ms | 70ms | 30ms | 27ms | 76ms | 65ms |
| **Askeleet** | 299 | 299 | 299 | 3047 | 194 | 194 |
| **Laskentakerrat** | 60 | 60 | 60 | 67 | 39 | 39 |
| **Ajoparametrit** | `java -jar pathfinding.jar t105x105_moving false A* 5 20 10 false false false 0` | `java -jar pathfinding.jar t105x105_moving false Dijkstra 5 20 10 false false false 0` | `java -jar pathfinding.jar t105x105_moving false Breadth-first 5 20 10 false false false 0` | `java -jar pathfinding.jar t105x105_moving false Depth-first 5 20 10 false false false 0` | `java -jar pathfinding.jar t105x105_moving false Depth-first 5 20 10 false false false 0` | `java -jar pathfinding.jar t105x105_moving true Dijkstra 5 20 10 false false false 0` |

Tulokset ei-vinottaisilla liikkeillä vastaavat suurin piirtein edellisten testien perusteella odotettuja.  Syvyyshaku teki kuitenkin hassun tempun liikkuvan kohteen kanssa; se käytti lähes saman ajan kuin leveyshaku, vaikka aiemmin se on ollut merkittävästi nopeampi. Huomattavasti huonomman reitin löytävä syvyyshaku joutui tekemään enemmän laskentakertoja, sillä etsijä ei pysynyt läheskään yhtä hyvin maalin perässä. Todennäköisesti syvyyshaulla olisi kuulunut kestää vielä paljon pidempään, mutta maaliruudulta loppuivat siirrot kesken 67 siirron jälkeen.

Vinottaisten liikkeiden tapauksessa huomataan päinvastainen ilmiö; vinottaisten liikkeiden avulla reitistä saadaan paljon lyhyempi, jolloin maaliruutu ei ehdi liikkua yhtä paljon ennen kuin se saadaan kiinni ja laskentakertoja tarvitaan paljon vähemmän. Tällöin ohjelman suoritus on huomattavasti nopeampi.

#### Käytännön peliympäristön simulaatio

Testataan siis sokkeloa, jossa esiintyvät kaikkien aiempien testien erityispiirteet yhtä aikaa, sekä ruutuja, joilla on vaihtelevat painot. Tämä tilanne muistuttaa eniten polunetsintäalgoritmin käytännön sovellutusta videopelissä.

| Algoritmi | A* | Dijkstra | Leveyshaku | Syvyyshaku | A* (vinottaiset) | Dijkstra (vinottaiset) |
|--------------------------------|-------------------------------------------------------------------------------|-------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------|------------------------------------------------------------------------------|------------------------------------------------------------------------------------|
| **Aika** | 115ms | 82ms | 36ms | Loputon looppi kuten aiemmin | 105ms | 86ms |
| **Askeleet** | 333 | 333 | 352 |  | 267 | 267 |
| **Laskentakerrat** | 71 | 71 | 72 |  | 58 | 58 |
| **Epäonnistunutta laskentakertaa** | 5 | 5 | 5 |  | 5 | 5 |
| **Ajoparametrit** | `java -jar pathfinding.jar t105x105_all false A* 5 20 10 false false false 0` | `java -jar pathfinding.jar t105x105_all false Dijkstra 5 20 10 false false false 0` | `java -jar pathfinding.jar t105x105_all false Breadth-first 5 20 10 false false false 0` | `java -jar pathfinding.jar t105x105_all false Depth-first 5 20 10 false false false 0` | `java -jar pathfinding.jar t105x105_all true A* 5 20 10 false false false 0` | `java -jar pathfinding.jar t105x105_all true Dijkstra 5 20 10 false false false 0` |

Näissäkin testeissä leveyshaku osoittautui nopeimmaksi, Dijkstra toiseksi nopeimmaksi ja A* hitaimmaksi. Leveyshaku kuitenkin jostain syystä päätyi kulkemaan pidempää reittiä kuin kaksi muuta. Tämä johtuu siitä, että se ei huomioi solmujen painoja, kun taas A* ja Dijkstra pyrkivät kulkemaan nopeampia jääruutuja pitkin - ja sinänsä siis kulkivat siirtomäärällisesti pidempää reittiä, vaikka reitin paino oli matalampi. Leveyshaku posotti suoraan suoruuduista läpi.

Epäonnistuneet laskentakerrat seurasivat viiden siirron tauosta, jonka aikana yksikään reitti ei ollut etsijälle auki.

Vinottaisilla liikkeillä kului suurinpiirtein sama aika kuin ei-vinottaisilla; lyhyemmästä reitistä seuraavat vähemmät laskentakerrat kompensoivat solmujen naapurien suurempaa määrää.
