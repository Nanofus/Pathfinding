# Testausdokumentaatio

Testauksessa ajetaan kaikki hakualgoritmit samanlaisessa verkossa.

Testaus keskittyy algoritmien nopeuden ja löydetyn reitin lyhyyden mittaamiseen. Yleiseen käyttöön optimaalisin algoritmi on nopea ja löytää parhaan reitin.

Kaikissa testeissä käytetään 401x401 ruudun kokoista verkkoa.

### Testi 1

Testissä 1 käytetään perinteisen labyrintin mallista sokkeloa, Mazes-kansion sokkeloa "401x401".

Vinottainen liike on sallittu.

Komentoriviparametrit: 401x401 true

#### Tulokset

A*: 85ms, 2029 askelta<br />
Dijkstra: 58ms, 2029 askelta<br />
Leveyshaku: 30ms, 2029 askelta<br />
Syvyyshaku: 26ms, 7921 askelta<br />

### Testi 2

Testissä käytetään samaa sokkeloa kuin testissä 1.

Vinottainen liike on kielletty.

Komentoriviparametrit: 401x401 false

#### Tulokset

A*: 72ms, 2879 askelta<br />
Dijkstra: 48ms, 2879 askelta<br />
Leveyshaku: 34ms, 2879 askelta<br />
Syvyyshaku: 26ms, 7741 askelta<br />