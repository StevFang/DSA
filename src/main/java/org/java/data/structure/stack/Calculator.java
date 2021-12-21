package org.java.data.structure.stack;

import java.util.Stack;

/**
 * 栈实现综合计算器（中缀表达式）
 *
 * +、-、*、/、(、)，多位数
 *
 * @author Administrator
 */
public class Calculator {

    public static final char PLUS = '+';
    public static final char REDUCE = '-';
    public static final char MULTIPLY = '*';
    public static final char DIVISION = '/';
    public static final char LEFT_BRACKET = '(';
    public static final char RIGHT_BRACKET = ')';

    public static void main(String[] args) {
        Stack<Integer> numStack = new Stack<>();
        Stack<Character> operStack = new Stack<>();

        String expression = "(30+2)+(2-7)*(6-2)+5";
        char[] chars = expression.toCharArray();

        StringBuilder keepNum = new StringBuilder();

        for (int index = 0; index < chars.length; index++) {
            char c = chars[index];
            // 是数值，入数栈
            if (!isOper(c)) {
                keepNum.append(c);
                if (index == chars.length - 1 || isOper(chars[index + 1])) {
                    numStack.push(Integer.parseInt(keepNum.toString()));
                    keepNum.setLength(0);
                }
                continue;
            }
            // 左小括号
            if (LEFT_BRACKET == c) {
                operStack.push(c);
                continue;
            }
            // 右小括号
            // 1、数栈出栈两个数值，符号栈出一个符号，进行计算，结果入数栈
            // 2、栈顶为左小括号，则左小括号出栈，结束；栈顶不是左小括号，继续执行当前操作
            if (RIGHT_BRACKET == c) {
                while(operStack.peek() != LEFT_BRACKET) {
                    numStack.push(cal(numStack.pop(), numStack.pop(), operStack.pop()));
                }
                // 小括号出栈
                operStack.pop();
                continue;
            }
            // 符号栈栈顶的符号优先级高，则先计算
            if (!operStack.empty() && operPriority(operStack.peek()) >= operPriority(c)) {
                numStack.push(cal(numStack.pop(), numStack.pop(), operStack.pop()));
            }
            // 当前符号入栈
            operStack.push(c);
        }

        while (!operStack.empty()) {
            numStack.push(cal(numStack.pop(), numStack.pop(), operStack.pop()));
        }

        System.out.println(expression + "=" + numStack.peek());
    }

    private static boolean isOper(char c) {
        return c == LEFT_BRACKET || c == RIGHT_BRACKET
                || c == PLUS || c == REDUCE
                || c == MULTIPLY || c == DIVISION;
    }

    private static int operPriority(char c) {
        if (c == MULTIPLY || c == DIVISION) {
            return 1;
        } else if (c == PLUS || c == REDUCE) {
            return 0;
        } else {
            return -1;
        }
    }

    /**
     * num1是后入栈的，num2是先入栈的，所以在计算 - 和 / 时需要将num1和num2互换位置
     *
     * @param num1 先入栈的
     * @param num2 后入栈的
     * @param oper 操作符
     * @return
     */
    private static int cal(int num1, int num2, char oper) {
        switch (oper) {
            case '+':
                return num1 + num2;
            case '-':
                return num2 - num1;
            case '*':
                return num1 * num2;
            case '/':
                return num2 / num1;
            default:
                throw new RuntimeException("oper is unsupport.");
        }
    }

}
