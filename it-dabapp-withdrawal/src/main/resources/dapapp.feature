@featureTest @dabapp-cucumber
Feature: dabapp-payfree

  @Scenario
  Scenario: Check card validity
	Given The REST web service at "https://ws.esibank.inside.esiag.info/"
    And create account with sold "100"
    And create card with cardnum "9999999999" and pin "mypass"
	When Try to get a card checking "checkcard"
	Then Status code is OK
    And remove link Account to card
	And remove account
	And remove card

  @Scenario
  Scenario: Check transaction confirmation with card and amount
    Given The REST web service2 at "https://ws.esibank.inside.esiag.info/" with amount "5"
	And create account with sold "100"
	And create card with cardnum "9999999999" and pin "mypass"
	When Try to get a card and amount confirmation transaction "validatingtransaction"
	Then Status code is OK
	And remove link Account to card
	And remove account
	And remove card

  @Scenario
  Scenario: Check transaction validation with card and amount
	Given The REST web service2 at "https://ws.esibank.inside.esiag.info/" with amount "5"
	And create account with sold "100"
	And create card with cardnum "9999999999" and pin "mypass"
	When Try to get a card and amount validation transaction "checkvaliditytransaction"
	Then Status code is OK
	And remove link Account to card
	And remove account
	And remove card

  @Scenario
  Scenario: Confirmation with wrong cardnum
	Given The REST web service2 at "https://ws.esibank.inside.esiag.info/" with amount "5"
	And create account with sold "100"
	And create card with cardnum "9999999999" and pin "mypass"
	And set cardnum at "99999999998"
	When Try to get a card and amount confirmation transaction "validatingtransaction"
	Then Status code is KO
	And set cardnum at "9999999999"
	And remove link Account to card
	And remove account
	And remove card

  @Scenario
  Scenario: Validation with wrong cardnum
	Given The REST web service2 at "https://ws.esibank.inside.esiag.info/" with amount "5"
	And create account with sold "100"
	And create card with cardnum "9999999999" and pin "mypass"
	And set cardnum at "99999999998"
	When Try to get a card and amount validation transaction "checkvaliditytransaction"
	Then Status code is KO
	And set cardnum at "9999999999"
	And remove link Account to card
	And remove account
	And remove card

  @Scenario
  Scenario: Check card validity and amount>sold
	Given The REST web service2 at "https://ws.esibank.inside.esiag.info/" with amount "500"
	And create account with sold "100"
	And create card with cardnum "9999999999" and pin "mypass"
	When Try to get a card and amount confirmation transaction "checkvaliditytransaction"
	Then Status code is unauthorized
	And remove link Account to card
	And remove account
	And remove card

