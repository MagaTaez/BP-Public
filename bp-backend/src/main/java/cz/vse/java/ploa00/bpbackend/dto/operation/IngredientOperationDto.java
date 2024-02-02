package cz.vse.java.ploa00.bpbackend.dto.operation;

import cz.vse.java.ploa00.bpbackend.entity.operation.IngredientOperation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IngredientOperationDto {

    private Long id;
    private LocalDateTime operationDate;
    private IngredientOperation.OperationType operationType;
    private BigDecimal quantity;

    /*
    * public enum OperationType {
        PURCHASE,
        SALE
    }
    * */
}
