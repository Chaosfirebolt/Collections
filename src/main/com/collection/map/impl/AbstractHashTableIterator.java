package main.com.collection.map.impl;

import main.com.collection.list.contract.LinkList;

import java.util.Iterator;

/**
 * Created by ChaosFire on 27.9.2017 г.
 */
abstract class AbstractHashTableIterator<K, V> implements Iterator<Pair<K, V>> {

    private static final int INITIAL_COUNT = 0;

    private HashTable<K, V> hashTable;
    private Iterator<Pair<K, V>> iterator;
    private int totalCount;
    private int currentCount;
    private int index;

    AbstractHashTableIterator(HashTable<K, V> hashTable, int index) {
        this.setHashTable(hashTable);
        this.setTotalCount(hashTable.size());
        this.setCurrentCount(INITIAL_COUNT);
        this.setIndex(index);
    }

    boolean hasMoreElements() {
        boolean hasMore = this.currentCount < this.totalCount;
        if (!hasMore) {
            this.hashTable.getModCount().deactivateValidation();
        }
        return hasMore;
    }

    Pair<K, V> nextPair(int indexChange) {
        this.hashTable.getModCount().validate();
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
    }

    void incrementCount() {
        this.currentCount++;
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

    private void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    private void setCurrentCount(int currentCount) {
        this.currentCount = currentCount;
    }

    private void setIndex(int index) {
        this.index = index;
    }
}