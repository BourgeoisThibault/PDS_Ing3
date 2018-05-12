@featureTest @dabapp-cucumber
Feature: dabapp-payfree

  @Scenario
  Scenario: Check card validity
	Given The REST web service at "https://ws.esibank.inside.esiag.info/" with cardnum "999999999" and pin "mypass"
    And create account with sold "100"
    And create card
	When Try to get a card checking "checkcard"
	Then Status code is OK
    And remove link Account to card
	And remove account
	And remove card

  @Scenario
  Scenario: Check card validity and amount
    Given The REST web service2 at "https://ws.esibank.inside.esiag.info/" with cardnum "9999999999" and pin "mypass" and amount "5"
	And create account with sold "100"
	And create card
	When Try to get a card and amount validity transaction "validatingtransaction"
	Then Status code is OK
	And remove link Account to card
	And remove account
	And remove card


  @Scenario
  Scenario: Check wrong pin validity
	Given The REST web service2 at "https://ws.esibank.inside.esiag.info/" with cardnum "9999999999" and pin "toto" and amount "5"
	And create account with sold "100"
	And create card
	When Try to get a card and amount validity transaction "validatingtransaction"
	Then Status code is KO
	And remove link Account to card
	And remove account
	And remove card
#
#  @Scenario
#  Scenario: Check card validity and amount>sold
#	Given The REST web service2 at "https://ws.esibank.inside.esiag.info/" with cardnum "9999999999" and pin "mypass" and amount "500"
#	And create account with sold "100"
#	And create card
#	When Try to get a card and amount validity transaction "validatingtransaction"
#	Then Status code is KO
#	And remove link Account to card
#	And remove account
#	And remove card






#
#  @Scenario
#  Scenario: Send notification to uid 999999
#	Given The REST service at "http://notification.esibank.inside.esiag.info/"
#	And Stop listener of push server at "push.esibank.inside.esiag.info" on port "9999"
#	And Set new notification with title "This is test" and message "Test for uid 999999"
#	When Try to post Notification object at "send/" for uid "999999"
#	Then Status code is OK
#
#  @Scenario
#  Scenario: Connect device with uid 999999 and good token
#	Given The push host at "push.esibank.inside.esiag.info" on port "2702"
#	And Start listener of push server at "push.esibank.inside.esiag.info" on port "9999"
#	When Try to create socket to push server
#	Then Receive notification
#	And Check if title equal "This is test"
#	And Check if message equal "Test for uid 999999"