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
    Then the registration form should appear

  @CYS-5 @MANUAL
  Scenario: beautiful form
    Given Rama wants to enjoy the registration process
    When he asks for the registration form
    Then a beautifil registration form should be displayed
