package com.carlosbecker.spotify.oauth;

import com.carlosbecker.SpotifyException;
import com.google.common.net.HttpHeaders;
import com.jcabi.http.Request;
import com.jcabi.http.Response;
import com.jcabi.http.request.JdkRequest;
import com.jcabi.http.response.JsonResponse;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import javax.json.JsonObject;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

@RequiredArgsConstructor
public final class OAuth {
    private static final String ROOT = "https://accounts.spotify.com/";

    @NonNull
    private final transient String clientId;
    @NonNull
    private final transient String clientSecret;
    @NonNull
    private final transient String scope;
    @NonNull
    private final transient String callback;

    public String authorizationUri() {
        return new JdkRequest(ROOT + "authorize?")
            .uri()
            .queryParam("response_type", "code")
            .queryParam("client_id", this.clientId)
            .queryParam("scope", this.scope)
            .queryParam("redirect_uri", this.callback)
            .queryParam("state", new RandomString())
            .toString();
    }

    public Token token(String redirect) throws IOException, SpotifyException {
        Response response = request(queryStringValue(redirect, "code"));
        JsonObject json = jsonFrom(response);
        if (response.status() != 200) {
            throw new SpotifyException(json.getString("error"));
        }
        return tokenFrom(json);
    }

    private Token tokenFrom(JsonObject json) {
        return new Token(
            json.getString("access_token"),
            json.getString("token_type"),
            json.getInt("expires_in"));
    }

    private JsonObject jsonFrom(Response response) {
        return response
            .as(JsonResponse.class)
            .json()
            .readObject();
    }

    private Response request(final String code) throws IOException {
        return new JdkRequest(ROOT + "api/token")
            .body()
            .formParam("code", code)
            .formParam("redirect_uri", callback)
            .formParam("grant_type", "client_credentials")
            .back()
            .header(
                HttpHeaders.AUTHORIZATION,
                new Authorization(this.clientId, this.clientSecret)
            ).method(Request.POST)
            .fetch();
    }

    @SneakyThrows
    private String queryStringValue(final String uri, final String name) {
        final List<NameValuePair> values = URLEncodedUtils
            .parse(new URI(uri), "UTF-8");
        for (final NameValuePair pair : values) {
            if (pair.getName().equals(name)) {
                return pair.getValue();
            }
        }
        return null;
    }
}
