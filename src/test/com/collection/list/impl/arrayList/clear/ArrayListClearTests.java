package test.com.collection.list.impl.arrayList.clear;

import main.com.collection.list.impl.ArrayList;
import org.junit.Test;
import test.util.Helper;

import static org.junit.Assert.assertEquals;

/**
 * Created by ChaosFire on 24.9.2017 г.
 */
public class ArrayListClearTests {

    private ArrayList<Integer> list;

    public ArrayListClearTests() {
        this.list = new ArrayList<>();
    }

    @Test
    public void listIsEmpty_SizeShouldRemainZeroAfterClear() {
        this.list.clear();
        int expected = 0;
        assertEquals(expected, this.list.size());
    }

    @Test
    public void listHasElements_SizeShouldBeZeroAfterClear() {
        this.addHundredElements();
        this.list.clear();
        int expected = 0;
        assertEquals(expected, this.list.size());
    }

    @Test
    public void listHasElements_ListShouldBeEmptyAfterClear() {
        this.addHundredElements();
        this.list.clear();
        String expected = "";
        assertEquals(expected, Helper.printCollection(this.list));
    }

    private void addHundredElements() {
        for (int i = 0; i < 100; i++) {
            this.list.add(i);
        }
    }
}