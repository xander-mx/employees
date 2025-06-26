package mx.com.invex.employees.dtos.response;

import lombok.Builder;
import lombok.Data;
import mx.com.invex.employees.dtos.ErrorsDTO;

import java.util.List;

@Data
@Builder
public class GenericResponse<T> {

    private Data<T> data;
    private List<ErrorsDTO> errors;

    @lombok.Data
    @Builder
    public static class Data<T> {

        private List<T> attributes;
    }
}
