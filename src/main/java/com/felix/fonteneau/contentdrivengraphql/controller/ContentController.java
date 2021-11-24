package com.felix.fonteneau.contentdrivengraphql.controller;

/*
import com.felix.fonteneau.contentdrivengraphql.service.GraphQLService;
import graphql.ExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/autre")
@RestController
public class ContentController {

    private final GraphQLService graphQLService;

    @Autowired
    public ContentController(GraphQLService graphQLService) {
        this.graphQLService = graphQLService;
    }

    @PostMapping
    public ResponseEntity<Object> postContent(@RequestBody String query) {
        ExecutionResult execute = graphQLService.getGraphQL().execute(query);
        if (execute.getErrors().isEmpty()) {
            return new ResponseEntity<>(execute, HttpStatus.OK);
        }
        return new ResponseEntity<>(execute, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
*/
