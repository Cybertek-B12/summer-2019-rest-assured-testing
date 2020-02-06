@ws
Feature: Campus by location


  Scenario: Va campus
    Given I have a token as a student team member
    And request has the following "path" parameters
      | campus_location | VA |
    When I send a "get" request to "/api/campuses/{campus_location}"
    Then the response code should be 200
    And the response should contain "light-side"

  @campus
  Scenario: Illinois  campus
    Given I have a token as a student team member
    And request has the following "path" parameters
      | campus_location | IL |
    When I send a "get" request to "/api/campuses/{campus_location}"
    Then the response code should be 200
    And the response should contain "shgoogle"



