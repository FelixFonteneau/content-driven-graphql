package com.felix.fonteneau.contentdrivengraphql.dao;

import com.felix.fonteneau.contentdrivengraphql.model.*;
import com.felix.fonteneau.contentdrivengraphql.util.Pair;
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
        List<Contentable> startContents = List.of(
                new Content("test_id1", "screen", List.of(new Content(null, "text", null, null, "Test text", null, null)), null, null, null, null),
                new Content("test_id2", "screen", List.of(new Content(null, "button", null, new Link("id1"), "previous", null, null)), null, null, null, null),
                new Alternative(
                        "test_id3",
                        List.of(
                            Pair.of(
                                    new Content("test_id3", "screen", List.of(new Content(null, "text", null, null, "Alternative text marche", null, null)), null, null, null, null),
                                    new Condition(List.of("alwaysTrue"), null, null))))
        );
        startContents.parallelStream()
                        .forEach(contentable -> contentableById.put(contentable.getId(), contentable));
        return contentableById;
    }
}
