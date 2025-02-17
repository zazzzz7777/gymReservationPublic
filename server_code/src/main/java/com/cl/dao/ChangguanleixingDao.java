package com.cl.dao;

import com.cl.entity.ChangguanleixingEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.ChangguanleixingView;


/**
 * 场馆类型
 * 
 * @author 
 * @email 
 * @date 2025-01-16 16:10:41
 */
public interface ChangguanleixingDao extends BaseMapper<ChangguanleixingEntity> {
	
	List<ChangguanleixingView> selectListView(@Param("ew") Wrapper<ChangguanleixingEntity> wrapper);

	List<ChangguanleixingView> selectListView(Pagination page,@Param("ew") Wrapper<ChangguanleixingEntity> wrapper);
	
	ChangguanleixingView selectView(@Param("ew") Wrapper<ChangguanleixingEntity> wrapper);


}
