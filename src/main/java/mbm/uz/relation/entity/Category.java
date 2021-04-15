package mbm.uz.relation.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import mbm.uz.relation.entity.templete.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "category")
public class Category extends AbsEntity {


    private String name;

    @ManyToOne
    private Category parentCategory;
}
