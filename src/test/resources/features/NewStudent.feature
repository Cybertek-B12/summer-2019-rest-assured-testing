@ws
Feature: Create a new student

  Scenario: student permission
    Given I have a token as a student team member
    When I post a new student using "/api/students/student"
    Then the response code should be 403

  @post_student
    Scenario: Create and verify new student info
    Given I have a token as a teacher
    When I post a new student using "/api/students/student"
    Then the response code should be 201
    And the response should contain student name