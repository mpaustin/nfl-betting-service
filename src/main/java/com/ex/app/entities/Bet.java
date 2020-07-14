package com.ex.app.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import static javax.persistence.CascadeType.ALL;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Bets")
public class Bet {

    @Id
    @Column(columnDefinition="serial primary key")
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private int betId;

    @ManyToOne(cascade = ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "betUser", referencedColumnName = "username", columnDefinition = "STRING")
    private User betUser;

    @Column
    private String gameId;

    @Column
    private String favTeam;

    @Column
    private String dogTeam;

    @Column
    private String betTeam;

    @Column
    private float line;

    @Column
    private float betAmount;

    @Column
    private String gameDateTime;

    @Column
    private boolean completed;

}
