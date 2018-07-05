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
public class MaxMain {
    private static void testMaxHeap(Integer[] testData, boolean isHeapify, boolean isWhile){
        long startTime = System.nanoTime();
        MaxHeap<Integer> maxHeap =null;
        if (isHeapify){
            maxHeap=new MaxHeap<>(testData,isWhile);
        }else {
            maxHeap = new MaxHeap<>();
            for (Integer o:testData){
                maxHeap.add(o);
            }
        }
        int n = testData.length;
        Integer[] arr = new Integer[n];
        for (int i =0;i< n ;i++){
            if (isWhile){
                arr[i] = maxHeap.extractMaxWhile();
            }else {
                arr[i] = maxHeap.extractMax();
            }
        }
        for (int i =1;i< n ;i++)
            if (arr[i-1] < arr[i]){
                System.out.println("错误："+i+"err");
                break;
            }
        System.out.println("完成操作");
        long endTime = System.nanoTime();
        System.out.println("执行递归："+!isWhile+",isHeapify："+isHeapify+",执行时间为："+ ((endTime - startTime)/1000000000.0) );

    }
    public static void main(String[] args) {
        //判断是否正确
        int n = 10;
        Integer[] testDate = new Integer[n];
        Random random = new Random();
        for (int i =0;i< n ;i++){
            testDate[i] = random.nextInt(Integer.MAX_VALUE);
        }
        testMaxHeap(testDate,false,false);
//        testMaxHeap(testDate,true,true);
//
//        testMaxHeap(testDate,false,false);
//        testMaxHeap(testDate,true,false);



    }
}
