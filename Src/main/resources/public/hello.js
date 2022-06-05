$(document).ready(function() {
	let $listeVoyages = $("#listVoyages");
	let $listePersonnes = $("#listPersonnes");
	let $selectVoyages = $("#selectVoyages");

    $.get("http://localhost:8080/api/personnes",function(resp){
		resp.forEach( p => appendToListPersonne(p));
    });

	$.get("http://localhost:8080/api/voyages",function(resp){
		resp.forEach( l => {
			appendToListVoyage(l);
			appendToSelects(l);
		});
	});

	$listePersonnes.on("click", "li button", function() {
    	let elemid = $(this).parent().attr('id');
    	
    	$.ajax({
		    type: "DELETE",
		    url: "http://localhost:8080/api/personnes/" + elemid.replace('personne-',''),
		    dataType: "json",
		    success: function(data){
		    	$("#"+elemid).remove();
		    }
		});	
    });

    $listeVoyages.on("click", "li button", function() {
        	let elemid = $(this).parent().attr('id');
            let idVoyage = elemid.replace('voyage-','');

        	$.ajax({
    		    type: "DELETE",
    		    url: "http://localhost:8080/api/voyages/" + idVoyage,
    		    dataType: "json",
    		    success: function(data){
    		    	$("#"+elemid).remove();
    		    	$(`#selectVoyages option[value="${idVoyage}"]`).remove();
    		    },
                error: function (xhr, ajaxOptions, thrownError) {
                    console.log(xhr);
                    alert("Impossible de supprimer le voyage, des places ont déjà été réservées");
                }
    		});
        });
    
    $('#addbtnPersonne').click(function(){
		let prenom = $('#prenom-input').val();
		let age = $('#age-input').val();
		let idVoyage = $selectVoyages.val();
		
		$.ajax({
		    type: "POST",
		    url: "http://localhost:8080/api/personnes",
		    data: JSON.stringify({ "prenom": prenom, "age" : age }),
		    contentType: "application/json; charset=utf-8",
		    dataType: "json",
		    success: function(data){
		        addVoyageToPerson(data.id, idVoyage);
		    }
		});
		
		$('#prenom-input').val('');
		$('#age-input').val('');
		return false;
	});

	$('#addbtnVoyage').click(function(){
		let voyage = {
			Destination: $('#destination-input').val(),
			Depart: $('#depart-input').val(),
			Duree: $('#duree-input').val(),
			programme: {
				hotel: $('#hotel-programme-input').val(),
				restaurant: $('#restaurant-programme-input').val(),
				endroit1: $('#endoit1-programme-input').val(),
				endroit2: $('#endoit2-programme-input').val(),
				endroit3: $('#endoit3-programme-input').val()
			}
		};

		$.ajax({
			type: "POST",
			url: "http://localhost:8080/api/voyages",
			data: JSON.stringify(voyage),
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			success: function(data){
				appendToListVoyage(data);
				appendToSelects(data);
			}
		});

		$('#destination-input').val('');
		$('#depart-input').val('');
		$('#duree-input').val('');
		$('#hotel-programme-input').val('');
		$('#restaurant-programme-input').val('');
		$('#endroit1-programme-input').val('');
		$('#endroit2-programme-input').val('');
		$('#endroit3-programme-input').val('');
		return false;
	})

	/* Ajoute un élément v dans la liste de voyages*/
	function appendToListVoyage(voyage) {
		$listeVoyages.append(`<v id="voyage-${voyage.id}" class="list-group-item">${voyage.destination} - ${voyage.depart} - ${voyage.duree} - ${voyage.programme.hotel} - ${voyage.programme.restaurant} - ${voyage.programme.endroit1} - ${voyage.programme.endroit2}- ${voyage.programme.endroit3} <button class="btn btn-danger btn-xs" data-title="Delete" data-toggle="modal" data-target="#delete" >X</button> </v>`);
	}

	/* Ajoute un élément v dans le select des voyages*/
	function appendToSelects(voyage) {
		$selectVoyages.append(`<option value="${voyage.id}">${voyage.destination}</option>`)
	}

	/* Ajoute un élément v dans la liste de personne*/
	function appendToListPersonne(personne) {
	    let liToAppend = `<v id="personne-${personne.id}" class="list-group-item">${personne.prenom} ${personne.age}`;
	    personne.voyages.forEach( voyage => liToAppend+= ` - ${voyage.destination}`);
		liToAppend+= ` <button class="btn btn-danger btn-xs" data-title="Delete" data-toggle="modal" data-target="#delete" >X</button></v>`;

		$listePersonnes.append(liToAppend);
	}

	/* Ajout un voyage à une personne existante*/
	//pour une personne souhaitant réserver plusieurs voyages
	function addVoyageToPerson(idPersonne, idVoyage) {
	    $.ajax({
    			type: "POST",
    			url: "http://localhost:8080/api/personnes/"+idPersonne+"/voyages",
    			data: JSON.stringify({"idLivre" : idVoyage}),
    			contentType: "application/json; charset=utf-8",
    			dataType: "json",
    			success: function(data){
    				appendToListPersonne(data);
    			}
    	});
	}
});
