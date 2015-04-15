package fi.nano.pathfinding.dataStructures;

/**
 * HashSet käyttäen OwnHashMapia
 *
 * @author Nanofus
 */
public class OwnHashSet<O> {

    private OwnHashMap<O, Object> hashMap;

    /**
     * Konstruktori
     */
    public OwnHashSet() {
        hashMap = new OwnHashMap<>();
    }

    /**
     * Lisää objekti settiin
     *
     * @param object Objekti
     */
    public void add(O object) {
        hashMap.put(object, null);
    }

    /**
     * Onko objekti setissä
     *
     * @param object Objekti
     * @return
     */
    public boolean contains(O object) {
        return hashMap.contains(object) != null;
    }

    /**
     * Poista objekti
     *
     * @param object Objekti
     * @return Onnistuuko poisto
     */
    public boolean remove(O object) {
        return hashMap.remove(object);
    }

}
