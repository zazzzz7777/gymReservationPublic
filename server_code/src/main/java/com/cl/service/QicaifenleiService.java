package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.QicaifenleiEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.QicaifenleiView;


/**
 * 器材分类
 *
 * @author 
 * @email 
 * @date 2025-01-16 16:10:41
 */
public interface QicaifenleiService extends IService<QicaifenleiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<QicaifenleiView> selectListView(Wrapper<QicaifenleiEntity> wrapper);
   	
   	QicaifenleiView selectView(@Param("ew") Wrapper<QicaifenleiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<QicaifenleiEntity> wrapper);
   	
   
}

