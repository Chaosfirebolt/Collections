package main.com.collection.list.impl;

/**
 * Created by ChaosFire on 19.9.2017 г.
 */
class AscArrayIterator<T> extends AbstractArrayIterator<T> {

    private static final int INDEX_CHANGE = 1;

    AscArrayIterator(ArrayList<T> arrayList, int currentIndex, int startIndex, int endIndex, int expectedModCount) {
        super(arrayList, currentIndex, startIndex, endIndex, expectedModCount);
    }

    @Override
    public boolean hasNext() {
        return super.isIndexValid();
    }

    @Override
    public T next() {
        super.validateState();
        T value = super.getCurrentValue();
        super.changeCurrentIndex(INDEX_CHANGE);
        return value;
    }

    @Override
    public void remove() {
        super.removePrevious(-1);
    }
}