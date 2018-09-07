package com.gcx.api.elasticrepository;


import com.gcx.api.model.Flicker;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface FlickerRepository extends ElasticsearchRepository<Flicker,String> {


}
