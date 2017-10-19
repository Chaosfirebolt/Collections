package test.com.collection.trie.impl.keyIterator;

import main.com.collection.trie.impl.MapTrie;
import org.junit.Before;
import org.junit.Test;
import test.util.Helper;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by ChaosFire on 18.10.2017 г.
 */
public class MapTrieKeyIteratorTests {

    private MapTrie<Integer> trie;
    private List<String> keys;

    public MapTrieKeyIteratorTests() {
        this.trie = new MapTrie<>();
        this.keys = new ArrayList<>();
    }

    @Before
    public void init() {
        for (int i = 0; i <= 10; i++) {
            String key = Integer.toString(i);
            this.trie.insert(key, i);
            this.keys.add(key);
        }
    }

    @SuppressWarnings("Duplicates")
    @Test
    public void iterationShouldReturnCorrectKeys() {
        this.keys.sort(Comparator.naturalOrder());
        List<String> actual = new ArrayList<>();
        Iterator<String> keyIterator = this.trie.keyIterator();
        while (keyIterator.hasNext()) {
            actual.add(keyIterator.next());
        }
        actual.sort(Comparator.naturalOrder());
        assertEquals(Helper.printCollection(this.keys), Helper.printCollection(actual));
    }

    @Test
    public void elementIsRemoved_IterationShouldReturnCorrectKeys() {
        String key = "2";
        this.trie.remove(key);
        this.keys.remove(key);
        this.keys.sort(Comparator.naturalOrder());
        List<String> actual = new ArrayList<>();
        Iterator<String> keyIterator = this.trie.keyIterator();
        while (keyIterator.hasNext()) {
            actual.add(keyIterator.next());
        }
        actual.sort(Comparator.naturalOrder());
        assertEquals(Helper.printCollection(this.keys), Helper.printCollection(actual));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void elementIsInsertedDuringIteration_ShouldThrowException() {
        Iterator<String> keyIterator = this.trie.keyIterator();
        while (keyIterator.hasNext()) {
            String key = keyIterator.next();
            if (key.equals("5")) {
                this.trie.insert("10000", 10000);
            }
        }
    }

    @Test(expected = ConcurrentModificationException.class)
    public void elementIsRemovedDuringIteration_ShouldThrowException() {
        Iterator<String> keyIterator = this.trie.keyIterator();
        while (keyIterator.hasNext()) {
            String key = keyIterator.next();
            if (key.equals("5")) {
                this.trie.remove(key);
            }
        }
    }

    @Test
    public void existingKeyIsInsertedDuringIteration_ShouldReturnCorrectKeys() {
        Iterator<String> keyIterator = this.trie.keyIterator();
        List<String> actual = new ArrayList<>();
        while (keyIterator.hasNext()) {
            String key = keyIterator.next();
            actual.add(key);
            if (key.equals("5")) {
                this.trie.insert(key, 10000);
            }
        }
        this.keys.sort(Comparator.naturalOrder());
        actual.sort(Comparator.naturalOrder());
        assertEquals(Helper.printCollection(this.keys), Helper.printCollection(actual));
    }

    @Test
    public void nonExistingKeyIsRemovedDuringIteration_ShouldReturnCorrectKeys() {
        Iterator<String> keyIterator = this.trie.keyIterator();
        List<String> actual = new ArrayList<>();
        while (keyIterator.hasNext()) {
            String key = keyIterator.next();
            actual.add(key);
            this.trie.remove("500000");
        }
        this.keys.sort(Comparator.naturalOrder());
        actual.sort(Comparator.naturalOrder());
        assertEquals(Helper.printCollection(this.keys), Helper.printCollection(actual));
    }

    @Test
    public void iteratorRemove_ShouldReturnCorrectKeys() {
        Iterator<String> keyIterator = this.trie.keyIterator();
        List<String> actual = new ArrayList<>();
        while (keyIterator.hasNext()) {
            String key = keyIterator.next();
            if (key.equals("3")) {
                keyIterator.remove();
                this.keys.remove(key);
            } else {
                actual.add(key);
            }
        }
        this.keys.sort(Comparator.naturalOrder());
        actual.sort(Comparator.naturalOrder());
        assertEquals(Helper.printCollection(this.keys), Helper.printCollection(actual));
    }
}