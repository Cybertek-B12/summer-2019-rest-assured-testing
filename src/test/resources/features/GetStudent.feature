Feature: Get student by id

  @get_single_student @ws
  Scenario: get student by id
    Given I have a token as a teacher
    And request has the following "path" parameters
      | id | 1766 |
    When I send a "get" request to "/api/students/{id}"
    Then the response should contain "1766"
    And the response should contain "Meg"
    And the response should contain "student-team-member"