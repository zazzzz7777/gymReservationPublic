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

import com.cl.entity.DiscusssaishihuodongEntity;
import com.cl.entity.view.DiscusssaishihuodongView;

import com.cl.service.DiscusssaishihuodongService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.MapUtils;
import com.cl.utils.CommonUtil;

/**
 * 赛事活动评论表
 * 后端接口
 * @author 
 * @email 
 * @date 2025-01-16 16:10:41
 */
@RestController
@RequestMapping("/discusssaishihuodong")
public class DiscusssaishihuodongController {
    @Autowired
    private DiscusssaishihuodongService discusssaishihuodongService;







    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,DiscusssaishihuodongEntity discusssaishihuodong,
                                                                                                                            HttpServletRequest request){
                                    EntityWrapper<DiscusssaishihuodongEntity> ew = new EntityWrapper<DiscusssaishihuodongEntity>();
                                                                                                                                                                                        
        
        
        PageUtils page = discusssaishihuodongService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discusssaishihuodong), params), params));
        return R.ok().put("data", page);
    }
    
    
    
    
    
    
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,DiscusssaishihuodongEntity discusssaishihuodong, 
		HttpServletRequest request){
        EntityWrapper<DiscusssaishihuodongEntity> ew = new EntityWrapper<DiscusssaishihuodongEntity>();

		PageUtils page = discusssaishihuodongService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discusssaishihuodong), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( DiscusssaishihuodongEntity discusssaishihuodong){
       	EntityWrapper<DiscusssaishihuodongEntity> ew = new EntityWrapper<DiscusssaishihuodongEntity>();
      	ew.allEq(MPUtil.allEQMapPre( discusssaishihuodong, "discusssaishihuodong")); 
        return R.ok().put("data", discusssaishihuodongService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(DiscusssaishihuodongEntity discusssaishihuodong){
        EntityWrapper< DiscusssaishihuodongEntity> ew = new EntityWrapper< DiscusssaishihuodongEntity>();
 		ew.allEq(MPUtil.allEQMapPre( discusssaishihuodong, "discusssaishihuodong")); 
		DiscusssaishihuodongView discusssaishihuodongView =  discusssaishihuodongService.selectView(ew);
		return R.ok("查询赛事活动评论表成功").put("data", discusssaishihuodongView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        DiscusssaishihuodongEntity discusssaishihuodong = discusssaishihuodongService.selectById(id);
		discusssaishihuodong = discusssaishihuodongService.selectView(new EntityWrapper<DiscusssaishihuodongEntity>().eq("id", id));
        return R.ok().put("data", discusssaishihuodong);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        DiscusssaishihuodongEntity discusssaishihuodong = discusssaishihuodongService.selectById(id);
		discusssaishihuodong = discusssaishihuodongService.selectView(new EntityWrapper<DiscusssaishihuodongEntity>().eq("id", id));
        return R.ok().put("data", discusssaishihuodong);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody DiscusssaishihuodongEntity discusssaishihuodong, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(discusssaishihuodong);
        discusssaishihuodongService.insert(discusssaishihuodong);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody DiscusssaishihuodongEntity discusssaishihuodong, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(discusssaishihuodong);
        discusssaishihuodongService.insert(discusssaishihuodong);
        return R.ok();
    }

     /**
     * 获取用户密保
     */
    @RequestMapping("/security")
    @IgnoreAuth
    public R security(@RequestParam String username){
        DiscusssaishihuodongEntity discusssaishihuodong = discusssaishihuodongService.selectOne(new EntityWrapper<DiscusssaishihuodongEntity>().eq("", username));
        return R.ok().put("data", discusssaishihuodong);
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @IgnoreAuth
    public R update(@RequestBody DiscusssaishihuodongEntity discusssaishihuodong, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discusssaishihuodong);
        discusssaishihuodongService.updateById(discusssaishihuodong);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        discusssaishihuodongService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	
	/**
     * 前端智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,DiscusssaishihuodongEntity discusssaishihuodong, HttpServletRequest request,String pre){
        EntityWrapper<DiscusssaishihuodongEntity> ew = new EntityWrapper<DiscusssaishihuodongEntity>();
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
		PageUtils page = discusssaishihuodongService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discusssaishihuodong), params), params));
        return R.ok().put("data", page);
    }









}
