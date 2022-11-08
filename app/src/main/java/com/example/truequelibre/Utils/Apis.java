package com.example.truequelibre.Utils;

import com.example.truequelibre.Publicaciones;

public class Apis {

    public static final String HOST="http://10.0.2.2:8080/api/v1/";

    public static IPublicacionService getPublicacionService()
    {
        return Cliente.getCliente(HOST).create(IPublicacionService.class);
    }

    public static ICategoriaService getCategoriaService()
    {
        return Cliente.getCliente(HOST).create(ICategoriaService.class);
    }


}
