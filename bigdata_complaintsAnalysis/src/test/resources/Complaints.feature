@featureTest @Complaints
Feature: consumer Complaints Automated Test

  @Scenario
  Scenario: Complaints analysis test
    Given Here is "TA.csv" file with the following lines
      | Col    | Line                                                                                                                                                                                                                                                                       |
      | Header | Date_received,Product,Sub_product,Issue,Sub_issue,Consumer_complaint_narrative,Company_public_response,Company,State,ZIP_code,Tags,Consumer_consent_provided,Submitted_via,Date_sent_to_company,Company_response_to_consumer,Timely_response,Consumer_disputed,ComplaintID |
      | First  | 03/12/2014,Mortgage,Other mortgage,Loan modification collection foreclosure,,,,M&T BANK CORPORATION,MI,48382,,N/A,Referral,03/17/2014,Closed with explanation,Yes,No,759217                                                                                                |
    Then I get the structType
    When I have the csv structType I load the csv
    When I load the csv I create the tableview
    Then I format the Date
    When It s formatted I do treatment and insert in uat database
      | url      | jdbc:mysql://localhost:8889/pds_esibank |
      | user     | root                                    |
      | password | root                                    |