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

import com.cl.entity.DiscusschangguanxinxiEntity;
import com.cl.entity.view.DiscusschangguanxinxiView;

import com.cl.service.DiscusschangguanxinxiService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.MapUtils;
import com.cl.utils.CommonUtil;

/**
 * 场馆信息评论表
 * 后端接口
 * @author 
 * @email 
 * @date 2025-01-16 16:10:41
 */
@RestController
@RequestMapping("/discusschangguanxinxi")
public class DiscusschangguanxinxiController {
    @Autowired
    private DiscusschangguanxinxiService discusschangguanxinxiService;







    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,DiscusschangguanxinxiEntity discusschangguanxinxi,
                                                                                                                            HttpServletRequest request){
                                    EntityWrapper<DiscusschangguanxinxiEntity> ew = new EntityWrapper<DiscusschangguanxinxiEntity>();
                                                                                                                                                                                        
        
        
        PageUtils page = discusschangguanxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discusschangguanxinxi), params), params));
        return R.ok().put("data", page);
    }
    
    
    
    
    
    
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,DiscusschangguanxinxiEntity discusschangguanxinxi, 
		HttpServletRequest request){
        EntityWrapper<DiscusschangguanxinxiEntity> ew = new EntityWrapper<DiscusschangguanxinxiEntity>();

		PageUtils page = discusschangguanxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discusschangguanxinxi), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( DiscusschangguanxinxiEntity discusschangguanxinxi){
       	EntityWrapper<DiscusschangguanxinxiEntity> ew = new EntityWrapper<DiscusschangguanxinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( discusschangguanxinxi, "discusschangguanxinxi")); 
        return R.ok().put("data", discusschangguanxinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(DiscusschangguanxinxiEntity discusschangguanxinxi){
        EntityWrapper< DiscusschangguanxinxiEntity> ew = new EntityWrapper< DiscusschangguanxinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( discusschangguanxinxi, "discusschangguanxinxi")); 
		DiscusschangguanxinxiView discusschangguanxinxiView =  discusschangguanxinxiService.selectView(ew);
		return R.ok("查询场馆信息评论表成功").put("data", discusschangguanxinxiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        DiscusschangguanxinxiEntity discusschangguanxinxi = discusschangguanxinxiService.selectById(id);
		discusschangguanxinxi = discusschangguanxinxiService.selectView(new EntityWrapper<DiscusschangguanxinxiEntity>().eq("id", id));
        return R.ok().put("data", discusschangguanxinxi);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        DiscusschangguanxinxiEntity discusschangguanxinxi = discusschangguanxinxiService.selectById(id);
		discusschangguanxinxi = discusschangguanxinxiService.selectView(new EntityWrapper<DiscusschangguanxinxiEntity>().eq("id", id));
        return R.ok().put("data", discusschangguanxinxi);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody DiscusschangguanxinxiEntity discusschangguanxinxi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(discusschangguanxinxi);
        discusschangguanxinxiService.insert(discusschangguanxinxi);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody DiscusschangguanxinxiEntity discusschangguanxinxi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(discusschangguanxinxi);
        discusschangguanxinxiService.insert(discusschangguanxinxi);
        return R.ok();
    }

     /**
     * 获取用户密保
     */
    @RequestMapping("/security")
    @IgnoreAuth
    public R security(@RequestParam String username){
        DiscusschangguanxinxiEntity discusschangguanxinxi = discusschangguanxinxiService.selectOne(new EntityWrapper<DiscusschangguanxinxiEntity>().eq("", username));
        return R.ok().put("data", discusschangguanxinxi);
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @IgnoreAuth
    public R update(@RequestBody DiscusschangguanxinxiEntity discusschangguanxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discusschangguanxinxi);
        discusschangguanxinxiService.updateById(discusschangguanxinxi);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        discusschangguanxinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	
	/**
     * 前端智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,DiscusschangguanxinxiEntity discusschangguanxinxi, HttpServletRequest request,String pre){
        EntityWrapper<DiscusschangguanxinxiEntity> ew = new EntityWrapper<DiscusschangguanxinxiEntity>();
        Map<String, Object> newMap = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
		Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			String newKey = entry.getKey();
			if (pre.endsWith(".")) {
				newMap.put(pre + newKey, entry.getValue());
			} else if (StringUtils.isEmpty(pre)) {
				newMap.put(newKey, entry.getValue());
			} else {
				newMap.put(pre + "." + newKey, entry.getValue());
			}
		}
		params.put("sort", "clicktime");
        params.put("order", "desc");
		PageUtils page = discusschangguanxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discusschangguanxinxi), params), params));
        return R.ok().put("data", page);
    }









}
