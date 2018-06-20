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

import java.util.Random;

/**
 * @author cuisongliu [cuisongliu@qq.com]
 * @since 2018-06-19 23:06
 */
public class Main {

    private static double testQueue(Queue<Integer> q,int opCount){
        long startTime = System.nanoTime();
        Random random = new Random();
        for ( int i =0; i < opCount;i++){
            q.enqueue(random.nextInt(Integer.MAX_VALUE));
        }
        for ( int i =0; i < opCount;i++){
            q.dequeue();
        }
        long endTime = System.nanoTime();

        return  (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {
//        ArrayQueue<Integer> queue = new ArrayQueue<>();
//        for (int i = 0 ;i< 10 ; i ++){
//            queue.enqueue(i);
//            System.out.println(queue);
//        }
//
//        queue.dequeue();
//        System.out.println(queue);



//        LoopQueue<Integer> loopqueue = new LoopQueue<>();
//        for (int i = 0 ;i< 10 ; i ++){
//            loopqueue.enqueue(i);
//            System.out.println(loopqueue);
//            if (i %3 ==2){
//                loopqueue.dequeue();
//                System.out.println(loopqueue);
//            }
//
//        }
        Integer optCount = 100000;
        Queue<Integer> arrayQueue = new ArrayQueue<>();
        System.out.println("所用時間為1：" + testQueue(arrayQueue,optCount));
        Queue<Integer> loopQueue= new LoopQueue<>();
        System.out.println("所用時間為2：" + testQueue(loopQueue,optCount));
    }

}
