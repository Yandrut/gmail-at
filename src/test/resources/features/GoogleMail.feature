Feature: Test of the Google mail service

  Background: User opens Google mail service
    Given I open the Google mail service page
    And I login to the mail service as 'seleniumpilot@gmail.com' user

  Scenario: User should be logged in to the mail service
    Then User should be logged in to the mail service

  Scenario Outline: Created mail should be present in drafts page
    When I create new mail with '<address>' '<subject>' and '<body>'
    And I click on the drafts link
    And I click to the draft with '<subject>'
    Then The draft should contain the '<subject>' and '<body>'
    Examples:
      | address                    | subject                 | body                                 |
      | mykola_koltutskyi@epam.com | This is a test mail     | Just playing around                  |
      | nickolayko@yahoo.com       | Super important         | Write me back, buddy. Cheers!        |
      | seleniumpilot@gmail.com    | Please help, I\'m a bot | It is hard to get out of this server |

  Scenario Outline: Mail should be present in the sent page
    When I click on the drafts link
    And I click to the draft with '<subject>'
    And Click on the send mail button
    And Navigate to the sent page
    Then Mail with '<subject>' is present in the mails page
    Examples:
      | subject                 |
      | This is a test mail     |
      | Super important         |
      | Please help, I\'m a bot |

  Scenario: Mail should be present in the services main page
    Then Mail with subject 'Please help, I\'m a bot' should be present on the main page
