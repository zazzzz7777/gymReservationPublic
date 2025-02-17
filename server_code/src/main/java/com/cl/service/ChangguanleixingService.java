package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.ChangguanleixingEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.ChangguanleixingView;


/**
 * 场馆类型
 *
 * @author 
 * @email 
 * @date 2025-01-16 16:10:41
 */
public interface ChangguanleixingService extends IService<ChangguanleixingEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ChangguanleixingView> selectListView(Wrapper<ChangguanleixingEntity> wrapper);
   	
   	ChangguanleixingView selectView(@Param("ew") Wrapper<ChangguanleixingEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ChangguanleixingEntity> wrapper);
   	
   
}

