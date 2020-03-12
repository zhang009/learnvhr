package com.zzti.vhr.controller.system.basic;

import com.zzti.vhr.model.JobLevel;
import com.zzti.vhr.model.RespBean;
import com.zzti.vhr.service.JobLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName JobLevelController
 * @Description TODO
 * @Author Administrator
 * @Date 2020/3/8 15:49
 **/
@RestController
@RequestMapping("/system/basic/joblevel")
public class JobLevelController {
    @Autowired
    JobLevelService jobLevelService;
    @GetMapping("/")
    public List<JobLevel> getAllJobLevels(){
        return jobLevelService.getAllJobLevels();
    }
    @PostMapping("/")
    public RespBean addJobLevels(@RequestBody JobLevel jobLevel){
        if(jobLevelService.addJobLevels(jobLevel)==1){
            return RespBean.ok("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    @PutMapping("/")
    public RespBean updateJobLevel(@RequestBody JobLevel jobLevel){
        if(jobLevelService.updateJobLevel(jobLevel)==1){
            return RespBean.ok("修改成功！");
        }
        return RespBean.error("修改失败！");
    }
    @DeleteMapping("/{id}")
    public RespBean deleteJobLevelById(@PathVariable Integer id) {
        if (jobLevelService.deleteJobLevelById(id) == 1) {
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }
    @DeleteMapping("/")
    public RespBean deleteJobLevelsByIds(Integer [] ids){
        if(jobLevelService.deleteJobLevelsByIds(ids)==ids.length){
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }
}
