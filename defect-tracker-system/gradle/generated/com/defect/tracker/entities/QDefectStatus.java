package com.defect.tracker.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDefectStatus is a Querydsl query type for DefectStatus
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDefectStatus extends EntityPathBase<DefectStatus> {

    private static final long serialVersionUID = -701596598L;

    public static final QDefectStatus defectStatus = new QDefectStatus("defectStatus");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QDefectStatus(String variable) {
        super(DefectStatus.class, forVariable(variable));
    }

    public QDefectStatus(Path<? extends DefectStatus> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDefectStatus(PathMetadata metadata) {
        super(DefectStatus.class, metadata);
    }

}

