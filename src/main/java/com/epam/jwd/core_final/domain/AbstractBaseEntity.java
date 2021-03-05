package com.epam.jwd.core_final.domain;

/**
 * Expected fields:
 * <p>
 * id {@link Long} - entity id
 * name {@link String} - entity name
 */
public abstract class AbstractBaseEntity implements BaseEntity {
    protected static Long num = 0L;
    protected Long id = null;
    protected String name = null;


    @Override
    public Long getId() {
        // todo
        if (id == null) {
            id = ++num;
        }
        return id;
    }

    @Override
    public String getName() {
        // todo
        return name;
    }
}
