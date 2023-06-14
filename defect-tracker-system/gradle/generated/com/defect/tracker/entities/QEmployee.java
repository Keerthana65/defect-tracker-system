package com.defect.tracker.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEmployee is a Querydsl query type for Employee
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEmployee extends EntityPathBase<Employee> {

    private static final long serialVersionUID = 857756085L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEmployee employee = new QEmployee("employee");

    public final StringPath address = createString("address");

    public final QDesignation designation;

    public final StringPath email = createString("email");

    public final StringPath firName = createString("firName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath lasName = createString("lasName");

    public final NumberPath<Long> phoneNumber = createNumber("phoneNumber", Long.class);

    public QEmployee(String variable) {
        this(Employee.class, forVariable(variable), INITS);
    }

    public QEmployee(Path<? extends Employee> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEmployee(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEmployee(PathMetadata metadata, PathInits inits) {
        this(Employee.class, metadata, inits);
    }

    public QEmployee(Class<? extends Employee> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.designation = inits.isInitialized("designation") ? new QDesignation(forProperty("designation")) : null;
    }

}

