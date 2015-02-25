package com.carlosbecker;

import com.carlosbecker.spotify.Profile;
import com.carlosbecker.spotify.oauth.Token;
import com.google.common.net.HttpHeaders;
import com.jcabi.aspects.Immutable;
import com.jcabi.http.Request;
import com.jcabi.http.request.JdkRequest;
import lombok.RequiredArgsConstructor;

@Immutable
@RequiredArgsConstructor
public final class Spotify {
    private final transient Token token;

    public Request entry() {
        return new JdkRequest("https://api.spotify.com/v1/")
            .header(HttpHeaders.AUTHORIZATION, this.token.value())
            .header(HttpHeaders.ACCEPT, "application/json")
            .header(HttpHeaders.CONTENT_TYPE, "application/json; charset=utf-8")
            .header(HttpHeaders.USER_AGENT, "caarlos0/spotify-api");
    }

    public Profile me() {
        return new Profile.Me(this.entry());
    }
}
