package com.carlosbecker.spotify.oauth;

import com.jcabi.aspects.Immutable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Immutable
@AllArgsConstructor
public class Token {
    private String accessToken;
    private String tokenType;
    private int expiresIn;

    public String value() {
        return this.tokenType + " " + this.accessToken;
    }
}
