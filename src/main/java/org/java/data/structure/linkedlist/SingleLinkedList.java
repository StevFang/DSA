package org.java.data.structure.linkedlist;

import java.util.Stack;

public class SingleLinkedList {

    private final HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    public void add(HeroNode node) {
        // head节点不能动，因此需要一个辅助变量
        HeroNode tmpNode = head;
        while (tmpNode.getNext() != null) {
            tmpNode = tmpNode.getNext();
        }
        tmpNode.setNext(node);
    }

    /**
     * 修改节点的信息，根据no编号来修改，即no编号不能改
     *
     * @param newHeroNode
     */
    public void update(HeroNode newHeroNode) {
        HeroNode tmpNode = head;
        if (tmpNode.getNext() == null) {
            throw new RuntimeException("链表为空~~");
        }
        // 找到需要修改的节点
        boolean flag = false;
        while (true) {
            if (tmpNode == null) {
                break;
            }
            if (tmpNode.getNo() == newHeroNode.getNo()) {
                flag = true;
                break;
            }
            tmpNode = tmpNode.getNext();
        }
        if (flag) {
            tmpNode.setName(newHeroNode.getName());
            tmpNode.setNickname(newHeroNode.getNickname());
        } else {
            System.out.println("节点" + newHeroNode.getNo() + " 不存在，不能修改");
        }
    }

    /**
     * 删除节点
     * <p>
     * 思路：
     * 1、head不能动，需要一个temp辅助节点找到待删除节点的前一个节点
     * 2、我们在比较时，是temp.next.no 和 需要删除的节点的no比较
     *
     * @param deleteHeroNode
     */
    public void delete(HeroNode deleteHeroNode) {
        HeroNode tmpNode = head;
        if (tmpNode.getNext() == null) {
            throw new RuntimeException("链表为空~~");
        }
        boolean flag = false;
        while (true) {
            if (tmpNode.getNext() == null) {
                break;
            }
            if (tmpNode.getNext().getNo() == deleteHeroNode.getNo()) {
                flag = true;
                break;
            }
            tmpNode = tmpNode.getNext();
        }
        if (flag) {
            // 将下个节点的next设置为null
            tmpNode.getNext().setNext(null);
            // 将下下个节点赋值给当前节点
            tmpNode.setNext(tmpNode.getNext().getNext());
        } else {
            System.out.println("节点" + deleteHeroNode.getNo() + " 不存在，不能删除");
        }
    }

    /**
     * 查找
     *
     * @param no
     * @return
     */
    public HeroNode search(int no) {
        HeroNode tmpNode = head;
        if (tmpNode.getNext() == null) {
            System.out.println("链表为空~");
            return null;
        }
        // 找到需要修改的节点
        while (true) {
            if (tmpNode == null) {
                break;
            }
            if (tmpNode.getNo() == no) {
                break;
            }
            tmpNode = tmpNode.getNext();
        }
        return tmpNode;
    }

    /**
     * 添加，并根据序号排序
     *
     * @param node
     */
    public void addByOrder(HeroNode node) {
        // head节点不能动，因此需要一个辅助变量
        HeroNode tmpNode = head;
        // 标识添加的编号是否存在，默认为false
        boolean flag = false;
        while (true) {
            // 已经到链表最后
            if (tmpNode.getNext() == null) {
                break;
            }
            // 位置找到了，就在tmpNode的后面
            if (tmpNode.getNext().getNo() > node.getNo()) {
                break;
            } else if (tmpNode.getNext().getNo() == node.getNo()) {
                // 说明希望添加的heroNode已经存在
                flag = true;
                break;
            }
            // 后移，遍历当前链表
            tmpNode = tmpNode.getNext();
        }
        // 判断flag
        if (flag) {
            throw new RuntimeException("准备插入的英雄的编号" + node.getNo() + "已经存在了，不能加入");
        }
        // 插入节点
        node.setNext(tmpNode.getNext());
        tmpNode.setNext(node);
    }

    /**
     * 打印全部节点信息
     */
    public void list() {
        // head节点不能动，因此需要一个辅助变量
        HeroNode tmpNode = head;
        if (tmpNode.getNext() == null) {
            System.out.println("链表为空");
        }
        // 计数
        int count = 1;
        while (tmpNode.getNext() != null) {
            tmpNode = tmpNode.getNext();
            System.out.printf("节点%d=%s\n", count, tmpNode);
            count++;
        }
    }

