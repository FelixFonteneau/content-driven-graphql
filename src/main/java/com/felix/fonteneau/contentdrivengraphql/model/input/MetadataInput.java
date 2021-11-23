package com.felix.fonteneau.contentdrivengraphql.model.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MetadataInput {
    private String key;
    private List<String> value;
}
