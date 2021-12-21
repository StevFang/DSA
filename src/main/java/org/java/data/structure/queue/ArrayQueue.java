package org.java.data.structure.queue;

/**
 * 数组模拟队列
 *
 * @author Administrator
 */
public class ArrayQueue {

    /**
     * 数据数组最大容量
     */
    private final int maxSize;
    /**
     * 存数据的数组，模拟队列
     */
    private final int[] arr;
    /**
     * 队列头，指向队列最开始的位置的前一个位置
     */
    private int front = -1;
    /**
     * 队列尾，指向队列末尾可添加数据位置的前一个位置
     */
    private int rear = -1;

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }

    public boolean isFull() {
        return rear == maxSize - 1;
    }

    public boolean isEmpty() {
        return front == rear;
    }

    public void add(int data) {
        if (isFull()) {
            System.out.println("队列满，不能加入数据~~");
            return;
        }
        arr[++rear] = data;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("队列空，不能取数据~~");
        }
        return arr[++front];
    }

    public void show() {
        if (isEmpty()) {
            System.out.println("队列空，无数据~~");
            return;
        }
        for (int i = 0; i < rear - front; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i + front + 1]);
        }
    }

    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("队列空，无数据~~");
        }
        return arr[front + 1];
    }

    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(3);
        arrayQueue.add(3);
        arrayQueue.add(4);
        arrayQueue.add(5);
        arrayQueue.show();
        System.out.println(arrayQueue.pop());
        arrayQueue.show();
        System.out.println(arrayQueue.pop());
        System.out.println(arrayQueue.pop());
//        System.out.println(arrayQueue.pop());
    }

}
