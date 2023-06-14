package com.defect.tracker.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDesignation is a Querydsl query type for Designation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDesignation extends EntityPathBase<Designation> {

    private static final long serialVersionUID = 836158352L;

    public static final QDesignation designation = new QDesignation("designation");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QDesignation(String variable) {
        super(Designation.class, forVariable(variable));
    }

    public QDesignation(Path<? extends Designation> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDesignation(PathMetadata metadata) {
        super(Designation.class, metadata);
    }

}

