package com.company;

import java.util.Objects;

public class MyHashMap<K, V> implements MyMap<K, V> {
    private Node[] nodesArr;
    private double loadFactor = 0.75;
    private int threshold = 12;
    private int size = 16;
    private int capacity;

    MyHashMap() {
        nodesArr = new MyHashMap.Node[size];
    }

    public void setLoadFactor(double loadFactor) {
        this.loadFactor = loadFactor;
    }

    @Override
    public V put(K key, V value) {
        if (key != null) {
            remove(key);
            capacity++;
            if (capacity >= threshold) {
                resize();
            }
            putVal(hashIndex(key), value, key);
            return value;
        }
        return null;
    }

    public int hashIndex(K key) {
        if (key == null) {
            return 0;
        }
        return Math.abs(hashCode(key)) % nodesArr.length;
    }

    public int hashCode(K key) {
        return Objects.hashCode(key);
    }

    private void putVal(int index, V value, K key) {
        if (isEmpty(nodesArr[index])) {
            nodesArr[index] = new Node(hashCode(key), key, value, null);
        } else {
            Node currentNode = nodesArr[index];
            while (!isEmpty(currentNode.next)) {
                currentNode = currentNode.next;
            }
            currentNode.next = new Node(hashCode(key), key, value, null);
        }
    }

    private void resize() {
        int size = nodesArr.length;
        Node[] tempNode = new MyHashMap.Node[size * 2];
        System.arraycopy(nodesArr, 0, tempNode, 0, size);
        nodesArr = tempNode;
        this.size = size;
        threshold = (int) (nodesArr.length * loadFactor);
    }

    @Override
    public V remove(K key) {
        V val = null;
        if (key != null) {
                int index = hashIndex(key);
                val = removeVal(index, key);
        }
        return val;
    }

    private V removeVal(int index, K key) {
        if (!isEmpty(nodesArr[index])) {
            if (nodesArr[index].key.equals(key)) {
                Node currNode = nodesArr[index];
                nodesArr[index] = nodesArr[index].next;
                return currNode.value;
            } else {
                Node lastNode = nodesArr[index];
                Node currNode = nodesArr[index].next;
                if (currNode.key.equals(key)) {
                    lastNode.next = currNode.next;
                    return currNode.value;
                }
                while (!isEmpty(currNode.next)) {
                    lastNode = currNode;
                    currNode = lastNode.next;
                    if (nodesArr[index].key.equals(key)) {
                        lastNode.next = currNode.next;
                        return currNode.value;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public void clear() {
        threshold = (int) (loadFactor * size);
        capacity = 0;
        nodesArr = new MyHashMap.Node[size];
    }

    @Override
    public int size() {
        return nodesArr.length;
    }

    @Override
    public V get(K key) {
        V val = null;
        if (key != null) {
                int index = hashIndex(key);
                val = getVal(index, key);
        }
        return val;
    }

    private V getVal(int index, K key) {
        if (!isEmpty(nodesArr[index])) {
            if (nodesArr[index].key.equals(key)) {
                return nodesArr[index].value;
            } else {
                Node currNode = nodesArr[index].next;
                if (currNode.key.equals(key)) {
                    return currNode.value;
                }
                while (!isEmpty(currNode.next)) {
                    currNode = currNode.next;
                    if (nodesArr[index].key.equals(key)) {
                        return currNode.value;
                    }
                }
            }
        }
        return null;
    }

    public boolean isEmpty() {
        return capacity == 0;
    }

    private boolean isEmpty(Node node) {
        return node == null;
    }

    public boolean containsKey(K key) {
        return get(key) != null;
    }


    @Override
    public String toString() {
        StringBuilder stringValue = new StringBuilder("");
        for (int i = 0; i < nodesArr.length; i++) {
            try {
                Node currentNode = nodesArr[i];
                stringValue.append(currentNode.key).append(" ").append(nodesArr[i].value).append("\n");
                while (currentNode.next != null) {
                    currentNode = currentNode.next;
                    stringValue.append(currentNode.key).append(" ").append(currentNode.value).append("\n");
                }
            } catch (Exception e) {
            }
        }
        return stringValue.toString();
    }

    class Node {
        int hashcode;
        K key;
        V value;
        Node next;

        Node(int hashcode, K key, V value, Node next) {
            this.hashcode = hashcode;
            this.key = key;
            this.value = value;
            this.next = next;
        }

    }
}
