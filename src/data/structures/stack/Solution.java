package data.structures.stack;
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

import java.util.Stack;
//        题目难度 Easy
//        在柠檬水摊上，每一杯柠檬水的售价为 5 美元。
//
//        顾客排队购买你的产品，（按账单 bills 支付的顺序）一次购买一杯。
//
//        每位顾客只买一杯柠檬水，然后向你付 5 美元、10 美元或 20 美元。你必须给每个顾客正确找零，也就是说净交易是每位顾客向你支付 5 美元。
//
//        注意，一开始你手头没有任何零钱。
//
//        如果你能给每位顾客正确找零，返回 true ，否则返回 false 。
//        exm1
//        输入：[5,5,5,10,20]
//        输出：true
//        解释：
//        前 3 位顾客那里，我们按顺序收取 3 张 5 美元的钞票。
//        第 4 位顾客那里，我们收取一张 10 美元的钞票，并返还 5 美元。
//        第 5 位顾客那里，我们找还一张 10 美元的钞票和一张 5 美元的钞票。
//        由于所有客户都得到了正确的找零，所以我们输出 true。

//        输入：[5,5,10]
//        输出：true

//        输入：[10,10]
//        输出：false

//          输入：[5,5,10,10,20]
//          输出：false
//          解释：
//          前 2 位顾客那里，我们按顺序收取 2 张 5 美元的钞票。
//          对于接下来的 2 位顾客，我们收取一张 10 美元的钞票，然后返还 5 美元。
//          对于最后一位顾客，我们无法退回 15 美元，因为我们现在只有两张 10 美元的钞票。
//          由于不是每位顾客都得到了正确的找零，所以答案是 false。
/**
 * @author cuisongliu [cuisongliu@qq.com]
 * @since 2018-07-04 0:13
 */
public class Solution {
    public static boolean lemonadeChange(int[] bills) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0 ; i < bills.length;i++){
            if (bills[i] ==5){
                stack.push(bills[i]);
            }else {
                if (stack.isEmpty())return false;
                Integer ret  = stack.pop();
                if (bills[i] - ret !=5){
                    stack.push(bills[i]);
                }
            }
        }
        while (!stack.isEmpty()){
            Integer ret  = stack.pop();
            if (ret !=5){
                stack.push(ret);
            }
        }
        return stack.isEmpty();
    }
    public static int[] stringToIntegerArray(String input) {

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for(int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static String booleanToString(boolean input) {
        return input ? "True" : "False";
    }

    public static void main(String[] args) {
        System.out.println(lemonadeChange(stringToIntegerArray("10,10")));
    }
}
