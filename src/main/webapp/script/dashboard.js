visualizar = true;
button = document.getElementById("toggle");
valores = document.getElementsByClassName("show");
traco = document.getElementsByClassName("hide");

for (var i = 0; i < traco.length; i++) {
   if(visualizar == false){
		traco.item(i).style.display = "block";
		valores.item(i).style.display = "none";
	} else {
		traco.item(i).style.display = "none";
		valores.item(i).style.display = "block";
	}
}

button.addEventListener("click", () => {
	visualizar = !visualizar;
	
	for (var i = 0; i < traco.length; i++) {
   if(visualizar == false){
		traco.item(i).style.display = "block";
		valores.item(i).style.display = "none";
	} else {
		traco.item(i).style.display = "none";
		valores.item(i).style.display = "block";
	}
	}
})

fechar = document.getElementById("xis");
abrir = document.getElementById("adicionar");
container = document.getElementById("container");
container.style.display = "none";

fechar.addEventListener("click", () => {
	container.style.display = "none";
})

abrir.addEventListener("click", () => {
	container.style.display = "flex";
})


