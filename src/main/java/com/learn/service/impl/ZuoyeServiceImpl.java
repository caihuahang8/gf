package com.learn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

							

import com.learn.dao.ZuoyeDao;
import com.learn.entity.ZuoyeEntity;
import com.learn.service.ZuoyeService;
import com.learn.service.*;



@Service("zuoyeService")
public class ZuoyeServiceImpl implements ZuoyeService {
	@Autowired
	private ZuoyeDao zuoyeDao;

			

			

			

			

			

			

			

	

	
	@Override
	public ZuoyeEntity queryObject(Long id){
			ZuoyeEntity entity = zuoyeDao.queryObject(id);

																						
		return entity;
	}
	
	@Override
	public List<ZuoyeEntity> queryList(Map<String, Object> map){
        List<ZuoyeEntity> list = zuoyeDao.queryList(map);
        for(ZuoyeEntity entity : list){
																																																						}
		return list;
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return zuoyeDao.queryTotal(map);
	}
	
	@Override
	public void save(ZuoyeEntity zuoye){
		zuoyeDao.save(zuoye);
	}
	
	@Override
	public void update(ZuoyeEntity zuoye){
		zuoyeDao.update(zuoye);
	}
	
	@Override
	public void delete(Long id){
		zuoyeDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		zuoyeDao.deleteBatch(ids);
	}

	@Override
	public void findById(String id) {

	}

}
