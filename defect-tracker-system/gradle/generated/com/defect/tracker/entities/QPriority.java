package com.defect.tracker.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPriority is a Querydsl query type for Priority
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPriority extends EntityPathBase<Priority> {

    private static final long serialVersionUID = -1501174613L;

    public static final QPriority priority = new QPriority("priority");

    public final StringPath color = createString("color");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QPriority(String variable) {
        super(Priority.class, forVariable(variable));
    }

    public QPriority(Path<? extends Priority> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPriority(PathMetadata metadata) {
        super(Priority.class, metadata);
    }

}

