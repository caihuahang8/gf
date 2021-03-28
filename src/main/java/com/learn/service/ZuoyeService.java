package com.learn.service;

import com.learn.entity.ZuoyeEntity;

import java.util.List;
import java.util.Map;

/**
 * 作业信息
 */
public interface ZuoyeService {
    /**
    * 查询
	* @return
	*/
	ZuoyeEntity queryObject(Long id);

    /**
    * 查询列表
    * @return
    */
	List<ZuoyeEntity> queryList(Map<String, Object> map);

    /**
    * 查询总数
    * @return
    */
	int queryTotal(Map<String, Object> map);

    /**
    * 保存
    * @return
    */
	void save(ZuoyeEntity zuoye);

    /**
    * 修改
    * @return
    */
	void update(ZuoyeEntity zuoye);

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

	void findById(String id);
}
