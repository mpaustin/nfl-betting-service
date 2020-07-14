package com.ex.app.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Stat {
    @Id
    private int id;
    private String lastName;
    private String firstName;
    private String position;
    private String team;
    private float statValue;
}