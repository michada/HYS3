function anadirEvento(evento,parent) {
	parent.append(
			'<div class="evento">\
           	<table id="tablaEvento" class="table">\
           		<tbody>\
           			<tr>\
           				<td>Titulo evento</td>\
           				<td>Categoria</td>\
           				<td rowspan="2"><a href="#"><img src="media/img/poe.jpg" height="100"/></a></td>\
           			</tr>\
           			<tr>\
           				<td rowspan="2">Descripcion del evento</td>\
           				<td>Asistentes</td>\
           			</tr>\
           			<tr>\
           				<td>Fecha Inicio</td>\
           				<td>Fecha Fin</td>\
           			</tr>\
           			<tr>\
           				<td>Usuario</td>\
           				<td>Local</td>\
           				<td>Localidad</td>\
           			</tr>\
           		</tbody>\
           	</table>\
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