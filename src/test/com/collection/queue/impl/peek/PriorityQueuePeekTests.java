package test.com.collection.queue.impl.peek;

import main.com.collection.queue.impl.PriorityQueue;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by ChaosFire on 3.9.2017 г.
 */
public class PriorityQueuePeekTests {

    private PriorityQueue<Integer> priorityQueue;
    private Random random;

    public PriorityQueuePeekTests() {
        this.priorityQueue = new PriorityQueue<>(Integer.class, Comparator.reverseOrder());
        this.random = new Random();
    }

    private void setUp(int count) {
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
        this.priorityQueue.peek();
    }

    @Test
    public void hasElements_ShouldReturnRight() {
        int count = 100;
        this.setUp(count);
        Integer expected = count - 1;
        Integer actual = this.priorityQueue.peek();
        assertEquals(expected, actual);
    }

    @Test
    public void randomNumberOfElementsWereRemoved_ShouldReturnRight_Test1() {
        int count = 10;
        this.testPeekRandomRemoved(count);
    }

    @Test
    public void randomNumberOfElementsWereRemoved_ShouldReturnRight_Test2() {
        int count = 100;
        this.testPeekRandomRemoved(count);
    }

    @Test
    public void randomNumberOfElementsWereRemoved_ShouldReturnRight_Test3() {
        int count = 345;
        this.testPeekRandomRemoved(count);
    }

    @Test
    public void randomNumberOfElementsWereRemoved_ShouldReturnRight_Test4() {
        int count = 6235;
        this.testPeekRandomRemoved(count);
    }

    private void testPeekRandomRemoved(int count) {
        this.setUp(count);
        int removed = this.random.nextInt(this.priorityQueue.size());
        for (int i = 0; i < removed; i++) {
            this.priorityQueue.poll();
        }
        Integer expected = count - 1 - removed;
        Integer actual = this.priorityQueue.peek();
        assertEquals(expected, actual);
    }
}