@featureTest @thibault-example-cucumber
Feature: example-contact-google

  @Scenario
  Scenario: I want get google page

    When try to get page at "http://www.google.fr/"
    Then body value is not null