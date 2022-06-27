package cn.lemongo97.aom.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum UserType {

    LOCAL("LOCAL","本地"),
    REMOTE("REMOTE","远程");

    @EnumValue
    @JsonValue
    private final String name;
    private final String value;

    UserType(String name,String value){
        this.name = name;
        this.value = value;
    }


    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
