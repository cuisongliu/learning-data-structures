package data.structures.heap;
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

import data.structures.stack.Array;

/**
 * 最小堆
 *
 * @author cuisongliu [cuisongliu@qq.com]
 * @since 2018-06-30 21:44
 */
public class MinHeap<E extends Comparable<E>> {
    private Array<E> array;

    public MinHeap(Integer capacity) {
        array = new Array<>(capacity);
    }

    public MinHeap() {
        array = new Array<>();
    }

    public MinHeap(E[] arr, Boolean isWhile){
        array = new Array<>(arr);
        //找到最后一个非叶子节点
        for (int i =parent(arr.length-1);i>=0;i--){
            if (isWhile)
                siftDownWhile(i);
            else
                siftDown(i);
        }
    }
    public Integer getCapacity() {
        return array.getCapacity();
    }

    //返回堆中元素的个数
    public Integer getSize() {
        return array.getSize();
    }

    //返回堆是否为空
    public Boolean isEmpty() {
        return array.isEmpty();
    }

    // 返回完全二叉树的数组表现形式 一个索引所表示的元素的父亲结点的索引
    private Integer parent(Integer index) {
        if (index <= 0) throw new IllegalArgumentException("index is invalid.");
        return (index - 1) / 2;
    }

    // 返回完全二叉树的数组表现形式 一个索引所表示的元素的左孩子结点的索引
    private Integer leftChild(Integer index) {
        if (index < 0) throw new IllegalArgumentException("index is invalid.");
        return 2*index +1 ;
    }

    // 返回完全二叉树的数组表现形式 一个索引所表示的元素的右孩子结点的索引
    private Integer rightChild(Integer index) {
        if (index < 0) throw new IllegalArgumentException("index is invalid.");
        return 2*index +2 ;
    }

    public void add(E e){
        array.addLast(e);
        siftUp(array.getSize()-1);

    }
    //需要上浮的操作 越小越顶
    private void siftUp(Integer index){
        //若当前的值 大于父亲节点的值 则需要交换数据 完成大顶堆的数据结构
        while (index > 0 &&  array.get(index).compareTo(array.get(parent(index))) < 0 ){
            array.swap(index,parent(index));
            index = parent(index);
        }
    }
    public E findMin(){
        if (isEmpty()){
            throw new IllegalArgumentException("maxheap is empty.");
        }
        return array.get(0);
    }
    //取出堆中最小元素
    public E extractMin(){
        E returnObj = findMin();
        array.set(0,array.getLast());
        array.removeLast();
        //siftDown
        siftDown(0);
        //做堆性质的下沉操作
        return returnObj;
    }
    public E extractMinWhile(){
        E returnObj = findMin();
        array.set(0,array.getLast());
        array.removeLast();
        //siftDown
        siftDownWhile(0);
        //做堆性质的下沉操作
        return returnObj;
    }
    //取出堆中最小元素 并且替换成元素e
    public E replace(E e){
        E returnObj = findMin();
        array.set(0,e);
        //siftDown
        siftDown(0);
        //做堆性质的下沉操作
        return returnObj;
    }
    //如何计算最后一个非叶子节点的index？？ 获取最后一个值的父亲节点


    //下沉操作
    private void siftDown(Integer index){
        //获取出左边孩子和右边孩子的节点索引
        Integer leftIndex = leftChild(index);
        Integer rightIndex =rightChild(index);
        Integer nodeIndex =  getMinNode(index);//判断3个节点谁最小
        if (nodeIndex ==1){
            //左边
            array.swap(leftIndex,index);
            siftDown(leftIndex);
        }
        if (nodeIndex ==2){
            //右边
            array.swap(rightIndex,index);
            siftDown(rightIndex);
        }
    }
    private Integer getMinNode(Integer index){
        Integer leftIndex = leftChild(index);
        Integer rightIndex =rightChild(index);
        Integer compareIndex = leftIndex;
        //如果右孩子比左孩子小 则切换待比较索引
        if (rightIndex < array.getSize()-1 && array.get(leftIndex).compareTo(array.get(rightIndex)) > 0) {
            compareIndex = rightIndex;
        }
        //比较需要比较的索引和父节点比较数据即可
        if (array.get(index).compareTo(array.get(compareIndex))> 0)  {
            if (compareIndex .equals(leftIndex)) return 1;
            return 2;
        }else {
            return 0;
        }
    }
    private void siftDownWhile(Integer index){
        while (leftChild(index)<array.getSize()){
            int j = leftChild(index);
            if (j+1 < array.getSize()&& array.get(j+1).compareTo(array.get(j))<0){
                j=rightChild(index);
            }
            //array[j] 是左右中最大值
            if (array.get(index).compareTo(array.get(j))<=0)break;
            array.swap(index,j);
            index = j;
        }
    }

    @Override
    public String toString() {
        return "MaxHeap{" +
                "array=" + array +
                '}';
    }
}
