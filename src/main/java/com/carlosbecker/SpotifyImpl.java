package com.carlosbecker;

import com.carlosbecker.spotify.oauth.Token;
import com.google.common.net.HttpHeaders;
import com.jcabi.http.Request;
import com.jcabi.http.request.JdkRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class SpotifyImpl implements Spotify {
    private final transient Token token;

    @Override
    public Request entry() {
        return new JdkRequest("https://api.spotify.com/v1/")
            .header(HttpHeaders.AUTHORIZATION, this.token.value());
    }
}
