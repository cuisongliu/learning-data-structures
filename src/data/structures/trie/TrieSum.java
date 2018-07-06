package data.structures.trie;
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

import java.util.HashMap;

/**
 * trie 字典树
 *
 * @author cuisongliu [cuisongliu@qq.com]
 * @since 2018-07-06 下午1:01
 */
public class TrieSum {
    private class Node{
        private Integer value;
        private HashMap<Character,Node> next;
        public Node( Integer value) {
            this.next = new HashMap<>();
        }

        public Node() {
            this(0);
        }
    }
    private Node root;

    public TrieSum() {
        root = new Node();
    }
    //添加一个新的单词
    public void add(String word,int value){
        Node curr = root;
        for (int i =0; i < word.length();i++){
            char c = word.charAt(i);
            if (!curr.next.containsKey(c)){
                curr.next.put(c,new Node());
            }
            curr = curr.next.get(c);
        }
        curr.value = value;
    }

    public int sum(String prefix){
       Node curr = root;
        for (int i =0; i < prefix.length();i++){
            char c = prefix.charAt(i);
            if (!curr.next.containsKey(c)){
                return 0;
            }
            curr = curr.next.get(c);
        }

        return sum(curr);
    }

    private int sum(Node node){
//        if (node.next==null) return 0;
//        for (Character c : node.next.keySet()){
//            sum(node.next.get(c))
//        }
        return 0;
    }

}
