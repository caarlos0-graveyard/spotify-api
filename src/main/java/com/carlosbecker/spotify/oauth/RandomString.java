package com.carlosbecker.spotify.oauth;

import com.google.common.base.Joiner;
import com.jcabi.aspects.Immutable;
import java.util.ArrayList;
import java.util.List;

@Immutable
public class RandomString {
    private static final String possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    @Override
    public String toString() {
        return this.toString(16);
    }

    public String toString(int length) {
        final List<Character> text = new ArrayList<Character>();
        for (int i = 0; i < length; i++) {
            text.add(possible.charAt(randomPosition()));
        }
        return Joiner.on("").join(text);
    }

    private int randomPosition() {
        return (int) Math.floor(Math.random() * possible.length());
    }
}
