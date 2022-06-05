$(document).ready(function() {
	let $listeLivres = $("#listLivres");
	let $listePersonnes = $("#listPersonnes");
	let $selectLivres = $("#selectLivres");

    $.get("http://localhost:8080/api/personnes",function(resp){
		resp.forEach( p => appendToListPersonne(p));
    });

	$.get("http://localhost:8080/api/livres",function(resp){
		resp.forEach( l => {
			appendToListLivre(l);
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

    $listeLivres.on("click", "li button", function() {
        	let elemid = $(this).parent().attr('id');
            let idLivre = elemid.replace('livre-','');

        	$.ajax({
    		    type: "DELETE",
    		    url: "http://localhost:8080/api/livres/" + idLivre,
    		    dataType: "json",
    		    success: function(data){
    		    	$("#"+elemid).remove();
    		    	$(`#selectLivres option[value="${idLivre}"]`).remove();
    		    },
                error: function (xhr, ajaxOptions, thrownError) {
                    console.log(xhr);
                    alert("Impossible de supprimer le livre, il est peut-être encore utilisé");
                }
    		});
        });
    
    $('#addbtnPersonne').click(function(){
		let prenom = $('#prenom-input').val();
		let age = $('#age-input').val();
		let idLivre = $selectLivres.val();
		
		$.ajax({
		    type: "POST",
		    url: "http://localhost:8080/api/personnes",
		    data: JSON.stringify({ "prenom": prenom, "age" : age }),
		    contentType: "application/json; charset=utf-8",
		    dataType: "json",
		    success: function(data){
		        addLivreToPerson(data.id, idLivre);
		    }
		});
		
		$('#prenom-input').val('');
		$('#age-input').val('');
		return false;
	});

	$('#addbtnLivre').click(function(){
		let livre = {
			titre: $('#title-input').val(),
			nbPages: $('#nbpage-input').val(),
			auteur: {
				nom: $('#nom-auteur-input').val(),
				prenom: $('#prenom-auteur-input').val()
			}
		};

		$.ajax({
			type: "POST",
			url: "http://localhost:8080/api/livres",
			data: JSON.stringify(livre),
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			success: function(data){
				appendToListLivre(data);
				appendToSelects(data);
			}
		});

		$('#title-input').val('');
		$('#nbpage-input').val('');
		$('#nom-auteur-input').val('');
		$('#prenom-auteur-input').val('');
		return false;
	})

	/* Ajoute un élément li dans la liste de livres*/
	function appendToListLivre(livre) {
		$listeLivres.append(`<li id="livre-${livre.id}" class="list-group-item">${livre.titre} - ${livre.nbPages} - ${livre.auteur.nom} ${livre.auteur.prenom} <button class="btn btn-danger btn-xs" data-title="Delete" data-toggle="modal" data-target="#delete" >X</button> </li>`);
	}

	/* Ajoute un élément li dans le select des livres*/
	function appendToSelects(livre) {
		$selectLivres.append(`<option value="${livre.id}">${livre.titre}</option>`)
	}

	/* Ajoute un élément li dans la liste de personne*/
	function appendToListPersonne(personne) {
	    let liToAppend = `<li id="personne-${personne.id}" class="list-group-item">${personne.prenom} ${personne.age}`;
	    personne.livres.forEach( livre => liToAppend+= ` - ${livre.titre}`);
		liToAppend+= ` <button class="btn btn-danger btn-xs" data-title="Delete" data-toggle="modal" data-target="#delete" >X</button></li>`;

		$listePersonnes.append(liToAppend);
	}

	/* Ajout un livre à une personne existante*/
	function addLivreToPerson(idPersonne, idLivre) {
	    $.ajax({
    			type: "POST",
    			url: "http://localhost:8080/api/personnes/"+idPersonne+"/livres",
    			data: JSON.stringify({"idLivre" : idLivre}),
    			contentType: "application/json; charset=utf-8",
    			dataType: "json",
    			success: function(data){
    				appendToListPersonne(data);
    			}
    	});
	}
});