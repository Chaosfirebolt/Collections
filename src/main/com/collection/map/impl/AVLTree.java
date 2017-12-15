package main.com.collection.map.impl;

import main.com.collection.AbstractCollection;
import main.com.collection.Collection;

import java.util.Comparator;
import java.util.Iterator;

/**
 * Created by ChaosFire on 9.11.2017 г.
 */
class AVLTree<K extends Comparable<K>, V> extends AbstractCollection implements Collection {

    private static final boolean DEFAULT_REPLACE = true;

    private Node<Pair<K, V>> root;
    private Comparator<K> comparator;
    private boolean replaceEqual;
    private ModCount modCount;

    AVLTree(Node<Pair<K, V>> root, Comparator<K> comparator, boolean replaceEqual, ModCount modCount) {
        this.setRoot(root);
        this.setComparator(comparator);
        this.setReplaceEqual(replaceEqual);
        this.setModCount(modCount);
    }

    AVLTree(Comparator<K> comparator, boolean replaceEqual, ModCount modCount) {
        this(null, comparator, replaceEqual, modCount);
    }

    AVLTree(Comparator<K> comparator, boolean replaceEqual) {
        this(null, comparator, replaceEqual, new ModCount());
    }

    AVLTree() {
        this(null, DEFAULT_REPLACE);
    }

    AVLTree(Comparator<K> comparator) {
        this(comparator, DEFAULT_REPLACE);
    }

    AVLTree(boolean replaceEqual) {
        this(null, replaceEqual);
    }

    void insert(K key, V value) {
        Pair<K, V> pair = new Pair<>(key, value);
        this.setRoot(this.insert(this.root, pair));
    }

    private Node<Pair<K, V>> insert(Node<Pair<K, V>> node, Pair<K, V> pair) {
        if (node == null) {
            super.incrementSize();
            this.modCount.incrementCount();
            return new Node<>(pair);
        }

        int cmp = this.compareKeys(pair.getKey(), node.getNodeValue().getKey());
        if (cmp < 0) {
            node.setPrevNode(this.insert(node.getPrevNode(), pair));
        } else if (cmp > 0) {
            node.setNextNode(this.insert(node.getNextNode(), pair));
        } else {
            if (this.replaceEqual) {
                node.setNodeValue(pair);
            }
        }
        node = this.balance(node);
        this.updateHeight(node);

        return node;
    }

    void remove(K key) {
        if (this.root == null) {
            return;
        }
        this.setRoot(this.remove(this.root, key));
    }

    private Node<Pair<K, V>> remove(Node<Pair<K, V>> current, K targetKey) {
        if (current == null) {
            return null;
        }

        int cmp = this.compareKeys(targetKey, current.getNodeValue().getKey());
        if (cmp < 0) {
            current.setPrevNode(this.remove(current.getPrevNode(), targetKey));
            current = this.balance(current);
            this.updateHeight(current);
            return current;
        } else if (cmp > 0) {
            current.setNextNode(this.remove(current.getNextNode(), targetKey));
            current = this.balance(current);
            this.updateHeight(current);
            return current;
        }

        if (current.getPrevNode() == null && current.getNextNode() == null) {
            current = null;
        } else if (current.getPrevNode() == null) {
            current = current.getNextNode();
        } else if (current.getNextNode() == null) {
            current = current.getPrevNode();
        } else {
            current = this.replaceNode(current);
            current = this.balance(current);
            this.updateHeight(current);
        }
        super.decrementSize();
        this.modCount.incrementCount();

        return current;
    }

    private Node<Pair<K, V>> replaceNode(Node<Pair<K, V>> node) {
        Node<Pair<K, V>> replacement = node.getNextNode();
        Node<Pair<K, V>> parent = null;

        while (replacement.getPrevNode() != null) {
            parent = replacement;
            replacement = replacement.getPrevNode();
        }
        if (parent != null) {
            parent.setPrevNode(null);
        }

        replacement = this.setParentToMostRight(replacement, parent);
        replacement.setPrevNode(node.getPrevNode());
        return replacement;
    }

