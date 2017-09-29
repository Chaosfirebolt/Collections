package main.com.collection.map.impl;

import java.util.ConcurrentModificationException;

/**
 * Created by ChaosFire on 29.9.2017 г.
 */
class ModCount {

    private static final int DEFAULT_COUNT = 0;
    private static final int DEFAULT_EXPECTED_COUNT = -1;

    private int modCount;
    private ModCount parentModCount;
    private int expectedModCount;

    private ModCount(int modCount, ModCount parentModCount, int expectedModCount) {
        this.setModCount(modCount);
        this.setParentModCount(parentModCount);
        this.setExpectedModCount(expectedModCount);
    }

    ModCount() {
        this(DEFAULT_COUNT, null, DEFAULT_EXPECTED_COUNT);
    }

    ModCount(ModCount parentModCount) {
        this(DEFAULT_COUNT, parentModCount, DEFAULT_EXPECTED_COUNT);
    }

    void updateCount() {
        this.modCount++;
    }

    void activateValidation() {
        this.setExpectedModCount(this.modCount);
    }

    void deactivateValidation() {
        this.setExpectedModCount(DEFAULT_EXPECTED_COUNT);
    }

    void validate() {
        if (this.expectedModCount != -1 && this.modCount != this.expectedModCount) {
            throw new ConcurrentModificationException();
        }
    }

    private void setModCount(int modCount) {
        this.modCount = modCount;
    }

    private void setParentModCount(ModCount parentModCount) {
        this.parentModCount = parentModCount;
    }

    private void setExpectedModCount(int expectedModCount) {
        this.expectedModCount = expectedModCount;
    }
}