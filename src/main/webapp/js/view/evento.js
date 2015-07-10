function buscarCadena(cadena) {
	if (cadena == '') {
		$('#categorias').html('');
	}
	else {
		$('#categorias').html('<h2>Eventos titulados: ' + cadena + '</h2>');
	}
	$.getScript('js/dao/evento.js', function() {
		listCadena(cadena,function(eventos) {
			$.each(eventos, function(key, evento) {
				anadirEvento(evento,$('#categorias'));
			});
		});
	});
};

function buscarLocalidad(localidad) {
	if (localidad == '-') {
		$('#categorias').html('');
	}
	else {
		$('#categorias').html('<h2>Eventos en: ' + localidad + '</h2>');
	}
	$.getScript('js/dao/evento.js', function() {
		listLocalidad(localidad,function(eventos) {
			$.each(eventos, function(key, evento) {
				anadirEvento(evento,$('#categorias'));
			});
		});
	});
};

function buscarCategoria(categoria) {
	if (categoria == '-') {
		$('#categorias').html('');
	}
	else {
		$('#categorias').html('<h2>Eventos sobre: ' + categoria + '</h2>');
	}
	$.getScript('js/dao/evento.js', function() {
		listCategoria(categoria,function(eventos) {
			$.each(eventos, function(key, evento) {
				anadirEvento(evento,$('#categorias'));
			});
		});
	});
};

function editarModal(id) {
	$.ajax({
		url: 'rest/eventos/' + id,
		type: 'GET',
		success: function(data) {
			$('#consultarEvento').html(
					'<div class="modal-dialog modal-lg">\
					    <div class="modal-content">\
					      <div class="modal-header">\
					        <button type="button" class="close" data-dismiss="modal">&times;</button>\
					        <h4 class="modal-title">' + data.titulo + '</h4>\
					      </div>\
					      <div class="modal-body">\
					        <ul class="list-group">\
				        		<li class="list-group-item">Creado por: <strong>'+ data.usuario +'</strong>, para <strong>' + data.maxAsistentes + '</strong> personas</li>\
				        		<li class="list-group-item"><strong>'+ data.descripcionDetallada +'</strong></li>\
				        		<li class="list-group-item">El dia: <strong>' + data.inicio + ',</strong> hasta: <strong>' + data.fin + '</strong>. En: <strong>' + data.local + '</strong> (<strong>' + data.localidad + '</strong>)</li>\
				        	</ul>\
					      </div>\
					      <div class="modal-footer">\
				        		<button type="button" class="btn btn-success" data-dismiss="modal">Unirme</button>\
				        		<button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>\
					      </div>\
					    </div>\
					  </div>'
			);
		}
	});
};

function anadirEvento(evento,parent) {
	parent.append(
			'<div class="evento">\
			<a href="#" data-toggle="modal" data-target="#consultarEvento" onClick="editarModal('+ evento.idEvento +')">\
           	<table id="tablaEvento" class="table">\
           		<tbody>\
           			<tr>\
           				<td>' + evento.titulo + '</td>\
           				<td>Categoria: ' + evento.categoria + '</td>\
           				<td rowspan="2"><img src="media/img/poe.jpg" height="100"/></td>\
           			</tr>\
           			<tr>\
           				<td rowspan="2">' + evento.descripcion + '</td>\
           			</tr>\
           			<tr>\
           			</tr> <button type="button" class="btn btn-success" data-dismiss="modal">Apuntate!</button>\
           			<tr>\
           				<td>' + evento.localidad + '</td>\
           			</tr>\
           		</tbody>\
           	</table>\
           	</a>\
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