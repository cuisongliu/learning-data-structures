package data.structures.st;
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
 * 线段树
 *
 * @author cuisongliu [cuisongliu@qq.com]
 * @since 2018-07-05 21:51
 */
public class SegmentTree<E> {

    private E[] data;
    //存储树的结构
    private E[] tree;
    private Merger<E> merger;
    public SegmentTree(E[] arr,Merger<E> merger){
        this.merger = merger;
        data = (E[]) new Object[arr.length];
        for (int i =0;i<arr.length;i++){
            data[i] = arr[i];
        }
        tree = (E[]) new Object[4*arr.length];
        buildSegmentTree(0,0,data.length-1);
    }
    // 再treeIndex位置创建表示区间[l...r]的线段树
    private void buildSegmentTree(int treeIndex,int l ,int r){
        if (l==r){
            //递归到底的情况
            tree[treeIndex] = data[l];
            return;
        }
        int mid = l +(r-l)/2;
        int leftIndex = leftChild(treeIndex);
        int rightIndex = rightChild(treeIndex);

        buildSegmentTree(leftIndex,l,mid);
        buildSegmentTree(rightIndex,mid +1 ,r);
        tree[treeIndex] = merger.merger(tree[leftIndex],tree[rightIndex]);
    }


    public int getSize(){return data.length;}

    public E querySegmentTree(int l ,int r){
        if (l > data.length-1 || l < 0 ) throw new IllegalArgumentException("index l is invalidate.");
        if (r > data.length-1 || r < 0 ) throw new IllegalArgumentException("index r is invalidate.");
        if (l > r) throw new IllegalArgumentException("index l > r  is invalidate.");

        return querySegmentTree(0,0,data.length-1,l,r);
    }
    public E querySegmentTree(int treeIndex,int minIndex,int maxIndex,int l ,int r){
        if (l==minIndex && r==maxIndex){
            //递归到底的情况
            return tree[treeIndex];
        }
        int mid = l +(r-l)/2;
        int leftIndex = leftChild(treeIndex);
        int rightIndex = rightChild(treeIndex);

        if (l >= mid +1 ) return querySegmentTree(rightIndex,mid +1 ,maxIndex,l,r);
        if (r <= mid) return querySegmentTree(leftIndex,minIndex,mid,l,r);
        E leftNode = querySegmentTree(leftIndex,minIndex,mid,l,mid);
        E rightNode= querySegmentTree(rightIndex,mid +1 ,maxIndex,mid+1,r);

        return merger.merger(leftNode,rightNode);
    }

    public E get(int index){
        if (index  >  data.length-1 || index < 0 ) throw new IllegalArgumentException("index is invalidate.");
        return data[index];
    }
    //返回完全二叉树的数组 表示中 一个索引所表示的元素的左孩子结点的索引
    private int leftChild(int index){
        if (index < 0) throw new IllegalArgumentException("index is invalid.");
        return index*2 +1;
    }
    //返回完全二叉树的数组 表示中 一个索引所表示的元素的右孩子结点的索引
    private int rightChild(int index){
        if (index < 0) throw new IllegalArgumentException("index is invalid.");
        return index*2 +2;
    }

    public void set(int index,E e){
        if (index > data.length-1 || index < 0 ) throw new IllegalArgumentException("index l is invalidate.");
        data[index] = e ;
        set(0,0,data.length-1,index,e);
    }
    public void set(int treeIndex,int l ,int r,int index,E e ){
        if (l == r ){
            tree[treeIndex] = e ;
            return;
        }
        int mid = l +(r-l)/2;
        int leftIndex = leftChild(treeIndex);
        int rightIndex = rightChild(treeIndex);
        if (index >= mid +1 )  set(rightIndex,mid +1,r,index,e);
        if (index <= mid)  set(leftIndex,l,mid,index,e);
        tree[treeIndex] = merger.merger(tree[leftIndex],tree[leftIndex]);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i <tree.length;i++){
            if (tree[i]!=null) sb.append(tree[i]);
            else sb.append("null");

            if (i != tree.length-1) sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }
}
