package com.learn.service;

import com.learn.entity.HolidayEntity;

import java.util.List;
import java.util.Map;

/**
 * 学生请假
 */
public interface HolidayService {
    /**
    * 查询
	* @return
	*/
	HolidayEntity queryObject(Long id);

    /**
    * 查询列表
    * @return
    */
	List<HolidayEntity> queryList(Map<String, Object> map);

    /**
    * 查询总数
    * @return
    */
	int queryTotal(Map<String, Object> map);

    /**
    * 保存
    * @return
    */
	void save(HolidayEntity holiday);

    /**
    * 修改
    * @return
    */
	void update(HolidayEntity holiday);

    /**
    * 删除
    * @return
    */
	void delete(Long id);

    /**
    * 批量删除
    * @return
    */
	void deleteBatch(Long[] ids);
}
