package com.reto.torrent;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.reto.torrent");

        noClasses()
            .that()
                .resideInAnyPackage("com.reto.torrent.service..")
            .or()
                .resideInAnyPackage("com.reto.torrent.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..com.reto.torrent.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
