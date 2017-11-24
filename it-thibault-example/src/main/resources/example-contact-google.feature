@featureTest @thibault-example-cucumber
Feature: example-contact-google

  @Scenario
  Scenario: I want get google page

	Given The REST service at "http://notification.esibank.inside.esiag.info/"
    When Try to get page
	Then Status code is 200
    And Body value is not null
