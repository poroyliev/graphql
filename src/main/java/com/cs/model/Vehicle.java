package com.cs.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Vehicle implements Serializable {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "type", nullable = false)
    private String type;
    @Column(name = "model_code", nullable = false)
    private String modelCode;
    @Column(name = "brand_name")
    private String brandName;
    @Column(name = "launch_date")
    private LocalDate launchDate;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "vehicle")
    private List<Tyre> tyres = new ArrayList<Tyre>();

    private transient  String formattedDate;
    public String getFormattedDate() {
        return getLaunchDate().toString();
    }
}
