package com.jimikongjian;

import com.jimikongjian.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by fuxiuyang on 17/4/4.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMail {
    @Test
    public void contextLoads() {
        MailService mailService = new MailService();
//        mailService.sendMail("306781874@qq.com");
    }
}
