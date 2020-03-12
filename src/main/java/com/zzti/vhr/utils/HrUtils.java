package com.zzti.vhr.utils;

import com.zzti.vhr.model.Hr;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @ClassName HrUtils
 * @Description TODO
 * @Author Administrator
 * @Date 2020/3/10 19:22
 **/
public class HrUtils {
    public static Hr getCurrentHr(){
        return ((Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
