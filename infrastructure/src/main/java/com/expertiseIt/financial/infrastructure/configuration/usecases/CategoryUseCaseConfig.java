package com.expertiseIt.financial.infrastructure.configuration.usecases;

import com.expertiseIt.financial.application.category.create.CreateCategoryUseCase;
import com.expertiseIt.financial.application.category.create.DefaultCreateCategoryUseCase;
import com.expertiseIt.financial.application.category.delete.DefaultDeleteCategoryUseCase;
import com.expertiseIt.financial.application.category.delete.DeleteCategoryUseCase;
import com.expertiseIt.financial.application.category.retrive.get.DefaultGetCategoryByIdUseCase;
import com.expertiseIt.financial.application.category.retrive.get.GetCategoryByIdUseCase;
import com.expertiseIt.financial.application.category.retrive.list.DefaultListCategoryUseCase;
import com.expertiseIt.financial.application.category.retrive.list.ListCategoryUseCase;
import com.expertiseIt.financial.application.category.update.DefaultUpdateCategoryUseCase;
import com.expertiseIt.financial.application.category.update.UpdateCategoryUseCase;
import com.expertiseIt.financial.domain.category.CategoryGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CategoryUseCaseConfig {

    private final CategoryGateway categoryGateway;

    public CategoryUseCaseConfig(CategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    @Bean
    public CreateCategoryUseCase createCategoryUseCase() {
        return new DefaultCreateCategoryUseCase(categoryGateway);
    }
    @Bean
    public UpdateCategoryUseCase updateCategoryUseCase() {
        return new DefaultUpdateCategoryUseCase(categoryGateway);
    }
    @Bean
    public GetCategoryByIdUseCase getCategoryByIdUseCase() {
        return new DefaultGetCategoryByIdUseCase(categoryGateway);
    }
    @Bean
    public ListCategoryUseCase listCategoryUseCase() {
        return new DefaultListCategoryUseCase(categoryGateway);
    }

    @Bean
    public DeleteCategoryUseCase deleteCategoryUseCase() {
        return new DefaultDeleteCategoryUseCase(categoryGateway);
    }



}
