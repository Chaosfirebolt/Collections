package test.com.collection.queue.impl.clear;

import main.com.collection.queue.impl.PriorityQueue;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by ChaosFire on 3.9.2017 г.
 */
public class PriorityQueueClearTests {

    private PriorityQueue<Integer> priorityQueue;

    public PriorityQueueClearTests() {
        this.priorityQueue = new PriorityQueue<>(Integer.class, Comparator.reverseOrder());
    }

    @Before
    public void setUp() {
        int count = 100;
        List<Integer> elements = new ArrayList<>(count);
        for (int element = 0; element < count; element++) {
            elements.add(count);
        }
        Collections.shuffle(elements);
        for (Integer element : elements) {
            this.priorityQueue.insert(element);
        }
    }

    @Test
    public void sizeShouldBeZero() {
        this.priorityQueue.clear();
        int expected = 0;
        int actual = this.priorityQueue.size();
        assertEquals(expected, actual);
    }

    @Test
    public void underlyingListShouldBeEmpty() throws ReflectiveOperationException {
        this.priorityQueue.clear();
        Class clazz = this.priorityQueue.getClass();
        Field field = clazz.getSuperclass().getDeclaredField("heap");
        field.setAccessible(true);
        List heap = (List) field.get(this.priorityQueue);
        assertTrue(heap.isEmpty());
    }
}