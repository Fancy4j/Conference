package com.example.utils;

import org.jasypt.util.text.BasicTextEncryptor;

/**
 * 配置文件中密码加解密工具类
 */
public class JasyptUtil {

    public static void main(String[] args) {

        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        //加密所需的salt(盐)
        textEncryptor.setPassword("you salt");

        //要加密的数据（数据库的用户名或密码）
        String username = textEncryptor.encrypt("root");
        String password = textEncryptor.encrypt("123456");
        System.out.println("username:"+username);
        System.out.println("password:"+password);
    }
}