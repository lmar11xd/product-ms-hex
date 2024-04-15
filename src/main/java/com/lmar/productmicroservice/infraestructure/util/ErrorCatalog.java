package com.lmar.productmicroservice.infraestructure.util;

import lombok.Getter;

@Getter
public enum ErrorCatalog {

    PRODUCT_NOT_FOUND("ERR_PRODUCT_001", "Producto no encontrado."),
    INVALID_PRODUCT("ERR_PRODUCT_002", "Producto con campos no válidos."),
    GENERIC_ERROR("ERR_GEN_001", "Ha ocurrido un error.");

    private final String code;
    private final String message;

    ErrorCatalog(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
