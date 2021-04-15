package mbm.uz.relation.service;

import mbm.uz.relation.entity.Category;
import mbm.uz.relation.payload.*;
import mbm.uz.relation.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CategoryService implements CategoryServiceInterface {

    @Autowired
    CategoryRepository categoryRepository;


    @Override
    public ResPageble getAllCategories(int page, int size) {
        Page<Category> allByOrderByNameDesc = categoryRepository.findAll(PageRequest.of(page, size));
        ResPageble resPageble = new ResPageble();
        Page<ResCategory> collect = allByOrderByNameDesc.map(ResCategory::new);
        resPageble.setData(collect.getContent());
        resPageble.setTotalElements(collect.getTotalElements());
        resPageble.setTotalPages(collect.getTotalPages());
        return resPageble;
    }

    @Override
    public List<ResCategory> getAllParentCategories() {
        List<Category> allByOrderByNameDesc = categoryRepository.findParents();
//        List<Category> allByOrderByNameDesc = categoryRepository.findAllByParentCategoryIsNull();
        List<ResCategory> collect = allByOrderByNameDesc.stream().map(ResCategory::new)
                .collect(Collectors.toList());
        return collect;
    }

    @Override
    public ResCategory getOne(UUID id) {
        Optional<Category> byId = categoryRepository.findById(id);
        if (byId.isPresent()) {
            Category category = byId.get();
            ResCategory resCategory = new ResCategory(category);
            List<Category> childCategories = categoryRepository.findAllByParentCategory(category);
            List<ResCategory> collect = childCategories.stream().map(ResCategory::new).collect(Collectors.toList());
            resCategory.setChildCategories(collect);
            return resCategory;
        } else {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,
                    "Missing the require parameter 'id' when calling updateUser");
        }

    }

    @Override
    public ApiResponseModel editAndCreateCategory(ReqCategory reqCategory) {

        Optional<Category> byId = Optional.empty();
        if (reqCategory.getId() != null) {
            byId = categoryRepository.findById(reqCategory.getId());
        }
        Category category;

        if (byId.isPresent()) {
            category = byId.get();
        } else {
            if (reqCategory.getId() != null) {
                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,
                        "'id' topilmadi");
            }
            category = new Category();
        }
        category.setName(reqCategory.getName());

        if (reqCategory.getParentId() != null) {
            Optional<Category> parent = categoryRepository.findById(reqCategory.getParentId());
            if (parent.isPresent()){
                category.setParentCategory(parent.get());
            }
        }
        Category save = categoryRepository.save(category);

        return new ApiResponseModel(true, byId.isPresent() ? "O'zgartirldi" : "Saqlandi"
                , new ResCategory(save));

    }

    @Override
    public ApiResponse deleteCategory(UUID id) {
       getChildsIfNotDelete(id);
       return new ApiResponse();
    }

    public void getChildsIfNotDelete(UUID id){
        List<Category> allByParentCategoryId = categoryRepository.findAllByParentCategoryId(id);
        if (allByParentCategoryId.size() == 0){
            categoryRepository.deleteById(id);
        }else {
            allByParentCategoryId.forEach(category -> {
                getChildsIfNotDelete(category.getId());
            });
        }
    }
}
