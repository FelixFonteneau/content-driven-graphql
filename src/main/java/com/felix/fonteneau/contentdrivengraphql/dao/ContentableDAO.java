package com.felix.fonteneau.contentdrivengraphql.dao;

import com.felix.fonteneau.contentdrivengraphql.model.Contentable;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.Optional;

@Repository
public class ContentableDAO {
    private final LinkedHashMap<String, Contentable> contentableById;

    public ContentableDAO() {
        this.contentableById = new LinkedHashMap<>();
    }

    public Optional<Contentable> get(String id) {
        return Optional.ofNullable(contentableById.get(id));
    }

    public void addOrReplace(Contentable contentable) {
        contentableById.put(contentable.getId(), contentable);
    }
}
