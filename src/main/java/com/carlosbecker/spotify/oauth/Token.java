package com.carlosbecker.spotify.oauth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class Token {
    private String accessToken;
    private String tokenType;
    private int expiresIn;

    public String value() {
        return this.tokenType + " " + this.accessToken;
    }
}
