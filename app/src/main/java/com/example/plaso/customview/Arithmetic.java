package com.example.plaso.customview;

import android.text.TextUtils;
import android.util.Log;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Arithmetic {


    /**
     * 单链表反转
     */
    static class Node{
        int data;
        Node next;
        public Node(){};
        Node(int data){
            this.data = data;
        }
    }

    static Node makeNode(){
        Node node = new Node();
        Node linkNode1 = new Node(1);
        Node linkNode2 = new Node(2);
        Node linkNode3 = new Node(3);
        Node linkNode4 = new Node(4);
        Node linkNode5 = new Node(5);
        Node linkNode6 = new Node(6);

        linkNode1.next = linkNode2;
        linkNode2.next = linkNode3;
        linkNode3.next = linkNode4;
        linkNode4.next = linkNode5;
        linkNode5.next = linkNode6;
        return linkNode1;
    }


    /**
     * 单链表反转
     * @param node
     * @return
     */

    //遍历法
    static Node reverseLinkedList(Node node){
        Node previousNode = null;
        Node currentNode = node;
        Node reverseNode = null;
        while (currentNode !=null){
            Node nextNode = currentNode.next;
            if(null == nextNode){
                reverseNode = currentNode;
            }
            currentNode.next = previousNode;
            previousNode = currentNode;
            currentNode = nextNode;
        }
        return reverseNode;
    }

    //递归实现
    static Node recursionNode(Node node){
        if(null == node.next || node == null){
            return node;
        }
        Node nextNode = node.next;
        node.next = null;
        Node reverseNode = recursionNode(nextNode);
        nextNode.next = node;
        return reverseNode;
    }


    /**
     * 寻找链表的倒数第K个节点
     */
    static Node findLastK(Node node, int k){
        Node nodeList1 = node;
        Node nodeList2 ;
        if(k<=0 || null== node){
            return null;
        }

        for(int i=1; i<k; i++){
            if(nodeList1.next!=null){
                nodeList1 = nodeList1.next;
            }else {
                return null;
            }
        }

        nodeList2 = node;
        while (nodeList1.next!=null){
            nodeList1 = nodeList1.next;
            nodeList2 = nodeList2.next;
        }
        return nodeList2;
    }

    /**
     * 两个栈实现一个队列
     */
    public static class stackSolution{
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();

        public void push(int node){
            while (!stack1.empty()){
                stack2.push(stack1.pop());
            }
            stack1.push(node);
            while (!stack2.empty()){
                stack1.push(stack2.pop());
            }
        }

        public Stack getStack(){
            return stack1;
        }
    }

    /**
     * 两个列队实现一个栈
     */
    public static class linkSolution{
        LinkedList<Integer> list1 = new LinkedList<>();

        public void addList(int data){
            list1.add(0,data);
        }

        public LinkedList<Integer> getStack(){
            return list1;
        }
    }

    public static int hammingWeight(int n){
        /*String s = Integer.toBinaryString(n);
        int sum = 0;
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) == '1')  sum += 1;
        }
        return sum;*/
        int sum = 0;
        while (n!=0){
            sum += 1;
            n = n&(n-1);
        }
        return sum;
    }


    //计算二叉树的最大深度  BFS实现
    public static int maxDepth(TreeNode root){
        if(root == null){
            return 0;
        }

        List<TreeNode> list = new ArrayList<>();
        int count = 0;
        list.add(root);
        while (list.size()>0){
            int size = list.size();
            for(int i=size; i>0; i--){
                TreeNode node = list.remove(0);
                if(node.left != null){
                    list.add(node.left);
                }

                if(node.right != null){
                    list.add(node.right);
                }
            }
            count ++;
        }
        return count;
    }

    //二叉树最大深度，递归实现
    public static int maxDepth1(TreeNode root){
        if(root == null){
            return 0;
        }

        return 1+Math.max(maxDepth1(root.left),maxDepth1(root.right));
    }


    /**
     * 计算二叉树最小深度 BFS
     * @return
     */

    public static int minDepth(TreeNode root){
        if(root == null){
            return 0;
        }
        int count = 0;
        List<TreeNode> treeNodeList = new LinkedList<>();
        treeNodeList.add(root);
        while (treeNodeList.size()>0){
            int size = treeNodeList.size();
            for(int i=size; i>0; i--){
                TreeNode treeNode = treeNodeList.get(0);
                treeNodeList.remove(0);
                if(treeNode.left == null && treeNode.right == null){
                    treeNodeList.clear();
                    break;
                }else {
                    if(treeNode.left != null) treeNodeList.add(treeNode.left);
                    if(treeNode.right != null) treeNodeList.add(treeNode.right);
                }
            }
            count++;
        }
        return count;
    }

    /**
     * 二叉树最小深度 递归实现
     * @return
     */

    public static int minDepth1(TreeNode root){
        if(root == null){
            return 0;
        }

        if(root.left==null && root.right==null){
            return 1;
        }

        if(root.left == null ){
            return 1+minDepth1(root.right);
        }

        if(root.right == null){
            return 1+minDepth1(root.left);
        }

        return 1+Math.min(minDepth1(root.left),minDepth1(root.right));
    }


    /**
     * 二叉树前序中序后续遍历
     * @param root
     */
    public static void preOrder(TreeNode root){
        if(root != null){
            Log.d("zh","node_"+root.num);
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    public static void midOrder(TreeNode root){
        if(root != null){
            midOrder(root.left);
            Log.d("zh","node_"+root.num);
            midOrder(root.right);
        }
    }

    public static void postOrder(TreeNode root){
        if(root != null){
            postOrder(root.left);
            postOrder(root.right);
            Log.d("zh","node_"+root.num);
        }
    }

    /**
     * 已知前序 中序 求后序遍历
     * @param preOrder 前序遍历
     * @param midOrder 中序遍历
     * @return
     */
    public static TreeNode getOrdertree(String preOrder,String midOrder){
        if(TextUtils.isEmpty(preOrder) || TextUtils.isEmpty(midOrder)){
            return null;
        }

        TreeNode node = new TreeNode(Integer.parseInt(preOrder.substring(0,1)));
        if(preOrder.length() == 1){
            return node;
        }

        int leftTrerLength = midOrder.indexOf(preOrder.charAt(0));
        if(leftTrerLength == 0){
            node.left = null;
        }else {
            node.left = getOrdertree(preOrder.substring(1,leftTrerLength+1),midOrder.substring(0,leftTrerLength));
        }

        if(leftTrerLength == midOrder.length()-1){
            node.right = null;
        }else {
            node.right = getOrdertree(preOrder.substring(leftTrerLength+1),midOrder.substring(leftTrerLength+1));
        }

        return node;
    }


    public Node reverseList(Node head){
        if(head == null){
            return head;
        }

        Node preNode = null;
        Node currNode = head;
        Node reverseNode  = null;
        while (currNode!=null){
            Node nextNode = currNode.next;
            if(nextNode == null){
                reverseNode = currNode;
            }
            currNode.next = preNode;
            preNode = currNode;
            currNode = nextNode;
        }

        return reverseNode;
    }


    /**
     * 二叉树的所欲路径
     */
    List<String> list = new ArrayList<>();
    public List<String> binaryTreePaths(TreeNode root) {
        if(root == null){
            return new ArrayList<String>();
        }

        List<String> treePath = getTreePath(root, list, String.valueOf(root.num));
        return treePath;
    }

    public List<String> getTreePath(TreeNode root,List<String> list,String path){
        if(root.left==null && root.right==null){
            list.add(path);
        }

        if(root.left!=null){
            getTreePath(root.left,list,path+"->"+root.left.num);
        }

        if(root.right!=null){
            getTreePath(root.right,list,path+"->"+root.right.num);
        }
        return list;
    }


    /**
     * 创建树结构
     * @return
     */
    public static TreeNode makeTree(){
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode6 = new TreeNode(6);
        TreeNode treeNode7 = new TreeNode(7);
        TreeNode treeNode8 = new TreeNode(8);

        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;
        treeNode3.left = treeNode7;
        treeNode3.right = treeNode8;
        treeNode4.left = treeNode6;

        return treeNode1;
    }

    static class TreeNode{
        int num;
        TreeNode left;
        TreeNode right;

        TreeNode(int num){
            this.num = num;
        }
    }




    //合并有序链表
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        List<Integer> nodeVal = new ArrayList<>();
        while (l1!=null){
            nodeVal.add(l1.val);
            l1=l1.next;
        }

        while (l2!=null){
            nodeVal.add(l2.val);
            l2 = l2.next;
        }

        Integer[] array = nodeVal.toArray(new Integer[nodeVal.size()]);
        if(array.length==0){
            return null;
        }
        Integer[] sort = quickSort(array, 0, array.length-1);

        ListNode firstNode=null;
        ListNode node=null;
        for(int i=0;i<sort.length;i++){
            if(i==0){
                firstNode = new ListNode(sort[i]);
                node = firstNode;
            }else {
                ListNode listNode = new ListNode(sort[i]);
                node.next = listNode;
                node = listNode;
            }
        }
        return firstNode;
    }

    public static Integer[] quickSort(Integer[] list,int L,int R){
        int i=L;
        int j=R;
        int target = list[(L+R)/2];

        while (i<=j){
            while (list[i]<target){
                i++;
            }

            while (list[j]>target){
                j--;
            }

            if(i<=j){
                int temp = list[i];
                list[i]=list[j];
                list[j]=temp;
                i++;
                j--;
            }
        }

        if(i<R) quickSort(list,i,R);
        if(j>L) quickSort(list,L,j);
        return list;
    }

}
