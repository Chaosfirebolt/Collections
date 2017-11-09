package test.com.collection.queue.impl.iterator;

import main.com.collection.queue.impl.PriorityQueue;
import org.junit.Before;
import org.junit.Test;
import test.util.Helper;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by ChaosFire on 19.10.2017 г.
 */
public class PriorityQueueIteratorTests {

    private PriorityQueue<Integer> queue;

    public PriorityQueueIteratorTests() {
        this.queue = new PriorityQueue<>(Integer.class);
    }

    @Before
    public void setUp() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        for (Integer number : numbers) {
            this.queue.insert(number);
        }
    }

    @Test
    public void iteration_FirstElementShouldBeCorrect() {
        List<Integer> list = new ArrayList<>();
        for (Integer integer : this.queue) {
            list.add(integer);
        }
        int expected = 0;
        int actual = list.get(0);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldIterateThroughAllElements() {
        List<Integer> actual = new ArrayList<>();
        for (Integer integer : this.queue) {
            actual.add(integer);
        }
        actual.sort(Comparator.naturalOrder());
        List<Integer> expected = new ArrayList<>();
        for (int i = 0; i < this.queue.size(); i++) {
            expected.add(i);
        }
        assertEquals(Helper.printCollection(expected), Helper.printCollection(actual));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void iteratorRemove_ShouldThrowException() {
        Iterator<Integer> iterator = this.queue.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            iterator.next();
            if (count == 5) {
                iterator.remove();
            }
            count++;
        }
    }

    @Test(expected = ConcurrentModificationException.class)
    public void elementIsInsertedDuringIteration_ShouldThrowException() {
        int element = 10;
        for (Integer integer : this.queue) {
            if (integer == 0) {
                this.queue.insert(element);
            }
        }
    }

    @Test(expected = ConcurrentModificationException.class)
    public void pollMethodCallDuringIteration_ShouldThrowException() {
        for (Integer integer : this.queue) {
            if (integer == 1) {
                this.queue.poll();
            }
        }
    }

    @Test
    @SuppressWarnings("unused")
    public void peekMethodCallDuringIteration_IterationShouldContinue() {
        for (Integer integer : this.queue) {
            this.queue.peek();
        }
    }
}