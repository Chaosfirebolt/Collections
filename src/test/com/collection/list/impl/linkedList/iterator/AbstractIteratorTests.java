package test.com.collection.list.impl.linkedList.iterator;

import main.com.collection.list.contract.LinkList;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by ChaosFire on 2.9.2017 г.
 */
public abstract class AbstractIteratorTests {

    private LinkList<Integer> testList;

    AbstractIteratorTests(LinkList<Integer> testList) {
        this.testList = testList;
    }

    private void addElements(int count) {
        for (int element = 0; element < count; element++) {
            this.testList.add(element);
        }
    }

    @Test
    public void shouldIterateInRightOrder_Test1() {
        this.addElements(10);
        Integer expected = 0;
        for (Integer actual : this.testList) {
            assertEquals(expected, actual);
            expected++;
        }
    }

    @Test
    public void shouldIterateInRightOrder_Test2() {
        this.addElements(17);
        Integer expected = 0;
        for (Integer actual : this.testList) {
            assertEquals(expected, actual);
            expected++;
        }
    }

    @Test
    public void shouldIterateInRightOrder_Test3() {
        this.addElements(31);
        Integer expected = 0;
        for (Integer actual : this.testList) {
            assertEquals(expected, actual);
            expected++;
        }
    }
}