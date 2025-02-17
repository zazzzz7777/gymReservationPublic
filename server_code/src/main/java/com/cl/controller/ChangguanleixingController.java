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

import com.cl.entity.ChangguanleixingEntity;
import com.cl.entity.view.ChangguanleixingView;

import com.cl.service.ChangguanleixingService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.MapUtils;
import com.cl.utils.CommonUtil;

/**
 * 场馆类型
 * 后端接口
 * @author 
 * @email 
 * @date 2025-01-16 16:10:41
 */
@RestController
@RequestMapping("/changguanleixing")
public class ChangguanleixingController {
    @Autowired
    private ChangguanleixingService changguanleixingService;







    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ChangguanleixingEntity changguanleixing,
                                            HttpServletRequest request){
                                    EntityWrapper<ChangguanleixingEntity> ew = new EntityWrapper<ChangguanleixingEntity>();
                                                        
        
        
        PageUtils page = changguanleixingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, changguanleixing), params), params));
        return R.ok().put("data", page);
    }
    
    
    
    
    
    
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ChangguanleixingEntity changguanleixing, 
		HttpServletRequest request){
        EntityWrapper<ChangguanleixingEntity> ew = new EntityWrapper<ChangguanleixingEntity>();

		PageUtils page = changguanleixingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, changguanleixing), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ChangguanleixingEntity changguanleixing){
       	EntityWrapper<ChangguanleixingEntity> ew = new EntityWrapper<ChangguanleixingEntity>();
      	ew.allEq(MPUtil.allEQMapPre( changguanleixing, "changguanleixing")); 
        return R.ok().put("data", changguanleixingService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ChangguanleixingEntity changguanleixing){
        EntityWrapper< ChangguanleixingEntity> ew = new EntityWrapper< ChangguanleixingEntity>();
 		ew.allEq(MPUtil.allEQMapPre( changguanleixing, "changguanleixing")); 
		ChangguanleixingView changguanleixingView =  changguanleixingService.selectView(ew);
		return R.ok("查询场馆类型成功").put("data", changguanleixingView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ChangguanleixingEntity changguanleixing = changguanleixingService.selectById(id);
		changguanleixing = changguanleixingService.selectView(new EntityWrapper<ChangguanleixingEntity>().eq("id", id));
        return R.ok().put("data", changguanleixing);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ChangguanleixingEntity changguanleixing = changguanleixingService.selectById(id);
		changguanleixing = changguanleixingService.selectView(new EntityWrapper<ChangguanleixingEntity>().eq("id", id));
        return R.ok().put("data", changguanleixing);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ChangguanleixingEntity changguanleixing, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(changguanleixing);
        changguanleixingService.insert(changguanleixing);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ChangguanleixingEntity changguanleixing, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(changguanleixing);
        changguanleixingService.insert(changguanleixing);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody ChangguanleixingEntity changguanleixing, HttpServletRequest request){
        //ValidatorUtils.validateEntity(changguanleixing);
        changguanleixingService.updateById(changguanleixing);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        changguanleixingService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	









}
