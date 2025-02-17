package com.cl.dao;

import com.cl.entity.DiscussyundongqicaiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.DiscussyundongqicaiView;


/**
 * 运动器材评论表
 * 
 * @author 
 * @email 
 * @date 2025-01-16 16:10:41
 */
public interface DiscussyundongqicaiDao extends BaseMapper<DiscussyundongqicaiEntity> {
	
	List<DiscussyundongqicaiView> selectListView(@Param("ew") Wrapper<DiscussyundongqicaiEntity> wrapper);

	List<DiscussyundongqicaiView> selectListView(Pagination page,@Param("ew") Wrapper<DiscussyundongqicaiEntity> wrapper);
	
	DiscussyundongqicaiView selectView(@Param("ew") Wrapper<DiscussyundongqicaiEntity> wrapper);


}
