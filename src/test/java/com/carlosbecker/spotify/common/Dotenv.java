package com.carlosbecker.spotify.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map.Entry;
import java.util.Properties;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class Dotenv {
    public void load() throws IOException {
        File env = new File(".env");
        if (!env.exists()) {
            log.warn("`.env` file not found.");
        } else {
            load(env);
        }
    }

    private void load(File env) throws IOException {
        Properties variables = new Properties();
        variables.load(new FileInputStream(env));
        for (Entry<Object, Object> variable : variables.entrySet()) {
            log.info("Loading " + variable.getKey());
            System.setProperty(
                variable.getKey().toString(),
                variable.getValue().toString()
                );
        }
    }
}
