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
        remove(key);
        capacity++;
        if (capacity >= threshold) {
            resize();
        }
        if (key == null) {
            putVal(0, value, key);
            return value;
        }
        putVal(hashIndex(key), value, key);
        return value;
    }

    public int hashIndex(K key) {
        if (key == null) {
            return 0;
        }
        return hashCode(key) & (size - 1);
    }

    public int hashIndex(K key, int n) {
        if (key == null) {
            return 0;
        }
        return hashCode(key) & (n - 1);
    }

    public int hashCode(K key) {
        return Objects.hashCode(key);
    }

    private void putVal(int index, V value, K key) {
        remove(key);
        if (nodesArr[index] == null) {
            nodesArr[index] = new Node(hashCode(key), key, value, null);
        } else {
            Node currentNode = nodesArr[index];
            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }
            currentNode.next = new Node(hashCode(key), key, value, null);

        }
    }

    private void resize() {
        int size = this.size * 2;
        Node[] tempNode = new MyHashMap.Node[size];
        System.arraycopy(nodesArr, 0, tempNode, 0, this.size);
        this.nodesArr = tempNode;
        this.size = size;
        this.threshold = (int) (this.size * loadFactor);
    }

    @Override
    public V remove(K key) {
        if (key == null) {
            if (nodesArr[0].key == null) {
                Node currNode = nodesArr[0];
                nodesArr[0] = currNode.next;
                return currNode.value;
            } else {
                if (nodesArr[0].next != null) {
                    Node currNode = nodesArr[0].next;
                    if (currNode.key == null) {
                        nodesArr[0].next = currNode.next;
                        return currNode.value;
                    }
                    Node lastNode;
                    while (currNode.key != null) {
                        lastNode = currNode;
                        currNode = lastNode.next;
                        if (nodesArr[0].key == null) {
                            lastNode.next = currNode.next;
                            currNode = currNode.next;
                        }
                    }
                }
            }

        } else {
            int sizeArr = 16;
            while (sizeArr <= size) {
                int index = hashIndex(key, sizeArr);
                if (nodesArr[index] != null) {
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
                        while (currNode.next != null) {
                            lastNode = currNode;
                            currNode = lastNode.next;
                            if (nodesArr[index].key.equals(key)) {
                                lastNode.next = currNode.next;
                                return currNode.value;
                            }
                        }
                    }
                }
                sizeArr *= 2;
            }
        }
        return null;
    }

    @Override
    public void clear() {
        loadFactor = 0.75;
        threshold = 12;
        size = 16;
        capacity = 0;
        nodesArr = new MyHashMap.Node[size];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public V get(K key) {
        if (key == null) {
            if (nodesArr[0].key == null) {
                return nodesArr[0].value;
            } else {
                Node currNode = nodesArr[0].next;
                if (currNode != null) {
                    if (currNode.key == null) {
                        return currNode.value;
                    }
                    while (currNode.key != null) {
                        currNode = currNode.next;
                        if (nodesArr[0].key == null) {
                            return currNode.value;
                        }
                    }
                }
            }
        } else {
            int sizeArr = 16;
            while (sizeArr <= size) {
                int index = hashIndex(key, sizeArr);
                if (nodesArr[index] != null) {
                    if (nodesArr[index].key.equals(key)) {
                        return nodesArr[index].value;
                    } else {
                        Node currNode = nodesArr[index].next;
                        if (currNode.key.equals(key)) {
                            return currNode.value;
                        }
                        while (currNode.next != null) {
                            currNode = currNode.next;
                            if (nodesArr[index].key.equals(key)) {
                                return currNode.value;
                            }
                        }
                    }
                }
                sizeArr *= 2;
            }
        }
        return null;
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

        public Node getNode(int a) {
            return null;
        }

        Node(int hashcode, K key, V value, Node next) {
            this.hashcode = hashcode;
            this.key = key;
            this.value = value;
            this.next = next;
        }

    }
}
