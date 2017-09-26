package test.com.collection.trie.impl.insert;

import main.com.collection.trie.impl.MapTrie;
import org.junit.Before;
import org.junit.Test;
import test.util.Helper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by ChaosFire on 9.9.2017 г.
 */
public class MapTrieInsertTests {

    private MapTrie<Integer> trie;
    private List<String> keys;
    private List<Integer> values;

    public MapTrieInsertTests() {
        this.trie = new MapTrie<>();
        this.keys = new ArrayList<>();
        this.values = new ArrayList<>();
    }

    @Before
    public void init() {
        String key1 = "ivan";
        String key2 = "ivana";
        String key3 = "petkan";
        String key4 = "pesho";
        String key5 = "gosho";
        String key6 = "petya";
        String key7 = "spaska";
        String key8 = "ivanaN";
        String key9 = "ivanaG";
        int v1 = 1;
        int v2 = 2;
        int v3 = 3;
        int v4 = 4;
        int v5 = 5;
        int v6 = 6;
        int v7 = 7;
        int v8 = 8;
        int v9 = 9;

        this.trie.insert(key1, v1);
        this.keys.add(key1);
        this.values.add(v1);

        this.trie.insert(key2, v2);
        this.keys.add(key2);
        this.values.add(v2);

        this.trie.insert(key3, v3);
        this.keys.add(key3);
        this.values.add(v3);

        this.trie.insert(key4, v4);
        this.keys.add(key4);
        this.values.add(v4);

        this.trie.insert(key5, v5);
        this.keys.add(key5);
        this.values.add(v5);

        this.trie.insert(key6, v6);
        this.keys.add(key6);
        this.values.add(v6);

        this.trie.insert(key7, v7);
        this.keys.add(key7);
        this.values.add(v7);

        this.trie.insert(key8, v8);
        this.keys.add(key8);
        this.values.add(v8);

        this.trie.insert(key9, v9);
        this.keys.add(key9);
        this.values.add(v9);
    }

    @Test
    public void insertElement_CheckKeys_ShouldBeInserted() {
        this.keys.sort(Comparator.naturalOrder());
        List<String> actualKeys = new ArrayList<>();
        Iterator<String> iterator = this.trie.keyIterator();
        while (iterator.hasNext()) {
            actualKeys.add(iterator.next());
        }
        actualKeys.sort(Comparator.naturalOrder());

        assertEquals(Helper.printCollection(this.keys), Helper.printCollection(actualKeys));
    }

    @Test
    public void insertElements_CheckValues_ShouldBeInserted() {
        this.values.sort(Comparator.naturalOrder());
        List<Integer> actualValues = new ArrayList<>();
        for (Integer value : this.trie) {
            actualValues.add(value);
        }
        actualValues.sort(Comparator.naturalOrder());

        for (int index = 0; index < actualValues.size(); index++) {
            int expected = this.values.get(index);
            int actual = actualValues.get(index);
            assertEquals(expected, actual);
        }
    }
}