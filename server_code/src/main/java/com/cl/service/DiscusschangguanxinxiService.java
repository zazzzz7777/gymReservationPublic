package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.DiscusschangguanxinxiEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.DiscusschangguanxinxiView;


/**
 * 场馆信息评论表
 *
 * @author 
 * @email 
 * @date 2025-01-16 16:10:41
 */
public interface DiscusschangguanxinxiService extends IService<DiscusschangguanxinxiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DiscusschangguanxinxiView> selectListView(Wrapper<DiscusschangguanxinxiEntity> wrapper);
   	
   	DiscusschangguanxinxiView selectView(@Param("ew") Wrapper<DiscusschangguanxinxiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DiscusschangguanxinxiEntity> wrapper);
   	
   
}

