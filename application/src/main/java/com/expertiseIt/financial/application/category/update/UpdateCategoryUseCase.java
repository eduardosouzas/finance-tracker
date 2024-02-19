package com.expertiseIt.financial.application.category.update;

import com.expertiseIt.financial.application.UseCase;
import com.expertiseIt.financial.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class UpdateCategoryUseCase extends UseCase<UpdateCategoryCommand, Either<Notification, UpdateCategoryOutput>> {
}
