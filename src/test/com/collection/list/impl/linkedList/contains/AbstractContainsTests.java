package test.com.collection.list.impl.linkedList.contains;

import main.com.collection.list.contract.LinkList;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by ChaosFire on 2.9.2017 г.
 */
public abstract class AbstractContainsTests {

    protected LinkList<Integer> testList;

    AbstractContainsTests(LinkList<Integer> testList) {
        this.testList = testList;
    }

    void addElements() {
        for (int element = 0; element < 10; element++) {
            this.testList.add(element);
        }
    }

    @Test
    public void listIsEmpty_ShouldReturnFalse() {
        assertFalse(this.testList.contains(5));
    }

    @Test
    public void listDoesNotContainElement_ShouldReturnFalse() {
        this.addElements();
        assertFalse(this.testList.contains(19));
    }

    @Test
    public void listContainsElement_ShouldReturnTrue() {
        this.addElements();
        assertTrue(this.testList.contains(5));
    }
}