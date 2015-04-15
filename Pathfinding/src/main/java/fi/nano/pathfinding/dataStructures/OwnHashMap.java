package fi.nano.pathfinding.dataStructures;

/**
 * HashMapin toteutus
 *
 * @author Nanofus
 * @param <Key> Avain
 * @param <Value> Arvo
 */
public class OwnHashMap<Key, Value> {

    private MapObject<Key, Value>[] array;
    private int capacity = 12;

    private int hashCode;

    /**
     * Konstruktori
     */
    public OwnHashMap() {
        array = new MapObject[capacity];
    }

    /**
     * Laita objekti mappiin. Jos samalla hashilla on jo objekti mapissa,
     * laitetaan uusi sen "perään".
     *
     * @param key Avain
     * @param object Objekti
     */
    public void put(Key key, Value object) {
        if (key == null) {
            return;
        }

        hashCode = generateHash(key);

        MapObject<Key, Value> addedObject = new MapObject<>(key, object, null);

        // Jos bucketissa on jo objekti, lisätään uusi sen perään. Muuten luodaan uusi bucket.
        if (array[hashCode] != null) {
            MapObject<Key, Value> prev = null;
            MapObject<Key, Value> curr = array[hashCode];

            while (curr != null) {
                if (key.equals(curr.key)) {
                    if (prev != null) {
                        addedObject.next = curr.next;
                        prev.next = addedObject;

                        return;
                    } else {
                        addedObject.next = curr.next;
                        array[hashCode] = addedObject;

                        return;
                    }
                }
                prev = curr;
                curr = curr.next;
            }
            prev.next = addedObject;
        } else {
            array[hashCode] = addedObject;
        }
    }

    /**
     * Poista objekti avaimella
     *
     * @param key Avain
     * @return Onnistuuko poisto
     */
    public boolean remove(Key key) {

        hashCode = generateHash(key);

        // Etsitään osuva objekti ja poistetaan se, jos ei löydy niin false
        if (array[hashCode] != null) {
            MapObject<Key, Value> prev = null;
            MapObject<Key, Value> curr = array[hashCode];

            while (curr != null) {

                if (key.equals(curr.key)) {
                    if (prev != null) {
                        prev.next = curr.next;
                        return true;
                    } else {
                        array[hashCode] = array[hashCode].next;
                        return true;
                    }
                }

                prev = curr;
                curr = curr.next;
            }

            return false; //Ei löytynyt!
        } else {
            return false;
        }

    }

    /**
     * Onko tiettyä avainta mapissa
     *
     * @param key Avain
     * @return
     */
    public boolean contains(Key key) {

        hashCode = generateHash(key);

        // Etsitään bucketista oikea objekti, jos löytyy niin true, muutoin false
        if (array[hashCode] != null) {
            MapObject<Key, Value> object = array[hashCode];

            while (object != null) {
                if (object.key.equals(key)) {
                    return true;
                }
                object = object.next;
            }

            return false;
        } else {
            return false;
        }
    }

    /**
     * Hae objekti avaimella
     *
     * @param key Avain
     * @return Objekti
     */
    public Value get(Key key) {
        
        hashCode = generateHash(key);

        // Etsitään vastaava objekti, jos ei löydy niin null
        if (array[hashCode] != null) {
            MapObject<Key, Value> object = array[hashCode];

            while (object != null) {
                if (object.key.equals(key)) {
                    return object.value;
                }
                object = object.next;
            }
            return null;
        } else {
            return null;
        }
    }

    /**
     * Generoi avaimesta hashcoden objektin .hashCode():lla, kuitenkin niin
     * ettei arvo mene taulukosta yli
     *
     * @param key Avain
     * @return Hashcode
     */
    private int generateHash(Key key) {
        return Math.abs(key.hashCode()) % capacity;
    }

}

/**
 * Taulukkoon säilöttävä olio, joka sisältää avain-arvoparin.
 *
 * @param <Key> Avain
 * @param <Value> Arvo
 */
class MapObject<Key, Value> {

    /**
     * Avain
     */
    public Key key;

    /**
     * Arvo
     */
    public Value value;

    /**
     * Seuraava objekti, jos samaan bucketiin päätyy useita
     */
    public MapObject<Key, Value> next;

    /**
     * Konstruktori
     *
     * @param key Avain
     * @param value Arvo
     * @param next Seuraava objekti samassa bucketissa
     */
    public MapObject(Key key, Value value, MapObject<Key, Value> next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }
}
