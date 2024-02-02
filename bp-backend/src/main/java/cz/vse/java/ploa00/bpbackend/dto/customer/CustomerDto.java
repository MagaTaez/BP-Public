package cz.vse.java.ploa00.bpbackend.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    private Long id;
    private String name;
    private String description;
    private String email;
    private String phone;
}
