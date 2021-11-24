package com.felix.fonteneau.contentdrivengraphql.service;


import com.felix.fonteneau.contentdrivengraphql.service.datafetcher.ContentDataFetcher;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

/*
@Service
public class GraphQLService {
    @Value("classpath:content.graphqls")
    private Resource resource;

    @Getter
    private GraphQL graphQL;

    private final ContentDataFetcher contentDataFetcher;

    @Autowired
    public GraphQLService(ContentDataFetcher contentDataFetcher) {
        this.contentDataFetcher = contentDataFetcher;
    }

    @PostConstruct
    public void loadSchema() throws IOException {
        File schemaFile = resource.getFile();
        TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, wiring);
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("content", contentDataFetcher))
                .build();
    }
}

 */
