package main.com.collection.map.impl;

/**
 * Created by ChaosFire on 27.9.2017 г.
 */
class DescHashTableIterator<K, V> extends AbstractHashTableIterator<K, V> {

    private static final int INDEX_CHANGE = -1;

    DescHashTableIterator(HashTable<K, V> hashTable, int index) {
        super(hashTable, index);
    }

    @Override
    public boolean hasNext() {
        return super.hasMoreElements();
    }

    @Override
    public Pair<K, V> next() {
        Pair<K, V> next = super.nextPair(INDEX_CHANGE);
        super.incrementCount();
        return next;
    }

    @Override
    public void remove() {
        super.removePair();
    }
}