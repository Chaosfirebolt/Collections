package test.com.collection.list.impl.arrayList.addElementAtIndex;

import main.com.collection.list.impl.ArrayList;
import org.junit.Before;
import org.junit.Test;
import test.util.Helper;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by ChaosFire on 24.9.2017 г.
 */
public class ArrayListAddAtIndexTests {

    private ArrayList<Integer> list;

    public ArrayListAddAtIndexTests() {
        this.list = new ArrayList<>();
    }

    @Before
    public void setCondition() {
        for (int i = 0; i < 10; i++) {
            this.list.add(i);
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addAtNegativeIndex_ShouldThrowException() {
        this.list.add(-1, 10);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addAtIndexHigherThanSize_ShouldThrowException() {
        this.list.add(11, 10);
    }

    @Test
    public void addAtValidIndex_ShouldAddAtRightPosition_Test1() {
        this.list.add(0, 10);
        assertEquals(Helper.printCollection(Arrays.asList(10, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9)), Helper.printCollection(this.list));
    }

    @Test
    public void addAtValidIndex_ShouldAddAtRightPosition_Test2() {
        this.list.add(10, 10);
        assertEquals(Helper.printCollection(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)), Helper.printCollection(this.list));
    }

    @Test
    public void addAtValidIndex_ShouldAddAtRightPosition_Test3() {
        this.list.add(4, 10);
        assertEquals(Helper.printCollection(Arrays.asList(0, 1, 2, 3, 10, 4, 5, 6, 7, 8, 9)), Helper.printCollection(this.list));
    }
}