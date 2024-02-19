package com.expertiseIt.financial.infrastructure.models;


import com.expertiseIt.financial.JacksonTest;
import com.expertiseIt.financial.infrastructure.category.models.CategoryResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;
import java.time.Instant;

@JacksonTest
public class CategoryResponseTest {

    @Autowired
    private JacksonTester<CategoryResponse> json;

    @Test
    public void testMarshall() throws IOException {
        final var expectedId = "123";
        final var expectedName = "Casa";
        final var expectedType = "Expensive";
        final var expectedDescription = "Categoria para listar todas as dispensa relacionada a casa";
        final var expectedIsActive = true;
        final var expectedCreatedAt = Instant.now();
        final var expectedUpdateAt = Instant.now();
        final var expectedDeletedAt = Instant.now();

        final var response = new CategoryResponse(
                expectedId,
                expectedName,
                expectedDescription,
                expectedType,
                expectedIsActive,
                expectedCreatedAt,
                expectedUpdateAt,
                expectedDeletedAt
        );

        final var actualJason = this.json.write(response);

        Assertions.assertThat(actualJason)
                .hasJsonPath("$.id", expectedId)
                .hasJsonPath("$.name", expectedName)
                .hasJsonPath("$.description", expectedDescription)
                .hasJsonPath("$.type", expectedType)
                .hasJsonPath("$.is_active", expectedIsActive)
                .hasJsonPath("$.created_at", expectedCreatedAt)
                .hasJsonPath("$.updated_at", expectedUpdateAt)
                .hasJsonPath("$.deleted_at", expectedDeletedAt);


    }

    @Test
    public void testUnmarshall() throws IOException {
        final var expectedId = "123";
        final var expectedName = "Casa";
        final var expectedType = "Expensive";
        final var expectedDescription = "Categoria para listar todas as dispensa relacionada a casa";
        final var expectedIsActive = true;
        final var expectedCreatedAt = Instant.now();
        final var expectedUpdateAt = Instant.now();
        final var expectedDeletedAt = Instant.now();

        final var json = """
                {
                 "id" : "%s",
                 "name": "%s",
                 "description": "%s",
                 "type": "%s",
                 "is_active": %s,
                 "created_at": "%s",
                 "updated_at": "%s",
                 "deleted_at": "%s"
                 }
                """.formatted(expectedId, expectedName, expectedDescription, expectedType, expectedIsActive, expectedCreatedAt.toString(), expectedUpdateAt.toString(), expectedDeletedAt.toString());

        final var actualJason = this.json.parse(json);

        Assertions.assertThat(actualJason)
                .hasFieldOrPropertyWithValue("id", expectedId)
                .hasFieldOrPropertyWithValue("name", expectedName)
                .hasFieldOrPropertyWithValue("description", expectedDescription)
                .hasFieldOrPropertyWithValue("type", expectedType)
                .hasFieldOrPropertyWithValue("active", expectedIsActive)
                .hasFieldOrPropertyWithValue("createdAt", expectedCreatedAt)
                .hasFieldOrPropertyWithValue("updatedAt", expectedUpdateAt)
                .hasFieldOrPropertyWithValue("deletedAt", expectedDeletedAt);


    }
}
