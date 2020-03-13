package com.zzti.vhr.controller.emp;

import com.zzti.vhr.model.*;
import com.zzti.vhr.service.*;
import com.zzti.vhr.utils.POIUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @ClassName EmpBasicController
 * @Description TODO
 * @Author Administrator
 * @Date 2020/3/11 11:38
 **/
@RestController
@RequestMapping("/employee/basic")
public class EmpBasicController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    NationService nationService;
    @Autowired
    PoliticsstatusService politicsstatusService;
    @Autowired
    JobLevelService jobLevelService;
    @Autowired
    PositionService positionService;
    @Autowired
    DepartmentService departmentService;

    @GetMapping("/")
    public RespPageBean getEmployeeByPage(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "10")
            Integer size,String keyword){
        return employeeService.getEmployeeByPage(page,size,keyword);
    }

    @PostMapping("/")
    public RespBean addEmp(@RequestBody Employee employee){
        if(employeeService.addEmp(employee)==1){
            return RespBean.ok("添加成功！");
        }
        return RespBean.ok("添加成功！");
    }
    @DeleteMapping("/{id}")
    public RespBean deleteEmpByEid(@PathVariable Integer id){
        if(employeeService.deleteEmpByEid(id)==1){
            return RespBean.ok("删除成功！");
        }
        return RespBean.error("删除成功！");
    }

    @GetMapping("/nation")
    public List<Nation> getAllNations(){
       return nationService.getAllNations();
    }
    @GetMapping("/politicsstatus")
    public List<Politicsstatus> getAllPoliticsstatus(){
        return politicsstatusService.getAllPoliticsstatus();
    }

    @GetMapping("/joblevel")
    public List<JobLevel> getAllJobLevels(){
        return jobLevelService.getAllJobLevels();
    }
    @GetMapping("/positions")
    public List<Position> getAllPositions(){
        return positionService.getAllPositions();
    }

    @GetMapping("/maxWorkID")
    public RespBean maxWorkID(){
        //return   RespBean.ok("",String.format("%08d",employeeService.maxWorkID()+1));
        //return String.format("%08d",employeeService.maxWorkID()+1);//总长度为8，不足的补零
        RespBean respBean=RespBean.build().setStatus(200)
                .setObj(String.format("%08d",employeeService.maxWorkID()+1));
        return respBean;
    }
    @GetMapping("/deps")
    public List<Department> getAllDepartments(){
        return departmentService.getAllDepartments();
    }

    @PutMapping("/")
    public RespBean updateEmp(@RequestBody Employee employee){
        if(employeeService.updateEmp(employee)==1){
            return RespBean.ok("更新成功！");
        }
        return RespBean.error("更新失败！");
    }

    //下载文件返回的是ResponseEntity
    @GetMapping("/export")
    public ResponseEntity<byte[]> exportData(){
        //首先到数据库查询所有的员工数据，然后把数据生成Excel
        List<Employee> list= (List<Employee>) employeeService.getEmployeeByPage(null,null,null).getData();

        return POIUtils.employee2Excel(list);

    }
    @PostMapping("/import")
    public RespBean importData(MultipartFile file) throws IOException {
        //file当成excel文件来解析
       // file.transferTo(new File("E:\\javatest.xls"));
        List<Employee> list=POIUtils.excel2Employee(file,nationService.getAllNations(),politicsstatusService.getAllPoliticsstatus(),
                departmentService.getAllDepartmentsWithOutChildren(),positionService.getAllPositions(),jobLevelService.getAllJobLevels());
      /*  for (Employee employee : list) {
            System.out.println(employee.toString());
        }*/
      //将解析的数据插入到数据库
        if(employeeService.addEmps(list)==list.size()) {
            return RespBean.ok("上传成功！");
        }
       return RespBean.error("上传失败");
    }

}
