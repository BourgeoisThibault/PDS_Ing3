@featureTest
Feature: the status code of home can be get

  @Scenario
  Scenario: client makes call to GET notificationSrvHome

    @When
    When the client calls /

    @Then
    Then the client receives status code of 200