package org.java.data.structure.linkedlist;

class CircleSingleLinkedListDemo {

    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.add(new Node(2));
        circleSingleLinkedList.add(new Node(3));
        circleSingleLinkedList.add(new Node(4));
        circleSingleLinkedList.list();
    }

}

public class CircleSingleLinkedList {

    private Node first = null;

    public void add(Node node) {
        if (first == null) {
            first = node;
        } else {
            Node temp = first;
            while (temp.next != first) {
                temp = temp.next;
            }
            temp.next = node;
        }
        node.next = first;
    }

    public void list() {
        if (first == null) {
            System.out.println("单向循环链表为空~~");
            return;
        }
        Node temp = first;
        int count = 0;
        do {
            count++;
            System.out.println("序号: " + count + " ==> " + temp);
            temp = temp.next;
        } while (temp != first);
    }

}

class Node {

    private final int no;
    public Node next;

    public Node(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                '}';
    }
}