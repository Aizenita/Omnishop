package com.springwebappsb.omnishop.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "banner")
public class Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "banner_id_gen")
    @SequenceGenerator(name = "banner_id_gen", sequenceName = "banner_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 100)
    @Column(name = "titulo", length = 100)
    private String titulo;

    @Size(max = 255)
    @Column(name = "imagen_url")
    private String imagenUrl;

    @Size(max = 255)
    @Column(name = "enlace_destino")
    private String enlaceDestino;

    @ColumnDefault("true")
    @Column(name = "activo")
    private Boolean activo;

    @Size(max = 100)
    @Column(name = "creado_por", length = 100)
    private String creadoPor;

    @Size(max = 100)
    @Column(name = "modificado_por", length = 100)
    private String modificadoPor;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "fecha_creacion")
    private Instant fechaCreacion;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "fecha_modificacion")
    private Instant fechaModificacion;

}