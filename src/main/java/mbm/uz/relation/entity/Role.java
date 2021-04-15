package mbm.uz.relation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import mbm.uz.relation.entity.enums.RoleEnum;
import mbm.uz.relation.entity.templete.IntegerTemplete;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role extends IntegerTemplete {
    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private RoleEnum name;
}
