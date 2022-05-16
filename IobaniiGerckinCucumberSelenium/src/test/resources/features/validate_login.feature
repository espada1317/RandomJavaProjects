Feature: Want to test login feature with different data

  Scenario: I want to login with valid email and password
    Given I go to website
    When I select menu "Contul meu"
    And I login with password
    And Click "Login" button
    Then I should be logged on website with account

  Scenario: I want to login with invalid password
    Given I go to website
    When I select menu "Contul meu"
    And I log out with menu " Ieșire"
    And I select menu "Contul meu"
    And I login with bad password
    And Click "Login" button
    Then Should be visible text "Numele de utilizator sau parola incorectă"