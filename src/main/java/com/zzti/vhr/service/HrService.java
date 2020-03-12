package com.zzti.vhr.service;


import com.zzti.vhr.mapper.HrMapper;
import com.zzti.vhr.mapper.HrRoleMapper;
import com.zzti.vhr.model.Hr;
import com.zzti.vhr.utils.HrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author Administrator
 * @Date 2020/2/29 9:23
 **/
@Service
public class HrService implements UserDetailsService {
   @Autowired
    HrMapper hrMapper;
   @Autowired
    HrRoleMapper hrRoleMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Hr hr=hrMapper.loadUserByUsername(username);
        //这里传hr有可能为null，因为username有可能不对
        if(hr==null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        hr.setRoles(hrMapper.getHrRolesById(hr.getId()));
        return hr;
    }

    public List<Hr> getAllHrs(String keywords) {
        return hrMapper.getAllHrs(HrUtils.getCurrentHr().getId(),keywords);
    }

    public Integer updateHr(Hr hr) {
        return hrMapper.updateByPrimaryKeySelective(hr);
    }

    @Transactional
    public boolean updateHrRole(Integer hrid, Integer[] rids) {
        //做两个操作，一个删除，一个添加

      hrRoleMapper.deleteByHrid(hrid);
      return hrRoleMapper.addRole(hrid,rids)==rids.length;
    }

    public Integer deleteHrById(Integer id) {
        return hrMapper.deleteByPrimaryKey(id);
    }
}
