package com.felix.fonteneau.contentdrivengraphql.model.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContentInput {
    private String id;
    private String type;
    private List<ContentInput> nestedContent;
    private LinkInput link;
    private String text;
    private String media;
    private List<MetadataInput> metadataInput;
}
