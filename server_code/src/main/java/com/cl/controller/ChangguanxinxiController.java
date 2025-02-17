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

import com.cl.entity.ChangguanxinxiEntity;
import com.cl.entity.view.ChangguanxinxiView;

import com.cl.service.ChangguanxinxiService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.MapUtils;
import com.cl.utils.CommonUtil;
import com.cl.service.StoreupService;
import com.cl.entity.StoreupEntity;

/**
 * 场馆信息
 * 后端接口
 * @author 
 * @email 
 * @date 2025-01-16 16:10:41
 */
@RestController
@RequestMapping("/changguanxinxi")
public class ChangguanxinxiController {
    @Autowired
    private ChangguanxinxiService changguanxinxiService;

    @Autowired
    private StoreupService storeupService;






    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ChangguanxinxiEntity changguanxinxi,
                                                                                                                                                                            HttpServletRequest request){
                                    EntityWrapper<ChangguanxinxiEntity> ew = new EntityWrapper<ChangguanxinxiEntity>();
                                                                                                                                                                                                                                                                        
        
        
        PageUtils page = changguanxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, changguanxinxi), params), params));
        return R.ok().put("data", page);
    }
    
    
    
    
    
    
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ChangguanxinxiEntity changguanxinxi, 
		HttpServletRequest request){
        EntityWrapper<ChangguanxinxiEntity> ew = new EntityWrapper<ChangguanxinxiEntity>();

		PageUtils page = changguanxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, changguanxinxi), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ChangguanxinxiEntity changguanxinxi){
       	EntityWrapper<ChangguanxinxiEntity> ew = new EntityWrapper<ChangguanxinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( changguanxinxi, "changguanxinxi")); 
        return R.ok().put("data", changguanxinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ChangguanxinxiEntity changguanxinxi){
        EntityWrapper< ChangguanxinxiEntity> ew = new EntityWrapper< ChangguanxinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( changguanxinxi, "changguanxinxi")); 
		ChangguanxinxiView changguanxinxiView =  changguanxinxiService.selectView(ew);
		return R.ok("查询场馆信息成功").put("data", changguanxinxiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ChangguanxinxiEntity changguanxinxi = changguanxinxiService.selectById(id);
		changguanxinxi = changguanxinxiService.selectView(new EntityWrapper<ChangguanxinxiEntity>().eq("id", id));
        return R.ok().put("data", changguanxinxi);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ChangguanxinxiEntity changguanxinxi = changguanxinxiService.selectById(id);
		changguanxinxi = changguanxinxiService.selectView(new EntityWrapper<ChangguanxinxiEntity>().eq("id", id));
        return R.ok().put("data", changguanxinxi);
    }
    


    /**
     * 赞或踩
     */
    @RequestMapping("/thumbsup/{id}")
    public R vote(@PathVariable("id") String id,String type){
        ChangguanxinxiEntity changguanxinxi = changguanxinxiService.selectById(id);
        if(type.equals("1")) {
        	changguanxinxi.setThumbsupNumber(changguanxinxi.getThumbsupNumber()+1);
        } else {
        	changguanxinxi.setCrazilyNumber(changguanxinxi.getCrazilyNumber()+1);
        }
        changguanxinxiService.updateById(changguanxinxi);
        return R.ok("投票成功");
    }

    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ChangguanxinxiEntity changguanxinxi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(changguanxinxi);
        changguanxinxiService.insert(changguanxinxi);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ChangguanxinxiEntity changguanxinxi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(changguanxinxi);
        changguanxinxiService.insert(changguanxinxi);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody ChangguanxinxiEntity changguanxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(changguanxinxi);
        changguanxinxiService.updateById(changguanxinxi);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        changguanxinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	









}
