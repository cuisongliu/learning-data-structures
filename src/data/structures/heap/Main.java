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
public class Main {
    public static void main(String[] args) {
//        MaxHeap<Integer> maxHeap = new MaxHeap<>();
//        maxHeap.add(62);
//        maxHeap.add(41);
//        maxHeap.add(30);
//        maxHeap.add(28);
//        maxHeap.add(16);
//        maxHeap.add(22);
//        maxHeap.add(13);
//        maxHeap.add(19);
//        maxHeap.add(17);
//        maxHeap.add(15);
//        System.out.println(maxHeap);
//        System.out.println(maxHeap.extractMax());
//        System.out.println(maxHeap);
        int n = 10;
        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        Random random = new Random();
        for (int i =0;i< n ;i++){
            maxHeap.add(random.nextInt(100000));
        }
//        int n = maxHeap.getSize();
        Integer[] arr = new Integer[n];
        for (int i =0;i< n ;i++)
            arr[i] = maxHeap.extractMax();
        for (int i =0;i< n ;i++)
         System.out.print(arr[i]+",");
        for (int i =1;i< n ;i++)
            if (arr[i-1] < arr[i]){
                System.out.println("err");
                break;
            }

        System.out.println("success");
    }
}
