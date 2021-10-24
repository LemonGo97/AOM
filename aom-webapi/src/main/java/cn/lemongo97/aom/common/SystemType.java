package cn.lemongo97.aom.common;

import cn.hutool.core.map.MapUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.*;

/**
 * @author LemonGo97
 */

public enum SystemType {
    /**
     * Windows
     */
    WINDOWS("Windows", Platform.WIN_X64, Platform.WIN_X86),
    /**
     * Linux
     */
    LINUX("Linux", Platform.LINUX_X32, Platform.LINUX_X64, Platform.LINUX_AARCH32, Platform.LINUX_AARCH64),
    /**
     * Centos
     */
    CENTOS("CentOS", Platform.LINUX_X32, Platform.LINUX_X64, Platform.LINUX_AARCH32, Platform.LINUX_AARCH64),
    /**
     * Ubuntu
     */
    UBUNTU("Ubuntu", Platform.LINUX_X32, Platform.LINUX_X64, Platform.LINUX_AARCH32, Platform.LINUX_AARCH64),
    /**
     * Fedora
     */
    FEDORA("Fedora", Platform.LINUX_X32, Platform.LINUX_X64, Platform.LINUX_AARCH32, Platform.LINUX_AARCH64),
    /**
     * Rhel
     */
    RHEL("Rhel", Platform.LINUX_X32, Platform.LINUX_X64, Platform.LINUX_AARCH32, Platform.LINUX_AARCH64),
    /**
     * Debian
     */
    DEBIAN("Debian", Platform.LINUX_X32, Platform.LINUX_X64, Platform.LINUX_AARCH32, Platform.LINUX_AARCH64);

    private final static List<Map<Object, Object>> SYSTEM_TYPE = new ArrayList<>();

    private final String name;
    private final Platform[] platform;

    SystemType(String name, Platform... platform) {
        this.name = name;
        this.platform = platform;
    }

    public String getName() {
        return name;
    }

    public Platform[] getPlatform() {
        return platform;
    }

    public static Collection<Map<Object, Object>> getAllSystemType() {
        if (SYSTEM_TYPE.isEmpty()) {
            for (SystemType type : SystemType.values()) {
                ArrayList<Map<Object, Object>> platforms = Lists.newArrayList();
                for (Platform platform : type.platform) {
                    platforms.add(MapUtil.builder().put("label", platform.getName()[0]).put("value", platform).put("alias", platform.getName()).build());
                }
                SYSTEM_TYPE.add(MapUtil.builder().put("label", type.name).put("value", type).put("platform", platforms).build());
            }
        }
        return SYSTEM_TYPE;
    }
}
