package com.carlosbecker.spotify.oauth;

import com.jcabi.aspects.Immutable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Immutable
@RequiredArgsConstructor
public final class Token {
    private final String accessToken;
    private final String tokenType;
    private final int expiresIn;
    private final String refreshToken;

    public String value() {
        return this.tokenType + " " + this.accessToken;
    }
}
