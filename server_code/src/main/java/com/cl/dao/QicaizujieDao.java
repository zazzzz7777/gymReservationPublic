package com.cl.dao;

import com.cl.entity.QicaizujieEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.QicaizujieView;


/**
 * 器材租借
 * 
 * @author 
 * @email 
 * @date 2025-01-16 16:10:41
 */
public interface QicaizujieDao extends BaseMapper<QicaizujieEntity> {
	
	List<QicaizujieView> selectListView(@Param("ew") Wrapper<QicaizujieEntity> wrapper);

	List<QicaizujieView> selectListView(Pagination page,@Param("ew") Wrapper<QicaizujieEntity> wrapper);
	
	QicaizujieView selectView(@Param("ew") Wrapper<QicaizujieEntity> wrapper);


}
