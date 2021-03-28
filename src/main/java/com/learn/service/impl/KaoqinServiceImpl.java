package com.learn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

			            import com.learn.service.SysUserService;
				

import com.learn.dao.KaoqinDao;
import com.learn.entity.KaoqinEntity;
import com.learn.service.KaoqinService;
import com.learn.service.*;



@Service("kaoqinService")
public class KaoqinServiceImpl implements KaoqinService {
	@Autowired
	private KaoqinDao kaoqinDao;

			

			

			            @Autowired
            private SysUserService sysUserService;

		

			

			

			

	

	
	@Override
	public KaoqinEntity queryObject(Long id){
			KaoqinEntity entity = kaoqinDao.queryObject(id);

									            if (this.sysUserService.queryObject(entity.getUser()) != null)
                entity.setSysUserEntity(this.sysUserService.queryObject(entity.getUser()));

												
		return entity;
	}
	
	@Override
	public List<KaoqinEntity> queryList(Map<String, Object> map){
        List<KaoqinEntity> list = kaoqinDao.queryList(map);
        for(KaoqinEntity entity : list){
																					                    if (this.sysUserService.queryObject(entity.getUser()) != null)
                        entity.setSysUserEntity(this.sysUserService.queryObject(entity.getUser()));

																														}
		return list;
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return kaoqinDao.queryTotal(map);
	}
	
	@Override
	public void save(KaoqinEntity kaoqin){
		kaoqinDao.save(kaoqin);
	}
	
	@Override
	public void update(KaoqinEntity kaoqin){
		kaoqinDao.update(kaoqin);
	}
	
	@Override
	public void delete(Long id){
		kaoqinDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		kaoqinDao.deleteBatch(ids);
	}
	
}
