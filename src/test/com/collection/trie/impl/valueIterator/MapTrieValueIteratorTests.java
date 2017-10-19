package test.com.collection.trie.impl.valueIterator;

import main.com.collection.trie.impl.MapTrie;
import org.junit.Before;
import org.junit.Test;
import test.util.Helper;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by ChaosFire on 18.10.2017 г.
 */
public class MapTrieValueIteratorTests {

    private MapTrie<Integer> trie;
    private List<Integer> values;

    public MapTrieValueIteratorTests() {
        this.trie = new MapTrie<>();
        this.values = new ArrayList<>();
    }

    @Before
    public void init() {
        for (int i = 0; i <= 10; i++) {
            String key = Integer.toString(i);
            this.trie.insert(key, i);
            this.values.add(i);
        }
    }

    @Test
    public void iterationShouldReturnCorrectValues() {
        this.values.sort(Comparator.naturalOrder());
        List<Integer> actual = new ArrayList<>();
        for (Integer integer : this.trie) {
            actual.add(integer);
        }
        actual.sort(Comparator.naturalOrder());
        assertEquals(Helper.printCollection(this.values), Helper.printCollection(actual));
    }

    @Test
    public void elementIsRemoved_IterationShouldReturnCorrectValues() {
        String key = "2";
        this.trie.remove(key);
        this.values.remove(Integer.parseInt(key));
        this.values.sort(Comparator.naturalOrder());
        List<Integer> actual = new ArrayList<>();
        for (Integer integer : this.trie) {
            actual.add(integer);
        }
        actual.sort(Comparator.naturalOrder());
        assertEquals(Helper.printCollection(this.values), Helper.printCollection(actual));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void elementIsInsertedDuringIteration_ShouldThrowException() {
        for (Integer integer : this.trie) {
            if (integer == 5) {
                this.trie.insert("10000", 10000);
            }
        }
    }

    @Test(expected = ConcurrentModificationException.class)
    public void elementIsRemovedDuringIteration_ShouldThrowException() {
        for (Integer integer : this.trie) {
            if (integer == 5) {
                this.trie.remove("3");
            }
        }
    }

    @Test
    public void existingKeyIsInsertedDuringIteration_ShouldReturnCorrectValues() {
        List<Integer> actual = new ArrayList<>();
        for (Integer integer : this.trie) {
            if (integer == 5) {
                this.trie.insert(Integer.toString(integer), 10000);
            }
            actual.add(integer);
        }
        this.values.sort(Comparator.naturalOrder());
        actual.sort(Comparator.naturalOrder());
        assertEquals(Helper.printCollection(this.values), Helper.printCollection(actual));
    }

    @Test
    public void nonExistingKeyIsRemovedDuringIteration_ShouldReturnCorrectValues() {
        List<Integer> actual = new ArrayList<>();
        for (Integer integer : this.trie) {
            this.trie.remove("500000");
            actual.add(integer);
        }
        this.values.sort(Comparator.naturalOrder());
        actual.sort(Comparator.naturalOrder());
        assertEquals(Helper.printCollection(this.values), Helper.printCollection(actual));
    }

    @Test
    public void iteratorRemove_ShouldReturnCorrectValues() {
        Iterator<Integer> iterator = this.trie.iterator();
        List<Integer> actual = new ArrayList<>();
        while (iterator.hasNext()) {
            Integer value = iterator.next();
            if (value == 3) {
                iterator.remove();
                this.values.remove(value);
            } else {
                actual.add(value);
            }
        }
        this.values.sort(Comparator.naturalOrder());
        actual.sort(Comparator.naturalOrder());
        assertEquals(Helper.printCollection(this.values), Helper.printCollection(actual));
    }
}