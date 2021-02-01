Feature: Opdiel

  @OV-1 @OPEN
  Scenario: Pldoe
    Given kdowk
    When kdowk
    Then odklw

  @OV-1 @MANUAL @OPEN
  Scenario Outline: Fro
    Given I open the Greatest Factorial Calculator
    And enter <number> as number
    When calculation is performed
    Then I should receive: 'The factorial of <number> is: <result>'
    
    Examples:
      | number | result                 |
      | 0      | 1                      |
      | 1      | 1                      |
      | 2      | 2                      |
      | 3      | 6                      |
      | 4      | 24                     |
      | 171    | 1.241018070217667e+309 |
