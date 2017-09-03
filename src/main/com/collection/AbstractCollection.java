package main.com.collection;

/**
 * Created by ChaosFire on 30.6.2017 г
 */
public abstract class AbstractCollection {

    private static final int INITIAL_SIZE = 0;

    private int size;

    protected AbstractCollection() {
        super();
        this.setSize(INITIAL_SIZE);
    }

    protected void incrementSize() {
        this.size++;
    }

    protected void decrementSize() {
        this.size--;
    }

    protected void clearSize() {
        this.setSize(INITIAL_SIZE);
    }

    protected int getSize() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }
}