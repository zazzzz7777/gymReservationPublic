package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.QicaizujieEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.QicaizujieView;


/**
 * 器材租借
 *
 * @author 
 * @email 
 * @date 2025-01-16 16:10:41
 */
public interface QicaizujieService extends IService<QicaizujieEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<QicaizujieView> selectListView(Wrapper<QicaizujieEntity> wrapper);
   	
   	QicaizujieView selectView(@Param("ew") Wrapper<QicaizujieEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<QicaizujieEntity> wrapper);
   	
   
}

