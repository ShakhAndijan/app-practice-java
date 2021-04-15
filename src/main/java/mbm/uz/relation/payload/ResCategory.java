package mbm.uz.relation.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mbm.uz.relation.entity.Category;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResCategory {

    public ResCategory(Category category){
        this.id = category.getId();
        this.name = category.getName();
        this.parentId = category.getParentCategory() != null ? category.getParentCategory().getId() : null;
    }

    private UUID id;
    private String name;
    private UUID parentId;
    private List<ResCategory> childCategories;
}
