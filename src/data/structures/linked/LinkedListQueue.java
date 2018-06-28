package data.structures.linked;
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
 * 链表队列的实现
 *
 * @author cuisongliu [cuisongliu@qq.com]
 * @since 2018-06-27 下午12:56
 */
public class LinkedListQueue<E> implements Queue<E> {
    private Node head;
    private Node tail;
    private Integer size;
    class Node{
        E e;
        Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e){
            this(e,null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    public LinkedListQueue() {
        head = null;
        tail = null;
        size = 0 ;
    }

    @Override
    public Integer getSize() {
        return this.size;
    }

    @Override
    public Boolean isEmpty() {
        return size==0;
    }

    @Override
    public void enqueue(E e) {
        if (tail==null){
            tail = new Node(e);
            head = tail;
        }else {
            tail.next = new Node(e);
            tail = tail.next;
        }

        size ++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()){
            throw  new IllegalArgumentException("Not dequeue");
        }
        Node ret = head;
        head = head.next;
        ret.next = null;
        if (head ==null){
            tail = null;
        }
        size--;
        return ret.e;
    }

    @Override
    public E getFront() {
        if (isEmpty()){
            throw  new IllegalArgumentException("Not dequeue");
        }
        return head.e;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Queue: ");
        sb.append("front[");
        Node curr = head;
        while (curr!=null){
            sb.append(curr).append("->");
            curr = curr.next;
        }
        sb.append("Null ] tail ");
        return sb.toString();
    }
}
