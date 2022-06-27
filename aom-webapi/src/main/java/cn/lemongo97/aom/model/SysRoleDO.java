package cn.lemongo97.aom.model;

import java.io.Serializable;
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
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class SysRoleDO implements Serializable {

	private static final long serialVersionUID =  1578720469607677646L;

	private Long id;

	private String name;

	/**
	 * 角色权限字符串
	 */
	private String roleKey;

	/**
	 * 角色状态（0正常 1停用）
	 */
	private String status;

	private Long createBy;

	private java.sql.Timestamp createTime;

	private Long updateBy;

	private java.sql.Timestamp updateTime;

	/**
	 * 备注
	 */
	private String remark;

}
