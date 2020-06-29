package com.swamwithturtles.decrypto.model;

import java.util.Objects;
import java.util.UUID;

public class Player {

    private final String name;
    private final Team team;

    public Player(String name) {
        this.name = name;
        this.team = Team.UNALLOCATED;
    }

    public Player(String name, Team team) {
        this.name = name;
        this.team = team;
    }

    public String getName() {
        return name;
    }

    public Team getTeam() {
        return team;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }
}
