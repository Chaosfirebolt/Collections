package main.com.collection.map.impl;

import main.com.collection.list.contract.LinkList;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * Created by ChaosFire on 27.9.2017 г.
 */
abstract class AbstractHashTableIterator<K, V> implements Iterator<Pair<K, V>> {

    private static final int INITIAL_COUNT = 0;

    private HashTable<K, V> hashTable;
    private Iterator<Pair<K, V>> iterator;
    private int totalElementCount;
    private int currentElementCount;
    private int index;
    private int expectedModCount;

    AbstractHashTableIterator(HashTable<K, V> hashTable, int index) {
        this.setHashTable(hashTable);
        this.setTotalElementCount(hashTable.size());
        this.setCurrentElementCount(INITIAL_COUNT);
        this.setIndex(index);
        this.setExpectedModCount(hashTable.getModCount().getCount());
    }

    boolean hasMoreElements() {
        return this.currentElementCount < this.totalElementCount;
    }

    Pair<K, V> nextPair(int indexChange) {
        if (this.hashTable.getModCount().getCount() != this.expectedModCount) {
            throw new ConcurrentModificationException();
        }
        if (this.iterator == null || !this.iterator.hasNext()) {
            this.nextIndex(indexChange);
            this.iterator = indexChange > 0
                    ? this.hashTable.getTable()[this.index].iterator()
                    : this.hashTable.getTable()[this.index].descendingIterator();
        }
        return this.iterator.next();
    }

    void removePair() {
        this.hashTable.decreaseSize();
        this.iterator.remove();
        this.expectedModCount++;
    }

    void incrementCount() {
        this.currentElementCount++;
    }

    private void nextIndex(int indexChange) {
        LinkList<Pair<K, V>> list = this.hashTable.getTable()[this.index];
        while (list == null || list.size() == 0) {
            this.index += indexChange;
            if (this.index < 0 || this.index >= this.hashTable.getTable().length) {
                break;
            }
            list = this.hashTable.getTable()[this.index];
        }
    }

    private void setHashTable(HashTable<K, V> hashTable) {
        this.hashTable = hashTable;
    }

    private void setTotalElementCount(int totalElementCount) {
        this.totalElementCount = totalElementCount;
    }

    private void setCurrentElementCount(int currentElementCount) {
        this.currentElementCount = currentElementCount;
    }

    private void setIndex(int index) {
        this.index = index;
    }

    private void setExpectedModCount(int expectedModCount) {
        this.expectedModCount = expectedModCount;
    }
}