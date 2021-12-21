package org.java.data.structure.queue;

import java.util.Scanner;

/**
 * 数组模拟环形队列
 *
 * 思路分析：
 * a.front变量的含义做一个调整：front就指向队列的第一个元素，也就是说arr[front]就是队列的第一个元素，front的初始值为0
 * b.rear变量的含义做一个调整：rear指向队列的最后一个元素的后一个位置，因为希望空出一个空间作为约定，rear的初始值为0
 * c.当队列满时，条件是（rear+1）% maxSize = front【队列满】
 * d.队列为空的条件，rear = front【队列空】
 * e.队列中有效的数据的个数（rear + maxSize - front）% maxSize
 * f.这样就得到了一个数组模拟环形队列
 *
 * @author Administrator
 */
public class CircleArrayQueue {

    /**
     * 数据数组最大容量实际为maxSize-1
     */
    private final int maxSize;
    /**
     * 存数据的数组，模拟队列
     */
    private final int[] arr;
    /**
     * 队列头，指向队列最开始的位置
     */
    private int front = 0;
    /**
     * 队列尾，指向队列末尾的后一个位置
     */
    private int rear = 0;

    public CircleArrayQueue(int maxSize) {

        this.maxSize = maxSize + 1;
        arr = new int[maxSize + 1];
    }

    public boolean isFull() {
        // 不留间隔1，就会被当成空
        return (rear + 1) % maxSize == front;
    }

    public boolean isEmpty() {
        return front == rear;
    }

    public void add(int data) {
        if (isFull()) {
            System.out.println("队列满，不能加入数据~~");
            return;
        }
        // 添加数据时，直接加到末尾后一个位置即可，加完过后，需要将rear后移一位
        arr[rear] = data;
        // rear取模的原因：环形列表，直接rear++会越界
        rear = (rear + 1) % maxSize;
    }

    public int get() {
        if (isEmpty()) {
            throw new RuntimeException("队列空，不能取数据~~");
        }
        // 取数据时，直接取front数据，取完过后，需要将front后移一位
        int value = arr[front];
        // front取模的原因：环形列表，直接front++会越界
        front = (front + 1) % maxSize;
        return value;
    }

    public void show() {
        if (isEmpty()) {
            System.out.println("队列空，无数据~~");
            return;
        }
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    private int size() {
        // 直接rear减front是不对的，因为rear可能因为环形，比front还小
        // rear + maxSize - front 直接用也不对，因为rear可能比front大
        // 所以求和过后取模
        // 举例 rear = 1, front = 4, maxSize = 5，有效个数 = 2(arr[0],arr[4])，公式求解 = (1+5-4)%6=2
        // rear = 3, front = 1, maxSize = 5，有效个数 = 2(arr[1],arr[2])，公式求解 = (3+5-1)%5=2
        return (rear + maxSize - front) % maxSize;
    }

    public int head() {
        if (isEmpty()) {
            throw new RuntimeException("队列空，无数据~~");
        }
        return arr[front];
    }

    public static void main(String[] args) {
        CircleArrayQueue queue = new CircleArrayQueue(3);
        // 接收用户输入
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while(loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头数据");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    queue.show();
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                case 'a':
                    System.out.println("输入一个数");
                    int value = scanner.nextInt();
                    queue.add(value);
                    break;
                case 'g':
                    try {
                        int res = queue.get();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = queue.head();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出~~");
    }

}
