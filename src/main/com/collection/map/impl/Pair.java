package main.com.collection.map.impl;

/**
 * Created by ChaosFire on 27.9.2017 г.
 */
public class Pair<K, V> {

    private static final String EXCEPTION_MESSAGE = "Key and value objects don't permit null values.";

    private K key;
    private V value;

    Pair(K key, V value) {
        this.setKey(key);
        this.setValue(value);
    }

    @Override
    public String toString() {
        return String.format("%s -> %s", this.key.toString(), this.value.toString());
    }

    public K getKey() {
        return this.key;
    }

    private void setKey(K key) {
        this.validateNotNull(key);
        this.key = key;
    }

    public V getValue() {
        return this.value;
    }

    void setValue(V value) {
        this.validateNotNull(value);
        this.value = value;
    }

    private void validateNotNull(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }
    }
}