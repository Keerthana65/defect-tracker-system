package com.defect.tracker.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSeviarity is a Querydsl query type for Seviarity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSeviarity extends EntityPathBase<Seviarity> {

    private static final long serialVersionUID = 1159529297L;

    public static final QSeviarity seviarity = new QSeviarity("seviarity");

    public final StringPath color = createString("color");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QSeviarity(String variable) {
        super(Seviarity.class, forVariable(variable));
    }

    public QSeviarity(Path<? extends Seviarity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSeviarity(PathMetadata metadata) {
        super(Seviarity.class, metadata);
    }

}

