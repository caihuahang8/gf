package com.learn.service;

import com.learn.entity.ZiliaoEntity;

import java.util.List;
import java.util.Map;

/**
 * 资料中心
 */
public interface ZiliaoService {
    /**
    * 查询
	* @return
	*/
	ZiliaoEntity queryObject(Long id);

    /**
    * 查询列表
    * @return
    */
	List<ZiliaoEntity> queryList(Map<String, Object> map);

    /**
    * 查询总数
    * @return
    */
	int queryTotal(Map<String, Object> map);

    /**
    * 保存
    * @return
    */
	void save(ZiliaoEntity ziliao);

    /**
    * 修改
    * @return
    */
	void update(ZiliaoEntity ziliao);

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
