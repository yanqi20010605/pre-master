package com.xd.pre.modules.sys.controller;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.pre.common.constant.PreConstant;
import com.xd.pre.common.exception.PreBaseException;
import com.xd.pre.common.utils.R;
import com.xd.pre.log.annotation.SysOperaLog;
import com.xd.pre.modules.sys.domain.SysStudent;
import com.xd.pre.modules.sys.dto.StudentDTO;
import com.xd.pre.modules.sys.service.ISysStudentService;
import com.xd.pre.modules.sys.util.EmailUtil;
import com.xd.pre.modules.sys.util.PreUtil;
import com.xd.pre.security.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 学生表 前端控制器
 * </p>
 *
 * @author yanqi
 * @since 2022-07-06
 */
@RestController
@RequestMapping("/student")
public class SysStudentController {

    @Autowired
    private ISysStudentService studentService;

    @Autowired
    private EmailUtil emailUtil;

    /**
     * 添加学生包括班级
     *
     * @param studentDto
     * @return
     */
    @SysOperaLog(descrption = "保存学生包括班级")
    @PostMapping
    @PreAuthorize("hasAuthority('sys:student:add')")
    public R insert(@RequestBody StudentDTO studentDto) {
        return R.ok(studentService.insertStudent(studentDto));
    }


    /**
     * 获取用户列表集合
     *
     * @param page
     * @param studentDTO
     * @return
     */
    @SysOperaLog(descrption = "查询学生集合")
    @GetMapping
    @PreAuthorize("hasAuthority('sys:student:view')")
    public R getList( Page page, StudentDTO studentDTO) {
//        System.out.println(studentDTO);
        return R.ok(studentService.getStudentsPage(page, studentDTO));
    }

    /**
     * 更新学生包括班级
     *
     * @param studentDto
     * @return
     */
    @SysOperaLog(descrption = "更新用户包括班级")
    @PutMapping
    @PreAuthorize("hasAuthority('sys:student:update')")
    public R update(@RequestBody StudentDTO studentDto) {
        return R.ok(studentService.updateStudent(studentDto));
    }

    /**
     * 删除学生包括班级
     *
     * @param studentId
     * @return
     */
    @SysOperaLog(descrption = "根据用户id删除用户包括班级")
    @DeleteMapping("/{studentId}")
    @PreAuthorize("hasAuthority('sys:student:delete')")
    public R delete(@PathVariable("studentId") Integer studentId) {
        return R.ok(studentService.removeStudent(studentId));
    }

    /**
     * 获取个人信息
     *
     * @return
     */
//    @SysOperaLog(descrption = "获取个人信息")
//    @GetMapping("/info/{studentName}")
//    public R getStudentInfo(String studentName) {
//        return R.ok(studentService.findByStudentInfoName(studentName));
//    }

}

