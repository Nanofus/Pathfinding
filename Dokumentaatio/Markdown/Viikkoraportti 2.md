#Viikkoraportti 2

Nelosviikolla toteutin Dijkstran algoritmin, oman ArrayListin ja laajensin JUnit-testausta.

* **Dijkstra**-luokka sisältää Dijkstran polunetsintäalgoritmin. Luokan toiminta ei eroa suuresti AStarin luokasta, joskin se käyttää vain ArrayListia ja PriorityQueuea. Dijkstran algoritmi oli helpompi toteuttaa kuin A*, joten olisin ehkä ollut helpompaa toteuttaa algoritmit toisin päin ja käyttää Dijkstraa ATähden pohjana. No, tehty mikä tehty. Algoritmin lähteenä oli paria irto-Googlausta lukuunottamatta Wikipedia-artikkelin pseudokoodi sekä tämä sivu: http://www.geeksforgeeks.org/greedy-algorithms-set-6-dijkstras-shortest-path-algorithm/
* **OwnArrayList** sisältää ArrayListin oman toteutukseni, joka on ainakin toistaiseksi tosi riisuttu versio siitä mitä Java tarjoaa. Se esimerkiksi oikoo hitusen mutkia tuplaamalla kokonsa täyttyessään. 
* Aloitin myös oman PriorityQueuen toteuttamisen, mutta se ei ehdi tähän viikkopalautukseen. Yritin tehdä luokkaa jonka sekä OwnArrayList että OwnPriorityQueue perisivät, mutta huomasin, että olisin saman tien voinut vain laittaa OwnPriorityQueuen perimään OwnArrayListin. Joskin se saattaa olla ihan toimivaa?

Yksi seikka jota mietin sekä A*:n että Dijkstran kanssa oli itse polun luonti: tällä hetkellä molemmat algoritmit backtrackaavat kulkemansa reitin maalista takaisin alkuun päästyään loppuun. Tämä kuitenkin lienee lopulta nopein tapa tuottaa solmulista, vaikka se tapahtuukin vasta sen jälkeen kun varsinainen algoritmi on ajettu.

Seuraavana luvassa leveyshaku, syvyyshaku ja viimeisten omien tietorakenteiden toteutus. Aion myös laajentaa ohjelman perustoiminnallisuutta, ja lisätä esimerkiksi labyrintissa konkreettisesti siirtoja tekevän hahmon, joka joutuu välttelemään silloin tällöin itsestään aukeavia ja sulkeutuvia ovia ja optimoimaan reittiään lennosta. Aloitan myös algoritmien suorituskykytestauksen, ja niiden aikavaativuudet pitäisi määrittää. En kuitenkaan ole löytänyt vielä hyvää artikkelia A*:n aikavaativuuden laskemisesta.
