package be.howest.adria.infrastructure.webapi.controllers.util;

import be.howest.adria.application.contracts.usecases.UseCase;
import be.howest.adria.infrastructure.shared.contracts.Controller;
import be.howest.adria.infrastructure.webapi.shared.Request;

public abstract class ControllerWithUseCase<I> implements Controller<Request> { //I is short for input type but InputType doesn't comply with sonar

    private final UseCase<I> useCase;

    protected ControllerWithUseCase(UseCase<I> uc) {
        this.useCase = uc;
    }

    @Override
    public void handle(Request request) {
        useCase.execute(convertToUseCaseInput(request));
    }

    protected abstract I convertToUseCaseInput(Request request);

}