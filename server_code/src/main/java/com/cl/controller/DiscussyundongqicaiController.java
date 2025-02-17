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

import com.cl.entity.DiscussyundongqicaiEntity;
import com.cl.entity.view.DiscussyundongqicaiView;

import com.cl.service.DiscussyundongqicaiService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.MapUtils;
import com.cl.utils.CommonUtil;

/**
 * 运动器材评论表
 * 后端接口
 * @author 
 * @email 
 * @date 2025-01-16 16:10:41
 */
@RestController
@RequestMapping("/discussyundongqicai")
public class DiscussyundongqicaiController {
    @Autowired
    private DiscussyundongqicaiService discussyundongqicaiService;







    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,DiscussyundongqicaiEntity discussyundongqicai,
                                                                                                                            HttpServletRequest request){
                                    EntityWrapper<DiscussyundongqicaiEntity> ew = new EntityWrapper<DiscussyundongqicaiEntity>();
                                                                                                                                                                                        
        
        
        PageUtils page = discussyundongqicaiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussyundongqicai), params), params));
        return R.ok().put("data", page);
    }
    
    
    
    
    
    
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,DiscussyundongqicaiEntity discussyundongqicai, 
		HttpServletRequest request){
        EntityWrapper<DiscussyundongqicaiEntity> ew = new EntityWrapper<DiscussyundongqicaiEntity>();

		PageUtils page = discussyundongqicaiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussyundongqicai), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( DiscussyundongqicaiEntity discussyundongqicai){
       	EntityWrapper<DiscussyundongqicaiEntity> ew = new EntityWrapper<DiscussyundongqicaiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( discussyundongqicai, "discussyundongqicai")); 
        return R.ok().put("data", discussyundongqicaiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(DiscussyundongqicaiEntity discussyundongqicai){
        EntityWrapper< DiscussyundongqicaiEntity> ew = new EntityWrapper< DiscussyundongqicaiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( discussyundongqicai, "discussyundongqicai")); 
		DiscussyundongqicaiView discussyundongqicaiView =  discussyundongqicaiService.selectView(ew);
		return R.ok("查询运动器材评论表成功").put("data", discussyundongqicaiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        DiscussyundongqicaiEntity discussyundongqicai = discussyundongqicaiService.selectById(id);
		discussyundongqicai = discussyundongqicaiService.selectView(new EntityWrapper<DiscussyundongqicaiEntity>().eq("id", id));
        return R.ok().put("data", discussyundongqicai);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        DiscussyundongqicaiEntity discussyundongqicai = discussyundongqicaiService.selectById(id);
		discussyundongqicai = discussyundongqicaiService.selectView(new EntityWrapper<DiscussyundongqicaiEntity>().eq("id", id));
        return R.ok().put("data", discussyundongqicai);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody DiscussyundongqicaiEntity discussyundongqicai, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(discussyundongqicai);
        discussyundongqicaiService.insert(discussyundongqicai);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody DiscussyundongqicaiEntity discussyundongqicai, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(discussyundongqicai);
        discussyundongqicaiService.insert(discussyundongqicai);
        return R.ok();
    }

     /**
     * 获取用户密保
     */
    @RequestMapping("/security")
    @IgnoreAuth
    public R security(@RequestParam String username){
        DiscussyundongqicaiEntity discussyundongqicai = discussyundongqicaiService.selectOne(new EntityWrapper<DiscussyundongqicaiEntity>().eq("", username));
        return R.ok().put("data", discussyundongqicai);
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @IgnoreAuth
    public R update(@RequestBody DiscussyundongqicaiEntity discussyundongqicai, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discussyundongqicai);
        discussyundongqicaiService.updateById(discussyundongqicai);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        discussyundongqicaiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	
	/**
     * 前端智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,DiscussyundongqicaiEntity discussyundongqicai, HttpServletRequest request,String pre){
        EntityWrapper<DiscussyundongqicaiEntity> ew = new EntityWrapper<DiscussyundongqicaiEntity>();
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
		PageUtils page = discussyundongqicaiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussyundongqicai), params), params));
        return R.ok().put("data", page);
    }









}
