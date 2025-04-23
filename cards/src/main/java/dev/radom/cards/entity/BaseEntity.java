package dev.radom.cards.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@ToString
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    /**
     * Created timestamp.
     * <p>
     * This field is set only during object creation (INSERT).
     */
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime created;

    /**
     * Username of the user who created the object.
     * <p>
     * This field is set only during object creation (INSERT).
     */
    @CreatedBy
    @Column(updatable = false)
    private String createdBy;

    /**
     * Last updated timestamp.
     * <p>
     * This field is set only during object update (UPDATE).
     */

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updated;

    /**
     * Username of the user who last updated the object.
     * <p>
     * This field is set only during object update (UPDATE).
     */
    @LastModifiedBy
    @Column(insertable = false)
    private String updatedBy;

}
