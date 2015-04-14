var eventoListId = 'evento-list';
var eventoListQuery = '#' + eventoListId;

function insertPeopleList(parent) {
	parent.append(
		'<table id="' + eventoListId + '">\
			<tr>\
				<th>Titulo</th>\
				<th>Usuario</th>\
				<th>Maximo de asistentes</th>\
				<th>Inicio</th>\
				<th>Fin</th>\
				<th></th>\
				<th></th>\
			</tr>\
		</table>'
	);
}