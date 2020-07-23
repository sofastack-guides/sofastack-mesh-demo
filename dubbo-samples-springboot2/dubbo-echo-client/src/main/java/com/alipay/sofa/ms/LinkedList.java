/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.alipay.sofa.ms;

/**
 * @author yiji
 * @version : LinkNode.java, v 0.1 2020年06月07日 10:32 上午 yiji Exp $
 */

public class LinkedList {

    public static class Node {
        public int  data;
        public Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    public static Node mergeOf(Node head1, Node head2) {
        if (head1 == null) { return head2; }
        if (head2 == null) { return head1; }

        //合并后单链表头结点
        Node head = head1.data < head2.data ? head1 : head2;

        Node cur1 = head == head1 ? head1 : head2;
        Node cur2 = head == head1 ? head2 : head1;

        Node prev = null;   //cur1前一个元素
        Node next;          //cur2的后一个元素

        while (cur1 != null && cur2 != null) {
            if (cur1.data <= cur2.data) {
                prev = cur1;
                cur1 = cur1.next;
            } else {
                next = cur2.next; // 队列2节点后移一个
                prev.next = cur2; // 队列2节点插入队列1
                cur2.next = cur1; // 更新队列2尾结点
                prev = cur2;
                cur2 = next;
            }
        }

        prev.next = cur1 == null ? cur2 : cur1; // 挂载剩余队列节点
        return head;
    }

    public static Node mergeOf2(Node head1, Node head2) {
        if (head1 == null) { return head2; }
        if (head2 == null) { return head1; }

        //合并后的链表
        Node head = null;
        if (head1.data > head2.data) {
            //把head较小的结点给头结点
            head = head2;
            //继续递归head2
            head.next = mergeOf2(head1, head2.next);
        } else {
            head = head1;
            head.next = mergeOf2(head1.next, head2);
        }
        return head;
    }

    public static void main(String[] args) {

        Node node1 = new Node(1);
        Node node3 = new Node(3);
        Node node5 = new Node(5);

        Node node_1 = new Node(1);
        Node node2 = new Node(2);
        Node node4 = new Node(4);
        Node node6 = new Node(6);
        node1.next = node3;
        node3.next = node5;

        node_1.next = node2;
        node2.next = node4;
        node4.next = node6;

        Node node = mergeOf2(node_1, node1);
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
    }
}

