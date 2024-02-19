package com.expertiseIt.financial.application.category.create;

public record CreateCategoryCommand(
        String name,
        String description,
        String type,
        boolean isActive
) {

    public static CreateCategoryCommand with(
            final String aName,
            final String aDescription,
            final String aType,
            final boolean isActive
    ) {
        return new CreateCategoryCommand(aName, aDescription, aType, isActive);
    }
}
