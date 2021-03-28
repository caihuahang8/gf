package com.learn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

				            import com.learn.service.SysUserService;
			

import com.learn.dao.ZiliaoDao;
import com.learn.entity.ZiliaoEntity;
import com.learn.service.ZiliaoService;
import com.learn.service.*;



@Service("ziliaoService")
public class ZiliaoServiceImpl implements ZiliaoService {
	@Autowired
	private ZiliaoDao ziliaoDao;

			

			

			

			            @Autowired
            private SysUserService sysUserService;

		

			

			

	

	
	@Override
	public ZiliaoEntity queryObject(Long id){
			ZiliaoEntity entity = ziliaoDao.queryObject(id);

												            if (this.sysUserService.queryObject(entity.getUser()) != null)
                entity.setSysUserEntity(this.sysUserService.queryObject(entity.getUser()));

									
		return entity;
	}
	
	@Override
	public List<ZiliaoEntity> queryList(Map<String, Object> map){
        List<ZiliaoEntity> list = ziliaoDao.queryList(map);
        for(ZiliaoEntity entity : list){
																												                    if (this.sysUserService.queryObject(entity.getUser()) != null)
                        entity.setSysUserEntity(this.sysUserService.queryObject(entity.getUser()));

																							}
		return list;
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return ziliaoDao.queryTotal(map);
	}
	
	@Override
	public void save(ZiliaoEntity ziliao){
		ziliaoDao.save(ziliao);
	}
	
	@Override
	public void update(ZiliaoEntity ziliao){
		ziliaoDao.update(ziliao);
	}
	
	@Override
	public void delete(Long id){
		ziliaoDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		ziliaoDao.deleteBatch(ids);
	}
	
}
