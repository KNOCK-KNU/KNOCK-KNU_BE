package com.knock_knu.KNOCK_KNU_BE.domain.store;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private DoorType doorType;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ModifierType modifierType;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private PlaceType category;

    public Store(String name, String address, String doorType, String modifierType, float latitude, float longitude, String category) {
        this.name = name;
        this.address = address;
        this.doorType = DoorType.valueOf(doorType.toUpperCase());
        this.modifierType = ModifierType.valueOf(modifierType.toUpperCase());
        this.latitude = latitude;
        this.longitude = longitude;
        this.category = PlaceType.valueOf(category.toUpperCase());
    }

}
