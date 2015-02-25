package com.carlosbecker.spotify;

import com.carlosbecker.SpotifyException;
import com.carlosbecker.spotify.common.Dotenv;
import com.carlosbecker.spotify.oauth.OAuth;
import com.carlosbecker.spotify.oauth.Token;
import java.io.IOException;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws IOException, SpotifyException {
        new Dotenv().load();
        final String scope = "playlist-modify-public playlist-read-private playlist-modify-private user-library-read user-library-modify";
        final String callback = "http://localhost:4567/auth/spotify/callback";
        final String clientId = System.getProperty("SPOTIFY_CLIENT_ID");
        final String clientSecret = System.getProperty("SPOTIFY_CLIENT_SECRET");
        final OAuth oauth = new OAuth(clientId, clientSecret, scope, callback);
        final Scanner reader = new Scanner(System.in);
        System.out.printf("Follow the address " + oauth.authorizationUri()
            + " and paste the redirect address here:");
        Token token = oauth.token(reader.next());
        reader.close();
        System.out.println(token.value());
    }
}

// code=AQBjhr21y2FjgtB5cCbQGcJLY_tC0R4RhiA9rdVpCYwilIe4Dr1NUwgrn56FTy_CGucNIa18WqG9bgUyAj5DTQjAug4R4qX4ooX3hN9kGlUg3I56xhl4OyrxLv5sHi9qAY1pzOduJTjlfZ-wPngq9UlFTHYqCZFblG2-4nYfuzLhBBDGAO_QyFjhS-sRadparolzh-OwQO3zYiEcXr9u2dnTYrHL5Zz6pESqn5XLqwAHaUnHrxgLedFyYc3onlnT3j_wi9vdiUI3le7luJeJap_OpROPe_R8a-GMhoIflSO4wXA0TBD061lb8DZsaNLase61HIwZ8oEXJPkDMaUpmxZzcKIvHEgC5ZTIU0Y-WIqN&state=tOr797qDBh5XYNbl
