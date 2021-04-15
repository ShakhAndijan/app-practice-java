package mbm.uz.relation.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import mbm.uz.relation.entity.templete.AbsEntity;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true, exclude = {"followers", "follows"})
@Entity
@Data
@Table(name = "users")
public class User extends AbsEntity {

    private String name;
    private String surName;
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<User> followers;

    @ManyToMany(mappedBy = "followers", fetch = FetchType.LAZY)
    private Set<User> follows;
}
