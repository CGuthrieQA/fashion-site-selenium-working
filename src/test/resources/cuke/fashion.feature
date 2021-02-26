Feature: fashion site tests

  Scenario Outline: Search for an item
    Given that I can navigate to "http://automationpractice.com/index.php"
    When I search for "<item>"
    Then I can find a "<item>" in the returned terms.

    Examples: 
      | item  |
      | dress |
      | shirt |
      
   Scenario Outline: create an account
    Given that I can navigate to "http://automationpractice.com/index.php"
    When I click the sign-in button
    And I enter a new email address "<email>"
    And I click the register account button
    And I enter new credentials:
    | firstname | <firstname> |
    | lastname | <lastname> |
    | password | <password> |
    | gender | <gender> |
    | address | <address> |
    | city | <city> |
    | state | <state> |
    | postcode | <postcode> |
    | country | <country> |
    | mobile | <mobile> |
    | alias | <alias> |
    And I submit the form
    Then I can see the "My account - My Store" page

    Examples: 
      | email | firstname | lastname | password | gender | address | city | state | postcode | country | mobile |alias |
      | foot@bart.com | foo | bar | foobar123 | m | 123 main street | cityville | california | 12345 | united states of america | 000 111 2222 | bar@foo.com |
