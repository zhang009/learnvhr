package com.zzti.vhr.service;

import com.zzti.vhr.mapper.EmployeeMapper;
import com.zzti.vhr.model.Employee;
import com.zzti.vhr.model.RespPageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    SimpleDateFormat yearFormat=new SimpleDateFormat("yyyy");
    SimpleDateFormat monthFormat=new SimpleDateFormat("MM");
    DecimalFormat decimalFormat=new DecimalFormat("##.00");//年限保留两位小数

    public RespPageBean getEmployeeByPage(Integer page, Integer size, String keyword) {

        if(page!=null&& size!=null){
            page=(page-1)*size;
        }
        List<Employee> data=employeeMapper.getEmployeeByPage(page,size,keyword);
        Long total=employeeMapper.getTotal(keyword);//总记录数
        RespPageBean bean = new RespPageBean();
        bean.setData(data);
        bean.setTotal(total);
        return bean;
    }

    public Integer addEmp(Employee employee) {
        //计算合同年限
        Date beginContract = employee.getBeginContract();
        Date endContract = employee.getEndContract();
        //算出月份
        double month=(Double.parseDouble(yearFormat.format(endContract))-Double.parseDouble(yearFormat.format(beginContract)))*12+
                (Double.parseDouble(monthFormat.format(endContract))-Double.parseDouble(monthFormat.format(endContract)));//string转double
        //算出年份
        employee.setContractTerm(Double.parseDouble(decimalFormat.format(month/12)));
        return employeeMapper.insertSelective(employee);
    }

    public Integer maxWorkID() {
        return employeeMapper.maxWorkID();
    }

    public Integer deleteEmpByEid(Integer id) {
        return employeeMapper.deleteByPrimaryKey(id);
    }

    public Integer updateEmp(Employee employee) {
        return employeeMapper.updateByPrimaryKeySelective(employee);
    }


    public Integer addEmps(List<Employee> list) {
       return employeeMapper.addEmps(list);
    }
}
