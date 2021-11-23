package com.felix.fonteneau.contentdrivengraphql.resolver.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.felix.fonteneau.contentdrivengraphql.dao.ContentableDAO;
import com.felix.fonteneau.contentdrivengraphql.model.Alternative;
import com.felix.fonteneau.contentdrivengraphql.model.Condition;
import com.felix.fonteneau.contentdrivengraphql.model.Content;
import com.felix.fonteneau.contentdrivengraphql.model.Link;
import com.felix.fonteneau.contentdrivengraphql.model.input.*;
import com.felix.fonteneau.contentdrivengraphql.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class MutationResolver implements GraphQLMutationResolver {
    private final ContentableDAO contentableDAO;

    @Autowired
    public MutationResolver(ContentableDAO contentableDAO) {
        this.contentableDAO = contentableDAO;
    }

    public Content createContent(ContentInput contentInput) {
        Content content = buildContentFromInput(contentInput);
        contentableDAO.addOrReplace(content);
        return content;
    }

    public Boolean createAlternative(AlternativeInput alternativeInput) {
        Alternative alternative = new Alternative(
                alternativeInput.getId(),
                buildPossiblesContentByCondition(alternativeInput.getPossibleAlternativesWithCondition())
        );
        contentableDAO.addOrReplace(alternative);
        return true;
    }

    private static Content buildContentFromInput(ContentInput contentInput) {
        return new Content(
                contentInput.getId(),
                contentInput.getType(),
                buildNestedContentFromInput(contentInput.getNestedContent()),
                buildLinkFromInput(contentInput.getLink()),
                contentInput.getText(),
                contentInput.getMedia(),
                buildMetadataFromInput(contentInput.getMetadataInput())
        );
    }

    private static List<Content> buildNestedContentFromInput(List<ContentInput> contentInputList) {
        return contentInputList.parallelStream()
                .map(MutationResolver::buildContentFromInput)
                .collect(Collectors.toList());
    }


    private static Link buildLinkFromInput(LinkInput linkInput) {
        return new Link(linkInput.getTo());
    }

    private static Map<String, List<String>> buildMetadataFromInput(List<MetadataInput> metadataInputs) {
        Map<String, List<String>> metadata = new LinkedHashMap<>(metadataInputs.size());
        metadataInputs.parallelStream()
                .forEach(metadataInput -> metadata.put(metadataInput.getKey(), metadataInput.getValue()));
        return metadata;
    }

    private static List<Pair<Content, Condition>> buildPossiblesContentByCondition(List<AlternativeTupleInput> alternativeTupleInputList) {
        return alternativeTupleInputList.parallelStream()
                .map(tuple -> Pair.of(buildContentFromInput(tuple.getLeft()), buildConditionFromInput(tuple.getRight())))
                .collect(Collectors.toList());
    }

    private static Condition buildConditionFromInput(ConditionInput conditionInput) {
        return new Condition(
                conditionInput.getFiltersName(),
                conditionInput.getAND()
                        .stream()
                        .map(MutationResolver::buildConditionFromInput)
                        .collect(Collectors.toList()),
                conditionInput.getOR()
                        .stream()
                        .map(MutationResolver::buildConditionFromInput)
                        .collect(Collectors.toList()));
    }

}
