# NBA Player Trading Simulator

## Introduction

I am going to design an application called **NBA Player Trading Simulator**. 
This application can simulate possible trades of NBA players among different teams, 
display the changes of the teams due to trades, 
and calculate the luxury tax if the salary is above the tax line.

The administrators of each NBA team can use this application to assess their intended trades. 
In addition, NBA medias or fans who are interested in trades can use this application to simulate their desired trades.

As an NBA fan for several years, 
I always pay great attention to the trades among NBA teams. 
However, I am not able to gather sufficient information about the trades just from the news. 
Therefore, I want to design an application that allows me to view the trades from the manager's perspective.

## User Stories
- As a user, I want to be able to add a trade to the pending trade list and completed trade list.
- As a user, I want to be able to view all players in teams that conduct trades.
- As a user, I want to be able to add a player to a trade.
- As a user, I want to be able to judge whether the team's total salary is above the tax line.
- As a user, I want to be able to select a player in teams that conduct trades and view this player in detail.
- As a user, I want to be able to conduct trades by exchanging players between two teams.
- As a user, I want to be able to view all completed trades and the salary situation of each team in these trades.
- As a user, I want to be able to save all the data in the trade list when I quit the app.
- As a user, I want to be able to read all the data in the files when I start the app.
- As a user, I want to be able to add multiple Trades to a TradeList.
- As a user, I wanted to be prompted with the option to load data from file when the application starts and prompted 
  with the option to save data to file when the application ends.

## Instructions for Grader
- You can generate the first required action related to the user story "adding multiple Trades to a TradeList" by
  clicking the button labelled "Add a trade" and then enter team names and player names to form the trade. After that, 
  click the button labelled "Finished adding players" to add the Trade to the TradeList.
- You can generate the second required action related to the user story "adding multiple Trades to a TradeList" by 
  clicking the button labelled "Yes" when the message window asks the user "Do you want to conduct these trades?" to
  remove all Trades from the TradeList.
- You can locate my visual component by looking at code: JLabel image = new JLabel(new ImageIcon("data/NBA_logo.png"));
  panel.add(image); where I add the image to the panel. The image that represents an NBA logo which comes from 
  https://www.nba.com/news/first-look-nbas-refreshed-logo-2017-18-seasonis is in the data folder.
- You can save the state of my application by clicking the button labelled "Yes" when prompted with a message window "Do
  you want to save the current progress?" when you quit the application.
- You can reload the state of my application by clicking the button labelled "Yes" when prompted with a message window 
  "Do you want to reload the recent progress?" when you start the application.