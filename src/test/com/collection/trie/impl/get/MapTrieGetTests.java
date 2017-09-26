package test.com.collection.trie.impl.get;

import main.com.collection.trie.impl.MapTrie;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by ChaosFire on 10.9.2017 г.
 */
public class MapTrieGetTests {

    private MapTrie<Integer> trie;

    public MapTrieGetTests() {
        this.trie = new MapTrie<>();
    }

    @Before
    public void set() {
        for (int value = 100; value >= 0; value--) {
            String key = Integer.toString(value);
            this.trie.insert(key, value);
        }
    }

    @Test
    public void elementExists_ShouldReturnRightValue_Test1() {
        String key = "20";
        Integer expected = Integer.parseInt(key);
        assertEquals(expected, this.trie.get(key));
    }

    @Test
    public void elementExists_ShouldReturnRightValue_Test2() {
        String key = "9";
        Integer expected = Integer.parseInt(key);
        assertEquals(expected, this.trie.get(key));
    }

    @Test
    public void elementExists_ShouldReturnRightValue_Test3() {
        String key = "93";
        Integer expected = Integer.parseInt(key);
        assertEquals(expected, this.trie.get(key));
    }

    @Test
    public void allElements_ShouldReturnRightValues() {
        for (int expected = 0; expected <= 100; expected++) {
            String key = Integer.toString(expected);
            int actual = this.trie.get(key);
            assertEquals(expected, actual);
        }
    }

    @Test
    public void elementDoesNotExist_ShouldReturnNull_Test1() {
        String key = "1000";
        assertNull(this.trie.get(key));
    }

    @Test
    public void elementDoesNotExist_ShouldReturnNull_Test2() {
        String key = "-0";
        assertNull(this.trie.get(key));
    }

    @Test
    public void elementDoesNotExist_ShouldReturnNull_Test3() {
        String key = "ivan";
        assertNull(this.trie.get(key));
    }

    @Test
    public void elementWasRemoved_ShouldReturnNull() {
        String key = "55";
        this.trie.remove(key);
        assertNull(this.trie.get(key));
    }
}