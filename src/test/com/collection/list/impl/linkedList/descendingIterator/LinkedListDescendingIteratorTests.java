package test.com.collection.list.impl.linkedList.descendingIterator;

import main.com.collection.list.impl.LinkedList;
import org.junit.Test;

import java.util.Iterator;

/**
 * Created by ChaosFire on 2.9.2017 г.
 */
public class LinkedListDescendingIteratorTests extends AbstractDescIteratorTests {

    private LinkedList<Integer> testList;

    public LinkedListDescendingIteratorTests() {
        this.testList = new LinkedList<>();
    }

    private void addElements(int count) {
        for (int element = 0; element < count; element++) {
            this.testList.add(element);
        }
    }

    private void test(int count) {
        this.addElements(count);
        Integer expected = count - 1;
        Iterator<Integer> iterator = this.testList.descendingIterator();
        super.assertCollection(iterator, expected);
    }

    @Test
    public void shouldIterateInRightOrder_Test1() {
        int count = 10;
        this.test(count);
    }

    @Test
    public void shouldIterateInRightOrder_Test2() {
        int count = 17;
        this.test(count);
    }

    @Test
    public void shouldIterateInRightOrder_Test3() {
        int count = 31;
        this.test(count);
    }
}
