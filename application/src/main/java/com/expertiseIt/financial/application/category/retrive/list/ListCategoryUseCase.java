package com.expertiseIt.financial.application.category.retrive.list;

import com.expertiseIt.financial.application.UseCase;
import com.expertiseIt.financial.domain.category.CategorySearchQuery;
import com.expertiseIt.financial.domain.pagination.Pagination;

public abstract class ListCategoryUseCase extends UseCase<CategorySearchQuery, Pagination<CategoryListOutput>> {
}
