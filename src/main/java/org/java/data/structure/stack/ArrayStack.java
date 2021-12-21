package org.java.data.structure.stack;

import java.util.Scanner;

class ArrayStackDemo {

    public static void main(String[] args) {
        // 测试一下
        ArrayStack stack = new ArrayStack(5);
        String key = "";
        // 控制是否退出菜单
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);

        while(loop) {
            System.out.println("show: 显示栈");
            System.out.println("exit: 退出程序");
            System.out.println("push: 表示添加数据到栈(入栈)");
            System.out.println("pop: 表示从栈取出数据(出栈)");
            System.out.println("请输入你的选择");
            key = scanner.next();
            switch (key) {
                case "show":
                    stack.list();
                    break;
                case "exit":
                    loop = false;
                    scanner.close();
                    break;
                case "push":
                    System.out.println("请输入一个数字");
                    int num = scanner.nextInt();
                    stack.push(num);
                    break;
                case "pop":
                    try {
                        int popNum = stack.pop();
                        System.out.printf("%d出栈\n", popNum);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出成功");
    }

}

public class ArrayStack {

    private final int maxSize;
    private final int[] arr;
    private int top;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        top = -1;
    }

    public void push(int n) {
        if (isFull()) {
            System.out.println("栈已经满了~~");
            return;
        }
        arr[++top] = n;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈已经空了~~");
        }
        return arr[top--];
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void list() {
        if (isEmpty()) {
            System.out.println("栈空，没有数据~~");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, arr[i]);
        }
    }

}