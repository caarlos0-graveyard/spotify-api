package com.carlosbecker.spotify;

import com.jcabi.aspects.Immutable;
import com.jcabi.http.Request;
import com.jcabi.http.response.JsonResponse;
import javax.json.JsonObject;
import lombok.SneakyThrows;

public interface Profile {
    JsonObject data();

    @Immutable
    public static class Me implements Profile {
        private final transient Request req;

        public Me(Request req) {
            this.req = req.uri().path("me").back();
        }

        @Override
        @SneakyThrows
        public JsonObject data() {
            return this.req.fetch()
                .as(JsonResponse.class)
                .json()
                .readObject();
        }

        @Override
        public String toString() {
            return this.req.toString();
        }
    }
}
