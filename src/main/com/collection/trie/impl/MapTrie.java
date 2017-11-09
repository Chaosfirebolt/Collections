package main.com.collection.trie.impl;

import main.com.collection.AbstractCollection;
import main.com.collection.list.contract.LinkList;
import main.com.collection.list.impl.LinkedList;
import main.com.collection.trie.contract.Trie;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by ChaosFire on 9.9.2017 г.
 */
public class MapTrie<V> extends AbstractCollection implements Trie<V> {

    private static final int INITIAL_COUNT = 0;

    private Node<V> root;
    private int modCount;

    public MapTrie() {
        this.setRoot(new Node<>(false));
        this.setModCount(INITIAL_COUNT);
    }

    @Override
    public void insert(String key, V value) {
        this.add(key, value);
    }

    private void add(String key, V value) {
        Map<Character, Node<V>> next = this.root.getNext();
        for (int index = 0; index < key.length() - 1; index++) {
            char character = key.charAt(index);
            Node<V> node = next.get(character);
            if (node == null) {
                node = new Node<>(false);
                next.put(character, node);
            }
            next = node.getNext();
        }
        char lastChar = key.charAt(key.length() - 1);
        Node<V> lastNode = next.get(lastChar);
        if (lastNode == null) {
            lastNode = new Node<>(value);
            next.put(lastChar, lastNode);
            super.incrementSize();
            this.modCount++;
        } else {
            if (!lastNode.isTerminal()) {
                super.incrementSize();
                this.modCount++;
            }
            lastNode.setValue(value);
            lastNode.setTerminal(true);
        }
    }

    @Override
    public V remove(String key) {
        Node<V> node = this.find(key);
        if (node == null || !node.isTerminal()) {
            return null;
        }
        V value = node.getValue();
        node.setTerminal(false);
        node.setValue(null);
        super.decrementSize();
        this.modCount++;
        return value;
    }

    @Override
    public boolean contains(String key) {
        Node<V> node = this.find(key);
        return node != null && node.isTerminal();
    }

    @Override
    public V get(String key) {
        Node<V> node = this.find(key);
        if (node == null || !node.isTerminal()) {
            return null;
        }
        return node.getValue();
    }

    @Override
    public Iterable<String> getKeysByPrefix(String prefix) {
        LinkList<String> result = new LinkedList<>();
        Node<V> node = this.find(prefix);
        if (node == null) {
            return result;
        }
        this.traverseGetKeys(node, prefix, result);
        return result;
    }

    private void traverseGetKeys(Node<V> node, String prefix, LinkList<String> list) {
        if (node.isTerminal()) {
            list.add(prefix);
        }
        for (Map.Entry<Character, Node<V>> entry : node.getNext().entrySet()) {
            this.traverseGetKeys(entry.getValue(), prefix + entry.getKey(), list);
        }
    }

    @Override
    public Iterable<V> getValuesByPrefix(String prefix) {
        LinkList<V> result = new LinkedList<>();
        Node<V> node = this.find(prefix);
        if (node == null) {
            return result;
        }
        this.traverseGetValues(node, result);
        return result;
    }

    private void traverseGetValues(Node<V> node, LinkList<V> list) {
        if (node.isTerminal()) {
            list.add(node.getValue());
        }
        for (Node<V> nextNode : node.getNext().values()) {
            this.traverseGetValues(nextNode, list);
        }
    }

    private Node<V> find(String key) {
        Node<V> node = null;
        Map<Character, Node<V>> next = this.root.getNext();
        for (int index = 0; index < key.length(); index++) {
            char character = key.charAt(index);
            node = next.get(character);
            if (node == null) {
                return null;
            }
            next = node.getNext();
        }
        return node;
    }

    @Override
    public void clear() {
        this.root.getNext().clear();
        super.clearSize();
        this.modCount++;
    }

    @Override
    public int size() {
        return super.getSize();
    }

    @Override
    public Iterator<V> iterator() {
        return new ValueIterator<>(this.size(), this.root, this.modCount, this);
    }

    @Override
    public Iterator<String> keyIterator() {
        return new KeyIterator<>(this.size(), this.root, this.modCount, this);
    }

    private void setRoot(Node<V> root) {
        this.root = root;
    }

    int getModCount() {
        return this.modCount;
    }

    private void setModCount(int modCount) {
        this.modCount = modCount;
    }
}