package ru.greencat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class CatCounter {
    @Id
    @GeneratedValue
    Long id;

    int counter;
}
