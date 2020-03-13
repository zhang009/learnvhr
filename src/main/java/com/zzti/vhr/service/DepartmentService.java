package com.zzti.vhr.service;

import com.zzti.vhr.mapper.DepartmentMapper;
import com.zzti.vhr.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName DepartmentService
 * @Description TODO
 * @Author Administrator
 * @Date 2020/3/10 11:12
 **/
@Service
public class DepartmentService {
    @Autowired
    DepartmentMapper departmentMapper;

    public List<Department> getAllDepartments() {

        return departmentMapper.getAllDepartmentsByParentId(-1);//递归查询
    }

    public void addDep(Department dep) {
        dep.setEnabled(true);
        departmentMapper.addDep(dep);
    }

    public void deleteDepById(Department dep) {
         departmentMapper.deleteDepById(dep);
    }

    public List<Department> getAllDepartmentsWithOutChildren() {
        return departmentMapper.getAllDepartmentsWithOutChildren();
    }
}
