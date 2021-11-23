package com.felix.fonteneau.contentdrivengraphql.model.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlternativeInput {
    private String id;
    private String type;
    private List<AlternativeTupleInput> possibleAlternativesWithCondition;
}
