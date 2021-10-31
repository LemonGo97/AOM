package cn.lemongo97.aom.model.application;

import cn.org.atool.fluent.mybatis.annotation.FluentMybatis;
import cn.org.atool.fluent.mybatis.annotation.TableField;
import cn.org.atool.fluent.mybatis.annotation.TableId;
import cn.org.atool.fluent.mybatis.base.RichEntity;
import cn.org.atool.fluent.mybatis.metadata.DbType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author lemongo97
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@FluentMybatis(table = "t_application", dbType = DbType.MARIADB)
public class ApplicationPO extends RichEntity {

    @TableId(value = "uuid", auto = false, seqName = "select uuid() as uuid")
    private String uuid;

    @TableField(value = "name")
    private String name;

    @TableField(value = "change_log")
    private String changeLog;

    @TableField(value = "change_log_url")
    private String changeLogUrl;

}
