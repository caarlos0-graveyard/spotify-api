package com.carlosbecker.spotify.common;

import com.carlosbecker.spotify.oauth.RandomString;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

public class RandomStringTest {
    @Test
    public void generatesRandomStringWithDefaultLenght() throws Exception {
        MatcherAssert.assertThat(
            new RandomString().toString().length(),
            Matchers.equalTo(16)
        );
    }
    
    @Test
    public void generatesRandomStringWithCustomLenght() throws Exception {
        MatcherAssert.assertThat(
            new RandomString().toString(5).length(),
            Matchers.equalTo(5)
        );
    }
    
    @Test
    public void generatesEmptyString() throws Exception {
        MatcherAssert.assertThat(
            new RandomString().toString(0).length(),
            Matchers.equalTo(0)
        );
    }
}
