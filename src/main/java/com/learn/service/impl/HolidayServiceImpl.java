package com.learn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

			            import com.learn.service.SysUserService;
					

import com.learn.dao.HolidayDao;
import com.learn.entity.HolidayEntity;
import com.learn.service.HolidayService;
import com.learn.service.*;



@Service("holidayService")
public class HolidayServiceImpl implements HolidayService {
	@Autowired
	private HolidayDao holidayDao;

			

			

			            @Autowired
            private SysUserService sysUserService;

		

			

			

			

			

	

	
	@Override
	public HolidayEntity queryObject(Long id){
			HolidayEntity entity = holidayDao.queryObject(id);

									            if (this.sysUserService.queryObject(entity.getUser()) != null)
                entity.setSysUserEntity(this.sysUserService.queryObject(entity.getUser()));

															
		return entity;
	}
	
	@Override
	public List<HolidayEntity> queryList(Map<String, Object> map){
        List<HolidayEntity> list = holidayDao.queryList(map);
        for(HolidayEntity entity : list){
																					                    if (this.sysUserService.queryObject(entity.getUser()) != null)
                        entity.setSysUserEntity(this.sysUserService.queryObject(entity.getUser()));

																																					}
		return list;
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return holidayDao.queryTotal(map);
	}
	
	@Override
	public void save(HolidayEntity holiday){
		holidayDao.save(holiday);
	}
	
	@Override
	public void update(HolidayEntity holiday){
		holidayDao.update(holiday);
	}
	
	@Override
	public void delete(Long id){
		holidayDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		holidayDao.deleteBatch(ids);
	}
	
}
