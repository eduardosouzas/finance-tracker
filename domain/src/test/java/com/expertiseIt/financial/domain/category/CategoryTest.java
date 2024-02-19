package com.expertiseIt.financial.domain.category;


import com.expertiseIt.financial.domain.UnitTest;
import com.expertiseIt.financial.domain.exceptions.DomainException;
import com.expertiseIt.financial.domain.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class CategoryTest extends UnitTest  {
    @Test
    public void givenAValidParams_whenCallNewCategory_thenInstantiateACategory() {

        final var expectedName = "Casa";
        final var expectedType = "Expensive";
        final var expectedDescription = "Categoria para listar todas as dispensa relacionada a casa";
        final var expectedIsActive = true;
        final var actualCategory = Category.newCategory(expectedName, expectedDescription,expectedType, expectedIsActive);
        Assertions.assertNotNull(actualCategory);
        Assertions.assertNotNull(actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertEquals(expectedType, actualCategory.getType());
        Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertNotNull(actualCategory.getUpdatedAt());
        Assertions.assertNull(actualCategory.getDeletedAt());

    }
    @Test
    public void givenAnInvalidNullName_whenCallNewCategoryAndValidate_thenShouldReceiveError() {

        final String expectedName = null;
        final var expectedErrorMessage = "'name' should not be null";
        final var expectedErrorCount = 1;
        final var expectedType = "Expensive";
        final var expectedDescription = "Categoria para listar todas as dispensa relacionada a casa";
        final var expectedIsActive = true;
        final var actualCategory = Category.newCategory(expectedName, expectedDescription,expectedType, expectedIsActive);

        final var actualExpection = Assertions.assertThrows(DomainException.class, () -> actualCategory.validate(new ThrowsValidationHandler()));
        Assertions.assertEquals(expectedErrorMessage, actualExpection.getErrors().get(0).message());
        Assertions.assertEquals(expectedErrorCount, actualExpection.getErrors().size());
    }

    @Test
    public void givenAnInvalidEmptyName_whenCallNewCategoryAndValidate_thenShouldReceiveError() {

        final String expectedName = " ";
        final var expectedErrorMessage = "'name' should not be empty";
        final var expectedErrorCount = 1;
        final var expectedType = "Expensive";
        final var expectedDescription = "Categoria para listar todas as dispensa relacionada a casa";
        final var expectedIsActive = true;
        final var actualCategory = Category.newCategory(expectedName, expectedDescription,expectedType, expectedIsActive);

        final var actualExpection = Assertions.assertThrows(DomainException.class, () -> actualCategory.validate(new ThrowsValidationHandler()));
        Assertions.assertEquals(expectedErrorMessage, actualExpection.getErrors().get(0).message());
        Assertions.assertEquals(expectedErrorCount, actualExpection.getErrors().size());
    }
    @Test
    public void givenAnInvalidNameLengthLessThan3_whenCallNewCategoryAndValidate_thenShouldReceiveError() {

        final String expectedName = "Ca ";
        final var expectedErrorMessage = "'name' must be betwenn 3 and 255 caracters";
        final var expectedErrorCount = 1;
        final var expectedType = "Expensive";
        final var expectedDescription = "Categoria para listar todas as dispensa relacionada a casa";
        final var expectedIsActive = true;
        final var actualCategory = Category.newCategory(expectedName, expectedDescription,expectedType, expectedIsActive);

        final var actualExpection = Assertions.assertThrows(DomainException.class, () -> actualCategory.validate(new ThrowsValidationHandler()));
        Assertions.assertEquals(expectedErrorMessage, actualExpection.getErrors().get(0).message());
        Assertions.assertEquals(expectedErrorCount, actualExpection.getErrors().size());
    }

    @Test
    public void givenAnInvalidNameLengthMoreThan255_whenCallNewCategoryAndValidate_thenShouldReceiveError() {

        final String expectedName = """
                No entanto, não podemos esquecer que o fenômeno da Internet agrega valor ao estabelecimento do sistema de formação de quadros que corresponde às necessidades. Neste sentido, a adoção de políticas descentralizadoras 
                pode nos levar a considerar a reestruturação dos níveis de motivação departamental. Por conseguinte, a complexidade dos estudos efetuados maximiza as possibilidades por conta de todos os recursos funcionais envolvidos.
                 O incentivo ao avanço tecnológico, assim como a competitividade nas transações comerciais assume importantes posições no estabelecimento das diversas correntes de pensamento.
                """;
        final var expectedErrorMessage = "'name' must be betwenn 3 and 255 caracters";
        final var expectedErrorCount = 1;
        final var expectedType = "Expensive";
        final var expectedDescription = "Categoria para listar todas as dispensa relacionada a casa";
        final var expectedIsActive = true;
        final var actualCategory = Category.newCategory(expectedName, expectedDescription,expectedType, expectedIsActive);

        final var actualExpection = Assertions.assertThrows(DomainException.class, () -> actualCategory.validate(new ThrowsValidationHandler()));
        Assertions.assertEquals(expectedErrorMessage, actualExpection.getErrors().get(0).message());
        Assertions.assertEquals(expectedErrorCount, actualExpection.getErrors().size());
    }

    @Test
    public void givenAValidEmptyDescription_whenCallNewCategoryAndValidate_thenShouldReceiveError() {

        final String expectedName = "Casa";
        final var expectedType = "Expensive";
        final var expectedDescription = " ";
        final var expectedIsActive = true;
        final var actualCategory = Category.newCategory(expectedName, expectedDescription,expectedType, expectedIsActive);

        Assertions.assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertNotNull(actualCategory);
        Assertions.assertNotNull(actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertEquals(expectedType, actualCategory.getType());
        Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertNotNull(actualCategory.getUpdatedAt());
        Assertions.assertNull(actualCategory.getDeletedAt());
    }

    @Test
    public void givenAValidFalseIsActive_whenCallNewCategoryAndValidate_thenShouldReceiveError() {

        final String expectedName = "Casa";
        final var expectedType = "Expensive";
        final var expectedDescription = "Categoria para listar todas as dispensa relacionada a casa";
        final var expectedIsActive = false;
        final var actualCategory = Category.newCategory(expectedName, expectedDescription,expectedType, expectedIsActive);

        Assertions.assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertNotNull(actualCategory);
        Assertions.assertNotNull(actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertEquals(expectedType, actualCategory.getType());
        Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertNotNull(actualCategory.getUpdatedAt());
        Assertions.assertNotNull(actualCategory.getDeletedAt());
    }

    @Test
    public void givenAValidActiveCategory_whenCallDeactivate_thenReturnCategoryInactivated() {
        final String expectedName = "Casa";
        final var expectedType = "Expensive";
        final var expectedDescription = "Categoria para listar todas as dispensa relacionada a casa";
        final var expectedIsActive = false;
        final var aCategory = Category.newCategory(expectedName, expectedDescription,expectedType, true);

        Assertions.assertDoesNotThrow(() -> aCategory.validate(new ThrowsValidationHandler()));

        final var updatedAt = aCategory.getUpdatedAt();

        Assertions.assertTrue(aCategory.isActive());
        Assertions.assertNull(aCategory.getDeletedAt());

        final var actualCategory = aCategory.deactivate();

        Assertions.assertEquals(aCategory.getId(), actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertEquals(expectedType, actualCategory.getType());
        Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNotNull(actualCategory.getDeletedAt());

    }

    @Test
    public void givenAValidInactiveCategory_whenCallActivate_thenReturnCategoryActivated() {
        final String expectedName = "Casa";
        final var expectedType = "Expensive";
        final var expectedDescription = "Categoria para listar todas as dispensa relacionada a casa";
        final var expectedIsActive = true;
        final var aCategory = Category.newCategory(expectedName, expectedDescription,expectedType, false);

        Assertions.assertDoesNotThrow(() -> aCategory.validate(new ThrowsValidationHandler()));

        final var updatedAt = aCategory.getUpdatedAt();

        Assertions.assertFalse(aCategory.isActive());
        Assertions.assertNotNull(aCategory.getDeletedAt());

        final var actualCategory = aCategory.activate();

        Assertions.assertEquals(aCategory.getId(), actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertEquals(expectedType, actualCategory.getType());
        Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNull(actualCategory.getDeletedAt());

    }
    @Test
    public void givenAValidCategory_whenCallUpdate_thenReturnCategoryUpdated() {
        final String expectedName = "Casa";
        final var expectedType = "Expensive";
        final var expectedDescription = "Categoria para listar todas as dispensa relacionada a casa";
        final var expectedIsActive = true;
        final var aCategory = Category.newCategory("Home", "Home sweet home",expectedType, false);

        Assertions.assertDoesNotThrow(() -> aCategory.validate(new ThrowsValidationHandler()));

        final var updatedAt = aCategory.getUpdatedAt();

        final var actualCategory = aCategory.update(expectedName, expectedDescription, expectedType, expectedIsActive);

        Assertions.assertDoesNotThrow(() -> aCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(aCategory.getId(), actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertEquals(expectedType, actualCategory.getType());
        Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNull(actualCategory.getDeletedAt());
    }
    @Test
    public void givenAValidCategory_whenCallUpdateWithInvalidParams_thenReturnCategoryUpdated() {
        final String expectedName = null;
        final var expectedType = "Expensive";
        final var expectedDescription = "Categoria para listar todas as dispensa relacionada a casa";
        final var expectedIsActive = true;
        final var aCategory = Category.newCategory("Home", "Home sweet home",expectedType, false);

        Assertions.assertDoesNotThrow(() -> aCategory.validate(new ThrowsValidationHandler()));

        final var createdAt = aCategory.getCreatedAt();
        final var updatedAt = aCategory.getUpdatedAt();

        final var actualCategory = aCategory.update(expectedName, expectedDescription, expectedType, expectedIsActive);

        Assertions.assertEquals(aCategory.getId(), actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertEquals(expectedType, actualCategory.getType());
        Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
        Assertions.assertEquals(createdAt, actualCategory.getCreatedAt());
        Assertions.assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNull(actualCategory.getDeletedAt());
    }

}
