package com.cs.grapql.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
public class Tyre implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "vehicle_id")
    private Integer vehicleId;

    @Column(name = "brand")
    @Size(max = 5)
    private String brand;

    public Tyre(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }
}
