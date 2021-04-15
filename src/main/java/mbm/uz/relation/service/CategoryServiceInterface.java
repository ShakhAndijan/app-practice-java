package mbm.uz.relation.service;

import mbm.uz.relation.payload.*;

import java.util.List;
import java.util.UUID;

public interface CategoryServiceInterface {
    public ResPageble getAllCategories(int page, int size);
    public List<ResCategory> getAllParentCategories();
    public ResCategory getOne(UUID id);
    public ApiResponseModel editAndCreateCategory(ReqCategory reqCategory);
    public ApiResponse deleteCategory(UUID id);
}
