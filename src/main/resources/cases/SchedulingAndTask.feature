Feature: Task Assignment and Notifications for Kitchen Staff

  As a kitchen manager,
  I want to assign tasks to chefs based on their workload and expertise
  So that I can ensure balanced workloads and efficiency

  As a chef,
  I want to receive notifications about my assigned cooking tasks
  So that I can prepare meals on time

  Scenario: Assign a cooking task to a chef based on expertise and availability
    Given the kitchen manager is logged into the task management system
    And the system has the following chefs and their workload:
      | Name   | Expertise        | Current Tasks |
      | Ahmed  | Italian Cuisine  | 2             |
      | Lina   | Pastries         | 1             |
      | Omar   | Grilling         | 3             |
    When the manager assigns a new task "Prepare Lasagna"
    Then the system should assign the task to chef "Ahmed"
    And the system should notify chef "Ahmed" about the new task

  Scenario: Notify chef upon task assignment
    Given chef "Lina" has been assigned the task "Bake Croissants"
    When the system sends the notification
    Then chef "Lina" should receive a message: "You have been assigned: Bake Croissants"