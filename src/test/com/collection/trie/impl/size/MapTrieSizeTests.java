package test.com.collection.trie.impl.size;

import main.com.collection.trie.impl.MapTrie;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by ChaosFire on 10.9.2017 г.
 */
public class MapTrieSizeTests {

    private MapTrie<Integer> trie;

    public MapTrieSizeTests() {
        this.trie = new MapTrie<>();
    }

    private void setInsertElements(int count) {
        for (int value = 0; value < count; value++) {
            String key = Integer.toString(value);
            this.trie.insert(key, value);
        }
    }

    @Test
    public void sizeShouldBeZeroAfterInitialization() {
        int expected = 0;
        assertEquals(expected, this.trie.size());
    }

    @Test
    public void insertMany_SizeShouldBeCorrectAfterEachInsertion() {
        int expected = 1;
        for (int i = 0; i < 100; i++) {
            String key = Integer.toString(i);
            this.trie.insert(key, i);
            assertEquals(expected, this.trie.size());
            expected++;
        }
    }

    @Test
    public void insertManyRemoveAll_SizeShouldBeCorrectAfterEachRemoval() {
        int count = 100;
        this.setInsertElements(count);
        int expected = 99;
        for (int i = 0; i < count; i++) {
            String key = Integer.toString(i);
            this.trie.remove(key);
            assertEquals(expected, this.trie.size());
            expected--;
        }
    }

    @Test
    public void insertManyRemoveMoreThanCount_SizeShouldStayZero() {
        int count = 100;
        this.setInsertElements(count);
        for (int i = 0; i < count; i++) {
            String key = Integer.toString(i);
            this.trie.remove(key);
        }

        int expected = 0;
        for (int i = 0; i < count * 10; i++) {
            String key = Integer.toString(i);
            this.trie.remove(key);
            assertEquals(expected, this.trie.size());
        }
    }
}