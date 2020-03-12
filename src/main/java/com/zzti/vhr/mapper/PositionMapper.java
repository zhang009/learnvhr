package com.zzti.vhr.mapper;

import com.zzti.vhr.model.Position;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PositionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Position record);

    int insertSelective(Position record);

    Position selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Position record);

    int updateByPrimaryKey(Position record);

    //
    List<Position> getAllPositions();

    Integer deletePositionByIds(@Param("ids") Integer[] ids);//批量删除


}