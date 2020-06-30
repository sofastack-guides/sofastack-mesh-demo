/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.alipay.sofa.ms;

/**
 * @author yiji
 * @version : LinkNode.java, v 0.1 2020年06月07日 10:32 上午 yiji Exp $
 */
public class LinkNode {
    int      value;
    LinkNode next;

    public static LinkNode mergeOf(LinkNode firstLink, LinkNode secondLink) {

        // 如果当前是空链表
        if (firstLink == null) {
            return secondLink;
        }

        // 如果node是空链表
        if (secondLink == null) {
            return firstLink;
        }

        for (LinkNode insert = secondLink; insert.next != null; insert = secondLink.next) {
            LinkNode prev = firstLink;

            // 寻找次大于插入值的节点
            while (insert.value < prev.value) {
                prev = prev.next;
            }

            prev.next = insert;
        }

        // 返回当前链表
        return firstLink;
    }
}

