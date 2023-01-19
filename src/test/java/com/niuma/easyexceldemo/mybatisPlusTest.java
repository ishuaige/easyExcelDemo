package com.niuma.easyexceldemo;

import com.niuma.easyexceldemo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;


/**
 * @author niumazlb
 * @create 2023-01-19 20:39
 */
@SpringBootTest
public class mybatisPlusTest {
    @Resource
    UserService userService;

    @Test
    public void testUserService(){
        long count = userService.count();
        System.out.println(count);
    }

}
