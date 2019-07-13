package com.cqupt.sirius.downloadpaper;

public class Paper {
    private String name;
    private String path;

    public Paper(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }
}
