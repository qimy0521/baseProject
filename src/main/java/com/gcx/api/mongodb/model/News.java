package com.gcx.api.mongodb.model;

import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @Auther: root
 * @Date: 2018/9/13 10:53
 * @Description:
 */
@Document(collection = "test")
@Data
public class News {

    @Indexed
    private String id;

    @Indexed
    private String title;
    private List<Personlist> personlist;




}
