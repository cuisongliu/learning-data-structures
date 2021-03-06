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
 * @author cuisongliu [cuisongliu@qq.com]
 * @since 2018-06-25 下午3:59
 */
public class Main {
    public static void main(String[] args) {
//        LinkedDummyHeadList<Integer> list = new LinkedDummyHeadList<>();
//        for (Integer i = 0 ;i < 10 ;i++){
//            list.addFirst(i);
//            System.out.println(list);
//        }
//        System.out.println(list);
//
//        list.add(666,3);
//        System.out.println(list);
//        list.remove(10);
//        System.out.println(list);

        LinkedListQueue<Integer> list = new LinkedListQueue<>();
        for (Integer i = 0 ;i < 10 ;i++){
            list.enqueue(i);
            System.out.println(list);
        }
        System.out.println(list);

        list.enqueue(666);
        System.out.println(list);
        list.dequeue();
        System.out.println(list);

    }
}
