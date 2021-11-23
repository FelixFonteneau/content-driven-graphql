package com.felix.fonteneau.contentdrivengraphql.dao;

import com.felix.fonteneau.contentdrivengraphql.model.Content;
import com.felix.fonteneau.contentdrivengraphql.model.Contentable;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

@Repository
public class ContentableDAO {
    private final LinkedHashMap<String, Contentable> contentableById;

    public ContentableDAO() {
        this.contentableById = initTestContent();
    }

    public Optional<Contentable> get(String id) {
        return Optional.ofNullable(contentableById.get(id));
    }

    public void addOrReplace(Contentable contentable) {
        contentableById.put(contentable.getId(), contentable);
    }


    private static LinkedHashMap<String, Contentable> initTestContent() {
        LinkedHashMap<String, Contentable> contentableById = new LinkedHashMap<>();
        contentableById.put(
                "id1",
                new Content("id1", "screen", List.of(new Content(null, "text", null, null, "Test text", null, null)), null, null, null, null));
        return contentableById;
    }
}
