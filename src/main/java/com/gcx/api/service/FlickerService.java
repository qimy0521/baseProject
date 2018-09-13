package com.gcx.api.service;


import com.gcx.api.common.util.MyResult;
import com.gcx.api.model.Flicker;
/**
 * @author	qimy
 * @version 创建时间：2018-03-27 11:16:17 
 */

public interface FlickerService{


    public MyResult findAllRecords(Flicker record);

    public MyResult addRecord(Flicker record);

    public MyResult delRecords(String[] tidsArray);

    public MyResult updateRecord(Flicker record);

    public MyResult findById(String tid);






}
