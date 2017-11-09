package main.com.collection.map.impl;

import main.com.collection.AbstractCollection;
import main.com.collection.Collection;
import main.com.collection.list.contract.LinkList;
import main.com.collection.list.impl.LinkedList;

import java.lang.reflect.Array;
import java.util.Iterator;

/**
 * Created by ChaosFire on 27.9.2017 г.
 */
class HashTable<K, V> extends AbstractCollection implements Collection {

    private static final int DEFAULT_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;
    private static final int CHANGE_RATE = 2;

    private LinkList<Pair<K, V>>[] table;
    private int maxElements;
    private ModCount modCount;

    HashTable(int capacity) {
        this.setTable(this.createArray(capacity));
        this.setMaxElements(this.calcMaxElements(capacity));
        this.setModCount(new ModCount());
    }

    HashTable() {
        this(DEFAULT_CAPACITY);
    }

    void add(K key, V value) {
        if (this.shouldGrow()) {
            this.copyHashTable(this.table.length * CHANGE_RATE);
        }
        int index = this.getIndex(key, this.table.length);
        Pair<K, V> pair = this.findByKey(key);
        if (pair == null) {
            pair = new Pair<>(key, value);
            if (this.table[index] == null) {
                this.table[index] = new LinkedList<>();
            }
            this.table[index].add(pair);
            super.incrementSize();
            this.modCount.incrementCount();
        } else {
            pair.setValue(value);
        }
    }

    Pair<K, V> remove(K key) {
        int index = this.getIndex(key, this.table.length);
        LinkList<Pair<K, V>> list = this.table[index];
        if (list == null) {
            return null;
        }
        Pair<K, V> removed = null;
        Iterator<Pair<K, V>> iterator = list.iterator();
        while (iterator.hasNext()) {
            Pair<K, V> pair = iterator.next();
            if (pair.getKey().equals(key)) {
                removed = pair;
                iterator.remove();
                super.decrementSize();
                this.modCount.incrementCount();
                break;
            }
        }
        if (this.shouldShrink()) {
            this.copyHashTable(this.table.length / CHANGE_RATE);
        }
        return removed;
    }

    Pair<K, V> findByKey(K key) {
        int index = this.getIndex(key, this.table.length);
        LinkList<Pair<K, V>> list = this.table[index];
        if (list == null) {
            return null;
        } else {
            for (Pair<K, V> pair : list) {
                if (pair.getKey().equals(key)) {
                    return pair;
                }
            }
        }
        return null;
    }

    boolean containsValue(V value) {
        Iterator<Pair<K, V>> iterator = this.hashTableIterator();
        while (iterator.hasNext()) {
            if (iterator.next().getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        for (int i = 0; i < this.table.length; i++) {
            this.table[i] = null;
        }
        super.clearSize();
        this.modCount.incrementCount();
    }

    @Override
    public int size() {
        return super.getSize();
    }

    Iterator<Pair<K, V>> hashTableIterator() {
        return new AscHashTableIterator<>(this,0);
    }

    Iterator<Pair<K, V>> descendingHashTableIterator() {
        return new DescHashTableIterator<>(this,this.table.length - 1);
    }

    void decreaseSize() {
        super.decrementSize();
    }

    private int getIndex(K key, int length) {
        return Math.abs(key.hashCode()) % length;
    }

    private boolean shouldGrow() {
        return this.size() == this.maxElements;
    }

    private boolean shouldShrink() {
        int size = this.size();
        return size >= DEFAULT_CAPACITY && size == this.maxElements / 4;
    }

    private void copyHashTable(int capacity) {
        LinkList<Pair<K, V>>[] copy = this.createArray(capacity);
        Iterator<Pair<K, V>> iterator = this.hashTableIterator();
        while (iterator.hasNext()) {
            Pair<K, V> pair = iterator.next();
            int index = this.getIndex(pair.getKey(), capacity);
            if (copy[index] == null) {
                copy[index] = new LinkedList<>();
                copy[index].add(pair);
            } else {
                copy[index].add(pair);
            }
        }
        this.setTable(copy);
        this.setMaxElements(this.calcMaxElements(copy.length));
    }

    @SuppressWarnings("unchecked")
    private LinkList<Pair<K, V>>[] createArray(int capacity) {
        return (LinkList<Pair<K, V>>[]) Array.newInstance(LinkList.class, capacity);
    }

    private int calcMaxElements(int capacity) {
        return (int) (capacity * LOAD_FACTOR);
    }

    LinkList<Pair<K, V>>[] getTable() {
        return this.table;
    }

    ModCount getModCount() {
        return this.modCount;
    }

    private void setTable(LinkList<Pair<K, V>>[] table) {
        this.table = table;
    }

    private void setMaxElements(int maxElements) {
        this.maxElements = maxElements;
    }

    private void setModCount(ModCount modCount) {
        this.modCount = modCount;
    }
}