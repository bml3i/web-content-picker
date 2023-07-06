package bi.leo.picker.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "extract_hist")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExtractHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "val")
    private String value;

    @Column(name = "create_dttm")
    private LocalDateTime createDateTime;

}
