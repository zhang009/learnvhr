package com.zzti.vhr.controller.emp;

import com.zzti.vhr.model.RespPageBean;
import com.zzti.vhr.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName EmpBasicController
 * @Description TODO
 * @Author Administrator
 * @Date 2020/3/11 11:38
 **/
@RestController
@RequestMapping("/emp/basic")
public class EmpBasicController {

    @Autowired
    EmployeeService employeeService;
    @GetMapping("/")
    public RespPageBean getEmployeeByPage(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "10")  Integer size){
        return employeeService.getEmployeeByPage(page,size);
    }
}
