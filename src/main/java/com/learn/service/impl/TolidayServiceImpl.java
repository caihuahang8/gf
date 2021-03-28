package com.learn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

			            import com.learn.service.SysUserService;
					

import com.learn.dao.TolidayDao;
import com.learn.entity.TolidayEntity;
import com.learn.service.TolidayService;
import com.learn.service.*;



@Service("tolidayService")
public class TolidayServiceImpl implements TolidayService {
	@Autowired
	private TolidayDao tolidayDao;

			

			

			            @Autowired
            private SysUserService sysUserService;

		

			

			

			

			

	

	
	@Override
	public TolidayEntity queryObject(Long id){
			TolidayEntity entity = tolidayDao.queryObject(id);

									            if (this.sysUserService.queryObject(entity.getUser()) != null)
                entity.setSysUserEntity(this.sysUserService.queryObject(entity.getUser()));

															
		return entity;
	}
	
	@Override
	public List<TolidayEntity> queryList(Map<String, Object> map){
        List<TolidayEntity> list = tolidayDao.queryList(map);
        for(TolidayEntity entity : list){
																					                    if (this.sysUserService.queryObject(entity.getUser()) != null)
                        entity.setSysUserEntity(this.sysUserService.queryObject(entity.getUser()));

																																					}
		return list;
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tolidayDao.queryTotal(map);
	}
	
	@Override
	public void save(TolidayEntity toliday){
		tolidayDao.save(toliday);
	}
	
	@Override
	public void update(TolidayEntity toliday){
		tolidayDao.update(toliday);
	}
	
	@Override
	public void delete(Long id){
		tolidayDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		tolidayDao.deleteBatch(ids);
	}
	
}
