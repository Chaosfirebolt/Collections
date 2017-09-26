package test.com.collection.queue.impl.constructor;

import main.com.collection.queue.impl.PriorityQueue;
import org.junit.Test;
import test.util.PersonTest;

/**
 * Created by ChaosFire on 3.9.2017 г.
 */
public class PriorityQueueConstructorTests {

    @Test(expected = IllegalStateException.class)
    public void firstConstructor_ComparatorIsNullClassNotImplComparable_ShouldThrowException() {
        new PriorityQueue<>(100, PersonTest.class, null);
    }

    @Test(expected = IllegalStateException.class)
    public void secondConstructor_ComparatorIsNullClassNotImplComparable_ShouldThrowException() {
        new PriorityQueue<>(100, PersonTest.class);
    }

    @Test(expected = IllegalStateException.class)
    public void thirdConstructor_ComparatorIsNullClassNotImplComparable_ShouldThrowException() {
        new PriorityQueue<>(PersonTest.class, null);
    }

    @Test(expected = IllegalStateException.class)
    public void fourthConstructor_ComparatorIsNullClassNotImplComparable_ShouldThrowException() {
        new PriorityQueue<>(PersonTest.class);
    }
}