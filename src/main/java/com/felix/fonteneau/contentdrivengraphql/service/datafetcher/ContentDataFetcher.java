package com.felix.fonteneau.contentdrivengraphql.service.datafetcher;

import com.felix.fonteneau.contentdrivengraphql.model.Content;
import com.felix.fonteneau.contentdrivengraphql.service.ContentService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContentDataFetcher implements DataFetcher<Content> {

    private final ContentService contentService;


    @Autowired
    public ContentDataFetcher(ContentService contentService) {
        this.contentService = contentService;
    }


    @Override
    public Content get(DataFetchingEnvironment dataFetchingEnvironment) {
        String id = dataFetchingEnvironment.getArgument("id");
        return contentService.getScreen(id, null).orElseThrow();
    }
}
