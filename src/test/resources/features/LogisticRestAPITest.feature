@api @smoke @regression @MB-200
Feature: Validating Logistics Rest API calls
  Scenario: Validating Get Company API call
    Given user creates company with post call
    When user sends get company api call
    Then user validates 200 status code

    Scenario: Valdating Create Company API call and persisted in DataBase
      Given user creates company with post call
      When user connects to database
      Then user validates created company is persisted in DataBase