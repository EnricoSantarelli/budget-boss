email = document.getElementById("email");
emailConfirm = document.getElementById("emailConfirm");
senha = document.getElementById("senha");
senhaConfirm = document.getElementById("senhaConfirm");
erro = document.getElementById("erro");
button = document.getElementById("submit");

button.addEventListener("click", (event) => {
	if(email.value != "" && emailConfirm.value != "" && email.value !== emailConfirm.value){
		erro.innerHTML = "Os e-mails devem ser iguais";
		event.preventDefault();
	} else {
		if(senha.value != "" && senhaConfirm.value != "" && senha.value !== senhaConfirm.value){
			erro.innerHTML = "As senhas devem ser iguais";
			event.preventDefault();
		} else {
			erro.innerHTML = "";
		}
	}
	
});

