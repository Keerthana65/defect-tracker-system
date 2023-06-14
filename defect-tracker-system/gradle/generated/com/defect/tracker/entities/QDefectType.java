package com.defect.tracker.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDefectType is a Querydsl query type for DefectType
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDefectType extends EntityPathBase<DefectType> {

    private static final long serialVersionUID = -1073319534L;

    public static final QDefectType defectType = new QDefectType("defectType");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QDefectType(String variable) {
        super(DefectType.class, forVariable(variable));
    }

    public QDefectType(Path<? extends DefectType> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDefectType(PathMetadata metadata) {
        super(DefectType.class, metadata);
    }

}

