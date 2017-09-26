package test.com.collection.list.impl.arrayList.size;

import main.com.collection.list.impl.ArrayList;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by ChaosFire on 24.9.2017 г.
 */
public class ArrayListSizeTests {

    private ArrayList<Integer> list;

    public ArrayListSizeTests() {
        this.list = new ArrayList<>();
    }

    @Test
    public void sizeShouldBeZeroAfterInitialization() {
        assertEquals(0, this.list.size());
    }

    @Test
    public void addOneElement_SizeShouldBeCorrect() {
        this.list.add(0);
        assertEquals(1, this.list.size()
        );
    }

    @Test
    public void addManyElements_SizeShouldBeCorrectAfterEachAdd() {
        int expected = 1;
        for (int i = 0; i < 1000; i++) {
            this.list.add(i);
            assertEquals(expected, this.list.size());
            expected++;
        }
    }

    @Test
    public void removeAllElements_AtZeroIndex_SizeShouldBeCorrectAfterEachRemoval() {
        this.addThousandElements();
        int index = 0;
        int expected = 999;
        for (int i = 0; i < 1000; i++) {
            this.list.remove(index);
            assertEquals(expected, this.list.size());
            expected--;
        }
    }

    @Test
    public void removeAllElements_AsObjects_SizeShouldBeCorrectAfterEachRemoval() {
        this.addThousandElements();
        int expected = 999;
        for (Integer element = 0; element < 1000; element++) {
            this.list.remove(element);
            assertEquals(expected, this.list.size());
            expected--;
        }
    }

    private void addThousandElements() {
        for (int i = 0; i < 1000; i++) {
            this.list.add(i);
        }
    }
}