    /**
     * 获取单链表长度
     * <p>
     * 思路分析：
     * 1、包含头结点，头结点不计入
     * 2、从头到尾遍历，同时使用一个临时变量存储长度
     *
     * @param heroNode
     * @return
     */
    public static int getLength(HeroNode heroNode) {
        if (heroNode.getNext() == null) {
            return 0;
        }
        int length = 0;
        while (heroNode.getNext() != null) {
            length++;
            heroNode = heroNode.getNext();
        }
        return length;
    }

    /**
     * 获取倒数第N个节点
     * <p>
     * 思路分析：
     * 1、获取单链表长度size
     * 2、倒数第N个，即顺数第size-N个（size=10，倒数第1个，10-1=9，9即是倒数第1个）
     * 3、遍历单链表，获取第size-N个节点，如果存在返回该节点，不存在返回null
     *
     * @param heroNode
     * @param index
     * @return
     */
    public static HeroNode getLastIndexNode(HeroNode heroNode, int index) {
        int size = getLength(heroNode);
        // 倒数第N个的值不能大于size，且N应该是有意义的正数
        if (index > size || index <= 0) {
            return null;
        }
        // 头结点不算
        HeroNode cur = heroNode.getNext();
        // 假设size=3， index=1， 3-1=2， 所以移动两下
        for (int i = 0; i < size - index; i++) {
            cur = cur.getNext();
        }
        return cur;
    }

    /**
     * 单链表反转
     * <p>
     * 思路分析：
     * 1、先定义一个节点 reverseHead = new HeroNode();
     * 2、从头到尾遍历原来的节点，每遍历一个节点，就将其取出，并放在新的链表的最前端
     * 3、原来的链表的head.next = reverseHead.next
     *
     * @param head
     */
    public static void reverse(HeroNode head) {
        // 如果当前链表为空，或者只有一个节点，无需反转，直接返回
        if (head.getNext() == null || head.getNext().getNext() == null) {
            return;
        }
        // 定义一个辅助的指针（变量），帮助我们遍历原来的链表
        HeroNode cur = head.getNext();
        // 指向当前节点[cur]的下一个节点
        HeroNode next = null;
        // 定义一个reverseHead
        HeroNode reverseHead = new HeroNode(0, "", "");

        // 遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表的最前端
        // 动脑筋
        while (cur != null) {
            // 先暂时保存当前节点的下一个节点，因为后面需要使用
            next = cur.getNext();
            // 将cur的下一个节点指向新的链表的最前端
            cur.setNext(reverseHead.getNext());
            // 将新的链表的头节点的next指向当前节点
            reverseHead.setNext(cur);
            // 让cur后移
            cur = next;
        }

        head.setNext(reverseHead.getNext());
    }

    /**
     * 逆序打印单链表
     * <p>
     * 思路分析：
     * 1、方式1：先将单链表进行反转操作，然后再遍历即可，这样的做的问题是会破坏原来的单链表的结构，不建议
     * 2、方式2：可以利用栈这个数据结构将各个节点压入到栈中，利用栈的先进后出的特点，就实现了逆序打印的效果
     *
     * @param head
     */
    public static void reversePrint(HeroNode head) {
        // 空链表
        if (head.getNext() == null) {
            return;
        }
        Stack<HeroNode> stack = new Stack<>();
        HeroNode temp = head.getNext();
        while (temp != null) {
            stack.push(temp);
            temp = temp.getNext();
        }
        while (!stack.empty()) {
            // 先进后出
            System.out.println(stack.pop());
        }
    }

    /**
     * 合并两个有序的链表，合并后的链表依然有序
     * <p>
     * 思路分析：
     * 1、循环一个链表，获取每个节点，将其写入到另一个链表的节点中去
     * 2、使用到链表的节点移除和有序添加
     * -----
     * 破坏了原来的链表结构
     *
     * @param head1
     * @param head2
     */
    public static HeroNode mergeOrder(HeroNode head1, HeroNode head2) {
        if (head1.getNext() == null && head2.getNext() == null) {
            return null;
        }
        if (head1.getNext() == null) {
            return head2;
        }
        if (head2.getNext() == null) {
            return head1;
        }
        HeroNode temp1 = head1.getNext();
        HeroNode next;
        while (temp1 != null) {
            next = temp1.getNext();
            HeroNode temp2 = head2;
            boolean flag = false;
            while (temp2.getNext() != null) {
                if (temp2.getNext() == null) {
                    break;
                }
                if (temp2.getNext().getNo() > temp1.getNo()) {
                    flag = true;
                    break;
                }
                temp2 = temp2.getNext();
            }
            if (flag) {
                temp1.setNext(temp2.getNext());
            } else {
                temp1.setNext(null);
            }
            temp2.setNext(temp1);

            temp1 = next;
        }
        return head2;
    }

