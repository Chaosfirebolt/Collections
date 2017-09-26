package main.com.collection.list.impl;

import java.util.ConcurrentModificationException;

/**
 * Created by ChaosFire on 26.9.2017 г.
 */
class ModCount {

    private static final int DEFAULT_COUNT = 0;

    private int count;
    private ModCount parentModCount;
    private int finalCount;

    private ModCount(int count, ModCount parentModCount, int finalCount) {
        this.setCount(count);
        this.setParentModCount(parentModCount);
        this.setFinalCount(finalCount);
    }

    ModCount() {
        this(DEFAULT_COUNT, null, DEFAULT_COUNT);
    }

    ModCount(ModCount parentModCount) {
        this(DEFAULT_COUNT, parentModCount, parentModCount.count);
    }

    void incrementCount() {
        this.count++;
    }

    void validate() {
        if (this.parentModCount != null && this.parentModCount.count != this.finalCount) {
            throw new ConcurrentModificationException();
        }
    }

    private void setCount(int count) {
        this.count = count;
    }

    private void setParentModCount(ModCount parentModCount) {
        this.parentModCount = parentModCount;
    }

    private void setFinalCount(int finalCount) {
        this.finalCount = finalCount;
    }
}