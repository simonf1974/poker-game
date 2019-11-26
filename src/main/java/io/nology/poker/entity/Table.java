package io.nology.poker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import java.util.stream.Collectors;

public class Table {
    private String tableCards;
    private List<Player> players;

    public Table(String tableCards, List<Player> players) {
        this.tableCards = tableCards;
        this.players = players;
    }

    public String getTableCards() {
        return tableCards;
    }

    public void setTableCards(String tableCards) {
        this.tableCards = tableCards;
    }

    public List<Player> getPlayers() {
        return players;
    }

    @JsonIgnore
    public List<String> getPlayerCards() {
        return players.stream()
                .map(Player::getHand)
                .collect(Collectors.toList());
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    @Override
    public String toString() {
        return "Table{" +
                "tableCards='" + tableCards + '\'' +
                ", playerCards=" + players +
                '}';
    }
}
