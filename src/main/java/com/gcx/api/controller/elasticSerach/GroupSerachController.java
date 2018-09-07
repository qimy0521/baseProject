package com.gcx.api.controller.elasticSerach;

import com.gcx.api.common.util.MyResult;
import com.gcx.api.model.Flicker;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("serach")
@Api(value = "GroupSerachController",description = "elasticSerach 搜索接口")
public class GroupSerachController {

    @Autowired
    GroupSerachService groupSerachService;


    @GetMapping
    @ResponseBody
    public MyResult serach(Flicker flicker){
        MyResult  resultList = groupSerachService.serach(flicker);
    return resultList;
    }
}
