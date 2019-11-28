# Poker Scorer (poker-game)

## Functionality

A web service that provides two APIs for scoring poker hands. One API simply returns a score for a given poker hand. The other API allows the client to provide details a whole table - including table cards and multiple player hands. The table cards are combined with each player's cards to work out the best 5 card hand for each player and then the API returns the score and rank of each player.

## Learning

I built this project based on a high level remit to write a set of Java classes to score poker hands. It was our first Java project and was substantially built in one day. I subsequently extended it to include the scoring of a whole table including working out the best five card hand from a set of seven (or more) cards. I also added Spring Boot to expose the functionality of the classes via a web service API.

## Screenshot

![Screenshot](poker.png)
