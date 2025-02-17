package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.DiscussyundongqicaiEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.DiscussyundongqicaiView;


/**
 * 运动器材评论表
 *
 * @author 
 * @email 
 * @date 2025-01-16 16:10:41
 */
public interface DiscussyundongqicaiService extends IService<DiscussyundongqicaiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DiscussyundongqicaiView> selectListView(Wrapper<DiscussyundongqicaiEntity> wrapper);
   	
   	DiscussyundongqicaiView selectView(@Param("ew") Wrapper<DiscussyundongqicaiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DiscussyundongqicaiEntity> wrapper);
   	
   
}

