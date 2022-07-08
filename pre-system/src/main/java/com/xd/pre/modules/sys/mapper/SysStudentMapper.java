package com.xd.pre.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.pre.modules.data.datascope.DataScope02;
import com.xd.pre.modules.sys.domain.SysStudent;
import com.xd.pre.modules.sys.dto.StudentDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 学生表 Mapper 接口
 * </p>
 */
@Repository
public interface SysStudentMapper extends BaseMapper<SysStudent> {

    @Insert("insert into sys_student (student_name,class_id,sex,telephone,email) values (#{studentName},#{classId},#{sex},#{telephone},#{email})")
    @Options(useGeneratedKeys = true, keyProperty = "studentId", keyColumn = "student_id")
    boolean insertStudent(SysStudent sysStudent);

    /**
     * 分页查询学生信息
     *
     * @param page      分页
     * @param studentDTO   查询参数
     * @param dataScope
     * @return list
     */
    IPage<SysStudent> getStudentVosPage(Page page, @Param("query") StudentDTO studentDTO, DataScope02 dataScope);

}