    private Node<Pair<K, V>> setParentToMostRight(Node<Pair<K, V>> replacement, Node<Pair<K, V>> parent) {
        if (replacement.getNextNode() == null) {
            if (parent != null) {
                parent = this.balance(parent);
                this.updateHeight(parent);
            }
            replacement.setNextNode(parent);
            return replacement;
        }
        this.setParentToMostRight(replacement.getNextNode(), parent);
        return replacement;
    }

    Node<Pair<K, V>> findMin() {
        return this.findMin(null, this.root, true);
    }

    private Node<Pair<K, V>> findMin(Node<Pair<K, V>> parent, Node<Pair<K, V>> current, boolean inclusive) {
        if (current.getPrevNode() == null) {
            return inclusive ? current : parent;
        }
        return this.findMin(current, current.getPrevNode(), inclusive);
    }

    Node<Pair<K, V>> findMax() {
        return this.findMax(null, this.root, true);
    }

    private Node<Pair<K, V>> findMax(Node<Pair<K, V>> parent, Node<Pair<K, V>> current, boolean inclusive) {
        if (current.getNextNode() == null) {
            return inclusive ? current : parent;
        }
        return this.findMax(current, current.getNextNode(), inclusive);
    }

    Node<Pair<K, V>> find(K key) {
        return this.find(key, this.root);
    }

    private Node<Pair<K, V>> find(K key, Node<Pair<K, V>> node) {
        if (node == null) {
            return null;
        }
        int cmp = this.compareKeys(key, node.getNodeValue().getKey());
        if (cmp == 0) {
            return node;
        } else if (cmp > 0) {
            return this.find(key, node.getNextNode());
        } else {
            return this.find(key, node.getPrevNode());
        }
    }

    AVLTree<K, V> range(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive) {
        if (this.compareKeys(fromKey, toKey) > 0) {
            throw new IllegalArgumentException("fromKey must be <= toKey");
        }
        Node<Pair<K, V>> fromNode;
        if (fromKey == null) {
            fromNode = this.findMin(null, this.root, fromInclusive);
        } else {
            fromNode = this.fromKey(null, this.root, fromKey, fromInclusive);
        }
        Node<Pair<K, V>> toNode;
        if (toKey == null) {
            toNode = this.findMax(null, this.root, toInclusive);
        } else {
            toNode = this.toKey(null, this.root, toKey, toInclusive);
        }
        return new SubTree<>(this.root, this.comparator, this.replaceEqual, this.modCount, fromNode, toNode);
    }

    private Node<Pair<K, V>> fromKey(Node<Pair<K, V>> temp, Node<Pair<K, V>> current, K target, boolean inclusive) {
        if (current == null) {
            return temp;
        }
        int targetCmp = this.compareKeys(target, current.getNodeValue().getKey());
        int tempCmp = this.nodeKeyCompare(temp, current);
        if (tempCmp < 0 && targetCmp > 0) {//curr > temp && curr < target
            temp = current;
        }
        if (targetCmp > 0) {
            return this.fromKey(temp, current.getNextNode(), target, inclusive);
        } else if (targetCmp < 0) {
            return this.fromKey(temp, current.getPrevNode(), target, inclusive);
        } else {
            if (inclusive) {
                return current;
            } else {
                Node<Pair<K, V>> node = this.findMin(null, current.getNextNode(), true);
                return node != null ? node : temp;
            }
        }
    }

    private Node<Pair<K, V>> toKey(Node<Pair<K, V>> temp, Node<Pair<K, V>> current, K target, boolean inclusive) {
        if (current == null) {
            return temp;
        }
        int targetCmp = this.compareKeys(target, current.getNodeValue().getKey());
        int tempCmp = this.nodeKeyCompare(temp, current);
        if (tempCmp > 0 && targetCmp < 0) {//curr < temp && curr > target
            temp = current;
        }
        if (targetCmp > 0) {
            return this.toKey(temp, current.getNextNode(), target, inclusive);
        } else if (targetCmp < 0) {
            return this.toKey(temp, current.getPrevNode(), target, inclusive);
        } else {
            if (inclusive) {
                return current;
            } else {
                Node<Pair<K, V>> node = this.findMax(null, current.getPrevNode(), true);
                return node != null ? node : temp;
            }
        }
    }

