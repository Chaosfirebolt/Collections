package test.com.collection.list.impl.arrayList.get;

import main.com.collection.list.impl.ArrayList;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by ChaosFire on 24.9.2017 г.
 */
public class ArrayListGetTests {

    private ArrayList<Integer> list;

    public ArrayListGetTests() {
        this.list = new ArrayList<>();
    }

    @Before
    public void setConditions() {
        for (int i = 999; i >= 0; i--) {
            this.list.add(i);
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getNegativeIndex_ShouldThrowException() {
        this.list.get(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getIndexEqualToSize_ShouldThrowException() {
        this.list.get(1000);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getIndexHigherThanSize_ShouldThrowException() {
        this.list.get(1100);
    }

    @Test
    public void allIndexAreValid_ShouldReturnRightElements() {
        int expected = 0;
        for (int i = 999; i >= 0; i--) {
            int actual = this.list.get(i);
            assertEquals(expected, actual);
            expected++;
        }
    }

    @Test
    public void elementWasRemoved_ShouldReturnRightElement() {
        int index = 0;
        int expected = 999;
        int actual = this.list.get(index);
        assertEquals(expected, actual);

        this.list.remove(index);
        expected = 998;
        actual = this.list.get(index);
        assertEquals(expected, actual);
    }
}