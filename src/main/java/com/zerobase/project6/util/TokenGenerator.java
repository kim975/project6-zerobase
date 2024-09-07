package com.zerobase.project6.util;

import java.util.UUID;

public class TokenGenerator {

    public static String randomUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
