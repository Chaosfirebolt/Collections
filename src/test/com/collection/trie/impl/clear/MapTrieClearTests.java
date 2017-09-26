package test.com.collection.trie.impl.clear;

import main.com.collection.trie.impl.MapTrie;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

/**
 * Created by ChaosFire on 10.9.2017 г.
 */
public class MapTrieClearTests {

    private MapTrie<Integer> trie;

    public MapTrieClearTests() {
        this.trie = new MapTrie<>();
    }

    @Before
    public void set() {
        for (int value = 0; value < 100; value++) {
            String key = Integer.toString(value);
            this.trie.insert(key, value);
        }
    }

    @Test
    public void sizeShouldBeZeroAfterClear() {
        int expected = 0;
        this.trie.clear();
        assertEquals(expected, this.trie.size());
    }

    @Test
    public void removeShouldReturnNullAfterClearForClearedKeys() {
        this.trie.clear();
        for (int i = 0; i < 100; i++) {
            String key = Integer.toString(i);
            Integer actual = this.trie.remove(key);
            assertNull(actual);
        }
    }

    @Test
    public void containsShouldReturnFalseAfterClearForClearedKeys() {
        this.trie.clear();
        for (int i = 0; i < 100; i++) {
            String key = Integer.toString(i);
            boolean actual = this.trie.contains(key);
            assertFalse(actual);
        }
    }

    @Test
    public void getShouldReturnNullAfterClearForClearedKeys() {
        this.trie.clear();
        for (int i = 0; i < 100; i++) {
            String key = Integer.toString(i);
            Integer actual = this.trie.get(key);
            assertNull(actual);
        }
    }
}