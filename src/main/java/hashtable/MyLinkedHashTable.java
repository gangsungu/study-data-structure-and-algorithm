package hashtable;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MyLinkedHashTable<K, V> implements IHashTable<K, V> {
    private List<Node>[] buckets;   // hashTable -> chaining
    private int size;
    private int bucketSize;

    public MyLinkedHashTable() {
        this.buckets = new List[1024];  // 2의 10제곱
        this.bucketSize = 1024;
        this.size = 0;
        for(int i = 0; i < bucketSize; i++) {
            this.buckets[i] = new LinkedList<>();
        }
    }

    public MyLinkedHashTable(int bucketSize) {
        this.buckets = new List[bucketSize];
        this.bucketSize = bucketSize;
        this.size = 0;
        for(int i = 0; i < bucketSize; i++) {
            this.buckets[i] = new LinkedList<>();
        }
    }

    @Override
    public void put(K key, V value) {
        int idx = this.hash(key);
        List<Node> bucket = this.buckets[idx];
        for (Node node : bucket) {
            if (node.key.equals(key)) {
                node.data = value;
                return;
            }
        }
        Node node = new Node(key, value);
        bucket.add(node);
        this.size++;
    }

    @Override
    public V get(K key) {
        int idx = this.hash(key);
        List<Node> bucket = this.buckets[idx];
        for (Node node : bucket) {
            if (node.key.equals(key)) {
                return node.data;
            }
        }
        return null;
    }

    @Override
    public boolean delete(K key) {
        int idx = this.hash(key);
        List<Node> bucket = this.buckets[idx];
        for (Iterator<Node> iter = bucket.iterator(); iter.hasNext(); ) {
            Node node = iter.next();
            if (node.key.equals(key)) {
                iter.remove();
                this.size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(K key) {
        int idx = this.hash(key);
        List<Node> bucket = this.buckets[idx];
        for (Node node : bucket) {
            if (node.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return this.size;
    }

    private int hash(K key) {
        int hash = 0;

        for (Character ch : key.toString().toCharArray()) {
            hash += (int) ch;
        }

        return hash % this.bucketSize;
    }

    private class Node {
        K key;
        V data;

        Node(K key, V data) {
            this.key = key;
            this.data = data;
        }
    }
}
