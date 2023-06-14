package com.defect.tracker.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRelease is a Querydsl query type for Release
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRelease extends EntityPathBase<Release> {

    private static final long serialVersionUID = -1414086624L;

    public static final QRelease release = new QRelease("release");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QRelease(String variable) {
        super(Release.class, forVariable(variable));
    }

    public QRelease(Path<? extends Release> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRelease(PathMetadata metadata) {
        super(Release.class, metadata);
    }

}

