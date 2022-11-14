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

    public static IUsuarioService getUsuarioService()
    {
        return Cliente.getCliente(HOST).create(IUsuarioService.class);
    }

    public static IOfertaService getOfertaService()
    {
        return Cliente.getCliente(HOST).create(IOfertaService.class);
    }

    public static ICalificacionUsuariosService getCalificacionUsuariosService()
    {
        return Cliente.getCliente(HOST).create(ICalificacionUsuariosService.class);
    }

    public static IFinalizarTruequeService getIFinalizarTruequeService()
    {
        return Cliente.getCliente(HOST).create(IFinalizarTruequeService.class);
    }
}
