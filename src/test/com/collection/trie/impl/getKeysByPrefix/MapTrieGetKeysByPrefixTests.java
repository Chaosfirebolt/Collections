package test.com.collection.trie.impl.getKeysByPrefix;

import main.com.collection.trie.impl.MapTrie;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static test.util.Helper.printCollection;

/**
 * Created by ChaosFire on 10.9.2017 г.
 */
public class MapTrieGetKeysByPrefixTests {

    private MapTrie<Integer> trie;
    private List<String> keys;

    public MapTrieGetKeysByPrefixTests() {
        this.trie = new MapTrie<>();
        this.keys = new ArrayList<>();
    }

    @Before
    public void set() {
        for (int value = 0; value <= 1000; value++) {
            String key = Integer.toString(value);
            this.trie.insert(key, value);
            this.keys.add(key);
        }
    }

    private Iterable<String> expected(String prefix) {
        List<String> result = new ArrayList<>();
        for (String key : this.keys) {
            if (key.startsWith(prefix)) {
                result.add(key);
            }
        }
        result.sort(Comparator.naturalOrder());
        return result;
    }

    private Iterable<String> actual(String prefix) {
        List<String> result = new ArrayList<>();
        Iterable<String> matches = this.trie.getKeysByPrefix(prefix);
        for (String match : matches) {
            result.add(match);
        }
        result.sort(Comparator.naturalOrder());
        return result;
    }

    @Test
    public void noSuchPrefix_ShouldReturnEmpty_Test1() {
        String prefix = "";
        String expected = "";
        Iterable<String> actual = this.actual(prefix);
        assertEquals(expected, printCollection(actual));
    }

    @Test
    public void noSuchPrefix_ShouldReturnEmpty_Test2() {
        String prefix = "h";
        String expected = "";
        Iterable<String> actual = this.actual(prefix);
        assertEquals(expected, printCollection(actual));
    }

    @Test
    public void noSuchPrefix_ShouldReturnEmpty_Test3() {
        String prefix = "99999";
        String expected = "";
        Iterable<String> actual = this.actual(prefix);
        assertEquals(expected, printCollection(actual));
    }

    @Test
    public void noSuchPrefix_ShouldReturnEmpty_Test4() {
        String prefix = "-5";
        String expected = "";
        Iterable<String> actual = this.actual(prefix);
        assertEquals(expected, printCollection(actual));
    }

    @Test
    public void prefixExists_ShouldReturnRightKeys_Test1() {
        String prefix = "0";
        assertEquals(printCollection(this.expected(prefix)), printCollection(this.actual(prefix)));
    }

    @Test
    public void prefixExists_ShouldReturnRightKeys_Test2() {
        String prefix = "1";
        assertEquals(printCollection(this.expected(prefix)), printCollection(this.actual(prefix)));
    }

    @Test
    public void prefixExists_ShouldReturnRightKeys_Test3() {
        String prefix = "10";
        assertEquals(printCollection(this.expected(prefix)), printCollection(this.actual(prefix)));
    }

    @Test
    public void prefixExists_ShouldReturnRightKeys_Test4() {
        String prefix = "23";
        assertEquals(printCollection(this.expected(prefix)), printCollection(this.actual(prefix)));
    }

    @Test
    public void prefixExists_ShouldReturnRightKeys_Test5() {
        String prefix = "748";
        assertEquals(printCollection(this.expected(prefix)), printCollection(this.actual(prefix)));
    }
}