    private int nodeKeyCompare(Node<Pair<K, V>> first, Node<Pair<K, V>> second) {
        if (first == null) {
            return -1;
        }
        if (second == null) {
            return 1;
        }
        return this.compareKeys(first.getNodeValue().getKey(), second.getNodeValue().getKey());
    }

    Iterator<Node<Pair<K, V>>> ascIterator() {
        return new AscTreeIterator<>(this, this.root);
    }

    Iterator<Node<Pair<K, V>>> descIterator() {
        return new DescTreeIterator<>(this, this.root);
    }

    @Override
    public void clear() {
        this.clearTree(this.root);
        this.setRoot(null);
        super.clearSize();
    }

    private void clearTree(Node<Pair<K, V>> node) {
        if (node == null) {
            return;
        }
        this.removeChild(node, node.getPrevNode(), true);
        this.clearTree(node.getPrevNode());
        this.removeChild(node, node.getNextNode(), false);
        this.clearTree(node.getNextNode());
    }

    private void removeChild(Node<Pair<K, V>> parent, Node<Pair<K, V>> child, boolean isChildPrev) {
        if (child != null) {
            if (child.getNextNode() != null && child.getNextNode() != null) {
                if (isChildPrev) {
                    parent.setPrevNode(null);
                } else {
                    parent.setNextNode(null);
                }
            }
        }
    }

    @Override
    public int size() {
        return super.getSize();
    }

    private Node<Pair<K, V>> balance(Node<Pair<K, V>> node) {
        int balanceFactor = this.getHeight(node.getPrevNode()) - this.getHeight(node.getNextNode());
        if (balanceFactor < -1) {
            int rightChildBalanceFactor = this.getHeight(node.getNextNode().getPrevNode()) - this.getHeight(node.getNextNode().getNextNode());
            if (rightChildBalanceFactor > 0) {
                node.setNextNode(this.rotateRight(node.getNextNode()));
            }
            node = this.rotateLeft(node);
        } else if (balanceFactor > 1) {
            int leftChildBalanceFactor = this.getHeight(node.getPrevNode().getPrevNode()) - this.getHeight(node.getPrevNode().getNextNode());
            if (leftChildBalanceFactor < 0) {
                node.setPrevNode(this.rotateLeft(node.getPrevNode()));
            }
            node = this.rotateRight(node);
        }
        return node;
    }

    private Node<Pair<K, V>> rotateRight(Node<Pair<K, V>> node) {
        Node<Pair<K, V>> leftChild = node.getPrevNode();
        node.setPrevNode(leftChild.getNextNode());
        this.updateHeight(node);
        leftChild.setNextNode(node);
        this.updateHeight(leftChild);
        return leftChild;
    }

    private Node<Pair<K, V>> rotateLeft(Node<Pair<K, V>> node) {
        Node<Pair<K, V>> rightChild = node.getNextNode();
        node.setNextNode(rightChild.getPrevNode());
        this.updateHeight(node);
        rightChild.setPrevNode(node);
        this.updateHeight(rightChild);
        return rightChild;
    }

    private void updateHeight(Node<Pair<K, V>> node) {
        int newHeight = Math.max(this.getHeight(node.getPrevNode()), this.getHeight(node.getNextNode())) + 1;
        node.setHeight(newHeight);
    }

    private int getHeight(Node<Pair<K, V>> node) {
        if (node == null) {
            return 0;
        }
        return node.getHeight();
    }

    int compareKeys(K firstKey, K secondKey) {
        if (this.comparator != null) {
            return this.comparator.compare(firstKey, secondKey);
        } else {
            return firstKey.compareTo(secondKey);
        }
    }

    ModCount getModCount() {
        return this.modCount;
    }

    private void setRoot(Node<Pair<K, V>> root) {
        this.root = root;
    }

    private void setComparator(Comparator<K> comparator) {
        this.comparator = comparator;
    }

    private void setReplaceEqual(boolean replaceEqual) {
        this.replaceEqual = replaceEqual;
    }

    private void setModCount(ModCount modCount) {
        this.modCount = modCount;
    }
}