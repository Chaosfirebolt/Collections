package test.com.collection.list.impl.linkedList.clear;

import main.com.collection.list.impl.MapLinkedList;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * Created by ChaosFire on 2.9.2017 г.
 */
public class MapLinkedListClearTests extends AbstractClearTests {

    public MapLinkedListClearTests() {
        super(new MapLinkedList<>());
    }

    @Test
    public void valueNodeMapShouldBeEmpty() throws ReflectiveOperationException {
        super.testList.clear();
        Field map = MapLinkedList.class.getDeclaredField("valueNodeMap");
        map.setAccessible(true);
        Map valueNodeMap = (Map) map.get(super.testList);
        assertTrue(valueNodeMap.isEmpty());
    }
}