package com.learn.service;

import com.learn.entity.KaoqinEntity;

import java.util.List;
import java.util.Map;

/**
 * 考勤记录
 */
public interface KaoqinService {
    /**
    * 查询
	* @return
	*/
	KaoqinEntity queryObject(Long id);

    /**
    * 查询列表
    * @return
    */
	List<KaoqinEntity> queryList(Map<String, Object> map);

    /**
    * 查询总数
    * @return
    */
	int queryTotal(Map<String, Object> map);

    /**
    * 保存
    * @return
    */
	void save(KaoqinEntity kaoqin);

    /**
    * 修改
    * @return
    */
	void update(KaoqinEntity kaoqin);

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
