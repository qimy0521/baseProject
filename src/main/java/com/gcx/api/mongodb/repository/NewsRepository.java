package com.gcx.api.mongodb.repository;

import com.gcx.api.mongodb.model.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

/**
 * @Auther: root
 * @Date: 2018/9/13 10:58
 * @Description:
 */
@Repository
public class NewsRepository {

    @Autowired
    MongoTemplate mongoTemplate;


    /**
     * 新增数据
     * @param news 数据对象
     * @return
     */
    public void insertData(News news){
        mongoTemplate.insert(news);
    }


    /**
     * 更新数据
     * @param news
     */
    public void updataDataByID(News news){


    }

    /**
     * 删除数据
     * @param news
     */
    public void deleteDataByID(News news){


    }

    /**
     * 查询数据
     * @param news
     */
    public void findData(News news){

    }


}
