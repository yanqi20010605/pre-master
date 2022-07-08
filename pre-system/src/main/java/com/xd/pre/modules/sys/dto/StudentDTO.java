package com.xd.pre.modules.sys.dto;

import com.xd.pre.common.sensitive.SensitiveInfo;
import com.xd.pre.common.sensitive.SensitiveType;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class StudentDTO implements Serializable {

    private Integer studentId;
    private String studentName;
    private Integer classId;
    private String sex;
    private String telephone;
    private String email;
    private String region;
    private String delFlag;
    private List<Integer> classList;
}
