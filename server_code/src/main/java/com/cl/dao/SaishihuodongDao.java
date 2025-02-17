package com.cl.dao;

import com.cl.entity.SaishihuodongEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.SaishihuodongView;


/**
 * 赛事活动
 * 
 * @author 
 * @email 
 * @date 2025-01-16 16:10:41
 */
public interface SaishihuodongDao extends BaseMapper<SaishihuodongEntity> {
	
	List<SaishihuodongView> selectListView(@Param("ew") Wrapper<SaishihuodongEntity> wrapper);

	List<SaishihuodongView> selectListView(Pagination page,@Param("ew") Wrapper<SaishihuodongEntity> wrapper);
	
	SaishihuodongView selectView(@Param("ew") Wrapper<SaishihuodongEntity> wrapper);


}
