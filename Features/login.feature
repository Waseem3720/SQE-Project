Feature: Facebook Login

  Scenario: User logs in
    Given I open the Chrome browser and navigate to "https://www.facebook.com/"
    When I enter "03042021926" into the "email" field
    When I enter "samar2487" into the "pass" field
    When I click the "login" button
    Then I should be logged in successfully

    
    
    
  Scenario: User posts a status
    Given I am logged into Facebook
    When I click on "What's on your mind?"
    And I type "Hello, world!" into the post box
    And I click on "Post"
    Then my status should be posted successfully

  Scenario: User registers
    Given I am on the Facebook login page
    When I fill in "John" as my first name
    And I fill in "Doe" as my last name
    And I enter "john.doe@example.com" as my email
    And I enter "password123" as my password
    And I select "10" from the day dropdown
    And I select "April" from the month dropdown
    And I select "1990" from the year dropdown
    And I select "Male"
    Then I should be registered successfully

  Scenario: User recovers password
    Given I am on the Facebook login page
    When I enter "john.doe@example.com" into the email field
    And I click the submit button
    Then I should see a confirmation message for password recovery

  Scenario: User logs out
    Given I am logged into Facebook
    When I click the "Logout" button
    Then I should be logged out successfully

  Scenario: User dismisses notification popup
    Given I am logged into Facebook
    When a notification popup appears
    Then I click anywhere on the body to dismiss the popup
    