package cache;

import java.util.HashMap;
import java.util.Map;

public class LRUCache<K, V> implements ICache<K, V> {

    private int capacity;
    private Map<K, Node<K, V>> map;

    private Node<K, V> head;    // double Linked List dummy
    private Node<K, V> tail;    // double Linked List dummy

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();

        this.head = Node.createDummy(); // dummy;
        this.tail = Node.createDummy(); // dummy;

        this.head.next = this.tail;
        this.tail.prev = this.head;
        this.head.prev = null;
        this.tail.next = null;
    }

    // 캐시에 데이터를 추가하는 연산
    @Override
    public void put(K key, V value) {
        Node<K, V> node = this.map.get(key);    // O(1)
        if (node == null) {
            Node<K, V> tmp = new Node(key, value);
            if (this.map.size() == this.capacity) {
                this.delete(this.tail.prev.key);
            }
            addToFirst(tmp);
            this.map.put(key, tmp);
        } else {
            node.value = value;
            removeNode(node);
            addToFirst(node);
        }
    }

    @Override
    public V get(K key) {
        Node<K, V> node = this.map.get(key);
        if (node != null) {
            removeNode(node);
            addToFirst(node);
            return node.value;
        }
        return null;
    }

    private void removeNode(Node<K, V> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addToFirst(Node<K, V> node) {
        Node currentHead = this.head.next;  // 현재 더블링크드 리스트에서 가장 앞에 있느 노드
        currentHead.prev = node;
        node.next = currentHead;
        node.prev = this.head;
        this.head.next = node;
    }

    @Override
    public boolean delete(K key) {
        Node<K, V> node = this.map.get(key);
        if (node != null) {
            this.removeNode(node);
            this.map.remove(key);
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return this.map.size();
    }

    private static class Node<K, V> {
        // 캐시의 키 밸류를 저장하기 위한 멤버 변수
        public K key;
        public V value;
        // 더블 링크드 리스트의 노드 이기 때문에 prev 와 next 를 가르키는 포인터 둘을 모두 갖고 있음
        Node<K, V> prev;
        Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.prev = null;
            this.next = null;
        }

        static Node createDummy() {
            return new Node(null, null);
        }
    }
}
