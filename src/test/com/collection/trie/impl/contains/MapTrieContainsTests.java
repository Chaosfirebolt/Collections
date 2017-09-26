package test.com.collection.trie.impl.contains;

import main.com.collection.trie.contract.Trie;
import main.com.collection.trie.impl.MapTrie;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by ChaosFire on 10.9.2017 г.
 */
public class MapTrieContainsTests {

    private Trie<Integer> trie;

    public MapTrieContainsTests() {
        this.trie = new MapTrie<>();
    }

    @Before
    public void prepareSetting() {
        for (int i = 0; i <= 100; i++) {
            this.trie.insert(Integer.toString(i), i);
        }
    }

    @Test
    public void elementExists_ShouldReturnTrue_Test1() {
        String key = "5";
        assertTrue(this.trie.contains(key));
    }

    @Test
    public void elementExists_ShouldReturnTrue_Test2() {
        String key = "10";
        assertTrue(this.trie.contains(key));
    }

    @Test
    public void elementExists_ShouldReturnTrue_Test3() {
        String key = "50";
        assertTrue(this.trie.contains(key));
    }

    @Test
    public void elementExists_ShouldReturnTrue_Test4() {
        String key = "59";
        assertTrue(this.trie.contains(key));
    }

    @Test
    public void elementExists_ShouldReturnTrue_Test5() {
        for (int i = 0; i <= 100; i++) {
            String key = Integer.toString(i);
            assertTrue(this.trie.contains(key));
        }
    }

    @Test
    public void elementDoesNotExist_ShouldReturnFalse_Test1() {
        String key = "-5";
        assertFalse(this.trie.contains(key));
    }

    @Test
    public void elementDoesNotExist_ShouldReturnFalse_Test2() {
        String key = "1000";
        assertFalse(this.trie.contains(key));
    }

    @Test
    public void elementDoesNotExist_ShouldReturnFalse_Test3() {
        String key = "ivan";
        assertFalse(this.trie.contains(key));
    }

    @Test
    public void elementDoesNotExist_ShouldReturnFalse_Test4() {
        String key = "101";
        assertFalse(this.trie.contains(key));
    }

    @Test
    public void elementDoesNotExist_ShouldReturnFalse_Test5() {
        String key = "953";
        assertFalse(this.trie.contains(key));
    }

    @Test
    public void elementWasRemoved_ShouldReturnFalse() {
        String key = "49";
        this.trie.remove(key);
        assertFalse(this.trie.contains(key));
    }
}