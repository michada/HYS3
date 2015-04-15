var eventosFormId = 'eventos-form';
var eventosListId = 'eventos-list';
var eventosFormQuery = '#' + eventosFormId;
var eventosListQuery = '#' + eventosListId;

function insertEventosList(parent) {
	parent.append(
		'<table id="' + eventosListId + '">\
			<tr>\
				<th>tituloEvento</th>\
				<th>propEvento</th>\
				<th></th>\
				<th></th>\
			</tr>\
		</table>'
	);
}

function insertEventosForm(parent) {
	parent.append(
		'<form id="' + eventosFormId + '">\
			<input name="id" type="hidden" value=""/>\
			<input name="name" type="text" value="" />\
			<input name="surname" type="text" value=""/>\
			<input id="btnSubmit" type="submit" value="Create"/>\
			<input id="btnClear" type="reset" value="Limpiar"/>\
		</form>'
	);
}

function createEventoRow(evento) {
	return '<tr id="evento-'+ evento.idEvento +'">\
		<td class="titulo">' + evento.titulo + '</td>\
		<td class="usuario">' + evento.usuario + '</td>\
		<td class="maxAsistentes">' + evento.maxAsistentes + '</td>\
		<td class="inicio">' + evento.inicio + '</td>\
		<td class="fin">' + evento.fin + '</td>\
		<td>\
			<a class="edit" href="#">Edit</a>\
		</td>\
		<td>\
			<a class="delete" href="#">Delete</a>\
		</td>\
	</tr>';
}

function formToEvento() {
	var form = $(eventosFormQuery);
	return {
		'idEvento': form.find('input[name="idEvento"]').val(),
		'titulo': form.find('input[name="titulo"]').val(),
		'usuario': form.find('input[name="usuario"]').val(),
		'maxAsistentes': form.find('input[name="maxAsistentes"]').val(),
		'inicio': form.find('input[name="inicio"]').val(),
		'fin': form.find('input[name="fin"]').val()
	};
}

function eventoToForm(person) {
	var form = $(eventosFormQuery);
	form.find('input[name="idEvento"]').val(evento.idEvento);
	form.find('input[name="titulo"]').val(evento.titulo);
	form.find('input[name="usuario"]').val(evento.usuario);
	form.find('input[name="maxAsistentes"]').val(evento.maxAsistentes);
	form.find('input[name="inicio"]').val(evento.inicio);
	form.find('input[name="fin"]').val(evento.fin);
}

function rowToEvento(idEvento) {
	var row = $('#evento-' + idEvento);

	return {
		'idEvento': idEvento,
		'titulo': row.find('td.titulo').text(),
		'usuario': row.find('td.usuario').text(),
		'maxAsistentes': row.find('td.maxAsistentes').text(),
		'inicio': row.find('td.inicio').text(),
		'fin': row.find('td.fin').text()
	};
}

function isEditing() {
	return $(eventosFormQuery + ' input[name="idEvento"]').val() != "";
}

function disableForm() {
	$(eventosFormQuery + ' input').prop('disabled', true);
}

function enableForm() {
	$(eventosFormQuery + ' input').prop('disabled', false);
}

function resetForm() {
	$(eventosFormQuery)[0].reset();
	$(eventosFormQuery + ' input[name="idEvento"]').val('');
	$('#btnSubmit').val('Crear');
}

function showErrorMessage(jqxhr, textStatus, error) {
	alert(textStatus + ": " + error);
}

function addRowListeners(evento) {
	$('#evento-' + evento.idEvento + ' a.edit').click(function() {
		eventoToForm(rowToEvento(evento.idEvento));
		$('input#btnSubmit').val('Modificar');
	});
	
	$('#evento-' + evento.id + ' a.delete').click(function() {
		if (confirm('Est� a punto de eliminar un evento. �Est� seguro de que desea continuar?')) {
			deleteEvento(evento.idEvento,
				function() {
					$('tr#evento-' + evento.idEvento).remove();
				},
				showErrorMessage
			);
		}
	});
}

function appendToTable(evento) {
	$(eventosListQuery + ' > tbody:last')
		.append(createEventoRow(evento));
	addRowListeners(evento);
}

function initEventos() {
	$.getScript('js/dao/evento.js', function() {
		listEventos(function(eventos) {
			$.each(eventos, function(key, evento) {
				appendToTable(evento);
			});
		});
		
		$(peopleFormQuery).submit(function(event) {
			var evento = formToEvento();
			
			if (isEditing()) {
				modifyEvento(evento,
					function(evento) {
						$('#evento-' + evento.id + ' td.titulo').text(evento.titulo);
						$('#evento-' + evento.id + ' td.usuario').text(evento.usuario);
						$('#evento-' + evento.id + ' td.maxAsistentes').text(evento.maxAsistentes);
						$('#evento-' + evento.id + ' td.inicio').text(evento.inicio);
						$('#evento-' + evento.id + ' td.fin').text(evento.fin);
						resetForm();
					},
					showErrorMessage,
					enableForm
				);
			} else {
				addEvento(evento,
					function(evento) {
						appendToTable(evento);
						resetForm();
					},
					showErrorMessage,
					enableForm
				);
			}
			
			return false;
		});
		
		$('#btnClear').click(resetForm);
	});
};