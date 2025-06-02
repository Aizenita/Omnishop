package com.springwebappsb.omnishop.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.springwebappsb.omnishop.dto.request.PagoRequestDto;
import com.springwebappsb.omnishop.dto.response.PagoRedsysResponseDto;
import com.springwebappsb.omnishop.entity.Venta;
import com.springwebappsb.omnishop.service.RedsysService;
import com.springwebappsb.omnishop.service.VentaService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

    private final RedsysService redsysService;
    private final VentaService ventaService;

    public CheckoutController(RedsysService redsysService, VentaService ventaService) {
        this.redsysService = redsysService;
        this.ventaService = ventaService;
    }

    @PostMapping("/iniciar-pago")
    public ResponseEntity<PagoRedsysResponseDto> iniciarPago(@RequestBody PagoRequestDto requestDto, Authentication auth) throws JsonProcessingException {
        // 1. Crear la venta en estado "pendiente"
        Venta venta = ventaService.crearVentaPendiente(requestDto, auth);

        // 2. Generar parámetros de Redsys
        String merchantParams = redsysService.generarMerchantParameters(venta);
        String signature = redsysService.generarFirma(merchantParams, String.format("%012d", venta.getId()));

        // 3. Enviar respuesta al frontend
        return ResponseEntity.ok(new PagoRedsysResponseDto(
                redsysService.getRedsysUrl(),
                "HMAC_SHA256_V1",
                merchantParams,
                signature
        ));
    }
    @PostMapping("/finalizar-pedido-simulado")
    public ResponseEntity<Map<String, Object>> finalizarPedidoSimulado(
            @RequestBody PagoRequestDto requestDto,
            Authentication auth) {

        // 1. Crear la venta como simulada/pagada
        Venta venta = ventaService.crearVentaPagadaSimulada(requestDto, auth);

        // 2. Respuesta simple
        Map<String, Object> response = new HashMap<>();
        response.put("orderId", venta.getId());
        response.put("message", "Pedido simulado realizado con éxito");

        return ResponseEntity.ok(response);
    }

}
