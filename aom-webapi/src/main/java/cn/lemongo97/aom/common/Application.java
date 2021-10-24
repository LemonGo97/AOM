package cn.lemongo97.aom.common;

public enum Application {
    REDIS("Redis");

    private final String name;

    Application(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
