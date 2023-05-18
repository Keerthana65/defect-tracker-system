package com.defect.tracker.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSeverity is a Querydsl query type for Severity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSeverity extends EntityPathBase<Severity> {

    private static final long serialVersionUID = 1142586884L;

    public static final QSeverity severity = new QSeverity("severity");

    public final StringPath color = createString("color");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QSeverity(String variable) {
        super(Severity.class, forVariable(variable));
    }

    public QSeverity(Path<? extends Severity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSeverity(PathMetadata metadata) {
        super(Severity.class, metadata);
    }

}

