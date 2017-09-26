package test.com.collection.list.impl.arrayList.subListCopy;

import main.com.collection.list.contract.IndexList;
import main.com.collection.list.impl.ArrayList;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by ChaosFire on 24.9.2017 г.
 */
public class ArrayListSubListCopyTests {

    private ArrayList<Integer> list;

    public ArrayListSubListCopyTests() {
        this.list = new ArrayList<>();
    }

    @Before
    public void setConditions() {
        for (int i = 0; i < 100; i++) {
            this.list.add(i);
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void fromIndexIsNegative_ToIndexIsValid_ShouldThrowException() {
        this.list.subListCopy(-1, 20);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void fromIndexIsEqualToSize_ToIndexIsValid_ShouldThrowException() {
        this.list.subListCopy(100, 20);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void fromIndexIsHigherThanSize_ToIndexIsValid_ShouldThrowException() {
        this.list.subListCopy(110, 20);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void fromIndexIsValid_ToIndexIsNegative_ShouldThrowException() {
        this.list.subListCopy(10, -20);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void fromIndexIsValid_ToIndexIsHigherThanSize_ShouldThrowException() {
        this.list.subListCopy(10, 101);
    }

    @Test(expected = IllegalArgumentException.class)
    public void bothAreValid_FromIndexEqualsToIndex_ShouldThrowException() {
        this.list.subListCopy(10, 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void bothAreValid_FromIndexIsHigherThanToIndex_ShouldThrowException() {
        this.list.subListCopy(10, 9);
    }

    @Test
    public void bothIndexesAreValid_ShouldReturnCorrectList() {
        IndexList<Integer> expected = new ArrayList<>();
        for (int i = 10; i < 20; i++) {
            expected.add(i);
        }
        IndexList<Integer> actual = this.list.subListCopy(10, 20);
        for (int i = 0; i < 10; i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }

    @Test
    public void bothIndexesAreValid_SizeShouldBeCorrect() {
        int expected = 10;
        IndexList<Integer> actual = this.list.subListCopy(10, 20);
        assertEquals(expected, actual.size());
    }
}