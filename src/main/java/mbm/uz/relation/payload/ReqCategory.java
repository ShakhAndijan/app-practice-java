package mbm.uz.relation.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqCategory {
    private UUID id;
    private String name;
    private UUID parentId;
}
