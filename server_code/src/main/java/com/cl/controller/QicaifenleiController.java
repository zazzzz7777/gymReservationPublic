package com.cl.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import com.cl.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cl.annotation.IgnoreAuth;

import com.cl.entity.QicaifenleiEntity;
import com.cl.entity.view.QicaifenleiView;

import com.cl.service.QicaifenleiService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.MapUtils;
import com.cl.utils.CommonUtil;

/**
 * 器材分类
 * 后端接口
 * @author 
 * @email 
 * @date 2025-01-16 16:10:41
 */
@RestController
@RequestMapping("/qicaifenlei")
public class QicaifenleiController {
    @Autowired
    private QicaifenleiService qicaifenleiService;







    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,QicaifenleiEntity qicaifenlei,
                                            HttpServletRequest request){
                                    EntityWrapper<QicaifenleiEntity> ew = new EntityWrapper<QicaifenleiEntity>();
                                                        
        
        
        PageUtils page = qicaifenleiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, qicaifenlei), params), params));
        return R.ok().put("data", page);
    }
    
    
    
    
    
    
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,QicaifenleiEntity qicaifenlei, 
		HttpServletRequest request){
        EntityWrapper<QicaifenleiEntity> ew = new EntityWrapper<QicaifenleiEntity>();

		PageUtils page = qicaifenleiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, qicaifenlei), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( QicaifenleiEntity qicaifenlei){
       	EntityWrapper<QicaifenleiEntity> ew = new EntityWrapper<QicaifenleiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( qicaifenlei, "qicaifenlei")); 
        return R.ok().put("data", qicaifenleiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(QicaifenleiEntity qicaifenlei){
        EntityWrapper< QicaifenleiEntity> ew = new EntityWrapper< QicaifenleiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( qicaifenlei, "qicaifenlei")); 
		QicaifenleiView qicaifenleiView =  qicaifenleiService.selectView(ew);
		return R.ok("查询器材分类成功").put("data", qicaifenleiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        QicaifenleiEntity qicaifenlei = qicaifenleiService.selectById(id);
		qicaifenlei = qicaifenleiService.selectView(new EntityWrapper<QicaifenleiEntity>().eq("id", id));
        return R.ok().put("data", qicaifenlei);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        QicaifenleiEntity qicaifenlei = qicaifenleiService.selectById(id);
		qicaifenlei = qicaifenleiService.selectView(new EntityWrapper<QicaifenleiEntity>().eq("id", id));
        return R.ok().put("data", qicaifenlei);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody QicaifenleiEntity qicaifenlei, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(qicaifenlei);
        qicaifenleiService.insert(qicaifenlei);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody QicaifenleiEntity qicaifenlei, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(qicaifenlei);
        qicaifenleiService.insert(qicaifenlei);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody QicaifenleiEntity qicaifenlei, HttpServletRequest request){
        //ValidatorUtils.validateEntity(qicaifenlei);
        qicaifenleiService.updateById(qicaifenlei);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        qicaifenleiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	









}
