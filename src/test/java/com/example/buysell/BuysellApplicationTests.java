package com.example.buysell;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class BuysellApplicationTests {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void contextLoads() {
    }

    @Test
    void testPasswordMatching() {
        String rawPassword = "test1"; // Түпнұсқа парольді осында енгізіңіз
        String encodedPassword = "$2a$08$a4cDZ9oTERbS1Yotij.HYuamcg30zxhThMpqlVYatsD3yqgaXwC/."; // Дерекқордан алынған кодталған пароль

        // Түпнұсқа пароль кодталған парольмен сәйкес келе ме, тексеру
        boolean isMatch = passwordEncoder.matches(rawPassword, encodedPassword);
        System.out.println("Пароль сәйкес келеді ме: " + isMatch);

        // Тест арқылы сәйкестікті тексеру
        Assertions.assertTrue(isMatch, "Пароль сәйкес келмейді");
    }
    @Test
    void encodePassword() {
        String rawPassword = "test1"; // Тексеру үшін түпнұсқа парольді қойыңыз
        String encodedPassword = passwordEncoder.encode(rawPassword);
        System.out.println("Encoded password: " + encodedPassword);
    }

}
