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


  Scenario: verify new student info using get student endpoint
    Given I have a token as a teacher
    And I post a new student using "/api/students/student"
    And I get the student id from db
    And I have a token as a teacher
    And request has the following "path" parameters
      | id | db |
    When I send a "get" request to "/api/students/{id}"
    Then the response code should be 200
    And the response should contain student name


