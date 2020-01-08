package com.cs.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
public class Tyre implements Serializable {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "vehicle_id",insertable = false, updatable= false)
    private Long vehicleId;

    @ManyToOne
    @JoinColumn(name="vehicle_id")
    private Vehicle vehicle;

    public Tyre(Integer vehicleId) {
        this.vehicleId = vehicleId.longValue();
    }
}
