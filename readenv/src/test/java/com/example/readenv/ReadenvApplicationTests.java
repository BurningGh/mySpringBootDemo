package com.example.readenv;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReadenvApplicationTests {


    @Test
    public void contextLoads() {
        String aaa = Global.getConfig("aaa",String.class,"ccc");
        System.out.println(aaa);
    }

}
