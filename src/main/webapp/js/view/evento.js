function anadirEvento(evento,parent) {
	parent.append(
			'<div class="evento">\
	        	<ul>\
	                <li class="imagenEvento"><div><a href="#"><img src="media/img/poe.jpg" /></a></div></li>\
	                <li class="descEvento"><div>\
	                    <div class="tituloEvento"><p>'+ evento.titulo +'</p></div>\
	                    <div class="propEvento"><p>Estas son las propiedades del evento</p></div>\
	                </div>\
	                <li>\
	            </ul>\
	        </div>'
		);
};
	
function listarEventos(parent) {
	$.getScript('js/dao/evento.js', function() { 
		listEventos(function(eventos) {
			$.each(eventos, function(key, evento) {
				anadirEvento(evento,parent);
			});
		});
	});
};