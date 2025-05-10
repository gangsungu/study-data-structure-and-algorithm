package list;

import java.util.Arrays;

public class MyArrayList<T> implements IList<T> {
    private static final int DEFAULT_CAPACITY = 10;

    private int size;
    private T[] elements;

    public MyArrayList() {
        this.size = 0;
        this.elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T t) {
        //  배열이 가득 찼을때
        if (this.size == this.elements.length) {
            // 기존 배열을 옮긴 후, 배열 사이즈를 2배로 증가
            this.elements = Arrays.copyOf(this.elements, this.size * 2);
        }

        this.elements[size++] = t;
    }

    @Override
    public void insert(int index, T t) {
        if (this.size == this.elements.length) {
            this.elements = Arrays.copyOf(this.elements, this.size * 2);
        }
        for (int i = index; i < this.size; i++) {
            this.elements[i+1] = this.elements[i];
        }
        this.elements[index] = t;
        this.size++;
    }

    @Override
    public void clear() {
        this.size = 0;
        this.elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public boolean delete(T t) {
        for (int i = 0; i < this.size; i++) {
            if (this.elements[i].equals(t)) {
                for (int j = i; j < this.size -1; j++) {
                    this.elements[j] = this.elements[j+1];
                }
                this.size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteByIndex(int index) {
        if (index < 0 || index > this.size - 1) {
            return false;
        }
        for (int i = index; i < this.size-1; i++) {
            this.elements[i] = this.elements[i+1];
        }
        this.size--;
        return true;
    }

    @Override
    public T get(int index) {
        if (this.size <= index) {
            throw new IndexOutOfBoundsException();
        }
        return this.elements[index];
    }

    @Override
    public int indexOf(T t) {
        for(int i = 0; i < this.size; i++) {
            if (this.elements[i].equals(t)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean contains(T t) {
        for(int i=0;i<this.size;i++) {
            if (this.elements[i].equals(t)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return this.size;
    }
}
