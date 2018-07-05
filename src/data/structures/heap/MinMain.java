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

import java.util.Random;

/**
 * @author cuisongliu [cuisongliu@qq.com]
 * @since 2018-07-04 下午1:51
 */
public class MinMain {
    private static void testMinHeap(Integer[] testData, boolean isHeapify, boolean isWhile){
        long startTime = System.nanoTime();
        MinHeap<Integer> minHeap =null;
        if (isHeapify){
            minHeap=new MinHeap<>(testData,isWhile);
        }else {
            minHeap = new MinHeap<>();
            for (Integer o:testData){
                minHeap.add(o);
            }
        }
        int n = testData.length;
        Integer[] arr = new Integer[n];
        for (int i =0;i< n ;i++){
            if (isWhile){
                arr[i] = minHeap.extractMinWhile();
            }else {
                arr[i] = minHeap.extractMin();
            }
        }
        for (int i =1;i< n ;i++)
            if (arr[i-1] > arr[i]){
                System.out.println("错误："+i+"err");
                break;
            }
        System.out.println("完成操作");
        long endTime = System.nanoTime();
        System.out.println("执行递归："+!isWhile+",isHeapify："+isHeapify+",执行时间为："+ ((endTime - startTime)/1000000000.0) );

    }
    // 在N个元素中选出前M个元素大的方法
    // 创建一个M大小的元素 （N >M ）堆
    private static  void nMinHeapM(Integer[] data ,Integer m){
        MinHeap<Integer> minHeap = new MinHeap<>();
        MinHeap<Integer> minHeap2 = new MinHeap<>();
        //1. 先将前M个元素扔到堆中去 完成一个M大小的堆
        //2. 只要比当前堆中最小的元素还要大的元素就放到堆中去
        for (int i =0;i< data.length ;i ++){
            minHeap2.add(data[i]);
            if (i < m ){
                minHeap.add(data[i]);
            }else {
                Integer min = minHeap.findMin();
                min = min < data[i] ? data[i] : min;
                minHeap.replace(min);
            }
        }
        System.out.println(minHeap.getSize());
        for (int i =0;i< m ;i++){
            System.out.print(minHeap.extractMin() + ",");
        }
        System.out.println("");
        for (int i =0;i< data.length ;i++){
            System.out.print(minHeap2.extractMin() + ",");
        }
    }
    // 在N个元素中选出前M个元素小的方法
    private static  void nMaxHeapM(Integer[] data ,Integer m){
        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        MaxHeap<Integer> maxHeap2 = new MaxHeap<>();
        //1. 先将前M个元素扔到堆中去 完成一个M大小的堆
        //2. 只要比当前堆中最大的元素还要小的元素就放到堆中去
        for (int i =0;i< data.length ;i ++){
            maxHeap2.add(data[i]);
            if (i < m ){
                maxHeap.add(data[i]);
            }else {
                Integer max = maxHeap.findMax();
                max = max > data[i] ? data[i] : max;
                maxHeap.replace(max);
            }
        }
        System.out.println(maxHeap.getSize());
        for (int i =0;i< m ;i++){
            System.out.print(maxHeap.extractMax() + ",");
        }
        System.out.println("");
        for (int i =0;i< data.length ;i++){
            System.out.print(maxHeap2.extractMax() + ",");
        }

    }
    public static void main(String[] args) {
        //判断是否正确
        int n = 100;
        Integer[] testDate = new Integer[n];
        Random random = new Random();
        for (int i =0;i< n ;i++){
            testDate[i] = random.nextInt(100);
        }
        nMaxHeapM(testDate,10);
    }
}
