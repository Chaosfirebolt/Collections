package main.com.collection.map.impl;

/**
 * Created by ChaosFire on 15.12.2017 г.
 */
class DescTreeIterator<K extends Comparable<K>, V> extends AbstractTreeIterator<K, V> {

    DescTreeIterator(AVLTree<K, V> tree, Node<Pair<K, V>> node) {
        super(tree);
        super.goRight(node);
    }

    @Override
    public boolean hasNext() {
        return super.hasMoreElements();
    }

    @Override
    public Node<Pair<K, V>> next() {
        return super.nextDesc();
    }

    @Override
    public void remove() {
        super.removeLast();
    }
}