package com.defect.tracker.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDefect is a Querydsl query type for Defect
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDefect extends EntityPathBase<Defect> {

    private static final long serialVersionUID = 1493060152L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDefect defect = new QDefect("defect");

    public final QEmployee assignTo;

    public final QDefectStatus defectStatus;

    public final QDefectType defectType;

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final QPriority priority;

    public final QProject project;

    public final QRelease release;

    public final QEmployee reporter;

    public final QSeverity seviarity;

    public final StringPath stepToRecreat = createString("stepToRecreat");

    public QDefect(String variable) {
        this(Defect.class, forVariable(variable), INITS);
    }

    public QDefect(Path<? extends Defect> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDefect(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDefect(PathMetadata metadata, PathInits inits) {
        this(Defect.class, metadata, inits);
    }

    public QDefect(Class<? extends Defect> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.assignTo = inits.isInitialized("assignTo") ? new QEmployee(forProperty("assignTo"), inits.get("assignTo")) : null;
        this.defectStatus = inits.isInitialized("defectStatus") ? new QDefectStatus(forProperty("defectStatus")) : null;
        this.defectType = inits.isInitialized("defectType") ? new QDefectType(forProperty("defectType")) : null;
        this.priority = inits.isInitialized("priority") ? new QPriority(forProperty("priority")) : null;
        this.project = inits.isInitialized("project") ? new QProject(forProperty("project"), inits.get("project")) : null;
        this.release = inits.isInitialized("release") ? new QRelease(forProperty("release")) : null;
        this.reporter = inits.isInitialized("reporter") ? new QEmployee(forProperty("reporter"), inits.get("reporter")) : null;
        this.seviarity = inits.isInitialized("seviarity") ? new QSeverity(forProperty("seviarity")) : null;
    }

}

