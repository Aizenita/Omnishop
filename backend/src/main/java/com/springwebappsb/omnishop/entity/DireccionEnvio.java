package com.springwebappsb.omnishop.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "direccion_envio")
public class DireccionEnvio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Size(max = 255)
    @Column(name = "calle")
    private String calle;

    @Size(max = 100)
    @Column(name = "ciudad", length = 100)
    private String ciudad;

    @Size(max = 10)
    @Column(name = "cp", length = 10)
    private String cp;

    @Size(max = 50)
    @Column(name = "pais", length = 50)
    private String pais;

    @ColumnDefault("false")
    @Column(name = "predeterminada")
    private Boolean predeterminada;

}