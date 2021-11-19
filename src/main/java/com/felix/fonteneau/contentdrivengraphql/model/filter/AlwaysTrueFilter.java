package com.felix.fonteneau.contentdrivengraphql.model.filter;


import com.felix.fonteneau.contentdrivengraphql.model.ApplicationData;
import com.felix.fonteneau.contentdrivengraphql.model.Content;

public class AlwaysTrueFilter implements Filter {
    @Override
    public boolean filter(Content content, ApplicationData appData) {
        return true;
    }
}
