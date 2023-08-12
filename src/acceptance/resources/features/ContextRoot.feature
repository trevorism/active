Feature: Context Root of this API
  In order to use the API, it must be available

  Scenario: HTTP GET on the ContextRoot
    Given the application is alive
    When I navigate to "https://active.project.trevorism.com/api"
    Then then a link to the help page is displayed

  Scenario: Ping https
    Given the application is alive
    When I navigate to /ping on "https://active.project.trevorism.com/api"
    Then pong is returned, to indicate the service is alive