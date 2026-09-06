Feature: Context Root of this API
  In order to use the API, it must be available

  Scenario: HTTP GET on the ContextRoot
    Given the application is alive
    When I navigate to the context root
    Then then a link to the help page is displayed

  Scenario: Ping https
    Given the application is alive
    When I navigate to the ping endpoint
    Then pong is returned, to indicate the service is alive
