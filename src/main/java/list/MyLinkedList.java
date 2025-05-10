package list;

public class MyLinkedList<T> implements IList<T> {

    private int size;
    private Node head;

    public MyLinkedList() {
        this.size = 0;
        this.head = new Node(null);
    }

    @Override
    public void add(T t) {
        Node current = this.head;
        while (current.next != null) {
            current = current.next;
        }
        Node n = new Node(t);
        current.next = n;
        this.size++;
    }

    @Override
    public void insert(int index, T t) {
        if (index > this.size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        Node prev = this.head;
        Node current = prev.next;

        int i = 0;
        while (i++ < index) {
            prev = prev.next;
            current = current.next;
        }

        Node newNode = new Node(t, current);
        prev.next = newNode;
        this.size++;
    }

    @Override
    public void clear() {
        this.size = 0;
        this.head.next = null;
    }

    @Override
    public boolean delete(T t) {
        Node prev = this.head;
        Node current = prev.next;
        while (current != null) {
            if (current.data.equals(t)) {
                prev.next = current.next;
                current.next = null;
                this.size--;
                return true;
            }
            prev = prev.next;
            current = current.next;
        }
        return false;
    }

    @Override
    public boolean deleteByIndex(int index) {
        if (index >= this.size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        Node prev = this.head;
        Node current = prev.next;
        int i = 0;
        while (i++ < index) {
            prev = prev.next;
            current = current.next;
        }

        prev.next = current.next;
        current.next = null;
        this.size--;
        return true;
    }

    @Override
    public T get(int index) {
        if (index >= this.size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        Node current = this.head.next;
        int i = 0;
        while (i++ < index) {
            current = current.next;
        }
        return current.data;
    }

    @Override
    public int indexOf(T t) {
        Node current = this.head.next;
        int index = 0;
        while (current != null) {
            if (current.data != null && current.data.equals(t)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return this.head.next == null;
    }

    @Override
    public boolean contains(T t) {
        Node current = this.head.next;
        while (current != null) {
            if (current.data != null && current.data.equals(t)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public int size() {
        return this.size;
    }

    private class Node {
        T data;
        Node next;

        Node(T data) {
            this.data = data;
        }

        Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
}
