package main.com.collection.list.impl;

/**
 * Created by ChaosFire on 27.8.2017 г.
 */
class DescListIterator<T> extends AbstractListIterator<T> {

    DescListIterator(Node<T> node, AbstractDoublyLinkedList<T> list, ModCount modCount) {
        super(node, list, modCount);
    }

    @Override
    public boolean hasNext() {
        return super.getNode() != null;
    }

    @Override
    public T next() {
        super.validateModCount();
        T value = super.getNode().getValue();
        super.setLastNode(super.getNode());
        super.setNode(super.getNode().getPrev());
        return value;
    }

    @Override
    public void remove() {
        super.unlinkPrevNode();
    }
}