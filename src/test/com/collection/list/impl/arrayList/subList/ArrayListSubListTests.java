package test.com.collection.list.impl.arrayList.subList;

import main.com.collection.list.contract.IndexList;
import main.com.collection.list.impl.ArrayList;
import org.junit.Before;
import org.junit.Test;
import test.util.Helper;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by ChaosFire on 24.9.2017 г.
 */
public class ArrayListSubListTests {

    private ArrayList<Integer> list;

    public ArrayListSubListTests() {
        this.list = new ArrayList<>();
    }

    @Before
    public void setConditions() {
        for (int i = 0; i < 10; i++) {
            this.list.add(i);
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void fromIndexIsNegative_ToIndexIsValid_ShouldThrowException() {
        this.list.subListCopy(-1, 8);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void fromIndexIsEqualToSize_ToIndexIsValid_ShouldThrowException() {
        this.list.subListCopy(100, 8);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void fromIndexIsHigherThanSize_ToIndexIsValid_ShouldThrowException() {
        this.list.subListCopy(110, 8);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void fromIndexIsValid_ToIndexIsNegative_ShouldThrowException() {
        this.list.subListCopy(4, -20);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void fromIndexIsValid_ToIndexIsHigherThanSize_ShouldThrowException() {
        this.list.subListCopy(4, 101);
    }

    @Test(expected = IllegalArgumentException.class)
    public void bothAreValid_FromIndexEqualsToIndex_ShouldThrowException() {
        this.list.subListCopy(5, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void bothAreValid_FromIndexIsHigherThanToIndex_ShouldThrowException() {
        this.list.subListCopy(6, 5);
    }

    @Test
    public void validIndexes_ShouldReturnCorrectSubList() {
        IndexList<Integer> expectedList = this.expectedList();
        IndexList<Integer> actualSubList = this.subListFromThreeToEight();

        String expected = Helper.printCollection(expectedList);
        String actual = Helper.printCollection(actualSubList);
        assertEquals(expected, actual);
    }

    @Test
    public void validIndexes_IndexesShouldBeCorrect() {
        IndexList<Integer> expectedList = this.expectedList();
        IndexList<Integer> actualSubList = this.subListFromThreeToEight();
        for (int index = 0; index < expectedList.size(); index++) {
            int expected = expectedList.get(index);
            int actual = actualSubList.get(index);
            assertEquals(expected, actual);
        }
    }

    @Test
    public void validIndexes_AddShouldAddElementInParentList() {
        IndexList<Integer> actualSubList = this.subListFromThreeToEight();

        int newElement = 10;
        actualSubList.add(newElement);

        int[] array = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 10, 8, 9};
        ArrayList<Integer> expectedParentList = new ArrayList<>();
        for (int element : array) {
            expectedParentList.add(element);
        }
        assertEquals(Helper.printCollection(expectedParentList), Helper.printCollection(this.list));
    }

    @Test
    public void validIndexes_AddAtIndexShouldAddElementInParentList() {
        IndexList<Integer> actualSubList = this.subListFromThreeToEight();

        int newElement = 10;
        actualSubList.add(2, newElement);

        int[] array = new int[]{0, 1, 2, 3, 4, 10, 5, 6, 7, 8, 9};
        ArrayList<Integer> expectedParentList = new ArrayList<>();
        for (int element : array) {
            expectedParentList.add(element);
        }
        assertEquals(Helper.printCollection(expectedParentList), Helper.printCollection(this.list));
    }

    @Test
    public void validIndexes_SetElement_ShouldBeReplacedInParentList() {
        IndexList<Integer> actualSubList = this.subListFromThreeToEight();

        int newElement = 10;
        actualSubList.set(3, newElement);

        int[] array = new int[]{0, 1, 2, 3, 4, 5, 10, 7, 8, 9};
        ArrayList<Integer> expectedParentList = new ArrayList<>();
        for (int element : array) {
            expectedParentList.add(element);
        }
        assertEquals(Helper.printCollection(expectedParentList), Helper.printCollection(this.list));
    }

    @Test
    public void validIndexes_RemoveAtIndex_ShouldReturnCorrectElement() {
        IndexList<Integer> actualSubList = this.subListFromThreeToEight();

        int expected = 6;
        int actual = actualSubList.remove(3);
        assertEquals(expected, actual);
    }

    @Test
    public void validIndexes_RemoveAtIndex_ShouldBeRemovedFromSubList() {
        IndexList<Integer> actualSubList = this.subListFromThreeToEight();

        actualSubList.remove(3);

        int[] array = new int[]{3, 4, 5, 7};
        ArrayList<Integer> expectedSubList = new ArrayList<>();
        for (int element : array) {
            expectedSubList.add(element);
        }
        assertEquals(Helper.printCollection(expectedSubList), Helper.printCollection(actualSubList));
    }

    @Test
    public void validIndexes_RemoveAtIndex_ShouldBeRemovedFromParentList() {
        IndexList<Integer> actualSubList = this.subListFromThreeToEight();

        actualSubList.remove(3);

        int[] array = new int[]{0, 1, 2, 3, 4, 5, 7, 8, 9};
        ArrayList<Integer> expectedParentList = new ArrayList<>();
        for (int element : array) {
            expectedParentList.add(element);
        }
        assertEquals(Helper.printCollection(expectedParentList), Helper.printCollection(this.list));
    }

    @Test
    public void validIndexes_RemoveObject_ShouldReturnTrue() {
        IndexList<Integer> actualSubList = this.subListFromThreeToEight();

        Integer obj = 5;
        boolean actual = actualSubList.remove(obj);
        assertTrue(actual);
    }

    @Test
    public void validIndexes_RemoveObject_ShouldBeRemovedFromSubList() {
        IndexList<Integer> actualSubList = this.subListFromThreeToEight();

        Integer obj = 5;
        actualSubList.remove(obj);

        int[] array = new int[]{3, 4, 6, 7};
        ArrayList<Integer> expectedSubList = new ArrayList<>();
        for (int element : array) {
            expectedSubList.add(element);
        }
        assertEquals(Helper.printCollection(expectedSubList), Helper.printCollection(actualSubList));
    }

    @Test
    public void validIndexes_RemoveObject_ShouldBeRemovedFromParentList() {
        IndexList<Integer> actualSubList = this.subListFromThreeToEight();

        Integer obj = 5;
        actualSubList.remove(obj);

        int[] array = new int[]{0, 1, 2, 3, 4, 6, 7, 8, 9};
        ArrayList<Integer> expectedParentList = new ArrayList<>();
        for (int element : array) {
            expectedParentList.add(element);
        }
        assertEquals(Helper.printCollection(expectedParentList), Helper.printCollection(this.list));
    }

    @Test
    public void validIndexes_SizeShouldBeCorrect() {
        IndexList<Integer> actualSubList = this.subListFromThreeToEight();

        int expected = 5;
        assertEquals(expected, actualSubList.size());
    }

    @Test
    public void validIndexes_SizeShouldBeCorrectAfterAdding() {
        IndexList<Integer> actualSubList = this.subListFromThreeToEight();

        actualSubList.add(10);
        int expected = 6;
        assertEquals(expected, actualSubList.size());
    }

    @Test
    public void validIndexes_SizeShouldBeCorrectAfterRemoval() {
        IndexList<Integer> actualSubList = this.subListFromThreeToEight();

        actualSubList.remove(1);
        int expected = 4;
        assertEquals(expected, actualSubList.size());
    }

    @Test
    public void validIndexes_GetShouldReturnCorrectElement() {
        IndexList<Integer> actualSubList = this.subListFromThreeToEight();

        int expected = 5;
        int actual = actualSubList.get(2);
        assertEquals(expected, actual);
    }

    @Test
    public void validIndexes_IterationShouldBeCorrect() {
        IndexList<Integer> actualSubList = this.subListFromThreeToEight();
        StringBuilder actual = new StringBuilder();
        for (Integer integer : actualSubList) {
            actual.append(integer);
        }
        String expected = "34567";
        assertEquals(expected, actual.toString());
    }

    @Test
    public void validIndexes_ReverseIterationShouldBeCorrect() {
        ArrayList<Integer> actualSubList = (ArrayList<Integer>) this.subListFromThreeToEight();
        StringBuilder actual = new StringBuilder();
        Iterator<Integer> iterator = actualSubList.descendingIterator();
        while (iterator.hasNext()) {
            actual.append(iterator.next());
        }
        String expected = "76543";
        assertEquals(expected, actual.toString());
    }

    @Test
    public void validIndexes_ElementExistsInParentButNotInSubList_ShouldReturnFalse() {
        IndexList<Integer> subList = this.subListFromThreeToEight();
        int element = 8;
        assertFalse(subList.contains(element));
    }

    @Test
    public void validIndexes_ElementExists_ShouldReturnTrue() {
        IndexList<Integer> subList = this.subListFromThreeToEight();
        int element = 3;
        assertTrue(subList.contains(element));
    }

    @Test
    public void validIndexes_ClearSubList_SizeShouldBeZero() {
        IndexList<Integer> subList = this.subListFromThreeToEight();
        subList.clear();

        int expectedSize = 0;
        assertEquals(expectedSize, subList.size());
    }

    @Test
    public void validIndexes_ClearSubList_SubListShouldBeEmpty() {
        IndexList<Integer> subList = this.subListFromThreeToEight();
        subList.clear();

        String expectedElements = "";
        assertEquals(expectedElements, Helper.printCollection(subList));
    }

    @Test
    public void validIndexes_ClearSubList_ParentListSizeShouldBeCorrect() {
        IndexList<Integer> subList = this.subListFromThreeToEight();
        subList.clear();

        int expectedSize = 5;
        assertEquals(expectedSize, this.list.size());
    }

    @Test
    public void validIndexes_ClearSubList_ElementsShouldBeRemovedFromParentList() {
        IndexList<Integer> subList = this.subListFromThreeToEight();
        subList.clear();

        String expected = "0 1 2 8 9 ";
        assertEquals(expected, Helper.printCollection(this.list));
    }

    @Test
    public void validIndexes_SetInParentList_ElementInSubListShouldBeChanged() {
        IndexList<Integer> subList = this.subListFromThreeToEight();
        this.list.set(4, 10);
        String expected = "3 10 5 6 7 ";
        assertEquals(expected, Helper.printCollection(subList));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void validIndexes_AddToParentList_ShouldThrowExceptionWhenAccessingSubList() {
        IndexList<Integer> subList = this.subListFromThreeToEight();
        this.list.add(10);
        subList.get(4);
    }

    @Test(expected = ConcurrentModificationException.class)
    public void validIndexes_AddAtIndexToParentList_ShouldThrowExceptionWhenIteratingSubList() {
        IndexList<Integer> subList = this.subListFromThreeToEight();
        this.list.add(0, 10);
        for (Integer integer : subList) {
            System.out.println(integer);
        }
    }

    @Test(expected = ConcurrentModificationException.class)
    public void validIndexes_RemoveFromParentList_SubListFromSubListShouldThrowException() {
        IndexList<Integer> subList = this.subListFromThreeToEight();
        this.list.remove(9);
        subList.subList(1, 3);
    }

    @Test(expected = ConcurrentModificationException.class)
    public void validIndexes_RemoveObjectFromParentList_SubListCopyFromSubListShouldThrowException() {
        IndexList<Integer> subList = this.subListFromThreeToEight();
        Integer element = 8;
        this.list.remove(element);
        subList.subListCopy(1, 3);
    }

    @Test(expected = ConcurrentModificationException.class)
    public void validIndexes_ClearParentList_AccessingSubListShouldThrowException() {
        IndexList<Integer> subList = this.subListFromThreeToEight();
        this.list.clear();
        subList.size();
    }

    private IndexList<Integer> subListFromThreeToEight() {
        final int fromIndex = 3;
        final int toIndex = 8;
        return this.list.subList(fromIndex, toIndex);
    }

    private IndexList<Integer> expectedList() {
        final int fromIndex = 3;
        final int toIndex = 8;
        ArrayList<Integer> expected = new ArrayList<>();
        for (int i = fromIndex; i < toIndex; i++) {
            expected.add(this.list.get(i));
        }
        return expected;
    }
}