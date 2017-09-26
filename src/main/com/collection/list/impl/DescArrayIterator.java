package main.com.collection.list.impl;

/**
 * Created by ChaosFire on 19.9.2017 г.
 */
class DescArrayIterator<T> extends AbstractArrayIterator<T> {

    DescArrayIterator(T[] array, int currentIndex, int startIndex, int endIndex) {
        super(array, currentIndex, startIndex, endIndex);
    }

    @Override
    public boolean hasNext() {
        return super.isIndexValid();
    }

    @Override
    public T next() {
        T value = super.getCurrentValue();
        super.decrementCurrentIndex();
        return value;
    }
}