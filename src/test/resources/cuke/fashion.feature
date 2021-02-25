
Feature: find a dress
  when searching the site for a dress I can find one

  Scenario Outline: Search for an item
    Given that I can navigate to "http://automationpractice.com/index.php"
    When I search for "<item>"
    Then I can find a "<item>" in the returned terms.

    Examples: 
      | item  |
      | dress |
      | shirt |
