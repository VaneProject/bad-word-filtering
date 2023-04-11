package com.badword.method;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.StringTokenizer;

public interface ReadURL extends EditWord {
    default void readURL(URL url, String delim, boolean rmBlank) {
        try (InputStream is = url.openConnection().getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            final String nextLine = System.lineSeparator();
            final StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) builder.append(line).append(nextLine);

            StringTokenizer tokenizer = delim == null
                    ? new StringTokenizer(builder.toString())
                    : new StringTokenizer(builder.toString(), delim);
            if (rmBlank) add(tokenizer.nextToken().strip());
            else add(tokenizer.nextToken());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    default void readURL(String url) {
        readURL(url, null, true);
    }

    default void readURL(String url, String delim) {
        readURL(url, delim, true);
    }

    default void readURL(String url, boolean rmBlank) {
        readURL(url, null, rmBlank);
    }

    default void readURL(String url, String delim, boolean rmBlank) {
        try {
            readURL(new URL(url), delim, rmBlank);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    default void readURL(URL url, String delim) {
        readURL(url, delim, true);
    }

    default void readURL(URL url, boolean rmBlank) {
        readURL(url, null, rmBlank);
    }

    default void readURL(URL url) {
        readURL(url, null, true);
    }
}
