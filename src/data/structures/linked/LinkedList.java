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
 * 鏈錶
 *
 * @author cuisongliu [cuisongliu@qq.com]
 * @since 2018-06-21 0:23
 */
public class LinkedList<E> {
    private Node head;
    private Integer size;
    private class Node{
        public E e;
        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e,null);
        }

        public Node() {
            this(null,null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    public LinkedList() {
        head = null;
        size =  0;
    }
    //返回链表中元素的个数
    public Integer getSize(){
        return  this.size;
    }
    //返回链表是否为空
    public Boolean isEmpty(){
        return size ==0;
    }
    // 在链表头添加元素
    public void addFirst(E e){
        head = new Node(e,head);
        size ++;
    }
    // 在链表的index(0-based)位置添加新的元素e
    // 在链表的中不是一个常用的操作 练习用
    public void add(E e,Integer index){
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed.Illage index.");
        if (index == 0 ){
            addFirst(e);
        }else {
            Node prev = head;
            //取到需要更改next地址的值
            for (int i=0; i < index -1 ;i++){
                prev = prev.next;
            }
            prev.next = new Node(e,prev.next);
            size ++ ;
        }
    }
    //在链表末尾添加新的元素 e
    public void addLast(E e){
        add(e,this.size);
    }

}
