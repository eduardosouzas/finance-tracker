package com.expertiseIt.financial.application.category.create;

import com.expertiseIt.financial.application.UseCase;
import com.expertiseIt.financial.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class CreateCategoryUseCase extends UseCase<CreateCategoryCommand, Either<Notification, CreateCategoryOutput>> {



}
