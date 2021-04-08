package com.denys.hibernateexample.db.entity;

import com.denys.hibernateexample.db.entity.common.CompositeId;
import com.denys.hibernateexample.db.entity.common.Result;
import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Entity(name = "exams_result")
public class ExamsResult {
    @EmbeddedId
    private CompositeId id;

    @NonNull
    @Column(name = "result")
    @Enumerated(EnumType.ORDINAL)
    private Result result;
}
