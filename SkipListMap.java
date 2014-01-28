/**
 * Created with IntelliJ IDEA.
 * User: brent
 * Date: 11/15/13
 * Time: 1:11 PM
 * To change this template use File | Settings | File Templates.
 */

//Brent McCorvey

import java.util.*;

public class SkipListMap<K extends Comparable<? super K>, V> implements IListMap<K, V> {
    protected CoinFlipper flipper;
    protected int size;

    private Node minValue;
    private Node maxValue;

    private Node head;
    private Node tail;
    private int level;


    /**
     * constructs a SkipListMap object that stores keys in ascending order
     * when a key value pair is inserted, the flipper is called until it returns a tails
     * if for an pair the flipper returns n heads, the corresponding node has n + 1 levels
     *
     * the skip list should have an empty node at the beginning and end that do not store any data
     * these are called sentinel nodes
     * @param flipper the source of randomness
     */


    //Make sure to consider going down first to add

    public SkipListMap(CoinFlipper flipper){
        this.flipper = flipper;
        minValue = new Node(null, null);
        maxValue = new Node(null, null);
        minValue.next = maxValue;
        maxValue.prev = minValue;
        head = minValue;
        tail = maxValue;
        size = 0;
        level = 0;
    }

    @Override
    public K firstKey() {
       return minValue.next.key;
    }

    @Override
    public K lastKey() {
       return maxValue.prev.key;
    }

    @Override
    public boolean containsKey(K key) throws IllegalArgumentException {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        Node node = search(key);
        if (node.key == key) {
            return true;
        }
        return false;
    }

    @Override
    public boolean containsValue(V value) throws IllegalArgumentException {
        if (value == null) {
            throw new IllegalArgumentException();
        }
        Node temp = minValue;
        temp = temp.next;
        for (int i = 0; i < size(); i++) {
           if(temp.value.equals(value)) {
               return true;
           }
           else {
               temp = temp.next;
           }
        }
        return false;
    }

    /**
     * {@link IListMap#put(Comparable, Object) IListMap}
     *
     * if a node is updated, only the key value pair should be changed
     */
    @Override
    public V put(K key, V newValue) throws IllegalArgumentException {
        if (key == null || newValue == null) {
            throw new IllegalArgumentException();
        }
        Node oldNode = search(key);
        if (key.equals(oldNode.key)) {
            V prev = oldNode.value;
            oldNode.value = newValue;
            return prev;
        }
        Node newNode = new Node(key,newValue);
        newNode.prev = oldNode;
        newNode.next = oldNode.next;
        oldNode.next.prev = newNode;
        oldNode.next = newNode;

        int levelCount = 0;
        while(flipper.flipCoin() == CoinFlipper.Coin.HEADS) {
            if (levelCount>=level) {
                Node minValue,maxValue;
                minValue = new Node(null,null);
                maxValue = new Node(null,null);

                minValue.next = maxValue;
                minValue.down = head;

                maxValue.prev = minValue;
                maxValue.down = tail;

                head.up = minValue;
                tail.up = maxValue;

                head = minValue;
                tail = maxValue;

                level = level + 1;
            }
            while (oldNode.up == null) {
                oldNode = oldNode.prev;
            }
            oldNode = oldNode.up;

            Node layerNode;
            layerNode = new Node(key,null);
            layerNode.prev = oldNode;
            layerNode.next = oldNode.next;
            layerNode.down = newNode;

            oldNode.next.prev = layerNode;
            oldNode.next = layerNode;
            newNode.up = layerNode;

            newNode = layerNode;

            levelCount = levelCount + 1;
        }

        size++;
        return null;
    }

    @Override
    public V get(K key) throws IllegalArgumentException {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        Node node = search(key);
        if(key.equals(node.key)) {
            return node.value;
        }
        else {
            return null;
        }
    }

    @Override
    public V remove(K key) throws IllegalArgumentException {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        Node node = search(key);
        if(node.key != key) {
            return null;
        }
        V value = node.value;
        while (node != null) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node = node.up;
        }
        size--;
        return value;
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        flipper = new CoinFlipper();
        minValue = new Node(null, null);
        maxValue = new Node(null, null);
        minValue.next = maxValue;
        maxValue.prev = minValue;
        head = minValue;
        tail = maxValue;
        size = 0;
        level = 0;
    }

    @Override
    public Set<K> keySet() {
        TreeSet<K> set = new TreeSet<K>();
        Node temp = minValue;
        for (int i = 0; i < size(); i++) {
            if(temp.equals(minValue)) {
                temp = temp.next;
            }
            if (temp.equals(maxValue)) {
                temp = temp.prev;
            }
            set.add(temp.key);
            temp = temp.next;
        }
        return set;
    }

    @Override
    public Collection<V> values() {
        LinkedList<V> list = new LinkedList<V>();
        Node temp = minValue;
        for (int i = 0; i < size(); i++) {
            if(temp.equals(minValue)) {
                temp = temp.next;
            }
            if (temp.equals(maxValue)) {
                temp = temp.prev;
            }
            list.add(temp.value);
            temp = temp.next;
        }
        return list;
    }

    protected Node search(K key){
        Node temp = head;
        while (true){
            while(temp.next.key != null && temp.next.key.compareTo(key) <= 0){
                temp = temp.next;
            }
            if (temp.down != null){
                temp = temp.down;
            }
            else {
                break;
            }
        }
        return temp;
    }

    private class Node{
        private K key;
        private V value;
        private Node next, prev, up, down;

        private Node minValue,maxValue;

        private Node(K key, V value){
            this.key = key;
            this.value = value;

        }
    }

}