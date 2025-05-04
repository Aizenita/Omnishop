package com.springwebappsb.omnishop.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_id_gen")
    @SequenceGenerator(name = "usuario_id_gen", sequenceName = "usuario_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 100)
    @NotNull
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Size(max = 100)
    @NotNull
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Size(max = 255)
    @NotNull
    @Column(name = "\"contraseña\"", nullable = false)
    private String contraseña;

    @Size(max = 20)
    @ColumnDefault("'CLIENTE'")
    @Column(name = "rol", length = 20)
    private String rol;

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