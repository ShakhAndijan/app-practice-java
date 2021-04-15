package mbm.uz.relation.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseModel {
    private Boolean success;
    private String message;
    private Object data;
}
