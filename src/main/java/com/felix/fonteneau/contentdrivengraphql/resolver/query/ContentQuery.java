package com.felix.fonteneau.contentdrivengraphql.resolver.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.felix.fonteneau.contentdrivengraphql.model.Content;
import com.felix.fonteneau.contentdrivengraphql.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ContentQuery implements GraphQLQueryResolver {

    private final ContentService contentService;

    @Autowired
    public ContentQuery(ContentService contentService) {
        this.contentService = contentService;
    }


    public Optional<Content> content(final String id) {
        return contentService.getScreen(id, null);
    }

}
