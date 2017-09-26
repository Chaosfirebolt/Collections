package test.com.collection.list.impl.arrayList.removeAtIndex;

import main.com.collection.list.impl.ArrayList;
import main.com.collection.list.impl.LinkedList;
import org.junit.Before;
import org.junit.Test;
import test.util.Helper;

import static org.junit.Assert.assertEquals;

/**
 * Created by ChaosFire on 24.9.2017 г.
 */
public class ArrayListRemoveAtIndexTests {

    private ArrayList<Integer> list;
    private LinkedList<Integer> copyList;

    public ArrayListRemoveAtIndexTests() {
        this.list = new ArrayList<>();
        this.copyList = new LinkedList<>();
    }

    @Before
    public void setCondition() {
        for (int i = 0; i < 10; i++) {
            this.list.add(i);
            this.copyList.add(i);
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeAtNegativeIndex_ShouldThrowException() {
        this.list.remove(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeAtIndexEqualToSize_ShouldThrowException() {
        this.list.remove(10);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeAtIndexHigherThanSize_ShouldThrowException() {
        this.list.remove(11);
    }

    @Test
    public void removeAtValidIndex_ElementShouldBeRemoved_Test1() {
        int index = 0;
        this.list.remove(index);
        this.copyList.remove(index);
        assertEquals(Helper.printCollection(this.copyList), Helper.printCollection(this.list));
    }

    @Test
    public void removeAtValidIndex_ElementShouldBeRemoved_Test2() {
        int index = 9;
        this.list.remove(index);
        this.copyList.remove(index);
        assertEquals(Helper.printCollection(this.copyList), Helper.printCollection(this.list));
    }

    @Test
    public void removeAtValidIndex_ElementShouldBeRemoved_Test3() {
        int index = 6;
        this.list.remove(index);
        this.copyList.remove(index);
        assertEquals(Helper.printCollection(this.copyList), Helper.printCollection(this.list));
    }

    @Test
    public void removeAtValidIndex_ShouldReturnRightValue_Test1() {
        int index = 0;
        int actual = this.list.remove(index);
        assertEquals(0, actual);
    }

    @Test
    public void removeAtValidIndex_ShouldReturnRightValue_Test2() {
        int index = 9;
        int actual = this.list.remove(index);
        assertEquals(9, actual);
    }

    @Test
    public void removeAtValidIndex_ShouldReturnRightValue_Test3() {
        int index = 6;
        int actual = this.list.remove(index);
        assertEquals(6, actual);
    }

    @Test
    public void removeAllElementsFromZeroIndex_ShouldReturnRightValues() {
        int index = 0;
        for (int expected = 0; expected < 10; expected++) {
            int actual = this.list.remove(index);
            assertEquals(expected, actual);
        }
    }
}