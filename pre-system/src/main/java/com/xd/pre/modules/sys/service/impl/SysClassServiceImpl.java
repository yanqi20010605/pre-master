package com.xd.pre.modules.sys.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xd.pre.modules.sys.domain.SysClass;
import com.xd.pre.modules.sys.dto.ClassDTO;
import com.xd.pre.modules.sys.mapper.SysClassMapper;
import com.xd.pre.modules.sys.service.ISysClassService;
import com.xd.pre.modules.sys.util.PreUtil;
import com.xd.pre.modules.sys.vo.ClassTreeVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 班级管理 服务实现类
 * </p>
 */
@Service
public class SysClassServiceImpl extends ServiceImpl<SysClassMapper, SysClass> implements ISysClassService {

    @Override
    public List<SysClass> selectClassList() {
        List<SysClass> classes = baseMapper.selectList(Wrappers.<SysClass>lambdaQuery().select(SysClass::getClassId, SysClass::getClassName, SysClass::getParentId, SysClass::getSort, SysClass::getCreateTime));
        List<SysClass> sysClasses = classes.stream()
                .filter(sysClass -> sysClass.getParentId() == 0 || ObjectUtil.isNull(sysClass.getParentId()))
                .peek(sysClass -> sysClass.setLevel(0))
                .collect(Collectors.toList());
        PreUtil.findChildren2(sysClasses, classes);
        return sysClasses;
    }


    @Override
    public boolean updateClassById(ClassDTO entity) {
        SysClass sysClass = new SysClass();
        BeanUtils.copyProperties(entity, sysClass);
        sysClass.setUpdateTime(LocalDateTime.now());
        return this.updateById(sysClass);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean removeById(Serializable id) {
        // 部门层级删除
        List<Integer> idList = this.list(Wrappers.<SysClass>query().lambda().eq(SysClass::getParentId, id)).stream().map(SysClass::getClassId).collect(Collectors.toList());
        // 删除自己
        idList.add((Integer) id);
        return super.removeByIds(idList);
    }

    @Override
    public String selectClassNameByClassId(int classId) {
        return baseMapper.selectOne(Wrappers.<SysClass>query().lambda().select(SysClass::getClassName).eq(SysClass::getClassId, classId)).getClassName();
    }

    @Override
    public List<SysClass> selectClassListByclassName(String className) {
        return null;
    }

    @Override
    public List<Integer> selectClassIds(int classId) {
        SysClass department = this.getDepartment(classId);
        List<Integer> classIdList = new ArrayList<>();
        if (department != null) {
            classIdList.add(department.getClassId());
            addClassIdList(classIdList, department);
        }
        return classIdList;
    }

    @Override
    public List<ClassTreeVo> getClassTree() {
        List<SysClass> classes = baseMapper.selectList(Wrappers.<SysClass>query().select("class_id", "class_name", "parent_id", "sort", "create_time"));
        List<ClassTreeVo> collect = classes.stream().filter(sysClass -> sysClass.getParentId() == 0 || ObjectUtil.isNull(sysClass.getParentId()))
                .map(sysClass -> {
                    ClassTreeVo classTreeVo = new ClassTreeVo();
                    classTreeVo.setId(sysClass.getClassId());
                    classTreeVo.setLabel(sysClass.getClassName());
                    return classTreeVo;

                }).collect(Collectors.toList());

        PreUtil.findChildren3(collect,classes);
        return collect;
    }


    /**
     * 根据部门ID获取该部门及其下属部门树
     */
    private SysClass getDepartment(Integer classId) {
        List<SysClass> departments = baseMapper.selectList(Wrappers.<SysClass>query().select("class_id", "class_name", "parent_id", "sort", "create_time"));
        Map<Integer, SysClass> map = departments.stream().collect(
                Collectors.toMap(SysClass::getClassId, department -> department));

        for (SysClass class1 : map.values()) {
            SysClass parent = map.get(class1.getParentId());
            if (parent != null) {
                List<SysClass> children = parent.getChildren() == null ? new ArrayList<>() : parent.getChildren();
                children.add(class1);
                parent.setChildren(children);
            }
        }
        return map.get(classId);
    }
    private void addClassIdList(List<Integer> classIdList, SysClass department) {
        List<SysClass> children = department.getChildren();
        if (children != null) {
            for (SysClass d : children) {
                classIdList.add(d.getClassId());
                addClassIdList(classIdList, d);
            }
        }
    }


}
