package data.structures.unionfind;
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
 * 并查集1
 *
 * @author cuisongliu [cuisongliu@qq.com]
 * @since 2018-07-06 22:38
 */
public class UnionFind1 implements UF {
    private int[] id;

    public UnionFind1(int size) {
        id = new int[size];
        for (int i =0;i< size;i++)
            id[i] = i;
    }

    @Override
    public int getSize() {
        return id.length;
    }

    private int find(int p ){
        if (p < 0 || p >= getSize()){
            throw new IllegalArgumentException("index p is illegal.");
        }
        return id[p];
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) ==find(q);
    }

    @Override
    public void unionElements(int p, int q) {
        if (!isConnected(p,q)){
            for (int i =0;i<getSize();i++){
                if (id[i] ==find(p)) id[i] = find(q);
            }
        }
    }
}
