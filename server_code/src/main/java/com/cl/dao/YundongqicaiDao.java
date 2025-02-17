package com.cl.dao;

import com.cl.entity.YundongqicaiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.YundongqicaiView;


/**
 * 运动器材
 * 
 * @author 
 * @email 
 * @date 2025-01-16 16:10:41
 */
public interface YundongqicaiDao extends BaseMapper<YundongqicaiEntity> {
	
	List<YundongqicaiView> selectListView(@Param("ew") Wrapper<YundongqicaiEntity> wrapper);

	List<YundongqicaiView> selectListView(Pagination page,@Param("ew") Wrapper<YundongqicaiEntity> wrapper);
	
	YundongqicaiView selectView(@Param("ew") Wrapper<YundongqicaiEntity> wrapper);


    List<Map<String, Object>> selectValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<YundongqicaiEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<YundongqicaiEntity> wrapper);

    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<YundongqicaiEntity> wrapper);



}
