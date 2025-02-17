package com.cl.dao;

import com.cl.entity.ChangguanyuyueEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.ChangguanyuyueView;


/**
 * 场馆预约
 * 
 * @author 
 * @email 
 * @date 2025-01-16 16:10:41
 */
public interface ChangguanyuyueDao extends BaseMapper<ChangguanyuyueEntity> {
	
	List<ChangguanyuyueView> selectListView(@Param("ew") Wrapper<ChangguanyuyueEntity> wrapper);

	List<ChangguanyuyueView> selectListView(Pagination page,@Param("ew") Wrapper<ChangguanyuyueEntity> wrapper);
	
	ChangguanyuyueView selectView(@Param("ew") Wrapper<ChangguanyuyueEntity> wrapper);


    List<Map<String, Object>> selectValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<ChangguanyuyueEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<ChangguanyuyueEntity> wrapper);

    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<ChangguanyuyueEntity> wrapper);



}
