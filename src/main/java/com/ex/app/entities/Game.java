package com.ex.app.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Games")
public class Game {

    @Id
    @Column(columnDefinition="serial primary key")
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private int gameId;

    @Column
    private String favTeam;

    @Column
    private String dogTeam;

    @Column
    private double line;

    @Column
    private boolean completed;
}
