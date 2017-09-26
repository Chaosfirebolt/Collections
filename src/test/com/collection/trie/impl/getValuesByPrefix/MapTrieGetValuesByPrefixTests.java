package test.com.collection.trie.impl.getValuesByPrefix;

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
public class MapTrieGetValuesByPrefixTests {

    private MapTrie<Integer> trie;
    private List<Integer> values;

    public MapTrieGetValuesByPrefixTests() {
        this.trie = new MapTrie<>();
        this.values = new ArrayList<>();
    }

    @Before
    public void set() {
        for (int value = 0; value <= 1000; value++) {
            String key = Integer.toString(value);
            this.trie.insert(key, value);
            this.values.add(value);
        }
    }

    private Iterable<Integer> expected(String prefix) {
        List<Integer> result = new ArrayList<>();
        for (Integer value : this.values) {
            if (Integer.toString(value).startsWith(prefix)) {
                result.add(value);
            }
        }
        result.sort(Comparator.naturalOrder());
        return result;
    }

    private Iterable<Integer> actual(String prefix) {
        List<Integer> result = new ArrayList<>();
        Iterable<Integer> matches = this.trie.getValuesByPrefix(prefix);
        for (Integer match : matches) {
            result.add(match);
        }
        result.sort(Comparator.naturalOrder());
        return result;
    }

    @Test
    public void noSuchPrefix_ShouldReturnEmpty_Test1() {
        String prefix = "";
        String expected = "";
        Iterable<Integer> actual = this.actual(prefix);
        assertEquals(expected, printCollection(actual));
    }

    @Test
    public void noSuchPrefix_ShouldReturnEmpty_Test2() {
        String prefix = "h";
        String expected = "";
        Iterable<Integer> actual = this.actual(prefix);
        assertEquals(expected, printCollection(actual));
    }

    @Test
    public void noSuchPrefix_ShouldReturnEmpty_Test3() {
        String prefix = "99999";
        String expected = "";
        Iterable<Integer> actual = this.actual(prefix);
        assertEquals(expected, printCollection(actual));
    }

    @Test
    public void noSuchPrefix_ShouldReturnEmpty_Test4() {
        String prefix = "-5";
        String expected = "";
        Iterable<Integer> actual = this.actual(prefix);
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