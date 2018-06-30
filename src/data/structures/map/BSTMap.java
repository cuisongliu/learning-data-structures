package data.structures.map;
/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 cuisongliu@qq.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

/**
 * 二分搜索树Map
 *
 * @author cuisongliu [cuisongliu@qq.com]
 * @since 2018-06-30 15:49
 */
public class BSTMap<K extends Comparable<K>, V> implements Map<K, V> {
    private class Node {
        public K key;
        public V value;
        public Node left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }

    }

    private Node root;
    private Integer size;

    public BSTMap() {
        root = null;
        size = 0;
    }

    @Override
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    //递归
    //返回插入新节点后的二分搜索树
    private Node add(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        } else {
            if (node.key.compareTo(key) > 0) {
                node.left = add(node.left, key, value);
            } else if (node.key.compareTo(key) < 0) {
                node.right = add(node.right, key, value);
            } else {
                node.value = value;
            }
        }
        return node;
    }

    private Node getNode(Node node, K key) {
        if (node == null) return null;
        if (node.key.compareTo(key) > 0) {
            return getNode(node.left, key);
        } else if (node.key.compareTo(key) < 0) {
            return getNode(node.right, key);
        } else {
            return node;
        }
    }

    private Node minimum(Node node) {
        if (node.left == null) return node;
        return minimum(node.left);
    }

    private Node removeMin(Node node) {
        if (node.left == null) {
            Node right = node.right;
            node.right = null;
            size--;
            return right;
        }
        node.left = removeMin(node.left);
        return node;
    }

    private Node remove(Node node, K key) {
        if (node == null) return null;

        if (node.key.compareTo(key) > 0) {
            node.left = remove(node.left, key);
            return node;
        } else if (node.key.compareTo(key) < 0) {
            node.right = remove(node.right, key);
            return node;
        } else {
            if (node.left == null) {
                Node right = node.right;
                node.right = null;
                size--;
                return right;
            }
            if (node.right == null) {
                Node left = node.left;
                node.left = null;
                size--;
                return left;
            }
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;
            node.left = node.right = null;
            return successor;
        }
    }

    @Override
    public V remove(K key) {
        Node retNode = getNode(root, key);
        if (retNode != null)
            return remove(root, key).value;
        else return null;
    }

    @Override
    public Boolean contains(K key) {
        Node retNode = getNode(root, key);
        return retNode != null;
    }

    @Override
    public V get(K key) {
        Node retNode = getNode(root, key);
        return retNode != null ? retNode.value : null;
    }

    @Override
    public void set(K key, V value) {
        Node retNode = getNode(root, key);
        if (retNode == null) {
            throw new IllegalArgumentException(key + "doesn't exist.");
        } else {
            retNode.value = value;
        }
    }

    @Override
    public Integer getSize() {
        return size;
    }

    @Override
    public Boolean isEmpty() {
        return size == 0;
    }
}
