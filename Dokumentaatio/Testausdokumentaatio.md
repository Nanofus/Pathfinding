# Testausdokumentaatio

Testauksessa ajetaan kaikki hakualgoritmit samanlaisessa verkossa.

Testaus keskittyy algoritmien nopeuden ja käytetyn askelmäärän mittaamiseen. Yleiseen käyttöön optimaalisin algoritmi on nopea ja löytää parhaan reitin.

Verkkojen koko vaihtelee muutamien kymmenien solmujen kokoisista 400x400-kokoisiin.

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

## Vanhentunut setti

### Testi 1

Testissä 1 käytetään perinteisen labyrintin mallista sokkeloa, Mazes-kansion sokkeloa "401x401".

Vinottainen liike on sallittu.

#### Tulokset

A*: 85ms, 2029 askelta<br />
Dijkstra: 58ms, 2029 askelta<br />
Leveyshaku: 30ms, 2029 askelta<br />
Syvyyshaku: 26ms, 7921 askelta<br />

### Testi 2

Testissä käytetään samaa sokkeloa kuin testissä 1.

Vinottainen liike on kielletty.

#### Tulokset

A*: 72ms, 2879 askelta<br />
Dijkstra: 48ms, 2879 askelta<br />
Leveyshaku: 34ms, 2879 askelta<br />
Syvyyshaku: 26ms, 7741 askelta<br />

