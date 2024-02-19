package com.expertiseIt.financial.application.category.update;

public record UpdateCategoryCommand(
        String id,
        String name,
        String description,
        String type,
        boolean isActive
) {
    public static UpdateCategoryCommand with(
            final String anInd,
            final String aName,
            final String aDescription,
            final String aType,
            final boolean isActive
    ) {
        return new UpdateCategoryCommand(anInd, aName, aDescription, aType, isActive);
    }
}
