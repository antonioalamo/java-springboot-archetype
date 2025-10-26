package com.example;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;

import static com.example.ArchConditions.implementMatchingInfoInterface;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.fields;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;


@AnalyzeClasses(packages = {"com.example.."})
public class GeneralArchTest {


    @ArchTest
    @DisplayName("Avoid injection with @Autowired annotation, prefer constructor injection.")
    void noAutowired(JavaClasses classes) {

        fields()
                .should().notBeAnnotatedWith(Autowired.class)
                .because("ADR 0001: Prefer constructor injection using @RequiredArgsConstructor or explicit constructors")
                .check(classes);
    }

    @ArchTest
    @DisplayName("Controllers should implement an interface with the same name and the suffix \"Info\"")
    void controller_info(JavaClasses classes) {

        classes().that()
                 .resideInAPackage("com.example.news.controller")
                 .should(implementMatchingInfoInterface)
                 .because("ADR 0009: Controller OpenAPI annotations through interface-based \"Info\" classes")
                 .check(classes);
    }





    @ArchTest
    @DisplayName("Test classes should have @DisplayName annotation for human readable description.")
    void tests_with_display(JavaClasses classes) {

        methods().that().areAnnotatedWith(org.junit.jupiter.api.Test.class)
                 .or().areAnnotatedWith(com.tngtech.archunit.junit.ArchTest.class)
                 .should().beAnnotatedWith(org.junit.jupiter.api.DisplayName.class)
                 .because("ADR 0010: Display annotations with @DisplayName methods")
                 .check(classes);
    }



}
