package com.back;

public class Util {
    // 특수문자 판별
    public static boolean isInValidContent(String content) {
        // 특수문자 조건
        return  !content.matches("[a-zA-Z0-9가-힣\\. ]+");
    }
}
