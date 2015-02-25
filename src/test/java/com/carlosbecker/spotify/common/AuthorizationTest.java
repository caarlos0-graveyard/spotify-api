package com.carlosbecker.spotify.common;

import com.carlosbecker.spotify.oauth.Authorization;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

public final class AuthorizationTest {
    @Test
    public void basicAuthToStringIsTheRightBase64() throws Exception {
        MatcherAssert.assertThat(
            new Authorization("id", "secret").toString(),
            Matchers.equalTo("Basic aWQ6c2VjcmV0")
        );
    }
}
