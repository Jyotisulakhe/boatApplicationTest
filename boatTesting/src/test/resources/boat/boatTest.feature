
@tag
Feature: Automate boat website


  @tag1
  Scenario Outline: filter true wireless earbuds
  
    Given user open browser and open boat website <browser>
   
    When select True Wireless Earbuds from catagory 
    And click on filter, click on price, select minimun and maximum price
    And click on playback, select 50-75 hrs
    Then click on Apply filter, filter should apply
  
   Examples:
   |browser|
   |chrome|
   |firefox|
   
  