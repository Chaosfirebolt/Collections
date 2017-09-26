package test.com.collection.list.impl.arrayList.addElement;

import main.com.collection.list.impl.ArrayList;
import main.com.collection.list.impl.LinkedList;
import org.junit.Test;
import test.util.Helper;

import static org.junit.Assert.assertEquals;

/**
 * Created by ChaosFire on 24.9.2017 г.
 */
public class ArrayListAddTests {

    private ArrayList<Integer> list;
    private LinkedList<Integer> copyList;

    public ArrayListAddTests() {
        this.list = new ArrayList<>();
        this.copyList = new LinkedList<>();
    }

    @Test
    public void addElements_ShouldBeAdded_Test1() {
        for (int i = 0; i < 10; i++) {
            this.list.add(i);
            this.copyList.add(i);
        }
        assertEquals(Helper.printCollection(this.copyList), Helper.printCollection(this.list));
    }

    @Test
    public void addElements_ShouldBeAdded_Test2() {
        for (int i = 10; i >= 0; i--) {
            this.list.add(i);
            this.copyList.add(i);
        }
        assertEquals(Helper.printCollection(this.copyList), Helper.printCollection(this.list));
    }
}