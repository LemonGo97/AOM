package cn.lemongo97.aom.model;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;

/**
 * @Description  
 * @Author  lipu
 * @Date 2022-05-19 
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysMenuDO implements Serializable {

	private static final long serialVersionUID =  4506295437119977366L;

	private Long id;

	/**
	 * 菜单名
	 */
	private String menuName;

	/**
	 * 路由地址
	 */
	private String path;

	/**
	 * 菜单状态（0显示 1隐藏）
	 */
	private Integer visible;

	/**
	 * 权限标识
	 */
	private String permission;

	private Long createBy;

	private Date createTime;

	private Long updateBy;

	private Date updateTime;

	private Long order;

	private Long parent;
}
