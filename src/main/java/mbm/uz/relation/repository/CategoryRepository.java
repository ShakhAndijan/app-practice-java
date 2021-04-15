package mbm.uz.relation.repository;

import mbm.uz.relation.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
    List<Category> findAllByParentCategoryIsNull();

    @Query(value = "SELECT * FROM category where parent_category_id is Null", nativeQuery = true)
    List<Category> findParents();
    
    List<Category> findAllByParentCategoryId(UUID parentCategory_id);
    List<Category> findAllByParentCategory(Category parentCategory);

    @Query(value = "SELECT * FROM category where parent_category_id=:parentId", nativeQuery = true)
    List<Category> findAllParentId(UUID parentId);
}
