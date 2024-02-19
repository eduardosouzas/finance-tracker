package com.expertiseIt.financial.application.category.create;

import com.expertiseIt.financial.domain.category.Category;
import com.expertiseIt.financial.domain.category.CategoryGateway;
import com.expertiseIt.financial.domain.validation.handler.Notification;
import io.vavr.API;
import io.vavr.control.Either;


public class DefaultCreateCategoryUseCase extends CreateCategoryUseCase {
    private final CategoryGateway categoryGateway;
    public DefaultCreateCategoryUseCase(CategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    @Override
    public Either<Notification, CreateCategoryOutput> execute(final CreateCategoryCommand aCommand) {
        final var aCategory = Category.newCategory(aCommand.name(), aCommand.description(), aCommand.type(), aCommand.isActive());
        final var notification = Notification.create();
        aCategory.validate(notification);
        return notification.hasError() ? Either.left(notification) : create(aCategory);
    }

    private Either<Notification, CreateCategoryOutput> create(Category aCategory) {
        return API.Try(() -> this.categoryGateway.create(aCategory))
                .toEither()
                .bimap(Notification::create, CreateCategoryOutput::from);
    }
}
