package com.cl.dao;

import com.cl.entity.DiscusssaishihuodongEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.DiscusssaishihuodongView;


/**
 * 赛事活动评论表
 * 
 * @author 
 * @email 
 * @date 2025-01-16 16:10:41
 */
public interface DiscusssaishihuodongDao extends BaseMapper<DiscusssaishihuodongEntity> {
	
	List<DiscusssaishihuodongView> selectListView(@Param("ew") Wrapper<DiscusssaishihuodongEntity> wrapper);

	List<DiscusssaishihuodongView> selectListView(Pagination page,@Param("ew") Wrapper<DiscusssaishihuodongEntity> wrapper);
	
	DiscusssaishihuodongView selectView(@Param("ew") Wrapper<DiscusssaishihuodongEntity> wrapper);


}
