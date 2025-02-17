package com.cl.dao;

import com.cl.entity.QicaifenleiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.QicaifenleiView;


/**
 * 器材分类
 * 
 * @author 
 * @email 
 * @date 2025-01-16 16:10:41
 */
public interface QicaifenleiDao extends BaseMapper<QicaifenleiEntity> {
	
	List<QicaifenleiView> selectListView(@Param("ew") Wrapper<QicaifenleiEntity> wrapper);

	List<QicaifenleiView> selectListView(Pagination page,@Param("ew") Wrapper<QicaifenleiEntity> wrapper);
	
	QicaifenleiView selectView(@Param("ew") Wrapper<QicaifenleiEntity> wrapper);


}
