package org.java.data.structure.stack;

import java.util.Scanner;

class LinkedListStackDemo {

    public static void main(String[] args) {
        // 测试一下
        LinkedListStack stack = new LinkedListStack();
        String key = "";
        // 控制是否退出菜单
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);

        while (loop) {
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

public class LinkedListStack {

    private final Node head = new Node(-1);

    public void push(int n) {
        Node node = new Node(n);
        node.next = head.next;
        head.next = node;
    }

    public int pop() {
        if (head.next == null) {
            throw new RuntimeException("栈已经空了~~");
        }
        Node next = head.next;
        head.next = next.next;
        return next.getValue();
    }

    public void list() {
        if (head.next == null) {
            System.out.println("栈是空的~~");
            return;
        }
        Node temp = head.next;
        while (temp != null) {
            System.out.println(temp.getValue());
            temp = temp.next;
        }
    }

}

class Node {

    private final int value;
    public Node next;

    public Node(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}