package com.badword.method;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public interface ReadFile extends EditWord {
    default void addFile(File file, String criteria, boolean rmBlank) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder fileContent = new StringBuilder();
            reader.lines().forEach(fileContent::append);
            StringTokenizer tokenizer = criteria == null
                    ? new StringTokenizer(fileContent.toString())
                    : new StringTokenizer(fileContent.toString(), criteria);
            if (rmBlank) {
                while (tokenizer.hasMoreTokens()) add(tokenizer.nextToken().strip());
            } else {
                while (tokenizer.hasMoreTokens()) add(tokenizer.nextToken());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    default void addFile(String path) {
        addFile(path, null, true);
    }

    default void addFile(String path, String criteria) {
        addFile(path, criteria, true);
    }

    default void addFile(String path, boolean rmBlank) {
        addFile(path, null, rmBlank);
    }

    default void addFile(String path, String criteria, boolean rmBlank) {
        addFile(new File(path), criteria, rmBlank);
    }

    default void addFile(File file) {
        addFile(file, null, true);
    }

    default void addFile(File file, String criteria) {
        addFile(file, criteria, true);
    }

    default void addFile(File file, boolean rmBlank) {
        addFile(file, null, rmBlank);
    }
}
