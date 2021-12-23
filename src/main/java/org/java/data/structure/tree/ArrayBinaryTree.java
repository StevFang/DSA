package org.java.data.structure.tree;

class ArrayBinaryTreeDemo {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrayBinaryTree tree = new ArrayBinaryTree(arr);
        System.out.println("开始数组的前序遍历");
        // 从根节点开始遍历，根节点的序号为0
        // 前序遍历：1,2,4,5,3,6,7 (当前,左,右)
        tree.preOrder();
        System.out.println("开始数组的中序遍历");
        // 中序遍历：4,2,5,1,6,3,7（左，当前，右）
        tree.infixOrder();
        System.out.println("开始数组的后序遍历");
        // 后序遍历：4,5,2,6,7,3,1（左，右，当前）
        tree.postOrder();
    }

}

public class ArrayBinaryTree {

    /**
     * 存储数据节点的数组
     */
    private final int[] arr;

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    /**
     * 重载前序遍历
     */
    public void preOrder() {
        this.preOrder(0);
    }

    /**
     * 顺序存储二叉树的前序遍历
     *
     * @param index 数组的下标
     */
    public void preOrder(int index) {
        // 如果数组为空，或者arr.length == 0
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
            return;
        }
        // 输出当前节点
        System.out.println(arr[index]);
        // 向左递归遍历
        int left = 2 * index + 1;
        if (left < arr.length) {
            this.preOrder(left);
        }
        // 向右递归遍历
        int right = 2 * index + 2;
        if (right < arr.length) {
            this.preOrder(right);
        }
    }

    public void infixOrder() {
        this.infixOrder(0);
    }

    /**
     * 顺序存储二叉树的中序遍历
     *
     * @param index
     */
    public void infixOrder(int index) {
        // 如果数组为空，或者arr.length == 0
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
            return;
        }
        // 向左递归遍历
        int left = 2 * index + 1;
        if (left < arr.length) {
            this.infixOrder(left);
        }
        // 输出当前节点
        System.out.println(arr[index]);
        // 向右递归遍历
        int right = 2 * index + 2;
        if (right < arr.length) {
            this.infixOrder(right);
        }
    }

    public void postOrder() {
        this.postOrder(0);
    }

    /**
     * 顺序存储二叉树的后序遍历
     *
     * @param index
     */
    private void postOrder(int index) {
        // 如果数组为空，或者arr.length == 0
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
            return;
        }
        // 向左递归遍历
        int left = 2 * index + 1;
        if (left < arr.length) {
            this.postOrder(left);
        }
        // 向右递归遍历
        int right = 2 * index + 2;
        if (right < arr.length) {
            this.postOrder(right);
        }
        // 输出当前节点
        System.out.println(arr[index]);
    }


}
