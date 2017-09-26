package test.com.collection.queue.impl.size;

import main.com.collection.queue.impl.PriorityQueue;
import org.junit.Test;
import test.util.PersonTest;

import java.util.Comparator;

import static org.junit.Assert.assertEquals;

/**
 * Created by ChaosFire on 3.9.2017 г.
 */
public class PriorityQueueSizeTests {

    private PriorityQueue<PersonTest> priorityQueue;

    public PriorityQueueSizeTests() {
        this.priorityQueue = new PriorityQueue<>(PersonTest.class, Comparator.comparing(PersonTest::getAge));
    }

    @Test
    public void sizeShouldBeZeroAfterInitialization() {
        int expected = 0;
        int actual = this.priorityQueue.size();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldBeRightAfterEachInsert() {
        int expected = 1;
        for (int i = 0; i < 10; i++) {
            this.priorityQueue.insert(new PersonTest(Integer.toString(i), i));
            assertEquals(expected, this.priorityQueue.size());
            expected++;
        }
    }

    @Test
    public void shouldBeRightAfterEachPoll() {
        for (int i = 0; i < 10; i++) {
            this.priorityQueue.insert(new PersonTest(Integer.toString(i), i));
        }
        int expected = 9;
        for (int i = 0; i < 10; i++) {
            this.priorityQueue.poll();
            assertEquals(expected, this.priorityQueue.size());
            expected--;
        }
    }
}