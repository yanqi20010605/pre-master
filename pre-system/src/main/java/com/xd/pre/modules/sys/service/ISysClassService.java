package com.xd.pre.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xd.pre.modules.sys.domain.SysClass;
import com.xd.pre.modules.sys.domain.SysDept;
import com.xd.pre.modules.sys.dto.ClassDTO;
import com.xd.pre.modules.sys.dto.DeptDTO;
import com.xd.pre.modules.sys.vo.ClassTreeVo;
import com.xd.pre.modules.sys.vo.DeptTreeVo;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 班级管理 服务类
 * </p>
 */
public interface ISysClassService extends IService<SysClass> {

    /**
     * 查询班级信息
     * @return
     */
    List<SysClass> selectClassList();

    /**
     * 更新班级
     * @param entity
     * @return
     */
    boolean updateClassById(ClassDTO entity);

    /**
     * 删除班级
     * @param id
     * @return
     */
    @Override
    boolean removeById(Serializable id);

    /**
     * 根据部门id查询班级名称
     * @param classId
     * @return
     */
    String selectClassNameByClassId(int classId);

    /**
     * 根据班级名称查询
     * @param className
     * @return
     */
    List<SysClass> selectClassListByclassName(String className);

    /**
     * 通过此部门id查询于此相关的班级ids
     * @param classId
     * @return
     */
    List<Integer> selectClassIds(int classId);

    /**
     * 获取班级树
     * @return
     */
    List<ClassTreeVo> getClassTree();


}
