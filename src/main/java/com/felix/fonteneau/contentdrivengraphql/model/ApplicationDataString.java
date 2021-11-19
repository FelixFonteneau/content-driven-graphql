package com.felix.fonteneau.contentdrivengraphql.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationDataString implements ApplicationData{
    private String string;
}
