xisDespesa = document.getElementById("xisDespesa");
xisReceita = document.getElementById("xisReceita");
abrirDespesa = document.getElementById("abrirDespesa");
abrirReceita = document.getElementById("abrirReceita");
containerDespesa = document.getElementById("containerDespesa");
containerReceita = document.getElementById("containerReceita");
containerDespesa.style.display = "none";
containerReceita.style.display = "none";

xisDespesa.addEventListener("click", () => {
	containerDespesa.style.display = "none";
})

xisReceita.addEventListener("click", () => {
	containerReceita.style.display = "none";
})

abrirDespesa.addEventListener("click", () => {
	containerDespesa.style.display = "flex";
})

abrirReceita.addEventListener("click", () => {
	containerReceita.style.display = "flex";
})