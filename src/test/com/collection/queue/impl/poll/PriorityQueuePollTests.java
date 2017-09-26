package test.com.collection.queue.impl.poll;

import main.com.collection.queue.impl.PriorityQueue;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by ChaosFire on 3.9.2017 г.
 */
public class PriorityQueuePollTests {

    private PriorityQueue<Integer> priorityQueue;

    private void initNaturalOrder() {
        this.priorityQueue = new PriorityQueue<>(Integer.class);
    }

    private void initReverseOrder() {
        this.priorityQueue = new PriorityQueue<>(Integer.class, Comparator.reverseOrder());
    }

    private void addShuffledElements(int count) {
        List<Integer> elements = new ArrayList<>(count);
        for (int element = 0; element < count; element++) {
            elements.add(element);
        }
        Collections.shuffle(elements);
        for (Integer element : elements) {
            this.priorityQueue.insert(element);
        }
    }

    @Test(expected = NoSuchElementException.class)
    public void queueIsEmpty_ShouldThrowException() {
        this.initNaturalOrder();
        this.priorityQueue.poll();
    }

    @Test
    public void shouldReturnRightElement_Test1() {
        this.initNaturalOrder();
        int count = 10;
        this.addShuffledElements(count);
        for (int expected = 0; expected < count; expected++) {
            int actual = this.priorityQueue.poll();
            assertEquals(expected, actual);
        }
    }

    @Test
    public void shouldReturnRightElement_Test2() {
        this.initReverseOrder();
        int count = 21;
        this.addShuffledElements(count);
        for (int expected = count - 1; expected >= 0; expected--) {
            int actual = this.priorityQueue.poll();
            assertEquals(expected, actual);
        }
    }

    @Test
    public void shouldReturnRightElement_Test3() {
        this.initReverseOrder();
        int count = 1000;
        this.addShuffledElements(count);
        for (int expected = count - 1; expected >= 0; expected--) {
            int actual = this.priorityQueue.poll();
            assertEquals(expected, actual);
        }
    }
}