package com.gleb.repository.elastic;

import com.gleb.domain.elastic.PersonSearch;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

import java.util.UUID;

public interface PersonElasticRepository extends ElasticsearchCrudRepository<PersonSearch, UUID> {
}
