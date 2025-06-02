package com.springwebappsb.omnishop.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springwebappsb.omnishop.entity.Venta;
import com.springwebappsb.omnishop.enums.EstadoVenta;
import com.springwebappsb.omnishop.repository.VentaRepository;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/pago")
public class PagoController {

    private final VentaRepository ventaRepository;

    public PagoController(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    @PostMapping("/notificacion")
    public ResponseEntity<Void> notificacionRedsys(@RequestParam("Ds_MerchantParameters") String params,
                                                   @RequestParam("Ds_Signature") String signature) {
        String decoded = new String(Base64.decodeBase64(params), StandardCharsets.UTF_8);
        // Parsear el JSON para extraer el ID de la venta (DS_MERCHANT_ORDER)
        ObjectMapper mapper = new ObjectMapper();
        try {
            Map<String, String> values = mapper.readValue(decoded, new TypeReference<>() {});
            String orderIdStr = values.get("DS_MERCHANT_ORDER");
            Long ventaId = Long.parseLong(orderIdStr.replaceFirst("^0+(?!$)", "")); // elimina ceros a la izquierda

            Optional<Venta> ventaOpt = ventaRepository.findById(ventaId);
            ventaOpt.ifPresent(venta -> {
                venta.setEstadoVenta(EstadoVenta.PAGADA);
                ventaRepository.save(venta);
            });


        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping("/ok")
    public RedirectView pagoCorrecto() {
        return new RedirectView("http://localhost:4200/pago-ok");
    }

    @GetMapping("/ko")
    public RedirectView pagoFallido() {
        return new RedirectView("http://localhost:4200/pago-ko");
    }
}

