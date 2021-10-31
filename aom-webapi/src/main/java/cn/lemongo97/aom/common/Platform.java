package cn.lemongo97.aom.common;

import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * @author LemonGo97
 */

public enum Platform {
    /**
     * Windows 64位
     */
    WIN_X64(0, "Windows 64位", "Windows AMD 64", "Windows Intel 64"),
    /**
     * Windows 32位
     */
    WIN_X86(1, "Windows 32位", "Windows i386"),
    /**
     * Linux 64位
     */
    LINUX_X64(2, "Linux 64位"),
    /**
     * Linux 32位
     */
    LINUX_X32(3, "Linux 32位"),
    /**
     * Linux arm32
     */
    LINUX_AARCH32(4, "Linux AArch32", "Linux arm", "Linux arm32", "Linux A32"),
    /**
     * Linux arm64
     */
    LINUX_AARCH64(5, "Linux AArch64", "Linux arm64", "Linux A64"),
    /**
     * 源码
     */
    SOURCE(6, "源码");

    @EnumValue
    private final int id;
    private final String[] name;

    Platform(int id, String... name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String[] getName(){
        return this.name;
    }
}
