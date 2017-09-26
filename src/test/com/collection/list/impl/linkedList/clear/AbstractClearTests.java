package test.com.collection.list.impl.linkedList.clear;

import main.com.collection.list.contract.LinkList;
import main.com.collection.list.impl.LinkedList;
import org.junit.Before;
import org.junit.Test;
import test.util.Helper;

import java.lang.reflect.Field;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by ChaosFire on 2.9.2017 г.
 */
public abstract class AbstractClearTests {

    protected LinkList<Integer> testList;
    private ArrayList<Integer> copyList;

    AbstractClearTests(LinkList<Integer> testList) {
        this.testList = testList;
        this.copyList = new ArrayList<>();
    }

    @Before
    public void initialize() {
        for (int element = 0; element < 10; element++) {
            this.testList.add(element);
            this.copyList.add(element);
        }
    }

    @Test
    public void clearList_SizeShouldBeZero() {
        int expected = 0;
        this.testList.clear();
        int actual = this.testList.size();
        assertEquals(expected, actual);
    }

    @Test
    public void clearList_ShouldBeEmpty() {
        this.testList.clear();
        this.copyList.clear();
        assertEquals(Helper.printCollection(this.copyList), Helper.printCollection(this.testList));
    }

    @Test
    public void clearList_StartNodeShouldBeNull() throws ReflectiveOperationException {
        this.testList.clear();
        Field startNode = LinkedList.class.getSuperclass().getDeclaredField("firstNode");
        startNode.setAccessible(true);
        assertNull(startNode.get(this.testList));
    }

    @Test
    public void clearList_EndNodeShouldBeNull() throws ReflectiveOperationException {
        this.testList.clear();
        Field endNode = LinkedList.class.getSuperclass().getDeclaredField("lastNode");
        endNode.setAccessible(true);
        assertNull(endNode.get(this.testList));
    }
}