package com.felix.fonteneau.contentdrivengraphql.resolver.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.felix.fonteneau.contentdrivengraphql.dao.ContentableDAO;
import com.felix.fonteneau.contentdrivengraphql.model.*;
import com.felix.fonteneau.contentdrivengraphql.model.input.*;
import com.felix.fonteneau.contentdrivengraphql.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
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

    private static List<Metadata> buildMetadataFromInput(List<MetadataInput> metadataInputs) {
        // List<Pair<String, List<String>>> metadata = new ArrayList<>(metadataInputs.size());
        // metadataInputs.parallelStream()
        //         .forEach(metadataInput -> metadata.add  (metadataInput.getKey(), metadataInput.getValue()));
        // return metadata;
        return metadataInputs.parallelStream()
                .map(tuple -> new Metadata(tuple.getKey(), tuple.getValue()))
                .collect(Collectors.toList());
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
