package InfinityGaming.entity;

import InfinityGaming.json_views.JsonViews;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("MODERATOR")
public class Moderator extends User {
    @JsonView(JsonViews.ModeratorPrivateView.class)
    private String phoneNumber;
}
