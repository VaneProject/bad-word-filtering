package com.badword;

import java.io.File;

class BadWordFilteringTest {
    public static void main(String[] args) {
        BadWordFiltering filtering = new BadWordFiltering();
        System.out.println(filtering.change("안녕 ㅅ_ㅂ", new String[] {"_"}));
    }
}
