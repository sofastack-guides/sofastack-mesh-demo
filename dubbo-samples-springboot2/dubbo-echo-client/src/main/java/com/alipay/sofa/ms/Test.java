/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.alipay.sofa.ms;

/**
 * @author yiji
 * @version : Test.java, v 0.1 2020年06月07日 10:06 上午 yiji Exp $
 */
public class Test {

    public static void main(String[] args) {

        System.out.println(parseInteger("-2147483648"));
        System.out.println(parseInteger("+2147483647"));

    }

    private static int parseInteger(String value) {

        // 空值，直接返回
        if (value == null) { return 0; }

        // 移除前后空字符, 空值，直接返回
        value = value.trim();
        if (value.length() == 0) {
            return 0;
        }

        // 提取有效数字部分，包括'+'和'-'
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < value.length(); i++) {
            char ch = value.charAt(i);

            if (i == 0 && (ch == '+' || ch == '-')) {
                sb.append(ch);
                continue;
            }

            if (ch >= '0' && ch <= '9') {
                sb.append(ch);
            } else {
                break;
            }
        }

        value = sb.toString();

        int parsed = 0;
        boolean negative = false;
        int i = 0, len = value.length();
        int digit;

        int limit = -Integer.MAX_VALUE;

        char firstChar = value.charAt(0);
        //  处理 "+" or "-"
        if (firstChar < '0') {
            if (firstChar == '-') {
                negative = true;
                limit = Integer.MIN_VALUE;
            } else if (firstChar != '+') {
                // 字符串无效
                return 0;
            }

            if (len == 1) {
                /**
                 * 判断第一个字符要么是+或者-,
                 * 只有符号位，无法解析
                 */
                return 0;
            }

            i++;
        }

        while (i < len) {
            digit = value.charAt(i++) - '0';
            if (digit < 0) {
                // 无效字符无法处理
                return 0;
            }
            parsed *= 10;

            if (parsed < limit + digit) {
                // 数字溢出
                return 0;
            }

            parsed -= digit;
        }

        return negative ? parsed : -parsed;

    }
}

