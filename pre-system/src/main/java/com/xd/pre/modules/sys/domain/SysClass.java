package com.xd.pre.modules.sys.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 班级管理
 * </p>
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_class")
public class SysClass extends Model<SysClass> {

    private static final long serialVersionUID = 1L;

    /**
     * 部门主键ID
     */
    @TableId(value = "class_id", type = IdType.AUTO)
    private Integer classId;

    /**
     * 部门名称
     */
    @TableId(value = "class_name")
    private String className;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 上级部门
     */
    private Integer parentId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 是否删除  -1：已删除  0：正常
     */
    private String delFlag;

    /**
     * 非数据库字段
     * 上级部门
     */
    @TableField(exist = false)
    private String parentName;
    /**
     * 非数据库字段
     * 等级
     */
    @TableField(exist = false)
    private Integer level;

    /**
     * 非数据库字段
     * 子部门
     */
    @TableField(exist = false)
    private List<SysClass> children;


}
