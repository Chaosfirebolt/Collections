package main.com.collection.list.impl;

/**
 * Created by ChaosFire on 27.8.2017 г.
 */
class DescListIterator<T> extends AbstractListIterator<T> {

    DescListIterator(Node<T> node) {
        super(node);
    }

    @Override
    public boolean hasNext() {
        return super.getNode() != null;
    }

    @Override
    public T next() {
        T value = super.getNode().getValue();
        super.setNode(super.getNode().getPrev());
        return value;
    }
}