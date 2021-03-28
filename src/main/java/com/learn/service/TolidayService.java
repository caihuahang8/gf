package com.learn.service;

import com.learn.entity.TolidayEntity;

import java.util.List;
import java.util.Map;

/**
 * 学生请假
 */
public interface TolidayService {
    /**
    * 查询
	* @return
	*/
	TolidayEntity queryObject(Long id);

    /**
    * 查询列表
    * @return
    */
	List<TolidayEntity> queryList(Map<String, Object> map);

    /**
    * 查询总数
    * @return
    */
	int queryTotal(Map<String, Object> map);

    /**
    * 保存
    * @return
    */
	void save(TolidayEntity toliday);

    /**
    * 修改
    * @return
    */
	void update(TolidayEntity toliday);

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
