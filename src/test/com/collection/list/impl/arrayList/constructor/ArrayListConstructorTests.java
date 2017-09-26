package test.com.collection.list.impl.arrayList.constructor;

import main.com.collection.list.impl.ArrayList;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by ChaosFire on 24.9.2017 г.
 */
public class ArrayListConstructorTests {

    @Test
    public void ctorCapacityParam_initializedListShouldNotBeNull() {
        int capacity = 100;
        ArrayList<Integer> list = new ArrayList<>(capacity);
        assertNotNull(list);
    }

    @Test
    public void ctorNoParam_initializedListShouldNotBeNull() {
        ArrayList<Integer> list = new ArrayList<>();
        assertNotNull(list);
    }
}