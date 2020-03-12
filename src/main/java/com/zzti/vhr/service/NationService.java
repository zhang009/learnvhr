package com.zzti.vhr.service;

import com.zzti.vhr.mapper.NationMapper;
import com.zzti.vhr.model.Nation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName NationService
 * @Description TODO
 * @Author Administrator
 * @Date 2020/3/12 11:51
 **/
@Service
public class NationService {

    @Autowired
    NationMapper nationMapper;
    public List<Nation> getAllNations() {
        return nationMapper.getAllNations();
    }
}
