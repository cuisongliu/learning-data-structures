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
 * 链表Map
 *
 * @author cuisongliu [cuisongliu@qq.com]
 * @since 2018-06-30 15:49
 */
public class LinkedListMap<K,V> implements Map<K,V> {

    private class Node {
        public K key;
        public V value;
        public Node next;

        public Node(K key,V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node(K key,V value) {
            this(key,value, null);
        }
        public Node(K key) {
            this(key,null, null);
        }
        public Node( ) {
            this(null,null, null);
        }

        @Override
        public String toString() {
            return key.toString()+":"+value.toString();
        }
    }

    private Node dummyHead;
    private Integer size;

    public LinkedListMap() {
        dummyHead = new Node();
        size = 0;
    }

    private Node getNode(K key){
        Node cur = dummyHead.next;
        while (cur!=null){
            if (cur.key.equals(key))return cur;
            cur = cur.next;
        }
        return null;
    }

    @Override
    public void add(K key, V value) {
        Node node =getNode(key);
        if (node == null) {
            dummyHead.next = new Node(key,value,dummyHead.next);
            size++;
        }else {
            node.value =value;
        }
    }

    @Override
    public V remove(K key) {
        Node prev = dummyHead;
        while (prev.next!=null){
            if (prev.next.key.equals(key))break;
            prev=prev.next;
        }
        if (prev.next!=null){
            Node delNode = prev.next;
            prev.next = delNode.next;
            size--;
            delNode.next=null;
            return delNode.value;
        }
        return null;
    }

    @Override
    public Boolean contains(K key) {
        return getNode(key)!=null;
    }

    @Override
    public V get(K key) {
        Node node =getNode(key);
        return node==null?null:node.value;
    }

    @Override
    public void set(K key, V value) {
        Node node =getNode(key);
        if (node == null) {
            throw new IllegalArgumentException(key+"doesn't exist.");
        }else {
            node.value =value;
        }
    }

    @Override
    public Integer getSize() {
        return size;
    }

    @Override
    public Boolean isEmpty() {
        return size==0;
    }
}
