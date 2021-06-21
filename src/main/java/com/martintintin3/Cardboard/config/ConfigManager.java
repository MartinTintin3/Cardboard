package com.martintintin3.Cardboard.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.martintintin3.Cardboard.Server;

public class ConfigManager {
    private static ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

    public static void init(Server server) {
        // TODO: Implement config
    }

    public static void writeNewConfig() {

    }
}
