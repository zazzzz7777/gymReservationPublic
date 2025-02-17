package com.cl.dao;

import com.cl.entity.DiscussforumEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.DiscussforumView;


/**
 * forum评论表
 * 
 * @author 
 * @email 
 * @date 2025-01-16 16:10:41
 */
public interface DiscussforumDao extends BaseMapper<DiscussforumEntity> {
	
	List<DiscussforumView> selectListView(@Param("ew") Wrapper<DiscussforumEntity> wrapper);

	List<DiscussforumView> selectListView(Pagination page,@Param("ew") Wrapper<DiscussforumEntity> wrapper);
	
	DiscussforumView selectView(@Param("ew") Wrapper<DiscussforumEntity> wrapper);


}
