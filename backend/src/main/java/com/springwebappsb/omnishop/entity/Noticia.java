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
@Entity
@Table(name = "noticia")
public class Noticia {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "noticia_id_gen")
    @SequenceGenerator(name = "noticia_id_gen", sequenceName = "noticia_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 150)
    @Column(name = "titulo", length = 150)
    private String titulo;

    @Column(name = "contenido", length = Integer.MAX_VALUE)
    private String contenido;

    @Size(max = 255)
    @Column(name = "imagen_url")
    private String imagenUrl;

    @Column(name = "fecha_publicacion")
    private Instant fechaPublicacion;

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