package com.springwebappsb.omnishop.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Getter
public class PagoRedsysResponseDto {
    private String redsysUrl;
    private String dsSignatureVersion;
    private String dsMerchantParameters;
    private String dsSignature;
}
