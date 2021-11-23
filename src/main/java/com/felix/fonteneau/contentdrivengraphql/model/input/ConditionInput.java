package com.felix.fonteneau.contentdrivengraphql.model.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConditionInput {
    private List<String> filtersName;
    private List<ConditionInput> AND;
    private List<ConditionInput> OR;
}
