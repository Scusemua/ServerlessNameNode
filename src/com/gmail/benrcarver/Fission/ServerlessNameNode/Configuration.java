package com.gmail.benrcarver.Fission.ServerlessNameNode;

public class Configuration {
    public String get(String name) {
        return name;
    }

    public String get(String name, String defaultValue) {
        return defaultValue;
    }

    public void set(String name, String value) {

    }

    public int getInt(String name, int defaultValue) {
        return defaultValue;
    }
}
