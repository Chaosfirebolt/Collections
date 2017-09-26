package test.com.collection.queue.impl.insert;

import main.com.collection.queue.impl.PriorityQueue;
import org.junit.Test;
import test.util.PersonTest;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by ChaosFire on 3.9.2017 г.
 */
public class PriorityQueueInsertTests {

    private PriorityQueue<PersonTest> priorityQueue;

    public PriorityQueueInsertTests() {
        this.priorityQueue = new PriorityQueue<>(PersonTest.class, Comparator.comparing(PersonTest::getName));
    }

    @Test(expected = NullPointerException.class)
    public void insertNull_ShouldThrowException() {
        this.priorityQueue.insert(null);
    }

    @Test
    public void insertElements_AllShouldBeInserted() {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            PersonTest person = new PersonTest(Integer.toString(i), i);
            this.priorityQueue.insert(person);
            set.add(person.toString());
        }

        int count = 0;
        for (PersonTest person : this.priorityQueue) {
            assertTrue(set.contains(person.toString()));
            count++;
        }
        int expected = 10;
        assertEquals(expected, count);
    }
}