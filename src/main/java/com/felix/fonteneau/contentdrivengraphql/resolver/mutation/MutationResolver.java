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
        if (contentInput != null) {
            return new Content(
                    contentInput.getId(),
                    contentInput.getType(),
                    buildNestedContentFromInput(contentInput.getNestedContent()),
                    buildLinkFromInput(contentInput.getLink()),
                    contentInput.getText(),
                    contentInput.getMedia(),
                    buildMetadataFromInput(contentInput.getMetadata())
            );
        }
        return null;
    }

    private static List<Content> buildNestedContentFromInput(List<ContentInput> contentInputList) {
        if (contentInputList != null) {
            return contentInputList.parallelStream()
                    .map(MutationResolver::buildContentFromInput)
                    .collect(Collectors.toList());
        }
        return null;
    }


    private static Link buildLinkFromInput(LinkInput linkInput) {
        if (linkInput != null) {
            return new Link(linkInput.getTo());
        }
        return null;
    }

    private static List<Metadata> buildMetadataFromInput(List<MetadataInput> metadataInputs) {
        if (metadataInputs != null) {
            return metadataInputs.parallelStream()
                    .map(tuple -> new Metadata(tuple.getKey(), tuple.getValue()))
                    .collect(Collectors.toList());
        }
        return null;
    }

    private static List<Pair<Content, Condition>> buildPossiblesContentByCondition(List<AlternativeTupleInput> alternativeTupleInputList) {
        if (alternativeTupleInputList != null) {
            return alternativeTupleInputList.parallelStream()
                    .map(tuple -> Pair.of(buildContentFromInput(tuple.getLeft()), buildConditionFromInput(tuple.getRight())))
                    .collect(Collectors.toList());
        }
        return null;
    }

    private static Condition buildConditionFromInput(ConditionInput conditionInput) {
        if (conditionInput != null) {
            return new Condition(
                    conditionInput.getFiltersName(),
                    conditionInput.getAND() == null ?
                            null
                            :
                            conditionInput.getAND()
                                    .stream()
                                    .map(MutationResolver::buildConditionFromInput)
                                    .collect(Collectors.toList()),
                    conditionInput.getOR() == null ?
                            null
                            :
                            conditionInput.getOR()
                                    .stream()
                                    .map(MutationResolver::buildConditionFromInput)
                                    .collect(Collectors.toList()));
        }
        return null;
    }

}
