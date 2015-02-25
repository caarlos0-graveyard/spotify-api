package com.carlosbecker;

import com.jcabi.aspects.Immutable;
import com.jcabi.http.Request;
import javax.validation.constraints.NotNull;

@Immutable
public interface Spotify {
    @NotNull
    Request entry();
}
