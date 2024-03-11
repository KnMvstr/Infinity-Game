package HB_CAPE_MAK.hb_cape_makindu.entity;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue ("GAMER")
public class Gamer extends User {

    private LocalDate birthAt;

    @OneToMany(mappedBy = "gamer")
    private List<Review> reviews = new ArrayList<>();

}
