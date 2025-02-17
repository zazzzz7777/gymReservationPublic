package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.SaishihuodongEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.SaishihuodongView;


/**
 * 赛事活动
 *
 * @author 
 * @email 
 * @date 2025-01-16 16:10:41
 */
public interface SaishihuodongService extends IService<SaishihuodongEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<SaishihuodongView> selectListView(Wrapper<SaishihuodongEntity> wrapper);
   	
   	SaishihuodongView selectView(@Param("ew") Wrapper<SaishihuodongEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<SaishihuodongEntity> wrapper);
   	
   
}

