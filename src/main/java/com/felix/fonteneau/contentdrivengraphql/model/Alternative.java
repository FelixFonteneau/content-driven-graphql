package com.felix.fonteneau.contentdrivengraphql.model;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.felix.fonteneau.contentdrivengraphql.util.Pair;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeName(Alternative.TYPE)
public class Alternative implements Contentable {
    public static final String TYPE = "ALTERNATIVE_REQUEST";

    private String id;
    private final String type = TYPE;
    private List<Pair<Content, Condition>> possibleAlternativesWithCondition;
}
