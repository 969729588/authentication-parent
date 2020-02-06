package com.milepost.authenticationUi.jasypt;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * Created by Ruifu Hua on 2020/2/7.
 */
@RunWith(SpringRunner.class)
//value属性配置属性，覆盖yml中的配置
@SpringBootTest(value = {"spring.main.allow-bean-definition-overriding=true",
        "jasypt.encryptor.password=milepost",
        "jasypt.encryptor.algorithm=PBEWithMD5AndDES"})
public class Test1 {

    @Autowired
    StringEncryptor encryptor;

    /**
     * #jasypt加密的密匙
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {
        String password = "认证服务";
        System.out.println(encryptor.encrypt(password));
    }
}
