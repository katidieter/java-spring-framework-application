//Subject
package com.unisinos.app.models;

public interface IAcao {
	public void anexar(ITipoAcao tipoDeAcao);
	public void desanexar(ITipoAcao tipoDeAcao);
	public void enviarNotificacao();
	public String getSituacao();
	public void setSitacao(String situacao);

}
