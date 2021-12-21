package org.java.data.structure.linkedlist;

class DoubleLinkedListDemo {

    public static void main(String[] args) {
        HeroNode2 h1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 h2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 h3 = new HeroNode2(3, "吴用", "智多星");
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        // 普通添加
//        doubleLinkedList.add(h1);
//        doubleLinkedList.add(h2);
//        doubleLinkedList.add(h3);

        // 按顺序添加
        doubleLinkedList.addByOrder(h3);
        doubleLinkedList.addByOrder(h2);
        doubleLinkedList.addByOrder(h1);

        doubleLinkedList.list();

        // 修改
        HeroNode2 newH = new HeroNode2(3, "林冲", "豹子头");
        doubleLinkedList.update(newH);
        System.out.println("修改后的链表~~");
        doubleLinkedList.list();

        // 删除
        doubleLinkedList.delete(1);
        doubleLinkedList.delete(2);
        doubleLinkedList.delete(3);
        System.out.println("删除后的链表~~");
        doubleLinkedList.list();
    }

}

public class DoubleLinkedList {

    private final HeroNode2 head = new HeroNode2(0, "", "");

    /**
     * 返回头节点
     *
     * @return
     */
    public HeroNode2 getHead() {
        return head;
    }

    /**
     * 遍历链表
     */
    public void list() {
        // head节点不能动，因此需要一个辅助变量
        HeroNode2 tmpNode = head;
        if (tmpNode.next == null) {
            System.out.println("链表为空");
        }
        // 计数
        int count = 1;
        while (tmpNode.next != null) {
            tmpNode = tmpNode.next;
            System.out.printf("节点%d=%s\n", count, tmpNode);
            count++;
        }
    }

    /**
     * 添加一个节点到双向链表的最后
     *
     * @param node
     */
    public void add(HeroNode2 node) {
        // head节点不能动，因此需要一个辅助变量
        HeroNode2 tmpNode = head;
        while (tmpNode.next != null) {
            tmpNode = tmpNode.next;
        }
        // 形成一个双向链表
        tmpNode.next = node;
        node.pre = tmpNode;
    }

    /**
     * 按编号顺序添加
     *
     * @param node
     */
    public void addByOrder(HeroNode2 node) {
        HeroNode2 temp = head;
        boolean flag = false;
        while (temp.next != null) {
            if (temp.getNo() == node.getNo()) {
                flag = true;
                break;
            } else if (temp.next.getNo() > node.getNo()) {
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            System.out.println("编号" + node.getNo() + "已存在!");
            return;
        }
        node.pre = temp;
        // temp不是最后一个节点
        if (temp.next != null) {
            temp.next.pre = node;
            node.next = temp.next;
        }
        temp.next = node;
    }

    /**
     * 修改节点的信息，根据no编号来修改，即no编号不能改
     *
     * @param newHeroNode
     */
    public void update(HeroNode2 newHeroNode) {
        // 使用一个辅助变量
        HeroNode2 tmpNode = head;
        if (tmpNode.next == null) {
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
            tmpNode = tmpNode.next;
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
     * 1、对于双向链表，我们可以直接找到要删除的这个节点
     * 2、找到后自我删除即可
     *
     * @param no
     */
    public void delete(int no) {
        HeroNode2 tmpNode = head;
        if (tmpNode.next == null) {
            throw new RuntimeException("链表为空~~");
        }
        boolean flag = false;
        tmpNode = tmpNode.next;
        while (tmpNode != null) {
            if (tmpNode.getNo() == no) {
                flag = true;
                break;
            }
            tmpNode = tmpNode.next;
        }
        if (flag) {
            tmpNode.pre.next = tmpNode.next;
            // 此处需要判断一下是否最后一个节点
            // 如果是最后一个节点，就不需要执行，否则出现空指针
            if (tmpNode.next != null) {
                tmpNode.next.pre = tmpNode.pre;
            }
            // 便于回收
            tmpNode.pre = null;
            tmpNode.next = null;
        } else {
            System.out.println("节点" + no + " 不存在，不能删除");
        }
    }

}

/**
 * 定义HeroNode，每个HeroNode对象就是一个节点
 *
 * @author Administrator
 */
class HeroNode2 {

    private int no;
    private String name;
    private String nickname;
    /**
     * 指向像一个节点，默认为null
     */
    public HeroNode2 next;
    /**
     * 指向上一个节点，默认为null
     */
    public HeroNode2 pre;

    public HeroNode2(int no, String name, String nickname) {
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

    public int getNo() {
        return no;
    }

    public String getName() {
        return name;
    }

    public String getNickname() {
        return nickname;
    }

    @Override
    public String toString() {
        return "HeroNode2{" + "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
