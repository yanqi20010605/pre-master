package com.xd.pre.modules.sys.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.xd.pre.common.sensitive.SensitiveInfo;
import com.xd.pre.common.sensitive.SensitiveType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 学生表
 * </p>
 *
 * @author yanqi
 * @since 2022-07-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_student")
public class SysStudent extends Model<SysStudent> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "student_id", type = IdType.AUTO)
    private Integer studentId;

    /**
     * 用户名
     */
    @SensitiveInfo(SensitiveType.CHINESE_NAME)
    private String studentName;


    /**
     * 班级ID
     */
    private Integer classId;

    /**
     * 性别
     */
    private String sex;

    /**
     * 联系电话
     */
//    @SensitiveInfo(SensitiveType.MOBILE_PHONE)
    private String telephone;

    /**
     * 邮箱
     */
//    @SensitiveInfo(SensitiveType.EMAIL)
    private String email;

    /**
     * 地区
     */
    private String region;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 0-正常，1-删除
     */
    private String delFlag;

    /**
     * 非数据库字段
     * 班级名称
     */
    @TableField(exist = false)
    private String className;

}
