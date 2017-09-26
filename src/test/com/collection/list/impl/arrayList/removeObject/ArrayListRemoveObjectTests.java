package test.com.collection.list.impl.arrayList.removeObject;

import main.com.collection.list.impl.ArrayList;
import main.com.collection.list.impl.LinkedList;
import org.junit.Before;
import org.junit.Test;
import test.util.Helper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by ChaosFire on 24.9.2017 г.
 */
public class ArrayListRemoveObjectTests {

    private ArrayList<String> list;
    private LinkedList<String> copyList;

    public ArrayListRemoveObjectTests() {
        this.list = new ArrayList<>();
        this.copyList = new LinkedList<>();
    }

    @Before
    public void setCondition() {
        for (int i = 0; i < 10; i++) {
            String element = Integer.toString(i);
            this.list.add(element);
            this.copyList.add(element);
        }
    }

    @Test
    public void removeNonExistingElement_ShouldReturnFalse_Test1() {
        String element = "ivan";
        boolean actual = this.list.remove(element);
        assertFalse(actual);
    }

    @Test
    public void removeNonExistingElement_ShouldReturnFalse_Test2() {
        String element = "10";
        boolean actual = this.list.remove(element);
        assertFalse(actual);
    }

    @Test
    public void removeNonExistingElement_ShouldReturnFalse_Test3() {
        String element = "-1";
        boolean actual = this.list.remove(element);
        assertFalse(actual);
    }

    @Test
    public void removeExistingElementShouldReturnTrue() {
        String element = "9";
        boolean actual = this.list.remove(element);
        assertTrue(actual);
    }

    @Test
    public void removeExistingElement_ShouldBeRemoved() {
        String element = "4";
        this.list.remove(element);
        this.copyList.remove(element);
        assertEquals(Helper.printCollection(this.copyList), Helper.printCollection(this.list));
    }

    @Test
    public void removeAllExistingElements_ShouldReturnTrueAfterEach() {
        for (int i = 0; i < 10; i++) {
            String element = Integer.toString(i);
            assertTrue(this.list.remove(element));
        }
    }

    @Test
    public void removeAllExistingElements_EachShouldBeRemoved() {
        for (int i = 0; i < 10; i++) {
            String element = Integer.toString(i);
            this.list.remove(element);
            this.copyList.remove(element);
            assertEquals(Helper.printCollection(this.copyList), Helper.printCollection(this.list));
        }
    }
}