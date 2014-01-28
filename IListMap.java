import java.util.Collection;
import java.util.Set;

/**
 * 
 * This interfaces specifies data structure implementing a simplified subset of java.util.SortedMap
 * 
 * @param <K> the key to find values
 * @param <V> the value to store by keys
 */
public interface IListMap<K extends Comparable<? super K>, V>{
	
	/**
	 * finds and returns the first element of the list map
	 * if the list is empty, return null
	 * @return the first key in the list map
	 */
	public K firstKey();
	
	
	/**
	 * finds and returns the last element of the list map
	 * if the list is empty, return null
	 * @return the last element in the list map
	 */
	public K lastKey();
	
	/**
	 * searches the list map for a given key
	 * if the list is empty, return false
	 * @param key a key that is equal to some other key in the list map
	 * @throws IllegalArgumentException if the parameter is null
	 * @return true if the key is in the map, false otherwise
	 */
	public boolean containsKey(K key);
	
	/**
	 * searches the list map for some value
	 * if the list is empty, return false
	 * @param value a value that is equal to some other value in the list map
	 * @throws IllegalArgumentException if the parameter is null
	 * @return if the value is in the map
	 */
	public boolean containsValue(V value);
	
	
	/**
	 * adds a new key value pair into the list map
	 * @param key the key to put into the list map
	 * @param newValue the value to put into the list map
	 * @throws IllegalArgumentException if any parameter is null
	 * @return the old value if there was one, otherwise null
	 */
	public V put(K key, V newValue);
	
	
	/**
	 * removes a key value pair from the list map
	 * @param key a key that is equal to some other ky in the list map
	 * @throws IllegalArgumentException if the parameter is null 
	 * @return the old value if there was one, otherwise null
	 */
	public V remove(K key);
	
	/**
	 * finds and returns the value associated with a key
	 * @param key a key that is equal to some other key in the list map
	 * @throws IllegalArgumentException if the parameter is null
	 * @return the value associated with the key, null otherwise
	 */
	public V get(K key);
	
	/**
	 * gives the size of the list map
	 * @return the number of key value pairs in the list
	 */
	public int size();
	
	/**
	 * clears the list map
	 * the size should be zero after this method is called
	 */
	public void clear();
	
	/**
	 * the keys of the list map
	 * if the list is empty, return an empty set
	 * @return a set of all the keys in the list map
	 */
	public Set<K> keySet();
	
	/**
	 * the values of the list map
	 * if the list is empty, return an empty collection
	 * @return a set of all the keys in the list map
	 */
	public Collection<V> values();
	
}
