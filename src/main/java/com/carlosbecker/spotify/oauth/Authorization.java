package com.carlosbecker.spotify.oauth;

import com.jcabi.aspects.Immutable;
import org.apache.commons.codec.binary.Base64;
import lombok.RequiredArgsConstructor;

@Immutable
@RequiredArgsConstructor
public final class Authorization {
    private final transient String clientId;
    private final transient String clientSecret;

    @Override
    public String toString() {
        final String idSecret = clientId + ":" + clientSecret;
        return "Basic " + new String(Base64.encodeBase64(idSecret.getBytes()));
    }
}
