package cz.vse.java.ploa00.bpbackend.dto.supplier;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SupplierDto {

    private Long id;
    private String name;
    private String description;
    private String email;
}
