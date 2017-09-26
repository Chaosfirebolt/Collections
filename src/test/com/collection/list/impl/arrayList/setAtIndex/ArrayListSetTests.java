package test.com.collection.list.impl.arrayList.setAtIndex;

import main.com.collection.list.impl.ArrayList;
import org.junit.Before;
import org.junit.Test;
import test.util.Helper;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by ChaosFire on 24.9.2017 г.
 */
public class ArrayListSetTests {

    private ArrayList<Integer> list;

    public ArrayListSetTests() {
        this.list = new ArrayList<>();
    }

    @Before
    public void setCondition() {
        for (int i = 0; i < 10; i++) {
            this.list.add(i);
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void setAtNegativeIndex_ShouldThrowException() {
        this.list.set(-1, 10);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void setAtIndexEqualToSize_ShouldThrowException() {
        this.list.set(10, 10);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void setAtIndexHigherThanSize_ShouldThrowException() {
        this.list.set(11, 10);
    }

    @Test
    public void setAtValidIndex_ShouldReplaceValue_Test1() {
        this.list.set(0, 10);
        assertEquals(Helper.printCollection(Arrays.asList(10, 1, 2, 3, 4, 5, 6, 7, 8, 9)), Helper.printCollection(this.list));
    }

    @Test
    public void setAtValidIndex_ShouldReplaceValue_Test2() {
        this.list.set(9, 10);
        assertEquals(Helper.printCollection(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 10)), Helper.printCollection(this.list));
    }

    @Test
    public void setAtValidIndex_ShouldReplaceValue_Test3() {
        this.list.set(7, 10);
        assertEquals(Helper.printCollection(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 10, 8, 9)), Helper.printCollection(this.list));
    }
}