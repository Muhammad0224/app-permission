package uz.pdp.apppermission.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import uz.pdp.apppermission.domain.Post;
import uz.pdp.apppermission.domain.templ.GenericEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    @NotBlank
    private String text;

    private Long postId;
}
