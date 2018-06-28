package data.structures.linked.leetcode;
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
 * 链表的删除操作多个数据
 * @author cuisongliu [cuisongliu@qq.com]
 * @since 2018-06-28 下午3:32
 */
public class Solution1 {
    public ListNode removeElements(ListNode head , int val){
        //删除头节点
        //若当前头结点有值则跳过指向下个结点即可
        while (head!=null && head.val == val){
            head = head.next;
        }
        if (head==null){
            return  null;
        }
        //删除非头节点
        ListNode preNode = head;
        while (preNode.next!=null){
            if (preNode.next.val==val){
                preNode.next = preNode.next.next;
            }else {
                preNode = preNode.next;
            }
        }

        return head;
    }
}
