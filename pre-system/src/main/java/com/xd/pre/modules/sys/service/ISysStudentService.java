package com.xd.pre.modules.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xd.pre.modules.sys.domain.SysStudent;
import com.xd.pre.modules.sys.dto.StudentDTO;

/**
 * <p>
 * 学生表 服务类
 * </p>
 */
public interface ISysStudentService extends IService<SysStudent> {

    /**
     * 分页查询学生信息
     *
     * @param page    分页对象
     * @param studentDTO 参数列表
     * @return
     */
    IPage<SysStudent> getStudentsPage(Page page, StudentDTO studentDTO);

    /**
     * 保存学生以及班级等信息
     * @param studentDto
     * @return
     */
    boolean insertStudent(StudentDTO studentDto);

    /**
     * 更新用户以及角色部门等信息
     * @param studentDto
     * @return
     */
    boolean updateStudent(StudentDTO studentDto);

    /**
     * 删除学生信息
     * @param studentId
     * @return
     */
    boolean removeStudent(Integer studentId);

    /**
     * 重置密码
     * @param studentId
     * @return
     */
//    boolean restPass(Integer studentId);

    /**
     * 通过用户名查找用户个人信息
     *
     * @param studentName 用户名
     * @return 用户信息
     */
//    SysStudent findByStudentInfoName(String studentName);

}
