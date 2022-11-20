/**
 * Confirmação de exclusão de um contato
 * @author Diogo de Araújo
 */
 
 function confirmar(idcon){
	let resposta = confirm("Confirma a exclusão desse contato?");
	if(resposta === true){
		//alert(idcon);
		window.location.href = "delete?idcon=" + idcon;
	}
}