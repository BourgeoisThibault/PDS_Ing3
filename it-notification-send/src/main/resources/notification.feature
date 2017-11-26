@featureTest @notification-cucumber
Feature: notification-token

  @Scenario
  Scenario: Receive new token
	Given The REST service at "http://notification.esibank.inside.esiag.info/"
	And Delete all token for uid "999999"
	When Try to post MobileToken object at "token/" with new set "true"
	Then Status code is OK
	And Convert response to MobileToken object
	And Receive random token with good pattern
	And Receive uid same

  @Scenario
  Scenario: Receive same token
	Given The REST service at "http://notification.esibank.inside.esiag.info/"
	When Try to post MobileToken object at "token/" with new set "false"
	Then Status code is OK
	And Save old MobileToken for check
	And Convert response to MobileToken object
	And Receive same token than previous scenario
	And Receive uid same

  @Scenario
  Scenario: Send notification to uid 999999
	Given The REST service at "http://notification.esibank.inside.esiag.info/"
	And Stop listener of push server at "push.esibank.inside.esiag.info" on port "9999"
	And Set new notification with title "This is test" and message "Test for uid 999999"
	When Try to post Notification object at "send/" for uid "999999"
	Then Status code is OK

  @Scenario
  Scenario: Connect device with uid 999999 and good token
	Given The push host at "push.esibank.inside.esiag.info" on port "2702"
	And Start listener of push server at "push.esibank.inside.esiag.info" on port "9999"
	When Try to create socket to push server
	Then Receive notification
	And Check if title equal "This is test"
	And Check if message equal "Test for uid 999999"