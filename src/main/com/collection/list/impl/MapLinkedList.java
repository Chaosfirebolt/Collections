package main.com.collection.list.impl;

import main.com.collection.list.contract.LinkList;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by ChaosFire on 27.8.2017 г.
 */
public class MapLinkedList<T> extends AbstractDoublyLinkedList<T> implements LinkList<T> {

    private static final int INITIAL_CAPACITY = 16;

    private Map<T, LinkList<Node<T>>> valueNodeMap;

    public MapLinkedList() {
        this(INITIAL_CAPACITY);
    }

    public MapLinkedList(int initialCapacity) {
        super();
        this.setValueNodeMap(new HashMap<>(initialCapacity));
    }

    @Override
    public void addFirst(T element) {
        Node<T> node = super.linkFirst(element);
        this.addToMap(element, node);
        super.incrementSize();
    }

    @Override
    public void addLast(T element) {
        Node<T> node = super.linkLast(element);
        this.addToMap(element, node);
        super.incrementSize();
    }

    @Override
    public T removeFirst() {
        super.validateHasElements();
        Node<T> node = super.getFirstNode();
        super.unlink(node);
        T value = node.getValue();
        this.valueNodeMap.get(value).removeFirst();
        return value;
    }

    @Override
    public T removeLast() {
        super.validateHasElements();
        Node<T> node = super.getLastNode();
        super.unlink(node);
        T value = node.getValue();
        this.valueNodeMap.get(value).removeLast();
        return value;
    }

    @Override
    public T getFirst() {
        super.validateHasElements();
        return super.getFirstNode().getValue();
    }

    @Override
    public T getLast() {
        super.validateHasElements();
        return super.getLastNode().getValue();
    }

    @Override
    public void add(T element) {
        this.addLast(element);
    }

    @Override
    public T get(int index) {
        super.validateIndexExists(index);
        return super.findNodeByIndex(index).getValue();
    }

    @Override
    public T remove(int index) {
        super.validateIndexExists(index);
        return super.removeNodeByIndex(index).getValue();
    }

    @Override
    public boolean remove(T element) {
        LinkList<Node<T>> list = this.valueNodeMap.get(element);
        if (list == null || list.size() == 0) {
            return false;
        }
        Node<T> node = list.removeFirst();
        super.unlink(node);
        return true;
    }

    @Override
    public boolean contains(T element) {
        LinkList<Node<T>> list = this.valueNodeMap.get(element);
        return list != null && list.size() > 0;
    }

    @Override
    public void clear() {
        super.clearConnections();
        this.valueNodeMap.clear();
        super.clearSize();
    }

    @Override
    public int size() {
        return super.getSize();
    }

    @Override
    public Iterator<T> iterator() {
        return new AscListIterator<>(super.getFirstNode(), this, super.getModCount());
    }

    @Override
    public Iterator<T> descendingIterator() {
        return new DescListIterator<>(super.getLastNode(), this, super.getModCount());
    }

    private void addToMap(T element, Node<T> node) {
        LinkList<Node<T>> list = this.valueNodeMap.get(element);
        if (list == null) {
            list = new LinkedList<>();
            list.addLast(node);
            this.valueNodeMap.put(element, list);
        } else {
            list.addLast(node);
        }
    }

    private void setValueNodeMap(Map<T, LinkList<Node<T>>> valueNodeMap) {
        this.valueNodeMap = valueNodeMap;
    }
}