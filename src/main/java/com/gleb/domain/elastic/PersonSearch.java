package com.gleb.domain.elastic;

import com.gleb.domain.Domain;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.UUID;

@Document(indexName = "person_search", type = "person")
public class PersonSearch implements Domain {

    @Id
    private UUID id;

    private String name;

    private String email;

    public PersonSearch() {
    }

    public PersonSearch(UUID id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "PersonSearch{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
