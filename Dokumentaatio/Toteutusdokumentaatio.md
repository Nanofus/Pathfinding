# Toteutusdokumentaatio

## Yleisrakenne

Ohjelman päätoiminnallisuus keskittyy **AlgorithmRunner**-luokkaan. **MazeReader**-luokka lataa tekstitiedostosta sokkelon, joka syötetään AlgorithmRunnerille. AlgorithmRunner ottaa myös tiedon käytettävästä polunetsintäalgoritmista, testausmoodista sekä siitä, sallitaanko liikkuminen ruudukossa vinottain.

AlgorithmRunner parseroi sokkelosta verkon, jonka solmut se kytkee toisiinsa. Seinät eivät sisälly verkkoon lainkaan, ne jätetään siitä pois jo parserointivaiheessa. Näin polunetsintäalgoritmin ei tarvitse käyttää aikaa tarkistaakseen, onko ruutu kuljettava vai ei. Se, kytketäänkö vinottaiset ruudut toisiinsa, riippuu siitä, onko vinottain liikkuminen sallittu. Verkko tallennetaan kaksiulotteiseen taulukkoon **Node**-olioita. Nodella on tieto naapurisolmuistaan, sijainnistaan ruudukossa sekä algoritmien käyttämiä muuttujia.

AlgorithmRunner luo instanssin algoritmiluokasta, jonka se käskee ratkomaan sokkelon. Tällöin algoritmi etsii polun aloitussolmusta loppusolmuun, ja muodostaa reitin antamalla kulkemilleen solmuille parent-solmun. Kun maali löytyy, AlgorithmRunner kulkee polun lopusta alkuun parent-viitteitä pitkin ja generoi reitistä listan. Jos maalia ei löydy, saadaan tyhjä lista.

Algoritmit toteuttavat **Algorithm**-rajapinnan, joka sisältää AlgorithmRunnerin kutsuman **FindPath**(alkusolmu,loppusolmu)-metodin.

Aikavaativuuksissa n on solmujen määrä ja e kaarien.

## Algoritmit

### A*

A*-algoritmin pitäisi olla suhteellisen nopea ja se löytää optimaalisimman reitin. Se toimii keon avulla. Jokaisella solmulla on kolme arvoa: g, f ja h. G on kuljettu matka lähtösolmusta tähän solmuun, h on arvioitu etäisyys maalisolmuun ja f on näiden summa. Keon avulla algoritmi pyrkii kulkemaan kevyintä reittiä pitkin. Tämä reitti on se, jonka f on pienin.

Algoritmi laskee h-arvon etäisyytenä suoraan linnuntietä nykyisestä solmusta maalisolmuun. Algoritmi pyöristää etäisyyden kuitenkin kokonaisluvuksi. Tämä saattaa aiheuttaa joissain tilanteissa pienen virheen, mutta nopeuttaa algoritmin toimintaa.

Algoritmi ottaa huomioon kaarien painot: kyljet vastakkain olevien solmujen välinen etäisyys on 10, vinottaisten 14. Tämä on pyöristys kahden neliöjuuresta.

*Aika 401x401-sokkelon ratkaisemiseen:* 85ms<br />
*Saadun reitin pituus:* 2029<br />
*Aikavaativuus:* O((e+n)*log(n))<br />
*Saavutettu aikavaativuus:* O((e+n)*log(n))<br />

### Dijkstran algoritmi

Dijkstran algoritmin toteutus käyttää myös kekoa. Aluksi se merkitsee etäisyyden kaikkiin muihin solmuihin äärettömäksi. Tämän jälkeen se alkaa käydä verkkoa läpi kevyintä reittiä pitkin, ja laskee matkan varrella oleville solmuille etäisyydet alkusolmusta. Jos algoritmi törmää jo löydettyyn solmuun, se tarkistaa onko uusi reitti vanhaa kevyempi; jos on, se korjaa solmun etäisyysarvon.

Algoritmi käyttää samoja kaaripainoja kuin A*: 10 vierekkäisille ja 14 vinottaisille.

*Aika 401x401-sokkelon ratkaisemiseen:* 58ms<br />
*Saadun reitin pituus:* 2029<br />
*Aikavaativuus:* O((e+n)*log(n))<br />
*Saavutettu aikavaativuus:* O((e+n)*log(n))<br />

### Leveyshaku

Leveyshaku on Dijkstran algoritmia ja A*:ta yksinkertaisempi. Se on nopeampi, ja löytää lyhimmän reitin, mutta ei kuitenkaan ole täysin vertailukelpoinen näiden kanssa, sillä se ei huomioi kaarien painoja. Leveyshaku käy solmuja läpi jonon avulla niin, että se tutkii verkkoa joka suuntaan tasaisesti.

*Aika 401x401-sokkelon ratkaisemiseen:* 30ms<br />
*Saadun reitin pituus:* 2029<br />
*Aikavaativuus:* O(n+e)<br />
*Saavutettu aikavaativuus:* O(n+e)<br />

### Syvyyshaku

Syvyyshaku on anhe eli nopea algoritmi, mutta se löytää epäoptimaalisen reitin. Se lähtee kulkemaan yhteen suuntaan kunnes ei pääse enää eteenpäin, tämän jälkeen pakittaa ja jatkaa seuraavasta haarasta. Toteutus valitsee aina solmun naapurilistasta ensimmäisen; näin ollen se suosii oikealle kulkemista (oikea solmu on listassa ensimmäisenä).

Syvyyshaun löytämä reitti olisi oikeassa navigointikäytössä käyttökelvoton, mutta silti hyödyllinen, jos riittää tietää, onko reitti kahden solmun välillä olemassa.

*Aika 401x401-sokkelon ratkaisemiseen:* 26ms<br />
*Saadun reitin pituus:* 7921<br />
*Aikavaativuus:* O(e)<br />
*Saavutettu aikavaativuus:* O(e)<br />