package com.learn.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learn.entity.TolidayEntity;
import com.learn.service.TolidayService;
import com.learn.utils.PageUtils;
import com.learn.utils.Query;
import com.learn.utils.R;


/**
 * 学生请假
 */
@RestController
@RequestMapping("toliday")
public class TolidayController extends AbstractController {
	@Autowired
	private TolidayService tolidayService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){

															                if (super.getUserId() > 1)
                    params.put("user", super.getUserId());
																									

		//查询列表数据
        Query query = new Query(params);

		List<TolidayEntity> tolidayList = tolidayService.queryList(query);
		int total = tolidayService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tolidayList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	

	/**
	 * 列表
	 */
	@RequestMapping("/list2")
	public R list2(@RequestParam Map<String, Object> params){
        Query query = new Query(params);
		List<TolidayEntity> tolidayList = tolidayService.queryList(query);
		return R.ok().put("list", tolidayList );
	}


	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Long id){
		TolidayEntity toliday = tolidayService.queryObject(id);
		
		return R.ok().put("toliday", toliday);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody TolidayEntity toliday){

															                if (toliday.getUser() == null)
                    toliday.setUser(super.getUserId());
																									

        tolidayService.save(toliday);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody TolidayEntity toliday){
		tolidayService.update(toliday);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Long[] ids){
		tolidayService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
