package cn.lemongo97.aom.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum UserStatus {
    ONLINE("ONLINE","在线"),
    OFFLINE("OFFLINE","离线");

    @EnumValue
    @JsonValue
    private final String name;
    private final String value;

    UserStatus(String name,String value){
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
