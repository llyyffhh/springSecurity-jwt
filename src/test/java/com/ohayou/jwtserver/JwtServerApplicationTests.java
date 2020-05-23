package com.ohayou.jwtserver;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class JwtServerApplicationTests {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    void contextLoads() {
        System.out.println(bCryptPasswordEncoder.matches("admin","$2a$10$zoe8QI1Fp7zRGSDHvihW1eRTRV60hEoDvFwbmtBfvBwa1X7r8Idv2"));
    }

}
