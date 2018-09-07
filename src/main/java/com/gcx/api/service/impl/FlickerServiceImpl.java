package com.gcx.api.service.impl;


import com.gcx.api.elasticrepository.FlickerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcx.api.common.dataSource.CustomDataSource;
import com.gcx.api.common.dataSource.DataSourceName;
import com.gcx.api.common.note.ModelDescription;
import com.gcx.api.common.util.MyResult;
import com.gcx.api.dao.FlickerMapper;
import com.gcx.api.model.Flicker;
import com.gcx.api.service.FlickerService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhanglin
 * @version 创建时间：2018-03-27 11:16:17
 */

@Service
@CustomDataSource(value = DataSourceName.DATA_ZW)
@ModelDescription(value="",key="")
public class FlickerServiceImpl implements FlickerService {

	@Autowired
	private FlickerMapper flickerDao;

	@Autowired
	private FlickerRepository flickerRepository;

	// 查询所有记录 
	public MyResult findAllRecords(Flicker record) {
		List<Flicker> resultList=new ArrayList<Flicker>();
		Iterable<Flicker> all = flickerRepository.findAll();
		all.forEach((flicker)->resultList.add(flicker));
		return MyResult.ok(resultList);
	}

	// 添加记录
	public MyResult addRecord(Flicker record) {
				Flicker result=flickerRepository.save(record);
				return MyResult.ok(result);
	}

	// 批量删除
	public MyResult delRecords(String[] tidsArray) {
				return MyResult.ok();
	}

	// 修改记录
	public MyResult updateRecord(Flicker record) {
				return MyResult.ok();
	}

	// 根据ID查看详情
	public MyResult findById(String tid) {
		Flicker one = flickerRepository.findOne(tid);
		return MyResult.ok(one);
	}
	
}
