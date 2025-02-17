package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.YundongqicaiEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.YundongqicaiView;


/**
 * 运动器材
 *
 * @author 
 * @email 
 * @date 2025-01-16 16:10:41
 */
public interface YundongqicaiService extends IService<YundongqicaiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<YundongqicaiView> selectListView(Wrapper<YundongqicaiEntity> wrapper);
   	
   	YundongqicaiView selectView(@Param("ew") Wrapper<YundongqicaiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<YundongqicaiEntity> wrapper);
   	
   
    List<Map<String, Object>> selectValue(Map<String, Object> params,Wrapper<YundongqicaiEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params,Wrapper<YundongqicaiEntity> wrapper);

    List<Map<String, Object>> selectGroup(Map<String, Object> params,Wrapper<YundongqicaiEntity> wrapper);



}

