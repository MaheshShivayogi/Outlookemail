Feature: Outlook email

Scenario: Sucessful login with valid credentials

Given User alreday present in the home page
When User clicks on Sign in button and lands on login page
Then User enters username and click on Next button
And enters password and clicks on Sign in button 
Then User lands on outlook homepage and logs out from the session