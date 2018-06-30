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
 * 最大堆
 *
 * @author cuisongliu [cuisongliu@qq.com]
 * @since 2018-06-30 21:44
 */
public class MaxHeap<E extends Comparable<E>> {
    private Array<E> array;

    public MaxHeap(Integer capacity) {
        array = new Array<>(capacity);
    }

    public MaxHeap() {
        array = new Array<>();
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
}
