package com.cl.dao;

import com.cl.entity.DiscusschangguanxinxiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.DiscusschangguanxinxiView;


/**
 * 场馆信息评论表
 * 
 * @author 
 * @email 
 * @date 2025-01-16 16:10:41
 */
public interface DiscusschangguanxinxiDao extends BaseMapper<DiscusschangguanxinxiEntity> {
	
	List<DiscusschangguanxinxiView> selectListView(@Param("ew") Wrapper<DiscusschangguanxinxiEntity> wrapper);

	List<DiscusschangguanxinxiView> selectListView(Pagination page,@Param("ew") Wrapper<DiscusschangguanxinxiEntity> wrapper);
	
	DiscusschangguanxinxiView selectView(@Param("ew") Wrapper<DiscusschangguanxinxiEntity> wrapper);


}
