# Määrittelydokumentti

Toteutin ohjelmoinnin harjoitustyönä [luolastoseikkailupelin](https://github.com/Nanofus/Tangential), jonka vihollisten polunetsintä perustui lähinnä pelaajan takaa-ajamiseen ja satunnaisuuteen. Siispä päätin toteuttaa tira-harjoitustyönä erilaisia pathfinding-algoritmeja, joista parhaat voin käyttää pelin jatkokehitykseen.

## Toteutustapa

* Lähtökohtaisesti algoritmin tulee löytää reitti ruuduista koostuvan sokkelon läpi, pisteestä A pisteeseen B

Testaan kutakin algoritmia myös kolmessa muussa tilanteessa:

* Kohderuutu liikkuu
  * Tällä simuloidaan pelaajan hahmoa takaa-ajavaa vihollista: pelaaja liikkuu tätä pakoon
  * Algoritmi joutuu koko ajan sopeutumaan uuteen tilanteeseen
* Muuttuva sokkelo
  * Tällä simuloidaan esimerkiksi porttien ja siltojen avautumista pelimaailmassa
  * Algoritmi joutuu tarkistamaan, onko vanha reitti yhä käyttökuntoinen
* Yllämainitut yhtä aikaa

Sokkelot ladataan tekstitiedostosta, jossa määritellään seinien ja lattioiden lisäksi myös algoritmin aloituspiste ja maalipiste. Sokkelot ovat suuria ja monimutkaisia.

Algoritmien testauksessa mitataan maaliin pääsyyn vaadittavien askelten määrä sekä reitin laskemiseen käytetty aika.

## Tutkittavat algoritmit

Tutkin yleisimpiä polunetsintään käytettyjä algoritmeja:

* A*
* Dijkstran algoritmi
* Syvyyshaku
* Leveyshaku
* Mahdollisesti ant colony -pohjainen algoritmi
* Todennäköisesti muita vielä määrittämättömiä

## Lähteet

* Yleisesti:
  * http://en.wikipedia.org/wiki/Pathfinding
* A*:
  * http://www.policyalmanac.org/games/aStarTutorial.htm
  * http://theory.stanford.edu/~amitp/GameProgramming/AStarComparison.html
