# Viikkoraportti 4

Viikon 4 paino oli algoritmien optimoinnissa sekä luokkien testauksen saattamisessa ajan tasalle.

Jokaisella tietorakenteella ja algoritmilla on nyt oma testausluokkansa. Tein ensimmäiset nopeusmittaukset (joiden tulokset löytyvät testausdokumentaatiosta) main-luokan ja komentoriville printtaamisen avulla. Perinteisen mallisessa labyrintissa leveyshaku toimi nopeammin ja tuotti aivan yhtä hyvän lopputuloksen kuin A* ja Dijkstra, mistä yllätyin suuresti. Syvyyshaku puolestaan tuotti yli kaksi kertaa pidemmän reitin kuin muut, mutta selvästi nopeiten. Syvyyshaku ei sovellu navigointiin, mutta jos riittää tietää onko reitti olemassa se on hyvä valinta.

A*-algoritmissa on vielä jotain kummallista; sen pitäisi periaatteessa olla nopeampi kuin Dijkstra, mutta ainakin testaamissani sokkeloissa se toimi Dijkstraa hitaammin. Tämä saattaa johtua myös sokkelon rakenteesta (vaikka en kyllä näe tai ole löytänyt mitään syytä sille miksi asia olisi näin), joten testaan sitä vielä muunmallisilla sokkeloilla.

Todo-lista loppukurssille:
* Lisää testejä
* Tarkoituksenani oli alunperin testata algoritmeja myös käytännön tilanteissa, joissa reittiä joudutaan miettimään koko ajan uudestaan sokkelon muuttuessa. Yritän ehtiä tehdä tämän vielä.
* Enemmän erilaisia sokkeloita testien käyttöön: tämänhetkiset sokkelot ovat hyvin perinteisiä labyrintteja, mutta algoritmit voivat käyttäytyä hyvin eri tavalla täysin avoimissa tiloissa joissa on yksittäisiä esteitä siellä täällä.
* A*-algoritmin mystisen hitauden syyn selvittäminen
* Testaus- ja toteutusdokumentaatioiden viimeistely
* Graafiset esitykset ja visuaalinen demonstraatio labyrintissa kulkemisesta