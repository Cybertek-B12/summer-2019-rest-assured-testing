@ws
Feature: Create a new student

  @post_student
  Scenario: student permission
    Given I have a token as a student team member
    And I post a new student using "/api/students/student"
    Then the response code should be 403

