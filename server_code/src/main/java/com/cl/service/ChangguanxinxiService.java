package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.ChangguanxinxiEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.ChangguanxinxiView;


/**
 * 场馆信息
 *
 * @author 
 * @email 
 * @date 2025-01-16 16:10:41
 */
public interface ChangguanxinxiService extends IService<ChangguanxinxiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ChangguanxinxiView> selectListView(Wrapper<ChangguanxinxiEntity> wrapper);
   	
   	ChangguanxinxiView selectView(@Param("ew") Wrapper<ChangguanxinxiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ChangguanxinxiEntity> wrapper);
   	
   
}

