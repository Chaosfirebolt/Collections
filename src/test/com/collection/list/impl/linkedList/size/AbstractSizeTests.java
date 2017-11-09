package test.com.collection.list.impl.linkedList.size;

import main.com.collection.list.contract.LinkList;
import org.junit.Test;

import java.util.Iterator;
import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * Created by ChaosFire on 2.9.2017 г.
 */
public abstract class AbstractSizeTests {

    private LinkList<Integer> testList;

    AbstractSizeTests(LinkList<Integer> testList) {
        this.testList = testList;
    }

    private void addElements() {
        for (int element = 0; element < 10; element++) {
            this.testList.add(element);
        }
    }

    @Test
    public void sizeShouldBeZeroAfterInitialization() {
        int expected = 0;
        int actual = this.testList.size();
        assertEquals(expected, actual);
    }

    @Test
    public void sizeShouldBeRightAfterEachElementAdding() {
        int expected = 1;
        for (int element = 0; element < 10; element++) {
            this.testList.add(element);
            int actual = this.testList.size();
            assertEquals(expected, actual);
            expected++;
        }
    }

    @Test
    public void sizeShouldBeRightAfterEachRemoval_Test1() {
        this.addElements();
        int expected = this.testList.size();
        int size = this.testList.size();
        for (int i = 0; i < size; i++) {
            this.testList.removeFirst();
            expected--;
            int actual = this.testList.size();
            assertEquals(expected, actual);
        }
    }

    @Test
    public void sizeShouldBeRightAfterEachRemoval_Test2() {
        this.addElements();
        int expected = this.testList.size();
        int size = this.testList.size();
        for (int i = 0; i < size; i++) {
            this.testList.removeLast();
            expected--;
            int actual = this.testList.size();
            assertEquals(expected, actual);
        }
    }

    @Test
    public void removeAllElementsNormalIteration_SizeShouldBeCorrect() {
        this.addElements();
        Iterator<Integer> iterator = this.testList.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
        int expectedSize = 0;
        assertEquals(expectedSize, this.testList.size());
    }

    @Test
    public void removeAllElementsReverseIteration_SizeShouldBeCorrect() {
        this.addElements();
        Iterator<Integer> iterator = this.testList.descendingIterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
        int expectedSize = 0;
        assertEquals(expectedSize, this.testList.size());
    }

    @Test
    public void removeElementsDuringNormalIteration_SizeShouldBeCorrect() {
        this.addElements();
        int toRemove = 4;
        int expectedSize = this.testList.size() - toRemove;
        Iterator<Integer> iterator = this.testList.iterator();
        this.removeElements(iterator, toRemove);
        assertEquals(expectedSize, this.testList.size());
    }

    @Test
    public void removeElementsDuringReverseIteration_SizeShouldBeCorrect() {
        this.addElements();
        int toRemove = 4;
        int expectedSize = this.testList.size() - toRemove;
        Iterator<Integer> iterator = this.testList.descendingIterator();
        this.removeElements(iterator, toRemove);
        assertEquals(expectedSize, this.testList.size());
    }

    @Test
    public void removeRandomNumberOfElementsDuringNormalIteration_SizeShouldBeCorrect() {
        this.addElements();
        int toRemove = new Random().nextInt(this.testList.size()) + 1;
        int expectedSize = this.testList.size() - toRemove;
        this.removeElements(this.testList.iterator(), toRemove);
        assertEquals(expectedSize, this.testList.size());
    }

    @Test
    public void removeRandomNumberOfElementsDuringReverseIteration_SizeShouldBeCorrect() {
        this.addElements();
        int toRemove = new Random().nextInt(this.testList.size()) + 1;
        int expectedSize = this.testList.size() - toRemove;
        this.removeElements(this.testList.descendingIterator(), toRemove);
        assertEquals(expectedSize, this.testList.size());
    }

    private void removeElements(Iterator<Integer> iterator, int toRemove) {
        int removed = 0;
        while (iterator.hasNext()) {
            iterator.next();
            if (removed < toRemove) {
                iterator.remove();
                removed++;
            }
        }
    }
}