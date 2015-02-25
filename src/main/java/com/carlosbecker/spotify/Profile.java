package com.carlosbecker.spotify;

import com.jcabi.aspects.Immutable;
import com.jcabi.http.Request;
import com.jcabi.http.Response;
import com.jcabi.http.response.JsonResponse;
import javax.json.JsonObject;
import lombok.SneakyThrows;

public interface Profile {
    String displayName();

    String uri();

    String id();

    String type();

    @Immutable
    public static class Me implements Profile {
        private final transient Request req;
        private transient Response res = null;
        private transient JsonObject json = null;

        public Me(Request req) {
            this.req = req.uri().path("me").back();
        }

        @Override
        public String displayName() {
            this.request();
            return json.getString("display_name");
        }

        @Override
        public String uri() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public String id() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public String type() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public String toString() {
            return this.req.toString();
        }

        @SneakyThrows
        private void request() {
            if (this.res != null) {
                return;
            }
            this.res = this.req.fetch();
            this.json = this.res.as(JsonResponse.class)
                .json()
                .readObject();
            System.out.println(this.json);
        }
    }
}
