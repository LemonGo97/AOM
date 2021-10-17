package cn.lemongo97.aom.common;

/**
 * @author LemonGo97
 */

public enum Platform {
    /**
     * Windows 64位
     */
    WIN_X64("Windows 64位", "Windows AMD 64", "Windows Intel 64"),
    /**
     * Windows 32位
     */
    WIN_X86("Windows 32位", "Windows i386"),
    /**
     * Linux 64位
     */
    LINUX_X64("Linux 64位"),
    /**
     * Linux 32位
     */
    LINUX_X32("Linux 32位"),
    /**
     * Linux arm32
     */
    LINUX_AARCH32("Linux AArch32", "Linux arm", "Linux arm32", "Linux A32"),
    /**
     * Linux arm64
     */
    LINUX_AARCH64("Linux AArch64", "Linux arm64", "Linux A64"),
    /**
     * 源码
     */
    SOURCE("源码");

    private final String[] name;

    Platform(String... name){
        this.name = name;
    }

    public String[] getName(){
        return this.name;
    }
}
