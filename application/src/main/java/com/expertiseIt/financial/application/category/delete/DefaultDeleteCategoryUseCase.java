package com.expertiseIt.financial.application.category.delete;

import com.expertiseIt.financial.domain.category.CategoryGateway;
import com.expertiseIt.financial.domain.category.CategoryID;

public  class DefaultDeleteCategoryUseCase extends DeleteCategoryUseCase{

    private final CategoryGateway categoryGateway;

    public DefaultDeleteCategoryUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    @Override
    public void execute(final String anIn) {
        this.categoryGateway.deleteById(CategoryID.from(anIn));
    }
}
