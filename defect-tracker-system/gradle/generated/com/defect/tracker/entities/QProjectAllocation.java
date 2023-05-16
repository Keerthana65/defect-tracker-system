package com.defect.tracker.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProjectAllocation is a Querydsl query type for ProjectAllocation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProjectAllocation extends EntityPathBase<ProjectAllocation> {

    private static final long serialVersionUID = 1778727346L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProjectAllocation projectAllocation = new QProjectAllocation("projectAllocation");

    public final QEmployee employee;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QProject project;

    public final QRole role;

    public QProjectAllocation(String variable) {
        this(ProjectAllocation.class, forVariable(variable), INITS);
    }

    public QProjectAllocation(Path<? extends ProjectAllocation> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProjectAllocation(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProjectAllocation(PathMetadata metadata, PathInits inits) {
        this(ProjectAllocation.class, metadata, inits);
    }

    public QProjectAllocation(Class<? extends ProjectAllocation> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.employee = inits.isInitialized("employee") ? new QEmployee(forProperty("employee"), inits.get("employee")) : null;
        this.project = inits.isInitialized("project") ? new QProject(forProperty("project"), inits.get("project")) : null;
        this.role = inits.isInitialized("role") ? new QRole(forProperty("role")) : null;
    }

}

