package test.com.collection.list.impl.arrayList.contains;

import main.com.collection.list.impl.ArrayList;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by ChaosFire on 24.9.2017 г.
 */
public class ArrayListContainsTests {

    private ArrayList<Integer> list;

    public ArrayListContainsTests() {
        this.list = new ArrayList<>();
    }

    @Before
    public void setCondition() {
        for (int element = 0; element < 1000; element++) {
            this.list.add(element);
        }
    }

    @Test
    public void allExistingElements_ShouldReturnTrueEveryTime() {
        for (int element = 999; element >= 0; element--) {
            assertTrue(this.list.contains(element));
        }
    }

    @Test
    public void nonExistingElement_ShouldReturnFalse() {
        assertFalse(this.list.contains(1000));
    }

    @Test
    public void elementWasRemoved_ShouldReturnFalse() {
        Integer element = 456;
        this.list.remove(element);
        assertFalse(this.list.contains(element));
    }

    @Test
    public void removeAllElements_ShouldReturnFalseAfterRemoval() {
        for (Integer element = 999; element >= 0; element--) {
            this.list.remove(element);
            assertFalse(this.list.contains(element));
        }
    }

    @Test
    public void elementWasRemovedThenAddedAgain_ShouldReturnTrue() {
        Integer element = 329;
        this.list.remove(element);
        assertFalse(this .list.contains(element));

        this.list.add(element);
        assertTrue(this.list.contains(element));
    }
}