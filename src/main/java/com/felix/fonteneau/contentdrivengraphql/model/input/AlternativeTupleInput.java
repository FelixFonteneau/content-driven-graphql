package com.felix.fonteneau.contentdrivengraphql.model.input;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlternativeTupleInput {
    private ContentInput left;
    private ConditionInput right;
}
