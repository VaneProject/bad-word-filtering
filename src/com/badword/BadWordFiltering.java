package com.badword;

import com.badword.method.ReadFile;
import com.badword.method.ReadURL;
import com.badword.words.BadWords;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class BadWordFiltering implements BadWords, ReadFile, ReadURL {
    private final Set<String> set = new HashSet<>(List.of(koreaWord1));
    private String substituteValue = "*";

    //대체 문자 지정
    //기본값 : *
    public BadWordFiltering(String substituteValue) {
        this.substituteValue = substituteValue;
    }

    public BadWordFiltering() {}

    //특정 문자 추가, 삭제
    @Override
    public void add(String text) {
        set.add(text);
    }

    @Override
    public void add(String...texts) {
        set.addAll(List.of(texts));
    }

    @Override
    public void add(List<String> texts) {
        set.addAll(texts);
    }

    @Override
    public void add(Set<String> texts) {
        set.addAll(texts);
    }

    @Override
    public void remove(String...texts) {
        List.of(texts).forEach(set::remove);
    }

    @Override
    public void remove(List<String> texts) {
        texts.forEach(set::remove);
    }

    @Override
    public void remove(Set<String> texts) {
        texts.forEach(set::remove);
    }

    //비속어 있다면 대체
    public String change(String text) {
        String[] words = set.stream().filter(text::contains).toArray(String[]::new);
        for (String v : words) {
            String sub = this.substituteValue.repeat(v.length());
            text = text.replace(v, sub);
        }
        return text;
    }

    public String change(String text, String[] sings) {
        StringBuilder singBuilder = new StringBuilder("[");
        for (String sing : sings) singBuilder.append(Pattern.quote(sing));
        singBuilder.append("]*");
        String patternText = singBuilder.toString();

        for (String word : set) {
            if (word.length() == 1) text = text.replace(word, substituteValue);
            String[] chars = word.chars().mapToObj(Character::toString).toArray(String[]::new);
            text = Pattern.compile(String.join(patternText, chars))
                    .matcher(text)
                    .replaceAll(v -> substituteValue.repeat(v.group().length()));
        }

        return text;
    }

    //비속어가 1개라도 존재하면 true 반환
    public boolean check(String text) {
        return set.stream()
                .anyMatch(text::contains);
    }

    //공백을 없는 상태 체크
    public boolean blankCheck(String text) {
        return check(text.replace(" ", ""));
    }
}