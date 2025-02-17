package com.cl.dao;

import com.cl.entity.ChangguanxinxiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.ChangguanxinxiView;


/**
 * 场馆信息
 * 
 * @author 
 * @email 
 * @date 2025-01-16 16:10:41
 */
public interface ChangguanxinxiDao extends BaseMapper<ChangguanxinxiEntity> {
	
	List<ChangguanxinxiView> selectListView(@Param("ew") Wrapper<ChangguanxinxiEntity> wrapper);

	List<ChangguanxinxiView> selectListView(Pagination page,@Param("ew") Wrapper<ChangguanxinxiEntity> wrapper);
	
	ChangguanxinxiView selectView(@Param("ew") Wrapper<ChangguanxinxiEntity> wrapper);


}
