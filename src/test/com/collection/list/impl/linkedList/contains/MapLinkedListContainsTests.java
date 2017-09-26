package test.com.collection.list.impl.linkedList.contains;

import main.com.collection.list.impl.MapLinkedList;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

/**
 * Created by ChaosFire on 2.9.2017 г.
 */
public class MapLinkedListContainsTests extends AbstractContainsTests {

    public MapLinkedListContainsTests() {
        super(new MapLinkedList<>());
    }

    @Test
    public void elementWasRemoved_ShouldReturnFalse() {
        super.addElements();
        Integer element = 7;
        super.testList.remove(element);
        assertFalse(super.testList.contains(element));
    }
}