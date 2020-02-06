package com.milepost.authenticationApi;

import com.milepost.api.util.EncryptionUtil;
import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;

import java.util.Date;

/**
 * Created by Ruifu Hua on 2020/2/3.
 */
public class Test1 {
    @Test
    public void test1() throws Exception {
        int result = 0;
        long milliseconds = new Date().getTime() - DateUtils.parseDate("2020/02/04", new String[]{"yyyy/MM/dd"}).getTime();
        if(milliseconds <= 0){
            result = 0;
        }
        result = (int)(milliseconds / (24*60*60*1000)) + 1;
        System.out.println(result);
    }

}
