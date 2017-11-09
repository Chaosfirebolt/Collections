package main.com.collection.map.impl;

import java.util.ConcurrentModificationException;

/**
 * Created by ChaosFire on 29.9.2017 г.
 */
class ModCount {

    private static final int DEFAULT_COUNT = 0;
    private static final int DEFAULT_EXPECTED_COUNT = -1;

    private int count;
    private ModCount parentModCount;
    private int expectedParentCount;

    private ModCount(int count, ModCount parentModCount, int expectedParentCount) {
        this.setCount(count);
        this.setParentModCount(parentModCount);
        this.setExpectedParentCount(expectedParentCount);
    }

    ModCount() {
        this(DEFAULT_COUNT, null, DEFAULT_EXPECTED_COUNT);
    }

    ModCount(ModCount parentModCount) {
        this(DEFAULT_COUNT, parentModCount, parentModCount.count);
    }

    int getCount() {
        return this.count;
    }

    void validateParentModCount() {
        if (this.parentModCount.count != expectedParentCount) {
            throw new ConcurrentModificationException();
        }
    }

    void incrementCount() {
        this.count++;
    }

    private void setCount(int count) {
        this.count = count;
    }

    private void setParentModCount(ModCount parentModCount) {
        this.parentModCount = parentModCount;
    }

    private void setExpectedParentCount(int expectedParentCount) {
        this.expectedParentCount = expectedParentCount;
    }
}