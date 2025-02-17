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

import com.cl.entity.SaishihuodongEntity;
import com.cl.entity.view.SaishihuodongView;

import com.cl.service.SaishihuodongService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.MapUtils;
import com.cl.utils.CommonUtil;
import com.cl.service.StoreupService;
import com.cl.entity.StoreupEntity;

/**
 * 赛事活动
 * 后端接口
 * @author 
 * @email 
 * @date 2025-01-16 16:10:41
 */
@RestController
@RequestMapping("/saishihuodong")
public class SaishihuodongController {
    @Autowired
    private SaishihuodongService saishihuodongService;

    @Autowired
    private StoreupService storeupService;






    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,SaishihuodongEntity saishihuodong,
                                                                                                                                                                                                    HttpServletRequest request){
                                    EntityWrapper<SaishihuodongEntity> ew = new EntityWrapper<SaishihuodongEntity>();
                                                                                                                                                                                                                                                                                                                
        
        
        PageUtils page = saishihuodongService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, saishihuodong), params), params));
        return R.ok().put("data", page);
    }
    
    
    
    
    
    
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,SaishihuodongEntity saishihuodong, 
		HttpServletRequest request){
        EntityWrapper<SaishihuodongEntity> ew = new EntityWrapper<SaishihuodongEntity>();

		PageUtils page = saishihuodongService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, saishihuodong), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( SaishihuodongEntity saishihuodong){
       	EntityWrapper<SaishihuodongEntity> ew = new EntityWrapper<SaishihuodongEntity>();
      	ew.allEq(MPUtil.allEQMapPre( saishihuodong, "saishihuodong")); 
        return R.ok().put("data", saishihuodongService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(SaishihuodongEntity saishihuodong){
        EntityWrapper< SaishihuodongEntity> ew = new EntityWrapper< SaishihuodongEntity>();
 		ew.allEq(MPUtil.allEQMapPre( saishihuodong, "saishihuodong")); 
		SaishihuodongView saishihuodongView =  saishihuodongService.selectView(ew);
		return R.ok("查询赛事活动成功").put("data", saishihuodongView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        SaishihuodongEntity saishihuodong = saishihuodongService.selectById(id);
		saishihuodong = saishihuodongService.selectView(new EntityWrapper<SaishihuodongEntity>().eq("id", id));
        return R.ok().put("data", saishihuodong);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        SaishihuodongEntity saishihuodong = saishihuodongService.selectById(id);
		saishihuodong = saishihuodongService.selectView(new EntityWrapper<SaishihuodongEntity>().eq("id", id));
        return R.ok().put("data", saishihuodong);
    }
    


    /**
     * 赞或踩
     */
    @RequestMapping("/thumbsup/{id}")
    public R vote(@PathVariable("id") String id,String type){
        SaishihuodongEntity saishihuodong = saishihuodongService.selectById(id);
        if(type.equals("1")) {
        	saishihuodong.setThumbsupNumber(saishihuodong.getThumbsupNumber()+1);
        } else {
        	saishihuodong.setCrazilyNumber(saishihuodong.getCrazilyNumber()+1);
        }
        saishihuodongService.updateById(saishihuodong);
        return R.ok("投票成功");
    }

    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody SaishihuodongEntity saishihuodong, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(saishihuodong);
        saishihuodongService.insert(saishihuodong);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody SaishihuodongEntity saishihuodong, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(saishihuodong);
        saishihuodongService.insert(saishihuodong);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody SaishihuodongEntity saishihuodong, HttpServletRequest request){
        //ValidatorUtils.validateEntity(saishihuodong);
        saishihuodongService.updateById(saishihuodong);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        saishihuodongService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	









}
