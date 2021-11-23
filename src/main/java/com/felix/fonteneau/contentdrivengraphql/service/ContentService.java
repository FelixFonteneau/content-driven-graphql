package com.felix.fonteneau.contentdrivengraphql.service;

import com.felix.fonteneau.contentdrivengraphql.dao.ContentableDAO;
import com.felix.fonteneau.contentdrivengraphql.model.Alternative;
import com.felix.fonteneau.contentdrivengraphql.model.ApplicationData;
import com.felix.fonteneau.contentdrivengraphql.model.Content;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContentService {
    private Logger logger = LoggerFactory.getLogger(ContentService.class);

    private final ContentableDAO contentableDAO;
    private final FilteringEngine filteringEngine;


    @Autowired
    public ContentService(ContentableDAO contentableDAO, FilteringEngine filteringEngine) {
        this.contentableDAO = contentableDAO;
        this.filteringEngine = filteringEngine;
    }

    /**
     * Get a screen
     * @param id id
     * @param appData filtering data
     * @return the screen if it exists and resolve the alternative if there exist some.
     */
    public Optional<Content> getScreen(String id, ApplicationData appData) {
        return contentableDAO.get(id)
                .map(contentable -> {
                    if (contentable instanceof Content) {
                        return (Content) contentable;
                    } else {
                        return filteringEngine.resolveAlternative((Alternative) contentable, appData)
                                .orElseGet(() -> {
                                    logger.error("No content found for alternative {}.", contentable);
                                    return null;
                                });
                    }
                });
    }
}
