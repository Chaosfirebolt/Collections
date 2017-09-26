package test.com.collection.list.impl.linkedList.addFirst;

import main.com.collection.list.contract.LinkList;
import org.junit.Test;
import test.util.Helper;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by ChaosFire on 2.9.2017 г.
 */
public abstract class AbstractAddFirstTests {

    private LinkList<Integer> testList;
    private ArrayList<Integer> copyList;

    AbstractAddFirstTests(LinkList<Integer> testList) {
        this.testList = testList;
        this.copyList = new ArrayList<>();
    }

    @Test
    public void addOneElement_ShouldContainIt() {
        int element = 0;
        this.testList.addFirst(element);
        this.copyList.add(0, element);
        assertEquals(Helper.printCollection(this.copyList), Helper.printCollection(this.testList));
    }

    @Test
    public void addTwoElements_ShouldContainThem() {
        int first = 0;
        int second = 1;
        int position = 0;

        this.testList.addFirst(first);
        this.testList.addFirst(second);
        this.copyList.add(position, first);
        this.copyList.add(position, second);

        assertEquals(Helper.printCollection(this.copyList), Helper.printCollection(this.testList));
    }

    @Test
    public void addMany_ShouldContainAll() {
        int position = 0;
        for (int element = 0; element < 10; element++) {
            this.testList.addFirst(element);
            this.copyList.add(position, element);
        }
        assertEquals(Helper.printCollection(this.copyList), Helper.printCollection(this.testList));
    }
}