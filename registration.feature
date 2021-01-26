Feature: Registration

  @CYS-1
  Scenario: User opens registration page
    Given Rama is on the Google email home page
    When he ask for the registration page
    Then Google registration page should open successfully

  @CYS-1
  Scenario: User provides all registration information
    Given Rama is on the Google registration page
    When he enters all required information on registration form
    Then he should be able to populate all information in registration form
