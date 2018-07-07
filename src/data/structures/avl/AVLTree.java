package data.structures.avl;
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

import data.structures.map.Map;

import java.util.ArrayList;
import java.util.List;

/**
 *  avl 平衡二叉树
 *
 * @author cuisongliu [cuisongliu@qq.com]
 * @since 2018-06-30 15:49
 */
public class AVLTree<K extends Comparable<K>, V> implements Map<K, V> {
    private class Node {
        public K key;
        public V value;
        public Node left, right;
        public int height;
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            height=1;
        }

    }

    private Node root;
    private Integer size;

    public AVLTree() {
        root = null;
        size = 0;
    }

    @Override
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    private int getHeight(Node node){
        if (node == null) return 0;
        return node.height;
    }
    //计算平衡因子
    private int getBalanceFactor(Node node){
        if (node == null) return 0;
        return getHeight(node.left) - getHeight(node.right);
    }
    //是否为二分搜索树
    private boolean isBST(Node node){
        List<K> keys = new ArrayList<>();
        order(node,keys);
        for (int i=1;i<getSize();i++){
            if (keys.get(i-1).compareTo(keys.get(i))> 0 ) return false;
        }
        return true;
    }
    //是否为平衡二叉树 左右孩子结点高度之差不能大于1
    private boolean isBalance(Node node){
        if (node!=null){
            int balanceFactor = getBalanceFactor(node);
            if (Math.abs(balanceFactor) >1){
                //需要调整树
                return false;
            }else {
                return  isBalance(node.left) && isBalance(node.right);
            }
        }
        return true;
    }
    private void order(Node node, List<K> keys) {
        if (node != null) {
            order(node.left,keys);
            keys.add(node.key);
            order(node.right,keys);
        }
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
            //更新height
            node.height = 1+ Math.max(getHeight(node.left),getHeight(node.right));
            // 平衡维护
            int balanceFactor = getBalanceFactor(node);
            //LL
            if (balanceFactor >1 &&  getBalanceFactor(node.left) >=0){
                //需要调整树
                //右旋转
                return rightRotate(node);
            }
            //RR
            if (balanceFactor < -1  &&  getBalanceFactor(node.right) <=0){
                //需要调整树
                //左旋转
                return leftRotate(node);
            }
            //LR
            if (balanceFactor >1  &&  getBalanceFactor(node.left) < 0){
                //需要调整树
                //左旋转
                node.left = leftRotate(node.left);
                //右旋转
                return rightRotate(node);
            }
            //RL
            if (balanceFactor < -1  &&  getBalanceFactor(node.right) > 0){
                //需要调整树
                //右旋转
                node.right = rightRotate(node.right);
                //左旋转
                return leftRotate(node);
            }
        }
        return node;
    }
    // 对结点Y 向右旋转 返回旋转后的新结点
    //         y                                      x
    //        / \                                   /   \
    //       x  T4     向右旋转(y)                  z    y
    //      / \        --------->                 /\   / \
    //     z  T3                                T1 T2 T3 T4
    //    / \
    //  T1  T2
    private Node rightRotate(Node y){

//        x.right = y
//        y.left  = T3
        Node x = y.left;
        Node T3 = x.right;
        //向右旋转
        x.right = y;
        y.left = T3;
        //更新height
        y.height = 1+ Math.max(getHeight(y.left),getHeight(y.right));
        x.height = 1+ Math.max(getHeight(x.left),getHeight(x.right));

        return x;
    }
    // 对结点Y 向左旋转 返回旋转后的新结点
    //         y                                      x
    //        / \                                   /  \
    //       T4 x     向左旋转(y)                  y     z
    //         / \    ---------->                /\    / \
    //       T3  z                              T4 T3 T1 T2
    //          / \
    //         T1 T2
    private Node leftRotate(Node y){

//        x.left  = y
//        y.right = T3
        Node x = y.right;
        Node T3 = x.left;
        //向左旋转
        x.left = y;
        y.right = T3;
        //更新height
        y.height = 1+ Math.max(getHeight(y.left),getHeight(y.right));
        x.height = 1+ Math.max(getHeight(x.left),getHeight(x.right));

        return x;
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
