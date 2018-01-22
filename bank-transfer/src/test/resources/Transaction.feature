@featureTest @Transaction
Feature: Transaction Automated Test

  @Scenario
  Scenario: Do an Xml document with transactions
    Given A transaction list
      | idTransaction     |          1 |          2 |
      | lastNameCrediter  | Martin     | Dubois     |
      | firstNameCrediter | Jules      | Emma       |
      | creditAccount     |         35 |         50 |
      | impactedBank      | BNP        | SG         |
      | amountTransaction | 1000.50    |       2000 |
      | lastNameCustomer  | Richard    | Durand     |
      | firstNameCustomer | Louise     | Batiste    |
      | debitAccount      | 15ABCD     | 16EFGH     |
      | dateTransaction   | 2018-01-20 | 2018-01-20 |
    When I receive the transactions from the database
    Then Making an XML document


  Scenario Outline: Transaction recovery from Xml document
    Given A Xml document "<xml>"
    When I receive the document I extract the transactions
    Then I insert them into the database

    Examples:
      | xml                                                                                                                                                                                                                                                                                                                                                                                                                                                                                |
      | <Transaction><id_transaction id='1'><lastname_crediter>Dupont</lastname_crediter><firstname_crediter>Marie</firstname_crediter><credit_account>012ACX</credit_account><impacted_bank>Societe Generale</impacted_bank><amount_transaction>150.6</amount_transaction><lastname_customer>Lepont</lastname_customer><firstname_customer>Nicolas</firstname_customer><debit_account>1ABCD</debit_account><date_transaction>2018-01-20</date_transaction></id_transaction></Transaction> |
