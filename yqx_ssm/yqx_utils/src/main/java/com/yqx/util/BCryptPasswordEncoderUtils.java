package com.yqx.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// 定义一个类 用于将密码进行加密
public class BCryptPasswordEncoderUtils {

    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    /**
     * Day03 定义一个方法 该方法用于将密码进行加密
     * @param password
     * @return
     * @Auto 于清晰
     */
    public static String encodePassword( String password ){
        return bCryptPasswordEncoder.encode( password );
    }


}
