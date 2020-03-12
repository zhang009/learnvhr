package com.zzti.vhr.service;

import com.zzti.vhr.mapper.PoliticsstatusMapper;
import com.zzti.vhr.model.Politicsstatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName PoliticsstatusService
 * @Description TODO
 * @Author Administrator
 * @Date 2020/3/12 11:53
 **/
@Service
public class PoliticsstatusService {

    @Autowired
    PoliticsstatusMapper politicsstatusMapper;
    public List<Politicsstatus> getAllPoliticsstatus() {
        return politicsstatusMapper.getAllPoliticsstatus();
    }
}
