package com.gcx.api.controller;

import javax.servlet.http.HttpServletRequest;

import com.gcx.api.common.elasticSerachs.ElasticSerachUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcx.api.common.util.MyResult;
import com.gcx.api.model.Flicker;
import com.gcx.api.service.FlickerService;

/**
 * @author qimy
 * @version 创建时间：2018-08-31 11:16:17
 */

@RestController
@Api(value="flickers",description="elasticSerachs测试接口")
@RequestMapping("/flickers")
public class FlickerController {

	@Autowired
	FlickerService  flickerService;

	// 查询所有记录 分页默认每页为10条记录，查询全部pageSize传-1
	@GetMapping
	@ApiOperation(value = "列表")
	MyResult  list(Flicker record) throws Exception{
		MyResult allRecords = flickerService.findAllRecords(record);
		return allRecords;
	}
	
	// 添加记录
	@PostMapping
	@ApiOperation(value = "新增")
	MyResult add(@RequestBody Flicker record,HttpServletRequest request) throws Exception{	
		MyResult result = flickerService.addRecord(record);
		return result;
	}
}
