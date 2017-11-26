@featureTest @notification-cucumber
Feature: notification-token

  @Scenario
  Scenario: Receive new token
	Given The REST service at "http://notification.esibank.inside.esiag.info/"
	When Try to post MobileToken object at "token/" with new set "true"
	Then Status code is OK
	And Convert response to MobileToken object
	And Receive random token with good pattern
	And Receive uid anonymous

  @Scenario
  Scenario: Receive same token
	Given The REST service at "http://notification.esibank.inside.esiag.info/"
	When Try to post MobileToken object at "token/" with new set "false"
	Then Status code is OK
	And Save old MobileToken for check
	And Convert response to MobileToken object
	And Receive same token than previous scenario
	And Receive uid is always anonymous