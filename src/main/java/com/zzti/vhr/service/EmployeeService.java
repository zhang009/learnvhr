package com.zzti.vhr.service;

import com.zzti.vhr.mapper.EmployeeMapper;
import com.zzti.vhr.model.Employee;
import com.zzti.vhr.model.RespPageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName EmployeeService
 * @Description TODO
 * @Author Administrator
 * @Date 2020/3/11 11:45
 **/
@Service
public class EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;
    public RespPageBean getEmployeeByPage(Integer page, Integer size) {

        if(page!=null&& size!=null){
            page=(page-1)*size;
        }
        List<Employee> data=employeeMapper.getEmployeeByPage(page,size);
        Long total=employeeMapper.getTotal();//总记录数
        RespPageBean bean = new RespPageBean();
        bean.setData(data);
        bean.setTotal(total);
        return bean;
    }
}
