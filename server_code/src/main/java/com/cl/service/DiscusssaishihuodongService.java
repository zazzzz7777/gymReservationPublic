package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.DiscusssaishihuodongEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.DiscusssaishihuodongView;


/**
 * 赛事活动评论表
 *
 * @author 
 * @email 
 * @date 2025-01-16 16:10:41
 */
public interface DiscusssaishihuodongService extends IService<DiscusssaishihuodongEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DiscusssaishihuodongView> selectListView(Wrapper<DiscusssaishihuodongEntity> wrapper);
   	
   	DiscusssaishihuodongView selectView(@Param("ew") Wrapper<DiscusssaishihuodongEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DiscusssaishihuodongEntity> wrapper);
   	
   
}

