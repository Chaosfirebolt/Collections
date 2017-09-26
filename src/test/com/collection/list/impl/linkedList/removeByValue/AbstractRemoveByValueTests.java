package test.com.collection.list.impl.linkedList.removeByValue;

import main.com.collection.list.contract.LinkList;
import org.junit.Before;
import org.junit.Test;
import test.util.Helper;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by ChaosFire on 2.9.2017 г.
 */
public abstract class AbstractRemoveByValueTests {

    private LinkList<String> testList;
    private ArrayList<String> copyList;

    AbstractRemoveByValueTests(LinkList<String> testList) {
        this.testList = testList;
        this.copyList = new ArrayList<>();
    }

    @Before
    public void initialize() {
        for (int i = 0; i < 10; i++) {
            String element = Integer.toString(i);
            this.testList.add(element);
            this.copyList.add(element);
        }
    }

    @Test
    public void elementExists_ShouldReturnTrue_Test1() {
        boolean actual = this.testList.remove("0");
        assertTrue(actual);
    }

    @Test
    public void elementExists_ShouldReturnTrue_Test2() {
        boolean actual = this.testList.remove("9");
        assertTrue(actual);
    }

    @Test
    public void elementExists_ShouldReturnTrue_Test3() {
        boolean actual = this.testList.remove("6");
        assertTrue(actual);
    }

    @Test
    public void elementExists_ShouldBeRemoved_Test1() {
        String element = "0";
        this.testList.remove(element);
        this.copyList.remove(element);
        assertEquals(Helper.printCollection(this.copyList), Helper.printCollection(this.testList));
    }

    @Test
    public void elementExists_ShouldBeRemoved_Test2() {
        String element = "9";
        this.testList.remove(element);
        this.copyList.remove(element);
        assertEquals(Helper.printCollection(this.copyList), Helper.printCollection(this.testList));
    }

    @Test
    public void elementExists_ShouldBeRemoved_Test3() {
        String element = "4";
        this.testList.remove(element);
        this.copyList.remove(element);
        assertEquals(Helper.printCollection(this.copyList), Helper.printCollection(this.testList));
    }

    @Test
    public void elementDoesNotExist_ShouldReturnFalse_Test1() {
        String element = "100";
        boolean actual = this.testList.remove(element);
        assertFalse(actual);
    }

    @Test
    public void elementDoesNotExist_ShouldReturnFalse_Test2() {
        String element = "";
        boolean actual = this.testList.remove(element);
        assertFalse(actual);
    }

    @Test
    public void elementDoesNotExist_ShouldReturnFalse_Test3() {
        String element = "ivan";
        boolean actual = this.testList.remove(element);
        assertFalse(actual);
    }
}