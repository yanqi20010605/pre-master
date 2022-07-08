package com.xd.pre.modules.sys.controller;


import com.xd.pre.common.utils.R;
import com.xd.pre.log.annotation.SysOperaLog;
import com.xd.pre.modules.sys.domain.SysClass;
import com.xd.pre.modules.sys.domain.SysDept;
import com.xd.pre.modules.sys.dto.ClassDTO;
import com.xd.pre.modules.sys.dto.DeptDTO;
import com.xd.pre.modules.sys.service.ISysClassService;
import com.xd.pre.modules.sys.service.ISysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/class")
public class SysClassController {

    @Autowired
    private ISysClassService classService;

    /**
     * 保存班级信息
     *
     * @param sysClass
     * @return
     */
    @SysOperaLog(descrption = "保存班级信息")
    @PostMapping
    @PreAuthorize("hasAuthority('sys:class:add')")
    public R save(@RequestBody SysClass sysClass) {
        return R.ok(classService.save(sysClass));
    }

    /**
     * 获取班级信息
     *
     * @return
     */
    @GetMapping
    @PreAuthorize("hasAuthority('sys:class:view')")
    public R getClassList() {
        return R.ok(classService.selectClassList());
    }

    /**
     * 获取班级树
     * @return
     */
    @GetMapping("/tree")
    public R getClassTree() {
        return R.ok(classService.getClassTree());
    }


    /**
     * 更新班级信息
     *
     * @return
     */
    @SysOperaLog(descrption = "更新班级信息")
    @PutMapping
    @PreAuthorize("hasAuthority('sys:class:update')")
    public R update(@RequestBody ClassDTO classDto) {
        return R.ok(classService.updateClassById(classDto));
    }

    /**
     * 根据id删除班级信息
     *
     * @return
     */
    @SysOperaLog(descrption = "根据id删除班级信息")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('sys:class:delete')")
    public R delete(@PathVariable("id") Integer id) {
        return R.ok(classService.removeById(id));
    }

}

