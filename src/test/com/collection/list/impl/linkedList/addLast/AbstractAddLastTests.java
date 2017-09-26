package test.com.collection.list.impl.linkedList.addLast;

import main.com.collection.list.contract.LinkList;
import org.junit.Test;
import test.util.Helper;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by ChaosFire on 2.9.2017 г.
 */
public abstract class AbstractAddLastTests {

    private LinkList<Integer> testList;
    private ArrayList<Integer> copyList;

    AbstractAddLastTests(LinkList<Integer> testList) {
        this.testList = testList;
        this.copyList = new ArrayList<>();
    }

    @Test
    public void addOneElement_ShouldContainIt() {
        int element = 0;
        this.testList.addLast(element);
        this.copyList.add(element);
        assertEquals(Helper.printCollection(this.copyList), Helper.printCollection(this.testList));
    }

    @Test
    public void addTwoElements_ShouldContainThem() {
        int first = 0;
        int second = 1;

        this.testList.addLast(first);
        this.testList.addLast(second);
        this.copyList.add(first);
        this.copyList.add(second);

        assertEquals(Helper.printCollection(this.copyList), Helper.printCollection(this.testList));
    }

    @Test
    public void addMany_ShouldContainAll() {
        for (int element = 0; element < 10; element++) {
            this.testList.addLast(element);
            this.copyList.add(element);
        }
        assertEquals(Helper.printCollection(this.copyList), Helper.printCollection(this.testList));
    }
}