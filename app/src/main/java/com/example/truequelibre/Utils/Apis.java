package com.example.truequelibre.Utils;

import com.example.truequelibre.Publicaciones;

public class Apis {

    public static final String HOST="http://localhost:8080";

    public static IPublicacionService getPublicacionService()
    {
        return Cliente.getCliente(HOST).create(IPublicacionService.class);
    }

}
