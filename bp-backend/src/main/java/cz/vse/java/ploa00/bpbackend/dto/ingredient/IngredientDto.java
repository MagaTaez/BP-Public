package cz.vse.java.ploa00.bpbackend.dto.ingredient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IngredientDto {

    private Long id;
    private String name;
    private String description;
    private String unitsOfMeasure;
    private BigDecimal stock;
}
