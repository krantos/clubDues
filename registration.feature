Feature: Registration

  @CYS-1
  Scenario: User opens registration page
    Given Rama is on the Google email home page
    When he ask for the registration page
    Then Google registration page should open successfully

  @CYS-4
  Scenario: User reach the registration form
    Given Rama is on the registration page
    When he ask for the registration form
    Then the form should appear
