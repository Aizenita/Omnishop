package com.springwebappsb.omnishop.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "log_accion_admin")
public class LogAccionAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "log_accion_admin_id_gen")
    @SequenceGenerator(name = "log_accion_admin_id_gen", sequenceName = "log_accion_admin_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 255)
    @Column(name = "accion")
    private String accion;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "fecha")
    private Instant fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private Usuario admin;

}