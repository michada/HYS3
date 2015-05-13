function listEventos(done, fail, always) {
	done = typeof done !== 'undefined' ? done : function() {};
	fail = typeof fail !== 'undefined' ? fail : function() {};
	always = typeof always !== 'undefined' ? always : function() {};
	$.ajax({
		url: 'rest/eventos',
		type: 'GET'
	})
	.done(done)
	.fail(fail)
	.always(always);
};

function listLocalidad(localidad,done, fail, always) {
	done = typeof done !== 'undefined' ? done : function() {};
	fail = typeof fail !== 'undefined' ? fail : function() {};
	always = typeof always !== 'undefined' ? always : function() {};
	$.ajax({
		url: 'rest/eventos',
		type: 'GET',
		data: 'localidad=' + localidad
	})
	.done(done)
	.fail(fail)
	.always(always);
};

function listLocalidad(localidad,done, fail, always) {
	done = typeof done !== 'undefined' ? done : function() {};
	fail = typeof fail !== 'undefined' ? fail : function() {};
	always = typeof always !== 'undefined' ? always : function() {};
	$.ajax({
		url: 'rest/eventos',
		type: 'GET',
		data: 'categoria=' + categoria
	})
	.done(done)
	.fail(fail)
	.always(always);
};