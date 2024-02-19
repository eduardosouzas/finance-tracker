package com.expertiseIt.financial.application.category.retrieve.list;

import com.expertiseIt.financial.IntegrationTest;
import com.expertiseIt.financial.application.category.retrive.list.ListCategoryUseCase;
import com.expertiseIt.financial.domain.category.Category;
import com.expertiseIt.financial.domain.category.CategorySearchQuery;
import com.expertiseIt.financial.infrastructure.category.persistence.CategoryJpaEntity;
import com.expertiseIt.financial.infrastructure.category.persistence.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Stream;

@IntegrationTest
public class ListCategoryUseCaseIT {

    @Autowired
    private ListCategoryUseCase useCase;

    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    void mockUp() {
       final var categories = Stream.of(
                Category.newCategory("Compras", "Novas compras", "Casa", true),
                Category.newCategory("Salario", "Salario mensal", "Casa", true),
                Category.newCategory("Amazon", "Amazon movies", "Casa", true),
                Category.newCategory("Sports", "Sports", "Casa", true),
                Category.newCategory("Kids", "Kids", "Casa", true),
                Category.newCategory("Series", "Series", "Casa", true)
        ).map(CategoryJpaEntity::from).toList();

       categoryRepository.saveAllAndFlush(categories);
    }

    @Test
    public void givenAValidTerm_whenTermDoestMatchsPrePersisted_shouldReturnEmptyPage() {
        final var expectedPage = 0;
        final var expectedPerPage = 10;
        final var expectedTerms = "reiowyrwoyrweoiyrw";
        final var expectedSort = "name";
        final var expectedDirection = "asc";
        final var expectedItemsCount = 0;
        final var expectedTotal = 0;
        final var aQuery =  new CategorySearchQuery(expectedPage, expectedPerPage, expectedTerms, expectedSort, expectedDirection);

        final var actualResult = useCase.execute(aQuery);

        Assertions.assertEquals(expectedItemsCount, actualResult.items().size());
        Assertions.assertEquals(expectedPage, actualResult.currentPage());
        Assertions.assertEquals(expectedPerPage, actualResult.perPage());
        Assertions.assertEquals(expectedTotal, actualResult.total());
    }

    @ParameterizedTest
    @CsvSource({
        "Ser, 0, 10, 1, 1,Series",
        "Sal, 0 , 10, 1, 1 , Salario mensal",
        "Ama, 0 , 10, 1, 1 , Amazon movies",
        "Spo, 0 , 10, 1, 1 , Sports",
        "KI, 0 , 10, 1, 1 , Kids",
    })
    public void givenAValidTerm_whenCallsListCategories_shouldReturnCategoriesFiltered(
            final String expectedTerms,
            final int expectedPage,
            final int expectedPerPage,
            final int expectedItemsCount,
            final long expectedTotal,
            final String expectedCategoryName
    ) {
        final var expectedSort = "name";
        final var expectedDirection = "asc";

        final var aQuery =  new CategorySearchQuery(expectedPage, expectedPerPage, expectedTerms, expectedSort, expectedDirection);

        final var actualResult = useCase.execute(aQuery);

        Assertions.assertEquals(expectedItemsCount, actualResult.items().size());
        Assertions.assertEquals(expectedPage, actualResult.currentPage());
        Assertions.assertEquals(expectedPerPage, actualResult.perPage());
        Assertions.assertEquals(expectedTotal, actualResult.total());
        Assertions.assertEquals(expectedCategoryName, actualResult.items().get(0).description());

    }
}
