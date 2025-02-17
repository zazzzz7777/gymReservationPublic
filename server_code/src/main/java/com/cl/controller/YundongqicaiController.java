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
import com.cl.utils.UserBasedCollaborativeFiltering;

import com.cl.entity.YundongqicaiEntity;
import com.cl.entity.view.YundongqicaiView;

import com.cl.service.YundongqicaiService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.MapUtils;
import com.cl.utils.CommonUtil;
import com.cl.service.StoreupService;
import com.cl.entity.StoreupEntity;

/**
 * 运动器材
 * 后端接口
 * @author 
 * @email 
 * @date 2025-01-16 16:10:41
 */
@RestController
@RequestMapping("/yundongqicai")
public class YundongqicaiController {
    @Autowired
    private YundongqicaiService yundongqicaiService;

    @Autowired
    private StoreupService storeupService;






    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,YundongqicaiEntity yundongqicai,
                                                                                                                                                                                        HttpServletRequest request){
                                    EntityWrapper<YundongqicaiEntity> ew = new EntityWrapper<YundongqicaiEntity>();
                                                                                                                                                                                                                                                                                            
        
        
        PageUtils page = yundongqicaiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yundongqicai), params), params));
        return R.ok().put("data", page);
    }
    
    
    
    
    
    
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,YundongqicaiEntity yundongqicai, 
		HttpServletRequest request){
        EntityWrapper<YundongqicaiEntity> ew = new EntityWrapper<YundongqicaiEntity>();

		PageUtils page = yundongqicaiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yundongqicai), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( YundongqicaiEntity yundongqicai){
       	EntityWrapper<YundongqicaiEntity> ew = new EntityWrapper<YundongqicaiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( yundongqicai, "yundongqicai")); 
        return R.ok().put("data", yundongqicaiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(YundongqicaiEntity yundongqicai){
        EntityWrapper< YundongqicaiEntity> ew = new EntityWrapper< YundongqicaiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( yundongqicai, "yundongqicai")); 
		YundongqicaiView yundongqicaiView =  yundongqicaiService.selectView(ew);
		return R.ok("查询运动器材成功").put("data", yundongqicaiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        YundongqicaiEntity yundongqicai = yundongqicaiService.selectById(id);
		yundongqicai = yundongqicaiService.selectView(new EntityWrapper<YundongqicaiEntity>().eq("id", id));
        return R.ok().put("data", yundongqicai);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        YundongqicaiEntity yundongqicai = yundongqicaiService.selectById(id);
		yundongqicai = yundongqicaiService.selectView(new EntityWrapper<YundongqicaiEntity>().eq("id", id));
        return R.ok().put("data", yundongqicai);
    }
    


    /**
     * 赞或踩
     */
    @RequestMapping("/thumbsup/{id}")
    public R vote(@PathVariable("id") String id,String type){
        YundongqicaiEntity yundongqicai = yundongqicaiService.selectById(id);
        if(type.equals("1")) {
        	yundongqicai.setThumbsupNumber(yundongqicai.getThumbsupNumber()+1);
        } else {
        	yundongqicai.setCrazilyNumber(yundongqicai.getCrazilyNumber()+1);
        }
        yundongqicaiService.updateById(yundongqicai);
        return R.ok("投票成功");
    }

    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody YundongqicaiEntity yundongqicai, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(yundongqicai);
        yundongqicaiService.insert(yundongqicai);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody YundongqicaiEntity yundongqicai, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(yundongqicai);
        yundongqicaiService.insert(yundongqicai);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody YundongqicaiEntity yundongqicai, HttpServletRequest request){
        //ValidatorUtils.validateEntity(yundongqicai);
        yundongqicaiService.updateById(yundongqicai);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        yundongqicaiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	
	/**
     * 前端智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,YundongqicaiEntity yundongqicai, HttpServletRequest request,String pre){
        EntityWrapper<YundongqicaiEntity> ew = new EntityWrapper<YundongqicaiEntity>();
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
		PageUtils page = yundongqicaiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yundongqicai), params), params));
        return R.ok().put("data", page);
    }

    /**
     * 协同算法（基于用户收藏的协同算法）
     */
    @RequestMapping("/autoSort2")
    public R autoSort2(@RequestParam Map<String, Object> params,YundongqicaiEntity yundongqicai, HttpServletRequest request){
        String userId = request.getSession().getAttribute("userId").toString();
        Integer limit = params.get("limit")==null?10:Integer.parseInt(params.get("limit").toString());
        List<StoreupEntity> storeups = storeupService.selectList(new EntityWrapper<StoreupEntity>().eq("type", 1).eq("tablename", "yundongqicai"));
        Map<String, Map<String, Double>> ratings = new HashMap<>();
        if(storeups!=null && storeups.size()>0) {
            for(StoreupEntity storeup : storeups) {
                Map<String, Double> userRatings = null;
                if(ratings.containsKey(storeup.getUserid().toString())) {
                    userRatings = ratings.get(storeup.getUserid().toString());
                } else {
                    userRatings = new HashMap<>();
                    ratings.put(storeup.getUserid().toString(), userRatings);
                }

                if(userRatings.containsKey(storeup.getRefid().toString())) {
                    userRatings.put(storeup.getRefid().toString(), userRatings.get(storeup.getRefid().toString())+1.0);
                } else {
                    userRatings.put(storeup.getRefid().toString(), 1.0);
                }
            }
        }
        // 创建协同过滤对象
        UserBasedCollaborativeFiltering filter = new UserBasedCollaborativeFiltering(ratings);

        // 为指定用户推荐物品
        String targetUser = userId;
        int numRecommendations = limit;
        List<String> recommendations = filter.recommendItems(targetUser, numRecommendations);

        // 输出推荐结果
        System.out.println("Recommendations for " + targetUser + ":");
        for (String item : recommendations) {
            System.out.println(item);
        }

        EntityWrapper<YundongqicaiEntity> ew = new EntityWrapper<YundongqicaiEntity>();
        ew.in("id", String.join(",", recommendations));
        if(recommendations!=null && recommendations.size()>0) {
            ew.last("order by FIELD(id, "+"'"+String.join("','", recommendations)+"'"+")");
        }

        PageUtils page = yundongqicaiService.queryPage(params, ew);
        List<YundongqicaiEntity> pageList = (List<YundongqicaiEntity>)page.getList();
        if(pageList.size()<limit) {
            int toAddNum = limit-pageList.size();
            ew = new EntityWrapper<YundongqicaiEntity>();
            if(recommendations.size()>0){
                ew.notIn("id", recommendations);
            }
            ew.orderBy("id", false);
            ew.last("limit "+toAddNum);
            pageList.addAll(yundongqicaiService.selectList(ew));
        } else if(pageList.size()>limit) {
            pageList = pageList.subList(0, limit);
        }
        page.setList(pageList);

        return R.ok().put("data", page);
    }




    /**
     * （按值统计）
     */
    @RequestMapping("/value/{xColumnName}/{yColumnName}")
    public R value(@PathVariable("yColumnName") String yColumnName, @PathVariable("xColumnName") String xColumnName,HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", MPUtil.camelToSnake(xColumnName));
        params.put("yColumn", MPUtil.camelToSnake(yColumnName));
        EntityWrapper<YundongqicaiEntity> ew = new EntityWrapper<YundongqicaiEntity>();
        List<Map<String, Object>> result = MPUtil.snakeListToCamel(yundongqicaiService.selectValue(params, ew));
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
        EntityWrapper<YundongqicaiEntity> ew = new EntityWrapper<YundongqicaiEntity>();
        for(int i=0;i<yColumnNames.length;i++) {
            params.put("yColumn", yColumnNames[i]);
            List<Map<String, Object>> result = MPUtil.snakeListToCamel(yundongqicaiService.selectValue(params, ew));
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
        EntityWrapper<YundongqicaiEntity> ew = new EntityWrapper<YundongqicaiEntity>();
        List<Map<String, Object>> result = MPUtil.snakeListToCamel(yundongqicaiService.selectTimeStatValue(params, ew));
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
        EntityWrapper<YundongqicaiEntity> ew = new EntityWrapper<YundongqicaiEntity>();
        for(int i=0;i<yColumnNames.length;i++) {
            params.put("yColumn", yColumnNames[i]);
            List<Map<String, Object>> result = MPUtil.snakeListToCamel(yundongqicaiService.selectTimeStatValue(params, ew));
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
        EntityWrapper<YundongqicaiEntity> ew = new EntityWrapper<YundongqicaiEntity>();
        List<Map<String, Object>> result = MPUtil.snakeListToCamel(yundongqicaiService.selectGroup(params, ew));
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
    public R count(@RequestParam Map<String, Object> params,YundongqicaiEntity yundongqicai, HttpServletRequest request){
        EntityWrapper<YundongqicaiEntity> ew = new EntityWrapper<YundongqicaiEntity>();
        int count = yundongqicaiService.selectCount(MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yundongqicai), params), params));
        return R.ok().put("data", count);
    }



}
