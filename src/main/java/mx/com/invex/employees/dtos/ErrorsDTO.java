package mx.com.invex.employees.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorsDTO {

    private Integer code;
    private String status;
    private String detail;
}
