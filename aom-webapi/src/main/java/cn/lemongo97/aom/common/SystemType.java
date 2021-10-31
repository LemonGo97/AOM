package cn.lemongo97.aom.common;

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.annotation.EnumValue;
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
    WINDOWS(0, "Windows", Platform.WIN_X64, Platform.WIN_X86),
    /**
     * Linux
     */
    LINUX(1, "Linux", Platform.LINUX_X32, Platform.LINUX_X64, Platform.LINUX_AARCH32, Platform.LINUX_AARCH64),
    /**
     * Centos
     */
    CENTOS(2, "CentOS", Platform.LINUX_X32, Platform.LINUX_X64, Platform.LINUX_AARCH32, Platform.LINUX_AARCH64),
    /**
     * Ubuntu
     */
    UBUNTU(3, "Ubuntu", Platform.LINUX_X32, Platform.LINUX_X64, Platform.LINUX_AARCH32, Platform.LINUX_AARCH64),
    /**
     * Fedora
     */
    FEDORA(4, "Fedora", Platform.LINUX_X32, Platform.LINUX_X64, Platform.LINUX_AARCH32, Platform.LINUX_AARCH64),
    /**
     * Rhel
     */
    RHEL(5, "Rhel", Platform.LINUX_X32, Platform.LINUX_X64, Platform.LINUX_AARCH32, Platform.LINUX_AARCH64),
    /**
     * Debian
     */
    DEBIAN(6,"Debian", Platform.LINUX_X32, Platform.LINUX_X64, Platform.LINUX_AARCH32, Platform.LINUX_AARCH64);

    private final static List<Map<Object, Object>> SYSTEM_TYPE = new ArrayList<>();

    @EnumValue
    private final int id;
    private final String name;
    private final Platform[] platform;

    SystemType(int id, String name, Platform... platform) {
        this.id = id;
        this.name = name;
        this.platform = platform;
    }

    public int getId() {
        return id;
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
