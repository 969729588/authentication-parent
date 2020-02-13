package com.milepost.authenticationUi.jasypt;

import com.milepost.authenticationUi.AuthenticationUiApplication;
import com.milepost.test.BaseTest;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Before;
import org.junit.Test;


/**
 * Created by Ruifu Hua on 2020/2/7.
 */
public class Test1 extends BaseTest<AuthenticationUiApplication> {

    private StringEncryptor encryptor;

    @Before
    public void init(){
        encryptor = getBean(StringEncryptor.class);
    }


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
