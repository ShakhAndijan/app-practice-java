package mbm.uz.relation.controller;

import mbm.uz.relation.payload.ReqCategory;
import mbm.uz.relation.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public HttpEntity<?> getAllCategories(@RequestParam(value = "page", defaultValue ="10") int page,
                                          @RequestParam(value = "size", defaultValue ="10") int size) {
        return ResponseEntity.ok().body(categoryService.getAllCategories(page, size));

    }

    @GetMapping("/parent")
    public HttpEntity<?> getAllParentCategories() {
        return ResponseEntity.ok().body(categoryService.getAllParentCategories());

    }

    @GetMapping("{id}")
    public HttpEntity<?> getOneCategory(@PathVariable(name = "id") UUID id) {
        return ResponseEntity.ok().body(categoryService.getOne(id));
    }

    @PostMapping
    public HttpEntity<?> createCategory(@RequestBody ReqCategory reqCategory) {
        return ResponseEntity.ok().body(categoryService.editAndCreateCategory(reqCategory));
    }

    @DeleteMapping("{id}")
    public HttpEntity<?> deleteCategory(@PathVariable UUID id) {
        return ResponseEntity.ok().body(categoryService.deleteCategory(id));
    }
}
