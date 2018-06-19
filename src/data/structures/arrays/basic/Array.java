package data.structures.arrays.basic;
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
 * 封装的数组类
 *
 * @author cuisongliu [cuisongliu@qq.com]
 * @since 2018-06-19 20:09
 */
public  class Array<T> {
    private T[] data;
    //第一个没有元素的索引
    private Integer size;
    private static final Integer DEFAULT_CAPACITY = 10;
    public Array(Integer capacity) {
        if (capacity < 10 ){
            capacity = DEFAULT_CAPACITY;
        }
        data = (T[])new Object[capacity];
        size = 0;
    }

    public Array() {
       this(Array.DEFAULT_CAPACITY);
    }

    /**
     * 获取数组中元素个数
     * @return
     */
    public Integer getSize() {
        return size;
    }

    /**
     * 获取数组长度
     * @return
     */
    public Integer getCapacity() {
        return this.data.length;
    }

    public Boolean isEmpty(){
        return this.size==0;
    }
    //在首位添加元素
    public void addFirst(T element){
        add(0,element);
    }
    //在最后添加元素
    public void addLast(T element){
        add(size,element);
    }

    private void resize(Integer newCapacity){
        T[] newData = (T[])new Object[newCapacity];
        for (int i =0;i<size;i++){
            newData[i] = data[i];
        }
        this.data=newData;
    }

    //在第index个位置插入一个新元素
    public void add(int index,T element){
        if (index < 0 || index > size){
            throw new IllegalArgumentException("Add failed.Array is .index >=  0 and index < size.");
        }
        if (data.length == size){
            resize(data.length * 2);
        }

        for (int i = size-1;i>=index;i--){
            data[i+1] =data[i];
        }
        size++;
        data[index] = element;
    }
    //获取index位置的值
    public T get(Integer index){
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("Get failed.Index is illegal.");
        }
        return (T) data[index];
    }
    //修改index为element值
    public void set(Integer index,T element){
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("Set failed.Index is illegal.");
        }
        data[index]=element;
    }
    //查找数组中是否有元素e
    public Boolean contains(T element){
        for (T obj:data) {
            if (obj.equals(element)){
                return true;
            }
        }
        return false;
    }
    //查找元素 若不存在则返回-1
    public Integer find(T element){
        for (int i = 0;i<size;i++) {
            if (data[i].equals(element)){
                return i;
            }
        }
        return -1;
    }
    //删除指定索引的值 返回删除的元素
    public T remove(Integer index){
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("Delete failed.Index is illegal.");
        }
        Object ret = data[index];
        for (int i = index+1 ; i <size;i++){
            data[i-1]=data[i];
        }
        size--;
        data[size] = null; //游离对象 不会内存泄漏 为了让JVM更有效的垃圾回收
        if (size == data.length/4 && data.length/2 !=0){
            //解决复杂度震荡问题
            resize(data.length / 2);
        }
        return (T)ret;
    }

    public T removeFirst(){
        return remove(0);
    }
    public T removeLast(){
        return remove(size-1);
    }
    public void removeElement(T element){
        Integer index = find(element);
        if (index!=-1){
            remove(index);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Array: size = %d, capacity = %d\n",size,this.data.length));
        sb.append("[");
        for (int i = 0; i <size;i++){
            sb.append(data[i]);
            if (i!=size-1){
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
