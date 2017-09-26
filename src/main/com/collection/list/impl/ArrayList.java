package main.com.collection.list.impl;

import main.com.collection.AbstractCollection;
import main.com.collection.list.contract.IndexList;

import java.util.Iterator;

/**
 * Created by ChaosFire on 18.9.2017 г.
 */
public class ArrayList<T> extends AbstractCollection implements IndexList<T> {

    private static final int DEFAULT_CAPACITY = 16;
    private static final int CHANGE_RATE = 2;
    private static final int DEFAULT_INDEX = 0;
    private static final int DEFAULT_OFFSET = 0;

    private T[] array;
    private ArrayList<T> parent;
    private ModCount modCount;
    private int startIndex;
    private int offset;

    public ArrayList(int capacity) {
        this(null, new ModCount(), DEFAULT_INDEX, DEFAULT_OFFSET);
        this.setArray(this.createArray(capacity));
    }

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    private ArrayList(ArrayList<T> parent, ModCount modCount, int startIndex, int offset) {
        this.setParent(parent);
        this.setModCount(modCount);
        this.setStartIndex(startIndex);
        this.setOffset(offset);
    }

    private ArrayList(T[] array, ArrayList<T> parent, ModCount modCount, int startIndex, int offset) {
        this(parent, modCount, startIndex, offset);
        this.setArray(array);
    }

    @Override
    public String toString() {
        this.modCount.validate();
        StringBuilder sb = new StringBuilder();
        int size = this.size() + this.offset;
        if (size > 0) {
            sb.append(this.array[this.startIndex]);
            for (int i = this.startIndex + 1; i < size; i++) {
                sb.append(", ").append(this.array[i]);
            }
        }
        return String.format("[%s]", sb);
    }

    @Override
    public T set(int index, T element) {
        this.modCount.validate();
        this.validateIndexInclusive(index);
        int realIndex = index + this.offset;
        T previous = this.array[realIndex];
        this.array[realIndex] = element;
        return previous;
    }

    @Override
    public void add(int index, T element) {
        this.modCount.validate();
        this.validateIndexExclusive(index);
        if (this.shouldGrow()) {
            this.growArray();
        }
        int realIndex = index + this.offset;
        this.shiftRight(realIndex);
        this.array[realIndex] = element;
        super.incrementSize();
        this.modCount.incrementCount();
        ArrayList<T> parent = this.parent;
        while (parent != null) {
            parent.incrementSize();
            parent = parent.parent;
        }
    }

    @Override
    public IndexList<T> subList(int fromIndex, int toIndex) {
        this.modCount.validate();
        this.validateRangeIndexes(fromIndex, toIndex);
        ModCount modCount = new ModCount(this.modCount);
        ArrayList<T> subList = new ArrayList<>(this.array, this, modCount, fromIndex, fromIndex + this.offset);
        subList.incrementSize(toIndex - fromIndex);
        return subList;
    }

    @Override
    public IndexList<T> subListCopy(int fromIndex, int toIndex) {
        this.modCount.validate();
        this.validateRangeIndexes(fromIndex, toIndex);
        int size = toIndex - fromIndex;
        ArrayList<T> copyList = size <= DEFAULT_CAPACITY ? new ArrayList<>() : new ArrayList<>(size);
        int end = toIndex + this.offset;
        for (int i = fromIndex + this.offset; i < end; i++) {
            copyList.add(this.array[i]);
        }
        return copyList;
    }

    @Override
    public void add(T element) {
        int index = super.getSize();
        this.add(index, element);
    }

    @Override
    public T get(int index) {
        this.modCount.validate();
        this.validateIndexInclusive(index);
        return this.array[index + this.offset];
    }

    @Override
    public T remove(int index) {
        this.modCount.validate();
        this.validateIndexInclusive(index);
        int realIndex = index + this.offset;
        T value = this.array[realIndex];
        this.shiftLeft(realIndex, 1);
        super.decrementSize();
        this.modCount.incrementCount();
        ArrayList<T> parent = this.parent;
        while (parent != null) {
            parent.decrementSize();
            parent = parent.parent;
        }
        if (this.shouldShrink()) {
            this.shrinkArray();
        }
        return value;
    }

