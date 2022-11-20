/**
 * Confirmar a exclusao de um contato
 *
 * @author Diogo de Araujo
 *
 * @param idcon
 */
 
 function confirmar(idcon){
	let resposta = confirm("Confirma a exclusão desse contato?");
	if(resposta === true){
		window.location.href = "delete?idcon=" + idcon;
	}
}