# Charity E2E Tests

End-to-end browser tests for [Sauce Demo](https://www.saucedemo.com/), written
in Java with Playwright, TestNG, and Maven.

## Requirements

- JDK 21
- Maven 3.6 or later
- A graphical display for the browser (the tests launch Chromium in headed mode)

## Setup

Install the Playwright Chromium browser once:

```bash
mvn exec:java -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args="install chromium"
```

On Linux, install the browser's system dependencies too:

```bash
mvn exec:java -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args="install --with-deps chromium"
```

## Run the tests

```bash
mvn clean test
```

The suite contains three tests:

- `SuccessfulLoginTest` checks a standard user's login.
- `InvalidCredentialsTest` checks that a locked-out user cannot log in.
- `CheckoutTest` covers adding a product to the cart and completing checkout.

The tests open a visible Chromium window. On a headless Linux machine, run them
with a virtual display:

```bash
xvfb-run -a mvn clean test
```

## Project layout

```text
src/test/java/com/example/
├── pages/   # Page objects for login, products, cart, and checkout
└── tests/   # TestNG browser tests
.github/workflows/maven.yml
```
