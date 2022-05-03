//Sacado de StackOverflow
Element.prototype.remove = function() {
    this.parentElement.removeChild(this);
}

NodeList.prototype.remove = HTMLCollection.prototype.remove = function() {
    for(var i = this.length - 1; i >= 0; i--) {
        if(this[i] && this[i].parentElement) {
            this[i].parentElement.removeChild(this[i]);
        }
    }
}
//--------------------------------------------------------------------------------

// Funciones que dan un mensaje por pantalla
function exito(){
	alert('¡Sesión modificada con éxito!');
}

function exito_critica(){
	alert('¡Crítica añadida con éxito!');
}

function exito_eliminar(){
	alert('¡Crítica eliminada con éxito!');
}

function exito_valorar(){
	alert('¡Crítica valorada con éxito!');
}

// Elimina el input de tipo datetime-local indicado por el ID pasado por parámetro
function eliminar_fecha(id_fecha){
	document.getElementById(id_fecha).remove();
}

//Añade una fecha al view de un espectáculo múltiple siendo registrado en el sistema
function addfecha(id_fecha){
	var id = id_fecha + 1;
	
	var container = document.getElementsByClassName("nuevo_espectaculo")[0];
	var multiple = document.getElementsByClassName("multiple")[0];
	var aniadir = multiple.lastElementChild;
	
	//Creamos un span que contendrá la fecha y su botón de eliminar
	var nueva_fecha = document.createElement("span");
	nueva_fecha.setAttribute("id", "fecha" + id);
	
	//Creamos el botón de eliminar
	var eliminar = document.createElement("button");
	eliminar.setAttribute("type", "button");
	eliminar.innerText = "Eliminar";
	eliminar.style.verticalAlign = "text-bottom";
	eliminar.setAttribute("onclick", "eliminar_fecha(" + "'fecha" + id + "')");
	
	//Creamos la fecha
	var fecha = document.createElement("input");
	fecha.setAttribute("type", "datetime-local");
	fecha.setAttribute("name", "fecha_m");
	fecha.required = true;
	
	//Metemos la fecha y el botón de eliminar en el span
	nueva_fecha.appendChild(fecha);
	nueva_fecha.appendChild(eliminar);
	
	aniadir.remove();
	aniadir.children[0].setAttribute("onclick", "addfecha(" + id + ")");
	
	multiple.appendChild(nueva_fecha);
	multiple.appendChild(aniadir);
};

//Algunas variables que tienen que ser globales
var puntual;
var temporada;
var multiple;
var enviar;

// Enseña los campos a rellenar según el tipo de espectáculo que esté siendo creado
function show_selected(){
	var tipo = document.getElementsByName("tipo");
	
	puntual = document.getElementsByClassName("puntual")[0];
	temporada = document.getElementsByClassName("temporada")[0];
	multiple = document.getElementsByClassName("multiple")[0];
	enviar = document.getElementsByClassName("control")[0];
	
	//PUNTUAL: quitamos los de temporada y múltiples
	if(tipo[0].checked == true){
		temporada.remove();
		multiple.remove();
	}
	//TEMPORADA: quitamos los puntuales y múltiples
	else if(tipo[1].checked == true){
		puntual.remove();
		multiple.remove();
	}
	//MULTIPLE: quitamos los de temporada y puntuales
	else{
		puntual.remove();
		temporada.remove();
	}
}

// Cuando se cambia el tipo de espectáculo siendo añadido a 'puntual', los elementos
// de los demás tipos son eliminados
function show_puntual(){	
	var container = document.getElementsByClassName("nuevo_espectaculo")[0];
	var form = document.getElementById("tipo_elementos");
	var temporada = document.getElementsByClassName("temporada");
	var multiple = document.getElementsByClassName("multiple");
	
	enviar.remove();
	
	if(temporada.length != 0)
		temporada.remove();
	
	if(multiple.length != 0)
		multiple.remove();
		
	form.appendChild(puntual);
	container.appendChild(enviar);
}

// Cuando se cambia el tipo de espectáculo siendo añadido a 'temporada', los elementos
// de los demás tipos son eliminados
function show_temporada(){	
	var container = document.getElementsByClassName("nuevo_espectaculo")[0];
	var form = document.getElementById("tipo_elementos");
	
	var puntual = document.getElementsByClassName("puntual");
	var multiple = document.getElementsByClassName("multiple");
	
	enviar.remove();
	
	if(puntual.length != 0)
		puntual.remove();
	
	if(multiple.length != 0)
		multiple.remove();
		
	form.appendChild(temporada);
	container.appendChild(enviar);
}

