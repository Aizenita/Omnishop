package com.springwebappsb.omnishop.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springwebappsb.omnishop.entity.Venta;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class RedsysService {

    private static final String SECRET_KEY = "sq7HjrUOBfKmC576ILgskD5srU870gJ7"; // Clave de pruebas
    private static final String MERCHANT_CODE = "999008881";

    /**
     * Genera los parámetros codificados en Base64 requeridos por Redsys.
     */
    public String generarMerchantParameters(Venta venta) throws JsonProcessingException {
        Map<String, Object> params = new HashMap<>();

        // Redsys quiere el importe en céntimos sin decimales ni puntos
        BigDecimal amount = venta.getTotal().multiply(BigDecimal.valueOf(100)).setScale(0, RoundingMode.HALF_UP);
        String order = String.format("%012d", venta.getId()); // 12 caracteres, relleno con ceros

        params.put("DS_MERCHANT_AMOUNT", amount.toBigInteger().toString());
        params.put("DS_MERCHANT_ORDER", order);
        params.put("DS_MERCHANT_MERCHANTCODE", MERCHANT_CODE);
        params.put("DS_MERCHANT_CURRENCY", "978");
        params.put("DS_MERCHANT_TRANSACTIONTYPE", "0");
        params.put("DS_MERCHANT_TERMINAL", "1");
        params.put("DS_MERCHANT_MERCHANTURL", "http://localhost:8080/api/pago/notificacion");
        params.put("DS_MERCHANT_URLOK", "http://localhost:4200/pago-ok");
        params.put("DS_MERCHANT_URLKO", "http://localhost:4200/pago-ko");
        params.put("DS_MERCHANT_DIRECTPAYMENT","true");



        // Convertir a JSON y codificar en Base64
        String json = new ObjectMapper().writeValueAsString(params);
        System.out.println("🔎 JSON A ENVIAR A REDSYS:\n" + json);

        return Base64.encodeBase64String(json.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Genera la firma HMAC_SHA256 de los parámetros, usando clave derivada con el número de pedido.
     */
    public String generarFirma(String merchantParameters, String order) {
        try {
            System.out.println("🧾 merchantParams (Base64): " + merchantParameters);

            // Paso 1: decodificar clave secreta (NO la vuelvas a codificar)
            byte[] keyBytes = Base64.decodeBase64("sq7HjrUOBfKmC576ILgskD5srU870gJ7");

            // Paso 2: derivar clave con HMAC usando el order
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(keyBytes, "HmacSHA256"));
            byte[] claveDerivada = mac.doFinal(order.getBytes(StandardCharsets.UTF_8));

            // Paso 3: firmar los merchantParameters (ya en base64)
            Mac macFinal = Mac.getInstance("HmacSHA256");
            macFinal.init(new SecretKeySpec(claveDerivada, "HmacSHA256"));
            byte[] firmaBytes = macFinal.doFinal(merchantParameters.getBytes(StandardCharsets.UTF_8));


            // 4. Codificar la firma final en Base64

            System.out.println("📦 decoded: " + new String(Base64.decodeBase64(merchantParameters)));
            return Base64.encodeBase64String(firmaBytes);

        } catch (Exception e) {
            throw new RuntimeException("Error generando firma", e);
        }
    }

    public String getRedsysUrl() {
        return "https://sis-t.redsys.es:25443/sis/realizarPago";
    }
}
