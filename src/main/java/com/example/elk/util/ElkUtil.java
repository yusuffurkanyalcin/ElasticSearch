package com.example.elk.util;

import co.elastic.clients.elasticsearch._types.query_dsl.*;
import com.example.elk.dto.SearchRequestDto;
import lombok.experimental.UtilityClass;
import org.springframework.data.elasticsearch.core.query.StringQuery;

import java.util.function.Supplier;

@UtilityClass
public class ElkUtil {

    public static Query createMatchAllQuery() {
        return Query.of(q -> q.matchAll(new MatchAllQuery.Builder().build()));
    }

    public static Supplier<Query> buildQueryForFieldAndValue(String fieldName, String searchValue) {
        return () -> Query.of(q -> q.match(buildMatchQueryForFieldAndValue(fieldName, searchValue)));
    }

        private static MatchQuery buildMatchQueryForFieldAndValue(String fieldName, String searchValue) {
            return new MatchQuery.Builder()
                    .field(fieldName)
                    .query(searchValue)
                    .build();
        }

    public static Supplier<Query> createBoolQuery(SearchRequestDto dto) {
        return () -> Query.of(q -> q.bool(boolQuery(dto.fieldName().get(0).toString(), dto.searchValue().get(0),
                dto.fieldName().get(1).toString(), dto.searchValue().get(1))));
    }

    public static BoolQuery boolQuery(String key1, String value1, String key2, String value2) {
        return new BoolQuery.Builder()
                .filter(termQuery(key1, value1))
                .must(termQuery(key2, value2))
                .build();
    }

        private static Query termQuery(String field, String value) {
            return Query.of(q -> q.term(new TermQuery.Builder()
                    .field(field)
                    .value(value)
                    .build()));
        }

    public static Query matchQuery(String field, String value) {
        return Query.of(q -> q.match(new MatchQuery.Builder()
                .field(field)
                .query(value)
                .build()));
    }

    public static Query buildAutoSuggestQuery(String name) {
        return Query.of(q -> q.match(createAutoSuggestMatchQuery(name)));
    }
        private static MatchQuery createAutoSuggestMatchQuery(String name) {
            return new MatchQuery.Builder()
                    .field("name")
                    .query(name)
                    .build();
        }

    public static Query createMatchAllQueryByKeyword(String keyword) {
        return Query.of(q -> q.multiMatch(m -> m
                .query(keyword)
                .fields("name", "brand", "category")
        ));
    }
}