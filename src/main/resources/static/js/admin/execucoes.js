$( document ).ready(function() {
	
	var dtaInicio = $("#dtaInicio").val();
	var origem = $("#origem").val();
	var cadastro = $("#cadastro").val();
	
  	// Bloquear formumario, porque ja tem uma consulta com filtros em andamento
	//bloquear(dtaInicio, origem, cadastro);	
    
    // Filtrar
	$("#filtrar").click(function (event) {
        event.preventDefault();
        
        dtaInicio = $("#dtaInicio").val();
		origem = $("#origem").val();
		cadastro = $("#cadastro").val();
           
        if (dtaInicio) {
        	window.location.assign("/execucoes?dtaInicio="+dtaInicio);
		} else if (origem != 'SN') {
			window.location.assign("/execucoes?origem="+origem);
		} else if (cadastro != 'SN') {
		   window.location.assign("/execucoes?cadastro="+cadastro);
		} else {       
        	window.location.assign("/execucoes");
        }	
    });  

    // Processa todos os dados dos itens
	$("#voltar").click(function (event) {		
		event.preventDefault();
		window.location.assign("/execucoes");
	});   

	 // Controle dos cliques no botoes de navegação da grid
    $(".btn-nav").click(function (event) {
        event.preventDefault();
		window.location.assign("/execucoes?pag="+this.textContent+"&dtaInicio="+dtaInicio+"&origem="+origem+"&cadastro="+cadastro);
    });

	$(".btn-anterior").click(function (event) {
		const val = parseInt($(".page-item.active").find('a').text())-1;		
        event.preventDefault();
        window.location.assign("/execucoes?pag="+val);
    });

  	$(".btn-proximo").click(function (event) {	   	
		const val = parseInt($(".page-item.active").find('a').text())+1;		
        event.preventDefault();
        window.location.assign("/execucoes?pag="+val);
    });

});

function bloquear(dtaInicio, origem, cadastro) {
	if (dtaInicio || origem || cadastro ) {
    	$("#dtaInicio").attr("disabled", true);
     	$("#origem").attr("disabled", true);
    	$("#cadastro").attr("disabled", true);
    	$("#filtrar").attr("disabled", true);
	}
}

function reset() {	
	window.location.assign("/execucoes");
}
		
