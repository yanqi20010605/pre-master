package com.xd.pre.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xd.pre.modules.data.datascope.DataScope02;
import com.xd.pre.modules.sys.domain.SysStudent;
import com.xd.pre.modules.sys.dto.StudentDTO;
import com.xd.pre.modules.sys.mapper.SysStudentMapper;
import com.xd.pre.modules.sys.service.ISysClassService;
import com.xd.pre.modules.sys.service.ISysStudentService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SysStudentServiceImpl extends ServiceImpl<SysStudentMapper,SysStudent> implements ISysStudentService {

//    @Autowired
//    private ISysStudentService studentService;
    @Autowired
    private ISysClassService classService;

    @Override
    public IPage<SysStudent> getStudentsPage(Page page, StudentDTO studentDTO) {
        if (studentDTO.getClassId() != null){
            studentDTO.setClassList(classService.selectClassIds(studentDTO.getClassId()));
        }
        return baseMapper.getStudentVosPage(page, studentDTO, new DataScope02());
//        ObjectUtils.anyNotNull(studentDTO) &&
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insertStudent(StudentDTO studentDto) {
        SysStudent sysStudent = new SysStudent();
        BeanUtils.copyProperties(studentDto, sysStudent);
        return baseMapper.insertStudent(sysStudent);

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateStudent(StudentDTO studentDto) {
        SysStudent sysStudent = new SysStudent();
        BeanUtils.copyProperties(studentDto, sysStudent);
        int i = 0;
        i = baseMapper.updateById(sysStudent);
        if(i != 0){
            return true;
        }
        return false;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean removeStudent(Integer studentId) {
        int i = 0;
        i = baseMapper.deleteById(studentId);
        if(i != 0){
            return true;
        }
        return false;
    }

//    @Override
//    public SysStudent findByStudentInfoName(String studentName) {
//
//        SysStudent sysStudent = baseMapper.selectOne(Wrappers.<SysStudent>lambdaQuery()
//                .select(SysStudent::getStudentId, SysStudent::getStudentName, SysStudent::getClassId, SysStudent::getSex, SysStudent::getTelephone, SysStudent::getEmail )
//                .eq(SysStudent::getStudentName, studentName));
//        // 获取班级
//        sysStudent.setClassName(classService.selectClassNameByClassId(sysStudent.getClassId()));
//
//        return sysStudent;
//    }

}
