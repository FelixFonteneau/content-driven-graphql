package com.felix.fonteneau.contentdrivengraphql.model.filter;

import com.felix.fonteneau.contentdrivengraphql.model.ApplicationData;
import com.felix.fonteneau.contentdrivengraphql.model.Content;

public interface Filter {
    boolean filter(Content content, ApplicationData appData);
}
