# SauceDemo Selenium Project

Projet d'automatisation de tests Selenium basé sur le sujet "TP 4SEL".

## Prérequis
- Java 21
- Maven
- Chrome install�

## Exécuter les tests

```bash
mvn clean test
```

## Exécuter en headless

```bash
set HEADLESS=true
mvn clean test
```

## Allure Report (local)

```bash
mvn clean test
mvn allure:serve
```

## Structure
- `src/main/java/com/saucedemo/pages` : pages (POM)
- `src/test/java/com/saucedemo/tests` : tests JUnit 5 + Allure
- `.github/workflows/tests.yml` : CI GitHub Actions
