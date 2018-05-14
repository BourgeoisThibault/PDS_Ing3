@featureTest @Sentiments
Feature: Sentiments Analysis Automated Test

  @Scenario
  Scenario: Sentiments analysis test
    Given Here is "TA.csv" file with the following lines
      | Col    | Line                                             |
      | Header | id,date,note,commentaire                         |
      | First  | 1,01/01/2018,5,Esibank est une formidable banque |
    Then I get the structType
    When I have the csv structType I load the csv
    When I load the csv I create the tableview
    Then I format the Date
    When It s formatted I do treatment and insert in uat database
      | url      | jdbc:mysql://localhost:8889/esibank     |
      | user     | root                                    |
      | password | root                                    |