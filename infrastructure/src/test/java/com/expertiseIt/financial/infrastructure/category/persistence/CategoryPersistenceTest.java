package com.expertiseIt.financial.infrastructure.category.persistence;

import com.expertiseIt.financial.domain.category.Category;
import com.expertiseIt.financial.MySQLGatewayTest;
import org.hibernate.PropertyValueException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

@MySQLGatewayTest
public class CategoryPersistenceTest {

    @Autowired
    private CategoryRepository categoryRepository;


    @Test
    public void givenAnInvalidNullName_whenCallsSave_shouldREturnsError() {
        final var expectedMessage = "not-null property references a null or transient value : com.expertiseIt.financial.infrastructure.category.persistence.CategoryJpaEntity.name";
        final var expectedPropertyName = "name";
        final var expectedName = "Casa";
        final var expectedType = "Expensive";
        final var expectedDescription = "Categoria para listar todas as dispensa relacionada a casa";
        final var expectedIsActive = true;
        final var aCategory = Category.newCategory(expectedName, expectedDescription,expectedType, expectedIsActive);

        final var anEntity = CategoryJpaEntity.from(aCategory);
        anEntity.setName(null);

        final var actualException = Assertions.assertThrows(DataIntegrityViolationException.class, () -> categoryRepository.save(anEntity));

        final var actualCause = Assertions.assertInstanceOf(PropertyValueException.class, actualException.getCause());

        Assertions.assertEquals(expectedPropertyName, actualCause.getPropertyName());
        Assertions.assertEquals(expectedMessage, actualCause.getMessage());
    }


    @Test
    public void givenAnInvalidNullCreatedAt_whenCallsSave_shouldREturnsError() {
        final var expectedMessage = "not-null property references a null or transient value : com.expertiseIt.financial.infrastructure.category.persistence.CategoryJpaEntity.createdAt";
        final var expectedPropertyName = "createdAt";
        final var expectedName = "Casa";
        final var expectedType = "Expensive";
        final var expectedDescription = "Categoria para listar todas as dispensa relacionada a casa";
        final var expectedIsActive = true;
        final var aCategory = Category.newCategory(expectedName, expectedDescription,expectedType, expectedIsActive);

        final var anEntity = CategoryJpaEntity.from(aCategory);
        anEntity.setCreatedAt(null);

        final var actualException = Assertions.assertThrows(DataIntegrityViolationException.class, () -> categoryRepository.save(anEntity));

        final var actualCause = Assertions.assertInstanceOf(PropertyValueException.class, actualException.getCause());

        Assertions.assertEquals(expectedPropertyName, actualCause.getPropertyName());
        Assertions.assertEquals(expectedMessage, actualCause.getMessage());
    }

    @Test
    public void givenAnInvalidNullType_whenCallsSave_shouldREturnsError() {
        final var expectedMessage = "not-null property references a null or transient value : com.expertiseIt.financial.infrastructure.category.persistence.CategoryJpaEntity.type";
        final var expectedPropertyName = "type";
        final var expectedName = "Casa";
        final var expectedType = "Expensive";
        final var expectedDescription = "Categoria para listar todas as dispensa relacionada a casa";
        final var expectedIsActive = true;
        final var aCategory = Category.newCategory(expectedName, expectedDescription,expectedType, expectedIsActive);

        final var anEntity = CategoryJpaEntity.from(aCategory);
        anEntity.setType(null);

        final var actualException = Assertions.assertThrows(DataIntegrityViolationException.class, () -> categoryRepository.save(anEntity));

        final var actualCause = Assertions.assertInstanceOf(PropertyValueException.class, actualException.getCause());

        Assertions.assertEquals(expectedPropertyName, actualCause.getPropertyName());
        Assertions.assertEquals(expectedMessage, actualCause.getMessage());
    }
    @Test
    public void givenAnInvalidNullUpdatedAt_whenCallsSave_shouldREturnsError() {
        final var expectedMessage = "not-null property references a null or transient value : com.expertiseIt.financial.infrastructure.category.persistence.CategoryJpaEntity.updatedAt";
        final var expectedPropertyName = "updatedAt";
        final var expectedName = "Casa";
        final var expectedType = "Expensive";
        final var expectedDescription = "Categoria para listar todas as dispensa relacionada a casa";
        final var expectedIsActive = true;
        final var aCategory = Category.newCategory(expectedName, expectedDescription,expectedType, expectedIsActive);

        final var anEntity = CategoryJpaEntity.from(aCategory);
        anEntity.setUpdatedAt(null);

        final var actualException = Assertions.assertThrows(DataIntegrityViolationException.class, () -> categoryRepository.save(anEntity));

        final var actualCause = Assertions.assertInstanceOf(PropertyValueException.class, actualException.getCause());

        Assertions.assertEquals(expectedPropertyName, actualCause.getPropertyName());
        Assertions.assertEquals(expectedMessage, actualCause.getMessage());
    }
}
