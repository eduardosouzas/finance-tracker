package com.expertiseIt.financial.infrastructure.category;

import com.expertiseIt.financial.domain.category.Category;
import com.expertiseIt.financial.domain.category.CategoryID;
import com.expertiseIt.financial.domain.category.CategorySearchQuery;
import com.expertiseIt.financial.MySQLGatewayTest;
import com.expertiseIt.financial.infrastructure.category.persistence.CategoryJpaEntity;
import com.expertiseIt.financial.infrastructure.category.persistence.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

@MySQLGatewayTest
public class CategoryMySQLGatewayTest {

    @Autowired
    private CategoryMySQLGateway categoryGateway;

    @Autowired
    private CategoryRepository categoryRepository;


    @Test
    public void givenAValidCategory_whenCallsCreate_shouldReturnNewCategory() {
        final var expectedName = "Casa";
        final var expectedType = "Expensive";
        final var expectedDescription = "Categoria para listar todas as dispensa relacionada a casa";
        final var expectedIsActive = true;
        final var aCategory = Category.newCategory(expectedName, expectedDescription,expectedType, expectedIsActive);

        Assertions.assertEquals(0, categoryRepository.count());

        final var actualCategory = categoryGateway.create(aCategory);

        Assertions.assertEquals(1, categoryRepository.count());
        Assertions.assertEquals(aCategory.getId(), actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertEquals(expectedType, actualCategory.getType());
        Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
        Assertions.assertEquals(aCategory.getCreatedAt(), actualCategory.getCreatedAt());
        Assertions.assertEquals(aCategory.getUpdatedAt(), actualCategory.getUpdatedAt());
        Assertions.assertNull(actualCategory.getDeletedAt());

        final var actualEntity = categoryRepository.findById(actualCategory.getId().getValue()).get();

        Assertions.assertEquals(actualCategory.getId().getValue(), actualEntity.getId());
        Assertions.assertEquals(actualCategory.getName(), actualEntity.getName());
        Assertions.assertEquals(actualCategory.getDescription(), actualEntity.getDescription());
        Assertions.assertEquals(actualCategory.getType(), actualEntity.getType());
        Assertions.assertEquals(actualCategory.isActive(), actualEntity.isActive());
        Assertions.assertEquals(actualCategory.getCreatedAt(), actualEntity.getCreatedAt());
        Assertions.assertEquals(actualEntity.getUpdatedAt(), actualCategory.getUpdatedAt());
        Assertions.assertNull(actualEntity.getDeletedAt());

    }
    @Test
    public void givenAValidCategory_whenCallsUpdate_shouldReturnCategoryUpdate() {
        final var expectedName = "Casa";
        final var expectedType = "Expensive";
        final var expectedDescription = "Categoria para listar todas as dispensa relacionada a casa";
        final var expectedIsActive = true;
        final var aCategory = Category.newCategory("Home", null,"Home", false);


        Assertions.assertEquals(0, categoryRepository.count());

        categoryRepository.saveAndFlush(CategoryJpaEntity.from(aCategory));

        Assertions.assertEquals(1, categoryRepository.count());

        final var aUpdatedCategory = aCategory.clone().update(expectedName, expectedDescription, expectedType, expectedIsActive);

        final var actualCategory = categoryGateway.update(aUpdatedCategory);

        Assertions.assertEquals(1, categoryRepository.count());
        Assertions.assertEquals(aCategory.getId(), actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertEquals(expectedType, actualCategory.getType());
        Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
        Assertions.assertEquals(aCategory.getCreatedAt(), actualCategory.getCreatedAt());
        Assertions.assertTrue(aCategory.getUpdatedAt().isBefore(actualCategory.getUpdatedAt()));
        Assertions.assertNull(actualCategory.getDeletedAt());

        final var actualEntity = categoryRepository.findById(actualCategory.getId().getValue()).get();

        Assertions.assertEquals(actualCategory.getId().getValue(), actualEntity.getId());
        Assertions.assertEquals(actualCategory.getName(), actualEntity.getName());
        Assertions.assertEquals(actualCategory.getDescription(), actualEntity.getDescription());
        Assertions.assertEquals(actualCategory.getType(), actualEntity.getType());
        Assertions.assertEquals(actualCategory.isActive(), actualEntity.isActive());
        Assertions.assertEquals(actualCategory.getCreatedAt(), actualEntity.getCreatedAt());
        Assertions.assertTrue(aCategory.getUpdatedAt().isBefore(actualCategory.getUpdatedAt()));
        Assertions.assertNull(actualEntity.getDeletedAt());

    }


    @Test
    public void GivenAPrePersistedCategoryAndValidCategoryId_whenTryToDeleteIt_shouldDeleteCategory() {
        Category aCategory = Category.newCategory("Home", null, "Home", true);

        Assertions.assertEquals(0, categoryRepository.count());

        categoryRepository.saveAndFlush(CategoryJpaEntity.from(aCategory));

        Assertions.assertEquals(1, categoryRepository.count());

        categoryGateway.deleteById(aCategory.getId());

        Assertions.assertEquals(0, categoryRepository.count());


    }

    @Test
    public void givenInvalidCategoryId_whenTryToDeleteIt_shouldDeleteCategory() {

        Assertions.assertEquals(0, categoryRepository.count());

        categoryGateway.deleteById(CategoryID.from("invalid_id"));

        Assertions.assertEquals(0, categoryRepository.count());
    }

    @Test
    public void givenAPrePersistedCategoryAndValidCategoryId_whenCallsFindByAId_shouldReturnCategory() {
        final var expectedName = "Casa";
        final var expectedType = "Expensive";
        final var expectedDescription = "Categoria para listar todas as dispensa relacionada a casa";
        final var expectedIsActive = true;
        final var aCategory = Category.newCategory(expectedName, expectedDescription ,expectedType, expectedIsActive);


        Assertions.assertEquals(0, categoryRepository.count());

        categoryRepository.saveAndFlush(CategoryJpaEntity.from(aCategory));

        Assertions.assertEquals(1, categoryRepository.count());

        final var actualCategory = categoryGateway.findById(aCategory.getId()).get();

        Assertions.assertEquals(1, categoryRepository.count());

        Assertions.assertEquals(aCategory.getId(), actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertEquals(expectedType, actualCategory.getType());
        Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
        Assertions.assertEquals(aCategory.getCreatedAt(), actualCategory.getCreatedAt());
        Assertions.assertEquals(aCategory.getUpdatedAt(), actualCategory.getUpdatedAt());
        Assertions.assertNull(actualCategory.getDeletedAt());
   }

    @Test
    public void givenAValidCategoryIdBotStore_whenCallsFindByAId_shouldReturnEmpty() {
        Assertions.assertEquals(0, categoryRepository.count());

        final var actualCategory = categoryGateway.findById(CategoryID.from("empty"));

        Assertions.assertTrue(actualCategory.isEmpty());

    }
    @Test
    public void givenPrePersistedCategories_whenCallsFindAll_shouldReturnPaginated() {
        final var expectedPage = 0;
        final var expectedPerPage = 1;
        final var expectedTotal = 3;

        final var aCategoryOne = Category.newCategory("Home", null ,"home", true);
        final var aCategoryTwo = Category.newCategory("Pet", null ,"home", true);
        final var aCategoryTree = Category.newCategory("Child", null ,"home", true);

        Assertions.assertEquals(0, categoryRepository.count());

        categoryRepository.saveAll(List.of(
                    CategoryJpaEntity.from(aCategoryOne),
                    CategoryJpaEntity.from(aCategoryTwo),
                    CategoryJpaEntity.from(aCategoryTree)
                ));

        Assertions.assertEquals(3, categoryRepository.count());

        final var query = new CategorySearchQuery(0,1,"", "name", "asc");
        final var actualResult = categoryGateway.findAll(query);

        Assertions.assertEquals(expectedPage, actualResult.currentPage());
        Assertions.assertEquals(expectedPerPage, actualResult.perPage());
        Assertions.assertEquals(expectedTotal, actualResult.total());

        Assertions.assertEquals(aCategoryTree.getId(), actualResult.items().get(0).getId());


    }
    @Test
    public void givenEmptyCategoriesTable_whenCallsFindAll_shouldReturnEmptyPage() {
        final var expectedPage = 0;
        final var expectedPerPage = 1;
        final var expectedTotal = 0;

        Assertions.assertEquals(0, categoryRepository.count());

        final var query = new CategorySearchQuery(0,1,"", "name", "asc");
        final var actualResult = categoryGateway.findAll(query);

        Assertions.assertEquals(expectedPage, actualResult.currentPage());
        Assertions.assertEquals(expectedPerPage, actualResult.perPage());
        Assertions.assertEquals(expectedTotal, actualResult.total());

    }

    @Test
    public void givenFollowPagination_whenCallsFindAllWithPage1_shouldReturnPaginated() {
        var expectedPage = 0;
        final var expectedPerPage = 1;
        final var expectedTotal = 3;

        final var aCategoryOne = Category.newCategory("Home", null ,"home", true);
        final var aCategoryTwo = Category.newCategory("Pet", null ,"home", true);
        final var aCategoryTree = Category.newCategory("Child", null ,"home", true);

        Assertions.assertEquals(0, categoryRepository.count());

        categoryRepository.saveAll(List.of(
                CategoryJpaEntity.from(aCategoryOne),
                CategoryJpaEntity.from(aCategoryTwo),
                CategoryJpaEntity.from(aCategoryTree)
        ));

        Assertions.assertEquals(3, categoryRepository.count());

        var query = new CategorySearchQuery(0,1,"", "name", "asc");
        var actualResult = categoryGateway.findAll(query);

        Assertions.assertEquals(expectedPage, actualResult.currentPage());
        Assertions.assertEquals(expectedPerPage, actualResult.perPage());
        Assertions.assertEquals(expectedTotal, actualResult.total());

        Assertions.assertEquals(aCategoryTree.getId(), actualResult.items().get(0).getId());

        expectedPage = 1;
        query = new CategorySearchQuery(1,1,"", "name", "asc");
        actualResult = categoryGateway.findAll(query);

        Assertions.assertEquals(expectedPage, actualResult.currentPage());
        Assertions.assertEquals(expectedPerPage, actualResult.perPage());
        Assertions.assertEquals(expectedTotal, actualResult.total());

        Assertions.assertEquals(aCategoryOne.getId(), actualResult.items().get(0).getId());

        expectedPage = 2;
        query = new CategorySearchQuery(2,1,"", "name", "asc");
        actualResult = categoryGateway.findAll(query);

        Assertions.assertEquals(expectedPage, actualResult.currentPage());
        Assertions.assertEquals(expectedPerPage, actualResult.perPage());
        Assertions.assertEquals(expectedTotal, actualResult.total());

        Assertions.assertEquals(aCategoryTwo.getId(), actualResult.items().get(0).getId());
    }

    @Test
    public void givenPrePersistedCategoriesAndChildAsTerms_whenCallsFindAllAndTermsMatchCategoryName_shouldReturnPaginated() {
        final var expectedPage = 0;
        final var expectedPerPage = 1;
        final var expectedTotal = 1;

        final var aCategoryOne = Category.newCategory("Home", null ,"home", true);
        final var aCategoryTwo = Category.newCategory("Pet", null ,"home", true);
        final var aCategoryTree = Category.newCategory("Child", null ,"home", true);

        Assertions.assertEquals(0, categoryRepository.count());

        categoryRepository.saveAll(List.of(
                CategoryJpaEntity.from(aCategoryOne),
                CategoryJpaEntity.from(aCategoryTwo),
                CategoryJpaEntity.from(aCategoryTree)
        ));

        Assertions.assertEquals(3, categoryRepository.count());

        final var query = new CategorySearchQuery(0,1,"Child", "name", "asc");
        final var actualResult = categoryGateway.findAll(query);

        Assertions.assertEquals(expectedPage, actualResult.currentPage());
        Assertions.assertEquals(expectedPerPage, actualResult.perPage());
        Assertions.assertEquals(expectedTotal, actualResult.total());

        Assertions.assertEquals(aCategoryTree.getId(), actualResult.items().get(0).getId());


    }
    @Test
    public void givenPrePersistedCategoriesAndSweetAsTerms_whenCallsFindAllAndTermsMatchCategoryDescription_shouldReturnPaginated() {
        final var expectedPage = 0;
        final var expectedPerPage = 1;
        final var expectedTotal = 1;

        final var aCategoryOne = Category.newCategory("Home", "Sweet Home" ,"home", true);
        final var aCategoryTwo = Category.newCategory("Pet", null ,"home", true);
        final var aCategoryTree = Category.newCategory("Child", null ,"home", true);

        Assertions.assertEquals(0, categoryRepository.count());

        categoryRepository.saveAll(List.of(
                CategoryJpaEntity.from(aCategoryOne),
                CategoryJpaEntity.from(aCategoryTwo),
                CategoryJpaEntity.from(aCategoryTree)
        ));

        Assertions.assertEquals(3, categoryRepository.count());

        final var query = new CategorySearchQuery(0,1,"SWEET", "name", "asc");
        final var actualResult = categoryGateway.findAll(query);

        Assertions.assertEquals(expectedPage, actualResult.currentPage());
        Assertions.assertEquals(expectedPerPage, actualResult.perPage());
        Assertions.assertEquals(expectedTotal, actualResult.total());

        Assertions.assertEquals(aCategoryOne.getId(), actualResult.items().get(0).getId());


    }
}
