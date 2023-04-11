package com.badword.method;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public interface ReadFile extends EditWord {
    default void readFile(File file, String delim, boolean rmBlank) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder fileContent = new StringBuilder();
            reader.lines().forEach(fileContent::append);
            StringTokenizer tokenizer = delim == null
                    ? new StringTokenizer(fileContent.toString())
                    : new StringTokenizer(fileContent.toString(), delim);
            if (rmBlank) {
                while (tokenizer.hasMoreTokens()) add(tokenizer.nextToken().strip());
            } else {
                while (tokenizer.hasMoreTokens()) add(tokenizer.nextToken());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    default void readFile(String path) {
        readFile(path, null, true);
    }

    default void readFile(String path, String delim) {
        readFile(path, delim, true);
    }

    default void readFile(String path, boolean rmBlank) {
        readFile(path, null, rmBlank);
    }

    default void readFile(String path, String delim, boolean rmBlank) {
        readFile(new File(path), delim, rmBlank);
    }

    default void readFile(File file) {
        readFile(file, null, true);
    }

    default void readFile(File file, String delim) {
        readFile(file, delim, true);
    }

    default void readFile(File file, boolean rmBlank) {
        readFile(file, null, rmBlank);
    }
}
