package org.java.data.structure.tree;

class BinaryTreeDemo {

    public static void main(String[] args) {
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode ljy = new HeroNode(2, "卢俊义");
        HeroNode wy = new HeroNode(3, "吴用");
        HeroNode lc = new HeroNode(4, "林冲");
        HeroNode test = new HeroNode(5, "测试");
        root.setLeft(ljy);
        root.setRight(wy);
        wy.setLeft(lc);
        ljy.setLeft(test);

        BinaryTree binaryTree = new BinaryTree();
        binaryTree.setRoot(root);
//        System.out.println("前序遍历");
//        binaryTree.preOrder();
//        System.out.println("中序遍历");
//        binaryTree.infixOrder();
//        System.out.println("后序遍历");
//        binaryTree.postOrder();

//        System.out.println("前序查找");
//        HeroNode heroNode = binaryTree.preOrderSearch(1);
//        System.out.println(heroNode);
//
//        System.out.println("中序查找");
//        heroNode = binaryTree.infixOrderSearch(4);
//        System.out.println(heroNode);
//
//        System.out.println("后序查找");
//        heroNode = binaryTree.postOrderSearch(6);
//        System.out.println(heroNode);

        binaryTree.preOrder();

        boolean suc = binaryTree.delNode(5);
        if (suc) {
            System.out.println("节点5删除成功");
        } else {
            System.out.println("节点5不存在, 删除失败");
        }
        binaryTree.preOrder();

        suc = binaryTree.delNode(3);
        if (suc) {
            System.out.println("节点3删除成功");
        } else {
            System.out.println("节点3不存在, 删除失败");
        }
        binaryTree.preOrder();


    }

}

public class BinaryTree {

    private HeroNode root = null;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    public HeroNode preOrderSearch(int no) {
        if (this.root != null) {
            return this.root.preOrderSearch(no);
        } else {
            System.out.println("二叉树为空");
        }
        return null;
    }

    public HeroNode infixOrderSearch(int no) {
        if (this.root != null) {
            return this.root.infixOrderSearch(no);
        } else {
            System.out.println("二叉树为空");
        }
        return null;
    }

    public HeroNode postOrderSearch(int no) {
        if (this.root != null) {
            return this.root.postOrderSearch(no);
        } else {
            System.out.println("二叉树为空");
        }
        return null;
    }

    public boolean delNode(int no) {
        if (this.root == null || this.root.getNo() == no) {
            this.root = null;
            return true;
        } else {
            return this.root.delNode(no);
        }
    }

}

class HeroNode {

    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    /**
     * 1、输出当前节点
     * 2、遍历左子节点
     * 3、遍历右子节点
     */
    public void preOrder() {
        // 先输出父节点
        System.out.println(this);
        // 先左遍历
        if (this.left != null) {
            this.left.preOrder();
        }
        // 再右遍历
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    /**
     * 1、遍历左子节点
     * 2、输出当前节点
     * 3、遍历右子节点
     */
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    /**
     * 1、遍历左子节点
     * 2、遍历右子节点
     * 3、输出当前节点
     */
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    /**
     * 前序查找
     *
     * (当前 -> 左 -> 右）
     *
     * @param no 目标no
     * @return 如果找到就返回该Node，没有找到则返回null
     */
    public HeroNode preOrderSearch(int no) {
        if (this.getNo() == no) {
            return this;
        }
        HeroNode resNode = null;
        // 左子节点递归查找
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        // 右子节点递归查找
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    /**
     * 中序查找
     *
     * (左 -> 当前 -> 右）
     *
     * @param no 目标no
     * @return 如果找到就返回该Node，没有找到则返回null
     */
    public HeroNode infixOrderSearch(int no) {
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.no == no) {
            return this;
        }
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    /**
     * 右序查找
     *
     * (左 -> 右 -> 当前）
     *
     * @param no 目标no
     * @return 如果找到就返回该Node，没有找到则返回null
     */
    public HeroNode postOrderSearch(int no) {
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.no == no) {
            return this;
        }
        return null;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    public boolean delNode(int no) {
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return true;
        }
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return true;
        }
        boolean flag = false;
        if (this.left != null) {
            flag = this.left.delNode(no);
        }
        if (flag) {
            return true;
        }
        if (this.right != null) {
            flag = this.right.delNode(no);
        }
        return flag;
    }

}