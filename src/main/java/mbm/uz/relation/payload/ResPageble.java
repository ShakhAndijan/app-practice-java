package mbm.uz.relation.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResPageble {
    private List<?> data;
    private long totalElements;
    private int totalPages;
}