    /**
     * 打印全部节点信息
     *
     * @param head
     */
    public static void list(HeroNode head) {
        // head节点不能动，因此需要一个辅助变量
        HeroNode tmpNode = head;
        if (tmpNode.getNext() == null) {
            System.out.println("链表为空");
        }
        // 计数
        int count = 1;
        while (tmpNode.getNext() != null) {
            tmpNode = tmpNode.getNext();
            System.out.printf("节点%d=%s\n", count, tmpNode);
            count++;
        }
    }

    public static void main(String[] args) {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        HeroNode heroNode = new HeroNode(8, "吴用", "智多星");
        singleLinkedList.addByOrder(heroNode);
        heroNode = new HeroNode(6, "林冲", "豹子头");
        singleLinkedList.addByOrder(heroNode);
        heroNode = new HeroNode(2, "宋江", "及时雨");
        singleLinkedList.addByOrder(heroNode);
        heroNode = new HeroNode(4, "卢俊义", "玉麒麟");
        singleLinkedList.addByOrder(heroNode);

//        heroNode = new HeroNode(5, "xxx", "xxx");
//        singleLinkedList.addByOrder(heroNode);
//
//        singleLinkedList.list();
//
//        heroNode = new HeroNode(5, "xxx1", "xxx2");
//        singleLinkedList.update(heroNode);
//
//        System.out.println("修改后的链表情况~~");
//        singleLinkedList.list();
//
//        heroNode = new HeroNode(6, "", "");
//        singleLinkedList.delete(heroNode);
//        heroNode = new HeroNode(5, "", "");
//        singleLinkedList.delete(heroNode);
//
//        System.out.println("删除后的链表情况~~");
//        singleLinkedList.list();

        // 求单链表中有效节点的个数
//        int size = getLength(singleLinkedList.getHead());
//        System.out.println("链表节点个数：" + size);

        // 求单链表中倒数第3个节点
//        HeroNode lastIndexNode = getLastIndexNode(singleLinkedList.getHead(), 3);
//        System.out.println("链表中倒数第3个节点：" + lastIndexNode);

        // 单链表反转
//        reverse(linkedList.getHead());
//        System.out.println("链表反转结果");
//        linkedList.list();

        // 单链表逆序打印
//        System.out.println("单链表的逆序打印");
//        reversePrint(singleLinkedList.getHead());

        SingleLinkedList singleLinkedList2 = new SingleLinkedList();
        heroNode = new HeroNode(1, "吴用", "智多星");
        singleLinkedList2.addByOrder(heroNode);
        heroNode = new HeroNode(3, "林冲", "豹子头");
        singleLinkedList2.addByOrder(heroNode);
        heroNode = new HeroNode(5, "宋江", "及时雨");
        singleLinkedList2.addByOrder(heroNode);
        heroNode = new HeroNode(7, "卢俊义", "玉麒麟");
        singleLinkedList2.addByOrder(heroNode);

        System.out.println("打印合并后的单链表1");
        singleLinkedList.list();
        System.out.println("打印合并后的单链表2");
        singleLinkedList2.list();
        HeroNode mergedHeroNode = mergeOrder(singleLinkedList.getHead(), singleLinkedList2.getHead());
        System.out.println("打印合并后的单链表");
        list(mergedHeroNode);
        System.out.println("打印合并后的单链表1");
        singleLinkedList.list();
        System.out.println("打印合并后的单链表2");
        singleLinkedList2.list();

    }


}

/**
 * 定义HeroNode，每个HeroNode对象就是一个节点
 *
 * @author Administrator
 */
class HeroNode {

    private int no;
    private String name;
    private String nickname;
    private HeroNode next;

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setNext(HeroNode next) {
        this.next = next;
    }

    public int getNo() {
        return no;
    }

    public String getName() {
        return name;
    }

    public String getNickname() {
        return nickname;
    }

    public HeroNode getNext() {
        return next;
    }

    @Override
    public String toString() {
        return "HeroNode{" + "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
