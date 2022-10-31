/**
 * Validação de Formulário
 @author Diogo de Araújo
 */
 
 function validar(){
	// Verificar se o JavaScript está linkado ao HTML -> alert('teste');
	let nome = frmContato.nome.value;
	let fone = frmContato.fone.value;
	if(nome === ""){
		alert('Preencha o campo Nome!');
		frmContato.nome.focus();
		return false;
	} else if (fone === "") {
		alert('Preencha o campo Fone!');
		frmContato.fone.focus();
		return false;
	} else {
		document.forms["frmContato"].submit();
	}
}