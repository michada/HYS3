function anadirEvento(evento,parent) {
	parent.append(
			'<div class="evento">\
           	<table id="tablaEvento" class="table">\
           		<tbody>\
           			<tr>\
           				<td>' + evento.titulo + '</td>\
           				<td>Categoria: ' + evento.categoria + '</td>\
           				<td rowspan="2"><a href="#"><img src="media/img/poe.jpg" height="100"/></a></td>\
           			</tr>\
           			<tr>\
           				<td rowspan="2">' + evento.descripcion + '</td>\
           				<td>Asistentes: ' + evento.maxAsistentes + '</td>\
           			</tr>\
           			<tr>\
           				<td>Fecha inicio: ' + evento.inicio + '</td>\
           				<td>Fecha fin: ' + evento.fin + '</td>\
           			</tr>\
           			<tr>\
           				<td>Creador: ' + evento.usuario + '</td>\
           				<td>Lugar: ' + evento.local + '</td>\
           				<td>' + evento.localidad + '</td>\
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