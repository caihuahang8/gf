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

import com.learn.entity.HolidayEntity;
import com.learn.service.HolidayService;
import com.learn.utils.PageUtils;
import com.learn.utils.Query;
import com.learn.utils.R;


/**
 * 学生请假
 */
@RestController
@RequestMapping("holiday")
public class HolidayController extends AbstractController {
    @Autowired
    private HolidayService holidayService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        if (super.getUserId() > 1 && "3".equals(super.getUser().getType()))
            params.put("user", super.getUserId());


        //查询列表数据
        Query query = new Query(params);

        List<HolidayEntity> holidayList = holidayService.queryList(query);
        int total = holidayService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(holidayList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }


    /**
     * 列表
     */
    @RequestMapping("/list2")
    public R list2(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<HolidayEntity> holidayList = holidayService.queryList(query);
        return R.ok().put("list", holidayList);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        HolidayEntity holiday = holidayService.queryObject(id);

        return R.ok().put("holiday", holiday);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody HolidayEntity holiday) {

        if (holiday.getUser() == null)
            holiday.setUser(super.getUserId());


        holidayService.save(holiday);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody HolidayEntity holiday) {
        holidayService.update(holiday);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        holidayService.deleteBatch(ids);

        return R.ok();
    }

}
