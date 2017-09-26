package test.com.collection.trie.impl.remove;

import main.com.collection.trie.impl.MapTrie;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static test.util.Helper.printCollection;

/**
 * Created by ChaosFire on 10.9.2017 г.
 */
public class MapTrieRemoveTests {

    private MapTrie<Integer> trie;
    private Set<String> keys;
    private Set<Integer> values;

    public MapTrieRemoveTests() {
        this.trie = new MapTrie<>();
        this.keys = new HashSet<>();
        this.values = new HashSet<>();
    }

    @Before
    public void set() {
        for (int value = 100; value >= 0; value--) {
            String key = Integer.toString(value);
            this.trie.insert(key, value);
            this.keys.add(key);
            this.values.add(value);
        }
    }

    private void checkKeys() {
        List<String> expected = new ArrayList<>(this.keys.size());
        expected.addAll(this.keys);
        expected.sort(Comparator.naturalOrder());

        List<String> actual = new ArrayList<>(this.keys.size());
        Iterator<String> keyIterator = this.trie.keyIterator();
        while (keyIterator.hasNext()) {
            actual.add(keyIterator.next());
        }
        actual.sort(Comparator.naturalOrder());

        assertEquals(printCollection(expected), printCollection(actual));
    }

    private void checkValues() {
        List<Integer> expected = new ArrayList<>(this.keys.size());
        expected.addAll(this.values);
        expected.sort(Comparator.naturalOrder());

        List<Integer> actual = new ArrayList<>(this.keys.size());
        for (Integer value : this.trie) {
            actual.add(value);
        }
        actual.sort(Comparator.naturalOrder());

        assertEquals(printCollection(expected), printCollection(actual));
    }

    @Test
    public void elementExists_KeyShouldBeRemoved_Test1() {
        String key = "5";
        this.trie.remove(key);
        this.keys.remove(key);

        this.checkKeys();
    }

    @Test
    public void elementExists_KeyShouldBeRemoved_Test2() {
        String key = "46";
        this.trie.remove(key);
        this.keys.remove(key);

        this.checkKeys();
    }

    @Test
    public void elementExists_KeyShouldBeRemoved_Test3() {
        String key = "100";
        this.trie.remove(key);
        this.keys.remove(key);

        this.checkKeys();
    }

    @Test
    public void removeManyElements_KeysShouldBeRemoved_Test1() {
        for (int i = 0; i < 20; i++) {
            String key = Integer.toString(i);
            this.trie.remove(key);
            this.keys.remove(key);
        }

        this.checkKeys();
    }

    @Test
    public void removeManyElements_KeysShouldBeRemoved_Test2() {
        for (int i = 85; i <= 100; i++) {
            String key = Integer.toString(i);
            this.trie.remove(key);
            this.keys.remove(key);
        }

        this.checkKeys();
    }

    @Test
    public void elementExists_ValueShouldBeRemoved_Test1() {
        String key = "9";
        this.trie.remove(key);
        this.values.remove(Integer.parseInt(key));

        this.checkValues();
    }

    @Test
    public void elementExists_ValueShouldBeRemoved_Test2() {
        String key = "82";
        this.trie.remove(key);
        this.values.remove(Integer.parseInt(key));

        this.checkValues();
    }

    @Test
    public void elementExists_ValueShouldBeRemoved_Test3() {
        String key = "93";
        this.trie.remove(key);
        this.values.remove(Integer.parseInt(key));

        this.checkValues();
    }

    @Test
    public void removeManyElements_ValuesShouldBeRemoved_Test1() {
        for (int value = 0; value < 31; value++) {
            this.trie.remove(Integer.toString(value));
            this.values.remove(value);
        }

        checkValues();
    }

    @Test
    public void removeManyElements_ValuesShouldBeRemoved_Test2() {
        for (int value = 79; value <= 100; value++) {
            this.trie.remove(Integer.toString(value));
            this.values.remove(value);
        }

        checkValues();
    }

    @Test
    public void removeElement_ShouldReturnRightValue_Test1() {
        String key = "10";
        int expected = Integer.parseInt(key);
        int actual = this.trie.remove(key);
        assertEquals(expected, actual);
    }

    @Test
    public void removeElement_ShouldReturnRightValue_Test2() {
        String key = "100";
        int expected = Integer.parseInt(key);
        int actual = this.trie.remove(key);
        assertEquals(expected, actual);
    }

    @Test
    public void removeElement_ShouldReturnRightValue_Test3() {
        for (int expected = 100; expected >= 0; expected--) {
            String key = Integer.toString(expected);
            int actual = this.trie.remove(key);
            assertEquals(expected, actual);
        }
    }

    @Test
    public void elementDoesNotExist_ShouldReturnNull_Test1() {
        String key = "";
        Integer actual = this.trie.remove(key);
        assertNull(actual);
    }

    @Test
    public void elementDoesNotExist_ShouldReturnNull_Test2() {
        String key = "103";
        Integer actual = this.trie.remove(key);
        assertNull(actual);
    }

    @Test
    public void elementDoesNotExist_ShouldReturnNull_Test3() {
        String key = "1000";
        Integer actual = this.trie.remove(key);
        assertNull(actual);
    }

    @Test
    public void elementWasAlreadyRemoved_ShouldReturnNull() {
        String key = "33";
        this.trie.remove(key);
        Integer actual = this.trie.remove(key);
        assertNull(actual);
    }
}