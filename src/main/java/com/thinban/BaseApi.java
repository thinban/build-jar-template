package com.thinban;

import cn.hutool.core.codec.Base64;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.Properties;
import java.util.UUID;

public class BaseApi {
    private static final Log log = LogFactory.get();
    private static final String FILE_NAME = "xinkeapi.properties";
    private static Properties prop;
    protected static String MAIL_URL;

    static {
        init();
    }

    //初始化
    private static void init() {
        try {
            log.info("[XINKEAPI] 初始化配置文件：{}", FILE_NAME);
            InputStream is = BaseApi.class.getClassLoader().getResourceAsStream(FILE_NAME);
            //属性列表
            prop = new Properties();
            prop.load(is);
            if (prop != null) {
                MAIL_URL = prop.getProperty("mail.url");
            } else {
                log.error("[XINKEAPI] 初始化配置文件失败：{}", FILE_NAME);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected static String uuid() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase(Locale.ROOT);
    }

    protected static String md5(String str) {
        String md5Str;
        String md5Str1 = null;
        if (str != null && str.length() != 0) {
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                try {
                    md.update(str.getBytes("UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                byte b[] = md.digest();

                StringBuffer buf = new StringBuffer("");
                for (int offset = 0; offset < b.length; offset++) {
                    int i = b[offset];
                    if (i < 0) i += 256;
                    if (i < 16) buf.append("0");
                    buf.append(Integer.toHexString(i));
                }
                //32位
                md5Str = buf.toString();
                //转成base64位
                try {
                    md5Str1 = Base64.encode(md5Str.getBytes("UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return md5Str1;
    }

}
