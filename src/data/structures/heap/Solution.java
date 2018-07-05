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

import java.util.*;
import java.util.PriorityQueue;

/**
 * 347
 *
 * @author cuisongliu [cuisongliu@qq.com]
 * @since 2018-07-05 20:57
 */
public class Solution {
    private class Freq {

        private int e,freq;

        public Freq(int e, int freq) {
            this.e = e;
            this.freq = freq;
        }
    }

    private class FreqComparator implements Comparator<Freq>{

        @Override
        public int compare(Freq o1, Freq o2) {
            return o1.freq - o2.freq;
        }
    }

    public List<Integer> topKFrequent(int[] nums,int k){
        //将优先级放入treeMap
        TreeMap<Integer,Integer> treeMap = new TreeMap<>();
        for (int num : nums){
            if (treeMap.containsKey(num)){
                treeMap.put(num,treeMap.get(num)+1);
            }else {
                treeMap.put(num,1);
            }
        }
        //优先队列重组
        PriorityQueue<Freq> freqPriorityQueue = new PriorityQueue<>(new FreqComparator());
        for (int key : treeMap.keySet()){
            if (freqPriorityQueue.size() < k ){
                freqPriorityQueue.add(new Freq(key,treeMap.get(key)));
            }else if (treeMap.get(key) > Objects.requireNonNull(freqPriorityQueue.peek()).freq){
                freqPriorityQueue.remove();
                freqPriorityQueue.add(new Freq(key,treeMap.get(key)));
            }

        }
        LinkedList<Integer> res = new LinkedList<>();
        while (!freqPriorityQueue.isEmpty())
            res.add(freqPriorityQueue.remove().e);
        return res;
    }

}
