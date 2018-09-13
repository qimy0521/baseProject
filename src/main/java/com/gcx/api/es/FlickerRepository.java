package com.gcx.api.es;


import com.gcx.api.model.Flicker;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlickerRepository extends ElasticsearchRepository<Flicker,String> {


}
