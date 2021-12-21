package org.java.data.structure.linkedlist;

import java.util.Arrays;

/**
 * 约瑟夫问题
 * <p>
 * 设编号为1,2,...n的n个人围坐一圈，约定编号为k（1<=k<=n）的人从1开始报数，
 * 数到m的那个人出列，它的下一位又从1开始报数，数到m的那个人又出列，
 * 依次类推，直到所有人出列为之，由此产生一个出队编号的序列。
 *
 * @author Administrator
 */
public class Jousphu {

    /**
     * 初始化单向循环链
     *
     * @param n
     * @return
     */
    public static CircleSingleLinkedList initLinkedList(int n) {
        // 做一个数据校验
        if (n < 1) {
            System.out.println("no的值不正确~~");
            return null;
        }
        CircleSingleLinkedList linkedList = new CircleSingleLinkedList();
        for (int i = 0; i < n; i++) {
            linkedList.addPerson(i + 1);
        }
        return linkedList;
    }

    /**
     * 获取出圈序列
     *
     * @param startNo    开始报数的人物编号
     * @param countNum   数的数
     * @param nums       表示最初有多少个人物在圈中
     * @param linkedList 单向环形链表
     * @return
     */
    public static int[] getOutCircleArr(int startNo,
                                        int countNum,
                                        int nums,
                                        CircleSingleLinkedList linkedList) {
        if (linkedList.first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数输入有误，请重新输入");
            return null;
        }
        int[] outCircleArr = new int[nums];
        // 创建辅助指针，帮助完成出圈
        Person helper = linkedList.first;
        // 创建一个辅助指针helper，指向最后一个节点
        while (helper.next != linkedList.first) {
            helper = helper.next;
        }
        // 人物报数前移动到k的位置，即移动k-1次
        for (int i = 1; i < startNo; i++) {
            linkedList.first = linkedList.first.next;
            helper = helper.next;
        }
        // 当人物报数时，让first和helper指针同时的移动 countNum - 1次，然后出圈
        // 这里是一个循环操作，直到圈中只有一个节点
        int outCount = 0;
        while (true) {
            if (helper == linkedList.first) {
                outCircleArr[outCount] = linkedList.first.getNo();
                System.out.printf("小孩%d出圈\n", linkedList.first.getNo());
                break;
            }
            // 让first和helper指针同时移动countNum-1次
            for (int j = 0; j < countNum - 1; j++) {
                linkedList.first = linkedList.first.next;
                helper = helper.next;
            }
            // 这时first指向的节点，就是要出圈的人物节点
            System.out.printf("小孩%d出圈\n", linkedList.first.getNo());
            // 出圈的操作
            outCircleArr[outCount++] = linkedList.first.getNo();
            linkedList.first = linkedList.first.next;
            helper.next = linkedList.first;
        }
        return outCircleArr;
    }

    public static void main(String[] args) {
        int n = 125, k = 10, m = 20;
        // 构建环
        CircleSingleLinkedList linkedList = initLinkedList(n);
        // 构建出队序列
        assert linkedList != null;
        int[] outCircleArr = getOutCircleArr(k, m, n, linkedList);
        System.out.println(Arrays.toString(outCircleArr));
        System.out.println("最后留在圈中的人：" + linkedList.first.getNo());
    }

    static class CircleSingleLinkedList {

        private Person first = null;

        /**
         * 添加人物
         *
         * @param no
         */
        public void addPerson(int no) {
            if (first == null) {
                first = new Person(no);
                first.next = first;
            } else {
                Person cur = first;
                while (cur.next != first) {
                    cur = cur.next;
                }
                cur.next = new Person(no);
                cur.next.next = first;
            }
        }

        public void showPersons() {
            if (first == null) {
                System.out.println("没有任何人");
                return;
            }
            Person cur = first;
            do {
                System.out.printf("人物的编号 %d \n", cur.getNo());
                cur = cur.next;
            } while (cur != first);
        }

        /**
         * 获取第一个
         *
         * @return
         */
        public Person getFirst() {
            return first;
        }

    }

    /**
     * 人员节点
     */
    static class Person {

        private final int no;
        public Person next;

        public Person(int no) {
            this.no = no;
        }

        public int getNo() {
            return no;
        }

    }

}