package io.pera.bookstore.util;

import io.pera.bookstore.model.entity.Sequence;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Component
@AllArgsConstructor
public class SequenceUtil {

    private final MongoOperations mongoOperations;

    public long generateSequence(String seqName) {
        Sequence counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
                new Update().inc("value", 1), options().returnNew(true).upsert(true),
                Sequence.class);
        return !Objects.isNull(counter) ? counter.getValue() : 1;
    }

}
