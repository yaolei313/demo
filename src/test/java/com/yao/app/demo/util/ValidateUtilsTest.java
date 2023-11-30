package com.yao.app.demo.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ValidateUtilsTest {

    @Test
    public void test1() {
        String email = "zhangsan@gmail.com";
        boolean result = ValidateUtils.isValidEmail(email);
        Assertions.assertTrue(result, "invalid validateEmail impl");
    }

}
