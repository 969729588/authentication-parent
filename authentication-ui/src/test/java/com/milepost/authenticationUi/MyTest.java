package com.milepost.authenticationUi;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ruifu Hua on 2020/3/5.
 */
public class MyTest {

    /**
     * 生成jks文件时指定的alias，
     * jks文件在core项目中
     */
    public static String JKS_ALIAS = "milepost-alias";
    public static String JKS_FILE_NAME = "milepost.jks";
    public static String JKS_PASSWORD = "milepost";

    @Test
    public void test2(){
        String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1ODQxMTUzMjYsInVzZXJfbmFtZSI6ImFkbWluIiwianRpIjoiN2UwZmQzNTAtZTM0My00MzI3LWE5MmMtODdjZDk2MzQ1ODM2IiwiY2xpZW50X2lkIjoiY2xpZW50X2lkX3RlbmFudDEiLCJzY29wZSI6WyJhbGwiXX0.iCDT5gxc0f44wpaqSC8mTz3xJx6txPzNCBt2c3K3jwgPpXswyzkN2dBrfYVOpYmIwBe9dhd8_lAy-Ou5PBkpTNTMECN0GQjssklZOS32wLn5gcrOSecp29c0chHQnOiT-7o7gAMh5yk35JwceAYfWAa8S8qy1pBURM4UBG2J3XhOzARGcrYopxxLwYgY9ykW783s5KcG3XJ4Dp-J0xPQK92HZwJaoufSIRMWq6M50_SRZg5BbzrdNWYHNuBEy-r8oSyOp5dcdOqK-T7LpPAaU5eVYPYFZax4ohvBnLf3hptlFQp-_7ei5w9b2Hwc-Ad_0nO8g4Hpb2XaWrJVtUkzNQ";
        Jwt jwt = JwtHelper.decode(token);
        System.out.println(jwt.getClaims());


        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource(JKS_FILE_NAME+"1"), JKS_PASSWORD.toCharArray());
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair(JKS_ALIAS));

        Map<String, Object> map = new HashMap<>();
        OAuth2AccessToken oAuth2AccessToken = converter.extractAccessToken(token, map);
        System.out.println(oAuth2AccessToken);
        System.out.println(map);
    }

    @Test
    public void test1(){
        System.out.println("adsfAA234".equalsIgnoreCase("Adsfaa234"));
    }
}
