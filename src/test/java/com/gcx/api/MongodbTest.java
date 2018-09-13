package com.gcx.api;

import com.gcx.api.common.util.UUIDUtils;
import com.gcx.api.mongodb.model.News;
import com.gcx.api.mongodb.model.Personlist;
import com.gcx.api.mongodb.repository.NewsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: qimy
 * @Date: 2018/9/13 11:10
 * @Description: mongodb 测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MongodbTest {

    @Autowired
    NewsRepository newsRepository;

    @Test
    public void insert(){
        List<Personlist> personlists=new ArrayList<>();
        for(int a=0;a<=3;a++){
            Personlist personlist=new Personlist();
            personlist.setAge("2"+a);
            personlist.setName("笑笑"+a);
            personlist.setSex("男");
            personlists.add(personlist);
        }
        News news=new News();
        news.setId(UUIDUtils.getUUID());
        news.setTitle("我是GOUGOU");
        news.setPersonlist(personlists);
        newsRepository.insertData(news);



    }

}
