package com.example.truequelibre.Entity;

public class UpdateFinalizarVM {

    private boolean usuario_principal_acepto;

    private boolean usuario_ofertante_acepto;

    public UpdateFinalizarVM() {
    }

    public boolean isUsuario_principal_acepto() {
        return usuario_principal_acepto;
    }

    public void setUsuario_principal_acepto(boolean usuario_principal_acepto) {
        this.usuario_principal_acepto = usuario_principal_acepto;
    }

    public boolean isUsuario_ofertante_acepto() {
        return usuario_ofertante_acepto;
    }

    public void setUsuario_ofertante_acepto(boolean usuario_ofertante_acepto) {
        this.usuario_ofertante_acepto = usuario_ofertante_acepto;
    }
}
