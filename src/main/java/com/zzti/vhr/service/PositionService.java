package com.zzti.vhr.service;

import com.zzti.vhr.mapper.PositionMapper;
import com.zzti.vhr.model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName PositionService
 * @Description TODO
 * @Author Administrator
 * @Date 2020/3/7 16:42
 **/
@Service
public class PositionService {

    @Autowired
    PositionMapper positionMapper;

    public List<Position> getAllPositions() {
       return positionMapper.getAllPositions();
    }

    public Integer addPosition(Position position) {

        position.setEnabled(true);
        position.setCreateDate(new Date());
        return positionMapper.insertSelective(position);//这里使用的是自动生成的添加方法
    }

    public Integer updatePositions(Position position) {

        return positionMapper.updateByPrimaryKeySelective(position);
    }

    public Integer deletePositionById(Integer id) {
        return positionMapper.deleteByPrimaryKey(id);
    }

    public Integer deletePositionByIds(Integer[] ids) {
        return positionMapper.deletePositionByIds(ids);
    }
}
