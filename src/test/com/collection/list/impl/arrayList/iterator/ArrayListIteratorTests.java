package test.com.collection.list.impl.arrayList.iterator;

import main.com.collection.list.impl.ArrayList;
import main.com.collection.list.impl.LinkedList;
import org.junit.Before;
import org.junit.Test;
import test.util.Helper;

import static org.junit.Assert.assertEquals;

/**
 * Created by ChaosFire on 24.9.2017 г.
 */
public class ArrayListIteratorTests {

    private ArrayList<Integer> list;
    private LinkedList<Integer> copyList;

    public ArrayListIteratorTests() {
        this.list = new ArrayList<>();
        this.copyList = new LinkedList<>();
    }

    @Before
    public void setConditions() {
        for (int i = 0; i < 100; i++) {
            this.list.add(i);
            this.copyList.add(i);
        }
    }

    @Test
    public void listHasElements_shouldIterateInCorrectOrder() {
        assertEquals(Helper.printCollection(this.copyList), Helper.printCollection(this.list));
    }
}