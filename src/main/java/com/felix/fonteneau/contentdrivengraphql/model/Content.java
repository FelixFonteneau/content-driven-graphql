package com.felix.fonteneau.contentdrivengraphql.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Content implements Contentable {
    private String id;
    private String type;
    private List<Content> nestedContent;
    private Link link;
    private String text;
    private String media;
    private List<Metadata> metadata;
}
