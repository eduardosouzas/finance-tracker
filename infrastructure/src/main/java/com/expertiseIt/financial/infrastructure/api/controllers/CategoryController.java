package com.expertiseIt.financial.infrastructure.api.controllers;

import com.expertiseIt.financial.application.category.create.CreateCategoryCommand;
import com.expertiseIt.financial.application.category.create.CreateCategoryOutput;
import com.expertiseIt.financial.application.category.create.CreateCategoryUseCase;
import com.expertiseIt.financial.application.category.delete.DeleteCategoryUseCase;
import com.expertiseIt.financial.application.category.retrive.get.GetCategoryByIdUseCase;
import com.expertiseIt.financial.application.category.retrive.list.ListCategoryUseCase;
import com.expertiseIt.financial.application.category.update.UpdateCategoryCommand;
import com.expertiseIt.financial.application.category.update.UpdateCategoryOutput;
import com.expertiseIt.financial.application.category.update.UpdateCategoryUseCase;
import com.expertiseIt.financial.domain.category.CategorySearchQuery;
import com.expertiseIt.financial.domain.pagination.Pagination;
import com.expertiseIt.financial.domain.validation.handler.Notification;
import com.expertiseIt.financial.infrastructure.api.CategoryAPI;
import com.expertiseIt.financial.infrastructure.category.models.CategoryResponse;
import com.expertiseIt.financial.infrastructure.category.models.CreateCategoryRequest;
import com.expertiseIt.financial.infrastructure.category.models.UpdateCategoryRequest;
import com.expertiseIt.financial.infrastructure.category.presenters.CategoryApiPresenter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Objects;
import java.util.function.Function;

@RestController
public class CategoryController implements CategoryAPI {

    private final CreateCategoryUseCase categoryUseCase;
    private final GetCategoryByIdUseCase getCategoryByIdUseCase;
    private final UpdateCategoryUseCase updateCategoryUseCase;
    private final DeleteCategoryUseCase deleteCategoryUseCase;
    private final ListCategoryUseCase listCategoryUseCase;


    public CategoryController(CreateCategoryUseCase categoryUseCase,
                              GetCategoryByIdUseCase getCategoryByIdUseCase,
                              UpdateCategoryUseCase updateCategoryUseCase,
                              DeleteCategoryUseCase deleteCategoryUseCase,
                              ListCategoryUseCase listCategoryUseCase
    ) {
        this.categoryUseCase = Objects.requireNonNull(categoryUseCase);
        this.getCategoryByIdUseCase = Objects.requireNonNull(getCategoryByIdUseCase);
        this.updateCategoryUseCase = Objects.requireNonNull(updateCategoryUseCase);
        this.deleteCategoryUseCase = Objects.requireNonNull(deleteCategoryUseCase);
        this.listCategoryUseCase = Objects.requireNonNull(listCategoryUseCase);
    }

    @Override
    public ResponseEntity<?> createCategory(final CreateCategoryRequest input) {
        final var aCommand = CreateCategoryCommand.with(
                input.name(),
                input.description(),
                input.type(),
                input.active() != null ? input.active() : true
        );
        final Function<Notification, ResponseEntity<?>> onError = notification -> ResponseEntity.unprocessableEntity().body(notification);
        final Function<CreateCategoryOutput, ResponseEntity<?>> onSuccess = output -> ResponseEntity.created(URI.create("/categories/" + output.id())).body(output);
        return this.categoryUseCase.execute(aCommand)
                .fold(onError, onSuccess);
    }

    @Override
    public Pagination<?> listCategories(
            final String terms,
            final int page,
            final int perPage,
            final String sort,
            final String direction) {

        return listCategoryUseCase.execute(new CategorySearchQuery(page, perPage, terms, sort, direction))
                .map(CategoryApiPresenter::present);
    }

    @Override
    public CategoryResponse getById(String id) {
        return CategoryApiPresenter.present.apply(this.getCategoryByIdUseCase.execute(id));
    }

    @Override
    public ResponseEntity<?> updateById(final String id, final UpdateCategoryRequest input) {
        final var aCommand = UpdateCategoryCommand.with(
                id,
                input.name(),
                input.description(),
                input.type(),
                input.active() != null ? input.active() : true
        );
        final Function<Notification, ResponseEntity<?>> onError = notification -> ResponseEntity.unprocessableEntity().body(notification);
        final Function<UpdateCategoryOutput, ResponseEntity<?>> onSuccess = ResponseEntity::ok;
        return this.updateCategoryUseCase.execute(aCommand)
                .fold(onError, onSuccess);
    }

    @Override
    public void deleteById(final String id) {
        this.deleteCategoryUseCase.execute(id);
    }
}
