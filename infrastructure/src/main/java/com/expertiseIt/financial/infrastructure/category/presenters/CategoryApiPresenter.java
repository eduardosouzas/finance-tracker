package com.expertiseIt.financial.infrastructure.category.presenters;

import com.expertiseIt.financial.application.category.retrive.get.CategoryOutput;
import com.expertiseIt.financial.application.category.retrive.list.CategoryListOutput;
import com.expertiseIt.financial.infrastructure.category.models.CategoryResponse;
import com.expertiseIt.financial.infrastructure.category.models.CategoryListResponse;

import java.util.function.Function;

public interface CategoryApiPresenter {

    Function<CategoryOutput, CategoryResponse> present = output -> new CategoryResponse(
            output.id().getValue(),
            output.name(),
            output.description(),
            output.type(),
            output.isActive(),
            output.createdAt(),
            output.updatedAt(),
            output.deletedAt()
    );
    static CategoryResponse present(final CategoryOutput output) {
        return new CategoryResponse(
                output.id().getValue(),
                output.name(),
                output.description(),
                output.type(),
                output.isActive(),
                output.createdAt(),
                output.updatedAt(),
                output.deletedAt()
        );
    }
    static CategoryListResponse present(final CategoryListOutput output) {
        return new CategoryListResponse(
                output.id().getValue(),
                output.name(),
                output.description(),
                output.type(),
                output.isActive(),
                output.createdAt(),
                output.deletedAt()
        );
    }
}
