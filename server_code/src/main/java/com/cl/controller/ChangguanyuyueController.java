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

import com.cl.entity.ChangguanyuyueEntity;
import com.cl.entity.view.ChangguanyuyueView;

import com.cl.service.ChangguanyuyueService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.MapUtils;
import com.cl.utils.CommonUtil;

/**
 * 场馆预约
 * 后端接口
 * @author 
 * @email 
 * @date 2025-01-16 16:10:41
 */
@RestController
@RequestMapping("/changguanyuyue")
public class ChangguanyuyueController {
    @Autowired
    private ChangguanyuyueService changguanyuyueService;







    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ChangguanyuyueEntity changguanyuyue,
                                                                                                                                            HttpServletRequest request){
                    String tableName = request.getSession().getAttribute("tableName").toString();
                                                                                                                                                            if(tableName.equals("yonghu")) {
                    changguanyuyue.setZhanghao((String)request.getSession().getAttribute("username"));
                                    }
                                                                                                                                                                                EntityWrapper<ChangguanyuyueEntity> ew = new EntityWrapper<ChangguanyuyueEntity>();
                                                                                                                                                                                                                        
        
        
        PageUtils page = changguanyuyueService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, changguanyuyue), params), params));
        return R.ok().put("data", page);
    }
    
    
    
    
    
    
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ChangguanyuyueEntity changguanyuyue, 
		HttpServletRequest request){
        EntityWrapper<ChangguanyuyueEntity> ew = new EntityWrapper<ChangguanyuyueEntity>();

		PageUtils page = changguanyuyueService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, changguanyuyue), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ChangguanyuyueEntity changguanyuyue){
       	EntityWrapper<ChangguanyuyueEntity> ew = new EntityWrapper<ChangguanyuyueEntity>();
      	ew.allEq(MPUtil.allEQMapPre( changguanyuyue, "changguanyuyue")); 
        return R.ok().put("data", changguanyuyueService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ChangguanyuyueEntity changguanyuyue){
        EntityWrapper< ChangguanyuyueEntity> ew = new EntityWrapper< ChangguanyuyueEntity>();
 		ew.allEq(MPUtil.allEQMapPre( changguanyuyue, "changguanyuyue")); 
		ChangguanyuyueView changguanyuyueView =  changguanyuyueService.selectView(ew);
		return R.ok("查询场馆预约成功").put("data", changguanyuyueView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ChangguanyuyueEntity changguanyuyue = changguanyuyueService.selectById(id);
		changguanyuyue = changguanyuyueService.selectView(new EntityWrapper<ChangguanyuyueEntity>().eq("id", id));
        return R.ok().put("data", changguanyuyue);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ChangguanyuyueEntity changguanyuyue = changguanyuyueService.selectById(id);
		changguanyuyue = changguanyuyueService.selectView(new EntityWrapper<ChangguanyuyueEntity>().eq("id", id));
        return R.ok().put("data", changguanyuyue);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ChangguanyuyueEntity changguanyuyue, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(changguanyuyue);
        changguanyuyueService.insert(changguanyuyue);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ChangguanyuyueEntity changguanyuyue, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(changguanyuyue);
        changguanyuyueService.insert(changguanyuyue);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody ChangguanyuyueEntity changguanyuyue, HttpServletRequest request){
        //ValidatorUtils.validateEntity(changguanyuyue);
        changguanyuyueService.updateById(changguanyuyue);//全部更新
        return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/shBatch")
    @Transactional
    public R update(@RequestBody Long[] ids, @RequestParam String sfsh, @RequestParam String shhf){
        List<ChangguanyuyueEntity> list = new ArrayList<ChangguanyuyueEntity>();
        for(Long id : ids) {
            ChangguanyuyueEntity changguanyuyue = changguanyuyueService.selectById(id);
            changguanyuyue.setSfsh(sfsh);
            changguanyuyue.setShhf(shhf);
            list.add(changguanyuyue);
        }
        changguanyuyueService.updateBatchById(list);
        return R.ok();
    }


    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        changguanyuyueService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	





    /**
     * （按值统计）
     */
    @RequestMapping("/value/{xColumnName}/{yColumnName}")
    public R value(@PathVariable("yColumnName") String yColumnName, @PathVariable("xColumnName") String xColumnName,HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", MPUtil.camelToSnake(xColumnName));
        params.put("yColumn", MPUtil.camelToSnake(yColumnName));
        EntityWrapper<ChangguanyuyueEntity> ew = new EntityWrapper<ChangguanyuyueEntity>();
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("yonghu")) {
            ew.eq("zhanghao", (String)request.getSession().getAttribute("username"));
		}
        List<Map<String, Object>> result = MPUtil.snakeListToCamel(changguanyuyueService.selectValue(params, ew));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Map<String, Object> m : result) {
            for(String k : m.keySet()) {
                if(m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date)m.get(k)));
                }
            }
        }
        return R.ok().put("data", result);
    }

    /**
     * （按值统计(多)）
     */
    @RequestMapping("/valueMul/{xColumnName}")
    public R valueMul(@PathVariable("xColumnName") String xColumnName,@RequestParam String yColumnNameMul, HttpServletRequest request) {
        String[] yColumnNames = MPUtil.camelToSnake(yColumnNameMul).split(",");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", MPUtil.camelToSnake(xColumnName));
        List<List<Map<String, Object>>> result2 = new ArrayList<List<Map<String,Object>>>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        EntityWrapper<ChangguanyuyueEntity> ew = new EntityWrapper<ChangguanyuyueEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("yonghu")) {
            ew.eq("zhanghao", (String)request.getSession().getAttribute("username"));
        }
        for(int i=0;i<yColumnNames.length;i++) {
            params.put("yColumn", yColumnNames[i]);
            List<Map<String, Object>> result = MPUtil.snakeListToCamel(changguanyuyueService.selectValue(params, ew));
            for(Map<String, Object> m : result) {
                for(String k : m.keySet()) {
                    if(m.get(k) instanceof Date) {
                        m.put(k, sdf.format((Date)m.get(k)));
                    }
                }
            }
            result2.add(result);
        }
        return R.ok().put("data", result2);
    }

    /**
     * （按值统计）时间统计类型
     */
    @RequestMapping("/value/{xColumnName}/{yColumnName}/{timeStatType}")
    public R valueDay(@PathVariable("yColumnName") String yColumnName, @PathVariable("xColumnName") String xColumnName, @PathVariable("timeStatType") String timeStatType,HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", MPUtil.camelToSnake(xColumnName));
        params.put("yColumn", MPUtil.camelToSnake(yColumnName));
        params.put("timeStatType", timeStatType);
        EntityWrapper<ChangguanyuyueEntity> ew = new EntityWrapper<ChangguanyuyueEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("yonghu")) {
            ew.eq("zhanghao", (String)request.getSession().getAttribute("username"));
        }
        List<Map<String, Object>> result = MPUtil.snakeListToCamel(changguanyuyueService.selectTimeStatValue(params, ew));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Map<String, Object> m : result) {
            for(String k : m.keySet()) {
                if(m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date)m.get(k)));
                }
            }
        }
        return R.ok().put("data", result);
    }

    /**
     * （按值统计）时间统计类型(多)
     */
    @RequestMapping("/valueMul/{xColumnName}/{timeStatType}")
    public R valueMulDay(@PathVariable("xColumnName") String xColumnName, @PathVariable("timeStatType") String timeStatType,@RequestParam String yColumnNameMul,HttpServletRequest request) {
        String[] yColumnNames = MPUtil.camelToSnake(yColumnNameMul).split(",");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        params.put("timeStatType", timeStatType);
        List<List<Map<String, Object>>> result2 = new ArrayList<List<Map<String,Object>>>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        EntityWrapper<ChangguanyuyueEntity> ew = new EntityWrapper<ChangguanyuyueEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("yonghu")) {
            ew.eq("zhanghao", (String)request.getSession().getAttribute("username"));
        }
        for(int i=0;i<yColumnNames.length;i++) {
            params.put("yColumn", yColumnNames[i]);
            List<Map<String, Object>> result = MPUtil.snakeListToCamel(changguanyuyueService.selectTimeStatValue(params, ew));
            for(Map<String, Object> m : result) {
                for(String k : m.keySet()) {
                    if(m.get(k) instanceof Date) {
                        m.put(k, sdf.format((Date)m.get(k)));
                    }
                }
            }
            result2.add(result);
        }
        return R.ok().put("data", result2);
    }

    /**
     * 分组统计
     */
    @RequestMapping("/group/{columnName}")
    public R group(@PathVariable("columnName") String columnName,HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("column", MPUtil.camelToSnake(columnName));
        EntityWrapper<ChangguanyuyueEntity> ew = new EntityWrapper<ChangguanyuyueEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("yonghu")) {
            ew.eq("zhanghao", (String)request.getSession().getAttribute("username"));
        }
        List<Map<String, Object>> result = MPUtil.snakeListToCamel(changguanyuyueService.selectGroup(params, ew));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Map<String, Object> m : result) {
            for(String k : m.keySet()) {
                if(m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date)m.get(k)));
                }
            }
        }
        return R.ok().put("data", result);
    }




    /**
     * 总数量
     */
    @RequestMapping("/count")
    public R count(@RequestParam Map<String, Object> params,ChangguanyuyueEntity changguanyuyue, HttpServletRequest request){
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("yonghu")) {
            changguanyuyue.setZhanghao((String)request.getSession().getAttribute("username"));
        }
        EntityWrapper<ChangguanyuyueEntity> ew = new EntityWrapper<ChangguanyuyueEntity>();
        int count = changguanyuyueService.selectCount(MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, changguanyuyue), params), params));
        return R.ok().put("data", count);
    }



}
