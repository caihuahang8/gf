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

import com.learn.entity.KaoqinEntity;
import com.learn.service.KaoqinService;
import com.learn.utils.PageUtils;
import com.learn.utils.Query;
import com.learn.utils.R;


/**
 * 考勤记录
 */
@RestController
@RequestMapping("kaoqin")
public class KaoqinController extends AbstractController {
    @Autowired
    private KaoqinService kaoqinService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        if (super.getUserId() > 1 && "3".equals(super.getUser().getType()))
            params.put("user", super.getUserId());


        //查询列表数据
        Query query = new Query(params);

        List<KaoqinEntity> kaoqinList = kaoqinService.queryList(query);
        int total = kaoqinService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(kaoqinList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }


    /**
     * 列表
     */
    @RequestMapping("/list2")
    public R list2(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<KaoqinEntity> kaoqinList = kaoqinService.queryList(query);
        return R.ok().put("list", kaoqinList);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        KaoqinEntity kaoqin = kaoqinService.queryObject(id);

        return R.ok().put("kaoqin", kaoqin);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody KaoqinEntity kaoqin) {

        if (kaoqin.getUser() == null)
            kaoqin.setUser(super.getUserId());


        kaoqinService.save(kaoqin);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody KaoqinEntity kaoqin) {
        kaoqinService.update(kaoqin);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        kaoqinService.deleteBatch(ids);

        return R.ok();
    }

}
