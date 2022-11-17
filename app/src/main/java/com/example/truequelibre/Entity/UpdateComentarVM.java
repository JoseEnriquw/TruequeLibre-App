package com.example.truequelibre.Entity;

public class UpdateComentarVM {
    private boolean usuario_principal_califico;

    private boolean usuario_ofertante_califico;

    public boolean isUsuario_principal_califico() {
        return usuario_principal_califico;
    }

    public UpdateComentarVM() {
    }

    public void setUsuario_principal_califico(boolean usuario_principal_califico) {
        this.usuario_principal_califico = usuario_principal_califico;
    }

    public boolean isUsuario_ofertante_califico() {
        return usuario_ofertante_califico;
    }

    public void setUsuario_ofertante_califico(boolean usuario_ofertante_califico) {
        this.usuario_ofertante_califico = usuario_ofertante_califico;
    }

    public UpdateComentarVM(boolean usuario_principal_califico, boolean usuario_ofertante_califico) {
        this.usuario_principal_califico = usuario_principal_califico;
        this.usuario_ofertante_califico = usuario_ofertante_califico;
    }
}
