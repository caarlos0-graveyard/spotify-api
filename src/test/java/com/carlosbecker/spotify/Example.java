package com.carlosbecker.spotify;

import com.carlosbecker.Spotify;
import com.carlosbecker.SpotifyException;
import com.carlosbecker.spotify.common.Dotenv;
import com.carlosbecker.spotify.oauth.OAuth;
import com.carlosbecker.spotify.oauth.Token;
import java.io.IOException;
import java.util.Scanner;

public class Example {
    public static void main(String[] args) throws IOException, SpotifyException {
        new Dotenv().load();
        final String scope = "";// "user-read-private playlist-modify-public playlist-read-private playlist-modify-private user-library-read user-library-modify";
        final String callback = "http://localhost:4567/auth/spotify/callback";
        final String clientId = System.getProperty("SPOTIFY_CLIENT_ID");
        final String clientSecret = System.getProperty("SPOTIFY_CLIENT_SECRET");
        final OAuth oauth = new OAuth(clientId, clientSecret, scope, callback);
        final Token token;
        try (final Scanner reader = new Scanner(System.in)) {
            System.out.printf("Follow the address:\n"
                + oauth.authorizationUri()
                + "\nand paste the redirect address here:");
            token = oauth.token(reader.next());
        }
        System.out.println("Token is: " + token.value());
        final Spotify spotify = new Spotify(token);
        final Profile me = spotify.me();
        System.out.println(me);
        System.out.println(me.data().toString());
    }
}
