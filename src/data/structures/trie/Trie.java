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
public class Trie {
    private class Node{
        private Boolean isWord;
        private HashMap<Character,Node> next;

        public Node(Boolean isWord) {
            this.isWord = isWord;
            this.next = new HashMap<>();
        }

        public Node() {
            this(false);
        }
    }
    private Node root;
    private int size;

    public Trie() {
        root = new Node();
        size =0;
    }
    //或得trie树中存储单词的数量
    public Integer getSize(){
        return size;
    }
    //添加一个新的单词
    public void add(String word){
        Node curr = root;
        for (int i =0; i < word.length();i++){
            char c = word.charAt(i);
            if (!curr.next.containsKey(c)){
                curr.next.put(c,new Node());
            }
            curr = curr.next.get(c);
        }
        if (!curr.isWord){
            curr.isWord=true;
            size++;
        }
    }
    //查询单词
    public Boolean contains(String word){
        Node curr = root;
        for (int i =0; i < word.length();i++){
            char c = word.charAt(i);
            if (!curr.next.containsKey(c)){
               return false;
            }
            curr = curr.next.get(c);
        }
        return curr.isWord;
    }
    // 查询是否在Trie中有单词以prefix为前缀
    public boolean isPrefix(String prefix){
        Node curr = root;
        for (int i =0; i < prefix.length();i++){
            char c = prefix.charAt(i);
            if (!curr.next.containsKey(c)){
                return false;
            }
            curr = curr.next.get(c);
        }
        return true;
    }
}
