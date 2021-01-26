Feature: Registration

  @CYS-1
  Scenario: User opens registration page
    Given Rama is on the Google email home page
    When he ask for the registration form
    Then Google registration page should be displayed successfully
