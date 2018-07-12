package data.structures.hash;
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

import java.util.TreeMap;

/**
 * @author cuisongliu [cuisongliu@qq.com]
 * @since 2018-07-12 15:42
 */
public class HashTable<K,V> {
    private final int[] capacity
            = {53, 97, 193, 389, 769, 1543, 3079, 6151, 12289, 24593,
            49157, 98317, 196613, 393241, 786433, 1572869, 3145739, 6291469,
            12582917, 25165843, 50331653, 100663319, 201326611, 402653189, 805306457, 1610612741};
    private TreeMap<K,V>[] hashTable;
    private int M;
    private int size;
    private static final  int upperTol = 10;
    private static final  int lowerTol = 2;
    private static int initCapacity = 0;
    public HashTable() {
        this.M = capacity[0];
        this.size = 0 ;
        hashTable = new TreeMap[M];
        for (int i =0;i<M ; i ++){
            hashTable[i] = new TreeMap<>();
        }
    }
    private int hash(K key){
        return (key.hashCode() & 0x7fffffff) % M ;
    }

    public int getSize(){
        return this.size;
    }

    public void add(K key,V value){
        TreeMap<K,V> map = hashTable[hash(key)];
        if (map.containsKey(key)){
            map.put(key,value);
        }else {
            map.put(key,value);
            size++;
            if (size >= upperTol * M && initCapacity+1 < capacity.length) {
                initCapacity++;
                resize();
            }
        }
    }

    public V remove(K key){
        TreeMap<K,V> map = hashTable[hash(key)];
        if (map.containsKey(key)){
            V v =map.remove(key);
            size--;
            if (size < lowerTol * M && M/2 >= initCapacity && initCapacity-1 >= 0 ) {
                initCapacity--;
                resize();
            }
            return v;
        }
        return null;
    }

    private void resize() {
        int newSize = capacity[initCapacity];
        TreeMap<K,V>[] newMap = new TreeMap[newSize];
        for (int i =0;i<newSize;i++){
            newMap[i] = new TreeMap<>();
        }
        //数据扩容
        int oldM = this.M;
        this.M = newSize;
        for (int i=0;i<oldM;i++){
            TreeMap<K,V> map = hashTable[i];
            for (K key:map.keySet()){
                newMap[hash(key)].put(key,map.get(key));
            }
        }
        this.hashTable = newMap;
    }

    public void set(K key,V value){
        TreeMap<K,V> map = hashTable[hash(key)];
        if (map.containsKey(key)){
            map.put(key,value);
        }else {
            throw  new IllegalArgumentException("key is not exist");
        }
    }

    public boolean contains(K key){
        TreeMap<K,V> map = hashTable[hash(key)];
        return map.containsKey(key);
    }

    public V get(K key){
        TreeMap<K,V> map = hashTable[hash(key)];
        if (map.containsKey(key)){
            return map.get(key);
        }
        return null;
    }
}
