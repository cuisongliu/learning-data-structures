package data.structures.rbtree;
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

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author cuisongliu [cuisongliu@qq.com]
 * @since 2018-07-10 22:03
 */
public class RBTree <E extends Comparable<E>> {

    private static final Boolean RED= true;
    private static final Boolean BLACK= false;

    private class Node {
        E e;
        Node left, right;
        private Boolean color;
        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
            color = RED;
        }
    }

    private Node root;
    private Integer size;

    public RBTree() {
        root = null;
        size = 0;
    }

    private Boolean isRed(Node node){
        if (node==null) return BLACK;
        return node.color;
    }

    public Integer getSize() {
        return this.size;
    }

    public Boolean isEmpty() {
        return size == 0;
    }

    public void add(E e) {
        root = add(root, e);
        root.color = BLACK;
    }

    //递归
    //返回插入新节点后的红黑树树
    private Node add(Node node, E e) {
        if (node == null) {
            size++;
            return new Node(e);
        } else {
            if (node.e.compareTo(e) > 0) {
                node.left = add(node.left, e);
            } else if (node.e.compareTo(e) < 0) {
                node.right = add(node.right, e);
            }
        }

        if (isRed(node.right) && !isRed(node.left)){
            //需要左旋转
            node = leftRotate(node);
        }
        if (isRed(node.left) && isRed(node.left.left)){
            //需要左旋转
            node = rightRotate(node);
        }
        if (isRed(node.right) && isRed(node.left)){
            //需要颜色翻转
           flipColors(node);
        }
        return node;
    }
    // 对结点Y 向左旋转 返回旋转后的新结点
    //         node                                   x
    //        / \                                   /  \
    //       T1 x     向左旋转(node)              node  T3
    //         / \    ---------->                /\
    //       T2  T3                            T1 T2
    private Node leftRotate(Node node){
        Node x = node.right;
        //左旋转
        node.right= x.left;
        x.left = node;
        //颜色设置
        x.color = node.color;
        node.color = RED;
        return x;
    }
    // 对结点Y 向右旋转 返回旋转后的新结点
    //         node                                   x
    //        / \                                   /   \
    //       x  T2     向右旋转(y)                  y    node
    //      / \        --------->                        / \
    //     y  T1                                        T1 T2
    private Node rightRotate( Node node){
        Node x = node.left;
       //右旋转
        node.left = x.right;
        x.right = node;
        //颜色设置
        x.color = node.color;
        node.color = RED;
        return x;
    }
    //颜色翻转
    private void flipColors(Node node ){
        node.color = RED;
        node.left.color=BLACK;
        node.right.color=BLACK;
    }

    //看二分搜索树上是否包含元素
    public boolean contains(E e) {
        return contains(root, e);
    }

    private boolean contains(Node node, E e) {
        if (node == null) {
            return false;
        } else {
            if (node.e.compareTo(e) > 0) {
                return contains(node.left, e);
            } else if (node.e.compareTo(e) < 0) {
                return contains(node.right, e);
            } else {
                return true;
            }
        }
    }

    //前序遍历 最自然的遍历方式  root -> 左子树 -> 右子树
    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.e + "->");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    //中序遍历 排序后 排序树  左子树 -> root -> 右子树
    public void order() {
        order(root);
    }

    private void order(Node node) {
        if (node != null) {
            order(node.left);
            System.out.print(node.e + "->");
            order(node.right);
        }
    }

    //后序遍历     左子树 -> 右子树 -> root
    public void sufOrder() {
        sufOrder(root);
    }

    private void sufOrder(Node node) {
        if (node != null) {
            sufOrder(node.left);
            sufOrder(node.right);
            System.out.print(node.e + "->");
        }
    }

    // 二分搜索树的非分递归前序遍历
    // TODO
    public void preOrderNR() {
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node curr = stack.pop();
            System.out.print(curr.e + "->");
            if (curr.right != null) {
                stack.push(curr.right);
            }
            if (curr.left != null) {
                stack.push(curr.left);
            }
        }
    }

    // 广度优先遍历 之前都是深度优先遍历方式
    public void levelOrder() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node curr = queue.remove();
            System.out.print(curr.e + "->");
            if (curr.left != null) {
                queue.add(curr.left);
            }
            if (curr.right != null) {
                queue.add(curr.right);
            }
        }
    }

    //寻找二分搜索树的最小元素
    public E minimum() {
        if (isEmpty()) throw new IllegalArgumentException("BST is empty.");
        return minimum(root).e;
    }

    private Node minimum(Node node) {
        if (node.left == null) return node;
        return minimum(node.left);
    }


    //寻找二分搜索树的最大元素
    public E maximum() {
        if (isEmpty()) throw new IllegalArgumentException("BST is empty.");
        return maximum(root).e;
    }

    private Node maximum(Node node) {
        if (node.right == null) return node;
        return maximum(node.right);
    }

    //删除最小元素 并返回元素
    public E removeMin() {
        E ret = minimum();
        root = removeMin(root);
        return ret;
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


    //删除最大元素 并返回元素
    public E removeMax() {
        E ret = maximum();
        root = removeMax(root);
        return ret;
    }

    private Node removeMax(Node node) {
        if (node.right == null) {
            Node left = node.left;
            node.left = null;
            size--;
            return left;
        }
        node.right = removeMin(node.right);
        return node;
    }

    //从二分搜索树中删除元素e
    public void remove(E e) {
        root = remove(root, e);
    }

    private Node remove(Node node, E e) {
        if (node == null) return null;

        if (node.e.compareTo(e) > 0) {
            node.left = remove(node.left, e);
            return node;
        } else if (node.e.compareTo(e) < 0) {
            node.right = remove(node.right, e);
            return node;
        } else {
            if (node.left == null){
                Node right = node.right;
                node.right = null;
                size--;
                return right;
            }
            if (node.right ==null){
                Node left = node.left;
                node.left = null;
                size--;
                return left;
            }
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left=node.left;
            node.left=node.right=null;
            return successor;
        }
    }
}
