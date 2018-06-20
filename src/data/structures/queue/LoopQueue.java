package data.structures.queue;
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

import java.util.Objects;

/**
 * 循环队列
 *
 * @author cuisongliu [cuisongliu@qq.com]
 * @since 2018-06-20 23:39
 */
public class LoopQueue<E> implements Queue<E> {

    private E[] data;

    private Integer front;
    private Integer tail;
    private Integer size;

    public LoopQueue(Integer capacity) {
        data = (E[]) new Object[capacity +1 ];
        front = 0;
        tail  = 0;
        size  = 0;
    }

    public LoopQueue() {
       this(10);
    }

    public Integer getCapacity() {
        return data.length-1;
    }

    @Override
    public Integer getSize() {
        return size;
    }

    @Override
    public Boolean isEmpty() {
        return Objects.equals(front, tail);
    }

    public Boolean isFull() {
        // 判斷是否隊列滿
        return (tail+1) % data.length == front ;
    }

    @Override
    public void enqueue(E e) {
        if (isFull()){
            resize(getCapacity() * 2);
        }
        data[tail] = e;
        tail = (tail +1) % data.length;
        size ++ ;
    }

    @Override
    public E dequeue() {
        if (isEmpty()){
            throw new IllegalArgumentException("queue not empty.");
        }
        E e = data[front];
        data[front] = null;
        front = (front+1) % data.length;
        size--;
        if (size == getCapacity() /4 && getCapacity()/2 !=0){
            resize(getCapacity()/2);
        }
        return e;
    }

    private void resize(Integer newSize){
        E[] newData = (E[]) new Object[newSize +1 ];
        for (int i=0; i < size ; i++){
            newData[i] = data[(front + i ) % data.length];
        }
        front = 0 ;
        tail = size;
        data = newData;
    }

    @Override
    public E getFront() {
        if (isEmpty()){
            throw new IllegalArgumentException("queue not empty.");
        }
        return data[front];
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue: size = %d , capacity = %d\n", size, getCapacity()));
        res.append("front [");
        for (int i = front; i!=tail; i = (i+1)%data.length){
            res.append(data[i]);
            if ((i +1) % data.length !=tail ){
                res.append(", ");
            }
        }
        res.append("] tail");
        return res.toString();
    }
}
