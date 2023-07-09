Feature: Login with Valid Credentials

@sanity @regression
  Scenario: Successful Login with Valid Credentials
    When User navigate to MyAccount menu
    And click on Login
    And User enters Email as "admin@gmail.com" and Password as "Kiran$178"
    And Click on Login button
    Then User navigates to MyAccount Page

    
 @regression
  Scenario: Successful Login with Valid Credentials
    When User navigate to MyAccount menu
    And click on Login
    And User enters Email as "admin@gmail.com" and Password as "Kiran$178"
    And Click on Login button
    Then User navigates to MyAccount Page
    