// Cuando se cambia el tipo de espectáculo siendo añadido a 'múltiple', los elementos
// de los demás tipos son eliminados
function show_multiple(){	
	var container = document.getElementsByClassName("nuevo_espectaculo")[0];
	var form = document.getElementById("tipo_elementos");
	
	var temporada = document.getElementsByClassName("temporada");
	var puntual = document.getElementsByClassName("puntual");
	
	enviar.remove();
	
	if(temporada.length != 0)
		temporada.remove();
	
	if(puntual.length != 0)
		puntual.remove();
		
	form.appendChild(multiple);
	container.appendChild(enviar);
}

// Función para cancelar una sesión de un espectáculo múltiple
function cancelar(id){
	var sesion = document.getElementById(id);
	var boton = document.getElementById(id + 'b');
	
	sesion.remove();
	boton.remove();
}

function add(n_multiples, id){
	
	//Obtenemos el elemento <div> con clase "espectaculo"
	var ultimo = document.getElementById(n_multiples);
	var espectaculo = ultimo.parentElement;
	
	var controles = espectaculo.getElementsByClassName("control")[0];
	
	var aniadir = document.getElementById(n_multiples + "_add");
	
	var nueva_fecha = document.createElement('div');
	var fecha = document.createElement('input');
	var cancel = document.createElement('input');
	var cancelar_id = (id + 1) + 'b';
	
	controles.remove();
	
	nueva_fecha.id = id + 1;
	nueva_fecha.style.display = "ruby";
	
	fecha.type = "datetime-local";
	fecha.setAttribute('name', 'fecha_m');
	fecha.required = "true";
	
	cancel.type = "button";
	cancel.id = cancelar_id;
	cancel.value = "Cancelar sesión";
	cancel.setAttribute("onclick", "cancelar(" + (id + 1) + ")");
	
	aniadir.setAttribute("onclick", "add( '" + n_multiples + "', " + (id + 1) + ")");
	
	nueva_fecha.appendChild(fecha);
	nueva_fecha.appendChild(cancel);
	ultimo.appendChild(nueva_fecha);
	espectaculo.appendChild(controles);
}

// Controla si el botón de "buscar" está activo. Para ello o bien la barra de 
// búsqueda de título o la de categoría deben contener texto
function enviar_vis(){
	var titulo = document.getElementsByName('titulo')[0];
	var categoria = document.getElementsByName('categoria')[0];
	var send = document.getElementsByName('send')[0];
	
	if(titulo.value || categoria.value){
		send.disabled = false;
	}
	else {
		send.disabled = true;
	}
}

// Elimina los botones de radio para valorar una crítica
function eliminarVal(id_critica){
	var form = document.getElementById("valorar" + id_critica);
	var boton = document.getElementById("val" + id_critica);
	var controles = form.parentElement.getElementsByClassName("control")[0];
	var i;
	
	for(i = 0; i < 10; i++)
		form.lastChild.remove();
		
	controles.lastChild.remove();
		
	boton.innerText = "Valorar crítica";
	boton.setAttribute("onclick", "valorar(" + id_critica + ")");
}

// Permite a un usuario valorar una crítica seleccionada
function valorar(id_critica){
	var i = 1;
	var form = document.getElementById("valorar" + id_critica);
	var boton = document.getElementById("val" + id_critica);
	var controles = form.parentElement.getElementsByClassName("control")[0];
	
	boton.innerText = "Cancelar";
	boton.setAttribute("onclick", "eliminarVal(" + id_critica + ")");
	
	for(i = 1; i < 6; i++){
		var radio = document.createElement("input");
		var label = document.createElement("label");
		
		radio.setAttribute("type", "radio");
		radio.setAttribute("name", "valoracion");
		radio.setAttribute("value", i);
		radio.style.fontSize = "20px";
		if(i == 3) radio.checked = true;
		
		label.innerHTML = i;
		
		form.appendChild(radio);
		form.appendChild(label);
	}
	
	var submit = document.createElement("button");
	
	submit.setAttribute("type", "submit");
	submit.setAttribute("form", "valorar" + id_critica);
	submit.innerText = "Enviar valoración";
	
	controles.appendChild(submit);
}

// Establece el tamaño de la letra de los botones del header
function set_size(){
	var header = document.getElementsByClassName("header")[0];
	var botones = header.getElementsByClassName("btn");
	var elementos = botones.length;
	var i;
	
	if(elementos > 2){
		for(i = 0; i < elementos; ++i){
			botones[i].style.fontSize = "20px";
		}
	}
}


















