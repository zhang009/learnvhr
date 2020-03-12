package com.zzti.vhr.controller.system.basic;

import com.zzti.vhr.model.Position;
import com.zzti.vhr.model.RespBean;
import com.zzti.vhr.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName PositionController
 * @Description 职位管理接口，注意这里路径/system/basic/**
 * @Author Administrator
 * @Date 2020/3/7 16:39
 **/
@RestController
@RequestMapping("/system/basic/pos")
public class PositionController {

    @Autowired
    PositionService positionService;

    @GetMapping("/")
    public List<Position> getAllPositions(){//查询所有职位
        return positionService.getAllPositions();
    }
    @PostMapping("/")
    public RespBean addPosition(@RequestBody Position position){
        if (positionService.addPosition(position)==1){
            return RespBean.ok("添加成功！");
        }else{
            return RespBean.error("添加失败！");
        }

    }

    @PutMapping("/")
    public RespBean updatePositions(@RequestBody Position position){
        if (positionService.updatePositions(position)==1){
            return RespBean.ok("更新成功！");
        }else{
            return RespBean.error("更新失败！");
        }
    }

    @DeleteMapping("/{id}")
    public RespBean deletePositionById(@PathVariable  Integer id){
        if(positionService.deletePositionById(id)==1){
            return RespBean.ok("删除成功！");
        }else{
            return RespBean.error("删除失败！");
        }
    }

    //批量删除
    @DeleteMapping("/")
    public RespBean deletePositionByIds(Integer[] ids){//这里用数组来接受id
        if(positionService.deletePositionByIds(ids)==ids.length){
            return RespBean.ok("删除成功！");
        }
        return RespBean.error("删除失败！");
    }

}
