package com.expertiseIt.financial.domain.category;

import com.expertiseIt.financial.domain.AggregateRoot;
import com.expertiseIt.financial.domain.validation.ValidationHandler;

import java.time.Instant;
import java.util.Objects;


public class Category extends AggregateRoot<CategoryID> implements Cloneable{
    private String name;
    private String description;
    private String type;
    private boolean active;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    private Category(
            final CategoryID anId,
            final String aName,
            final String aDescription,
            final String aType,
            final boolean isActive,
            final Instant aCreationDate,
            final Instant aUpdateDate,
            final Instant aDeleteDate)
    {
        super(anId);
        this.name = aName;
        this.description = aDescription;
        this.type = Objects.requireNonNull(aType, "'type' should not be null");
        this.active = isActive;
        this.createdAt = Objects.requireNonNull(aCreationDate, "'createdAt' should not be null");
        this.updatedAt = Objects.requireNonNull(aUpdateDate, "'updatedAt' should not be null");
        this.deletedAt = aDeleteDate;
    }

    public static Category newCategory(final String aName, final String aDescription, final String aType, final boolean isActive) {
        final var id = CategoryID.unique();
        final var now = Instant.now();
        final var deletedAt = isActive ? null : now;
        return new Category(id, aName, aDescription, aType, isActive, now, now, deletedAt);
    }

    public static Category with(
            final CategoryID anId,
            final String name,
            final String description,
            final String type,
            final boolean active,
            final Instant createdAt,
            final Instant updatedAt,
            final Instant deletedAt
    ) {
        return new Category(
                anId,
                name,
                description,
                type,
                active,
                createdAt,
                updatedAt,
                deletedAt
        );
    }

    public static Category with(final Category aCategory) {
        return with(
                aCategory.getId(),
                aCategory.name,
                aCategory.description,
                aCategory.type,
                aCategory.isActive(),
                aCategory.createdAt,
                aCategory.updatedAt,
                aCategory.deletedAt
        );
    }


    @Override
    public void validate(ValidationHandler handler) {
            new CategoryValidator(this, handler).validate();
    }

    public Category deactivate() {
        if (getDeletedAt() == null) {
            this.deletedAt = Instant.now();
        }

        this.active = false;
        this.updatedAt = Instant.now();
        return this;
    }
    public Category activate() {
       this.deletedAt = null;
        this.active = true;
        this.updatedAt = Instant.now();
        return this;
    }

    public Category update(final String aName, final String aDescription,final String aType, final boolean isActive) {
        this.name = aName;
        this.description = aDescription;
        this.type = aType;
        if(isActive) {
            activate();
        } else {
            deactivate();
        }
        this.updatedAt = Instant.now();
        return this;
    }
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public boolean isActive() {
        return active;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }

    @Override
    public Category clone()  {
        try {
            return (Category) super.clone();
        } catch (CloneNotSupportedException ex) {
            throw new AssertionError();
        }

    }
}