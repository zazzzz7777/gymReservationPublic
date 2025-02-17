package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.ChangguanyuyueEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.ChangguanyuyueView;


/**
 * 场馆预约
 *
 * @author 
 * @email 
 * @date 2025-01-16 16:10:41
 */
public interface ChangguanyuyueService extends IService<ChangguanyuyueEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ChangguanyuyueView> selectListView(Wrapper<ChangguanyuyueEntity> wrapper);
   	
   	ChangguanyuyueView selectView(@Param("ew") Wrapper<ChangguanyuyueEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ChangguanyuyueEntity> wrapper);
   	
   
    List<Map<String, Object>> selectValue(Map<String, Object> params,Wrapper<ChangguanyuyueEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params,Wrapper<ChangguanyuyueEntity> wrapper);

    List<Map<String, Object>> selectGroup(Map<String, Object> params,Wrapper<ChangguanyuyueEntity> wrapper);



}

