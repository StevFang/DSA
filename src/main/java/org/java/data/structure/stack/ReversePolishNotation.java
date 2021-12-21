package org.java.data.structure.stack;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 逆波兰表达式计算
 *
 * @author Administrator
 */
public class ReversePolishNotation {

    public static void main(String[] args) {
        // 先定义一个逆波兰表达式
        // (3 + 4) x 5 - 6 => 3 4 + 5 x 6 -
        // 4 x 5 - 9 + 60 + 8 / 2 => 4 5 x 9 - 60 + 8 2 / +
        // 为了方便，逆波兰表达式的数字和符号之间使用空格隔开
        String suffixExpression = "4 5 x 9 - 60 + 8 2 / +";
        // 思路分析
        // 1、先将表达式放到ArrayList中
        // 2、将ArrayList传递给一个方法，配合栈完成计算
        List<String> list = Arrays.asList(suffixExpression.split(" "));
        int res = getResult(list);
        System.out.printf("表达式结果：%s = %d\n", suffixExpression, res);
    }

    private static int getResult(List<String> list) {
        // 数栈
        Stack<Integer> numStack = new Stack<>();
        for (String item : list) {
            // 使用正则表达式来匹配
            if (item.matches("\\d+")) {
                numStack.push(Integer.valueOf(item));
            } else {
                int res = cal(numStack.pop(), numStack.pop(), item);
                numStack.push(res);
            }
        }
        return numStack.pop();
    }

    private static int cal(int num1, int num2, String oper) {
        switch (oper) {
            case "+":
                return num1 + num2;
            case "-":
                return num2 - num1;
            case "x":
                return num1 * num2;
            case "/":
                return num2 / num1;
            default:
                throw new UnsupportedOperationException("oper '" + oper + "' is unsupport");
        }
    }

    private static boolean isOper(String str) {
        return "+".equals(str) || "-".equals(str) || "x".equals(str) || "/".equals(str);
    }


}
