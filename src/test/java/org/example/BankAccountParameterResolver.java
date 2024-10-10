package org.example;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

// The class implements ParameterResolver from JUnit 5, which provides dependency injection into test methods.
public class BankAccountParameterResolver implements ParameterResolver {

    // This method checks if this resolver can handle the parameter in question.
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        // Here, we check if the type of the parameter is BankAccount.
        // 'parameterContext.getParameter().getType()' retrieves the type of the parameter from the test method.
        // If it matches the BankAccount class, it returns true.
        // This means the resolver can handle and resolve this parameter.
        return parameterContext.getParameter().getType() == BankAccount.class;
    }

    // This method creates and returns the actual object that will be injected into the test method.
    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        // If supportsParameter returned true, this method will be called.
        // It returns a new BankAccount instance with an initial balance of 0 and interest rate of 0.
        // This object will be passed to the test method.
        return new BankAccount(0, 0);
    }
}