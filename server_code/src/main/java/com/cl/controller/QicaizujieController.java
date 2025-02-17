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

import com.cl.entity.QicaizujieEntity;
import com.cl.entity.view.QicaizujieView;

import com.cl.service.QicaizujieService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.MapUtils;
import com.cl.utils.CommonUtil;

/**
 * 器材租借
 * 后端接口
 * @author 
 * @email 
 * @date 2025-01-16 16:10:41
 */
@RestController
@RequestMapping("/qicaizujie")
public class QicaizujieController {
    @Autowired
    private QicaizujieService qicaizujieService;







    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,QicaizujieEntity qicaizujie,
                                                                                                                                                                HttpServletRequest request){
                    String tableName = request.getSession().getAttribute("tableName").toString();
                                                                                                                                                            if(tableName.equals("yonghu")) {
                    qicaizujie.setZhanghao((String)request.getSession().getAttribute("username"));
                                    }
                                                                                                                                                                                EntityWrapper<QicaizujieEntity> ew = new EntityWrapper<QicaizujieEntity>();
                                                                                                                                                                                                                                                    
        
        
        PageUtils page = qicaizujieService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, qicaizujie), params), params));
        return R.ok().put("data", page);
    }
    
    
    
    
    
    
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,QicaizujieEntity qicaizujie, 
		HttpServletRequest request){
        EntityWrapper<QicaizujieEntity> ew = new EntityWrapper<QicaizujieEntity>();

		PageUtils page = qicaizujieService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, qicaizujie), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( QicaizujieEntity qicaizujie){
       	EntityWrapper<QicaizujieEntity> ew = new EntityWrapper<QicaizujieEntity>();
      	ew.allEq(MPUtil.allEQMapPre( qicaizujie, "qicaizujie")); 
        return R.ok().put("data", qicaizujieService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(QicaizujieEntity qicaizujie){
        EntityWrapper< QicaizujieEntity> ew = new EntityWrapper< QicaizujieEntity>();
 		ew.allEq(MPUtil.allEQMapPre( qicaizujie, "qicaizujie")); 
		QicaizujieView qicaizujieView =  qicaizujieService.selectView(ew);
		return R.ok("查询器材租借成功").put("data", qicaizujieView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        QicaizujieEntity qicaizujie = qicaizujieService.selectById(id);
		qicaizujie = qicaizujieService.selectView(new EntityWrapper<QicaizujieEntity>().eq("id", id));
        return R.ok().put("data", qicaizujie);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        QicaizujieEntity qicaizujie = qicaizujieService.selectById(id);
		qicaizujie = qicaizujieService.selectView(new EntityWrapper<QicaizujieEntity>().eq("id", id));
        return R.ok().put("data", qicaizujie);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody QicaizujieEntity qicaizujie, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(qicaizujie);
        qicaizujieService.insert(qicaizujie);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody QicaizujieEntity qicaizujie, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(qicaizujie);
        qicaizujieService.insert(qicaizujie);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody QicaizujieEntity qicaizujie, HttpServletRequest request){
        //ValidatorUtils.validateEntity(qicaizujie);
        qicaizujieService.updateById(qicaizujie);//全部更新
        return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/shBatch")
    @Transactional
    public R update(@RequestBody Long[] ids, @RequestParam String sfsh, @RequestParam String shhf){
        List<QicaizujieEntity> list = new ArrayList<QicaizujieEntity>();
        for(Long id : ids) {
            QicaizujieEntity qicaizujie = qicaizujieService.selectById(id);
            qicaizujie.setSfsh(sfsh);
            qicaizujie.setShhf(shhf);
            list.add(qicaizujie);
        }
        qicaizujieService.updateBatchById(list);
        return R.ok();
    }


    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        qicaizujieService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	









}
