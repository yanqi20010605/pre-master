package com.xd.pre.modules.sys.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.xd.pre.modules.sys.domain.SysClass;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class ClassDTO {

    private static final long serialVersionUID = 1L;

    private Integer classId;
    private String className;
    private Integer sort;
    private Integer parentId;

}