    @Override
    public boolean remove(T element) {
        int index = this.find(element);
        if (index >= 0) {
            this.remove(index);
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(T element) {
        this.modCount.validate();
        return this.find(element) >= 0;
    }

    @Override
    public void clear() {
        this.modCount.validate();
        int size = this.size() + this.offset;
        for (int index = this.startIndex; index < size; index++) {
            this.array[index] = null;
        }
        this.shiftLeft(this.startIndex, size - this.startIndex);
        int sizeReduction = this.size();
        ArrayList<T> parent = this.parent;
        while (parent != null) {
            parent.decrementSize(sizeReduction);
            parent = parent.parent;
        }
        super.clearSize();
        this.modCount.incrementCount();
    }

    @Override
    public int size() {
        this.modCount.validate();
        return super.getSize();
    }

    @Override
    public Iterator<T> iterator() {
        this.modCount.validate();
        return new AscArrayIterator<>(this.array, this.startIndex, this.startIndex, this.size() + this.offset);
    }

    public Iterator<T> descendingIterator() {
        this.modCount.validate();
        int size = this.size() + this.offset;
        return new DescArrayIterator<>(this.array, size - 1, this.startIndex, size);
    }

    private int find(T element) {
        int index = -1;
        int size = this.size() + this.offset;
        if (element == null) {
            for (int i = this.startIndex; i < size; i++) {
                if (this.array[i] == null) {
                    index = i - this.offset;
                    break;
                }
            }
        } else {
            for (int i = this.startIndex; i < size; i++) {
                if (this.array[i].equals(element)) {
                    index = i - this.offset;
                    break;
                }
            }
        }
        return index;
    }

    private boolean shouldGrow() {
        return super.getSize() == this.array.length;
    }

    private boolean shouldShrink() {
        return super.getSize() >= DEFAULT_CAPACITY && super.getSize() == this.array.length / 4;
    }

    private void validateIndexInclusive(int index) {
        if (index < 0 || index >= super.getSize()) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void validateIndexExclusive(int index) {
        if (index < 0 || index > super.getSize()) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void validateRangeIndexes(int fromIndex, int toIndex) {
        this.validateIndexInclusive(fromIndex);
        this.validateIndexExclusive(toIndex);
        if (toIndex <= fromIndex) {
            throw new IllegalArgumentException();
        }
    }

    private void growArray() {
        T[] copy = this.createArray(this.array.length * CHANGE_RATE);
        System.arraycopy(this.array, 0, copy, 0, super.getSize());
        this.setArray(copy);
    }

    private void shrinkArray() {
        T[] copy = this.createArray(this.array.length / CHANGE_RATE);
        System.arraycopy(this.array, 0, copy, 0, super.getSize());
        this.setArray(copy);
    }

    @SuppressWarnings("ManualArrayCopy")
    private void shiftLeft(int index, int offset) {
        int size = this.getArraySize() - offset;
        for (int i = index; i < size; i++) {
            this.array[i] = this.array[i + offset];
        }
    }

    @SuppressWarnings("ManualArrayCopy")
    private void shiftRight(int index) {
        for (int i = this.getArraySize() - 1; i >= index; i--) {
            this.array[i + 1] = this.array[i];
        }
    }

    @SuppressWarnings("unchecked")
    private T[] createArray(int capacity) {
        return (T[]) new Object[capacity];
    }

    private int getArraySize() {
        ArrayList<T> current = this;
        while (current.parent != null) {
            current = current.parent;
        }
        return current.size();
    }

    private void setArray(T[] array) {
        this.array = array;
    }

    private void setParent(ArrayList<T> parent) {
        this.parent = parent;
    }

    private void setModCount(ModCount modCount) {
        this.modCount = modCount;
    }

    private void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    private void setOffset(int offset) {
        this.offset = offset;
    }
}