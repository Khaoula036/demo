$(document).ready(function() {
	let $listeDestinations = $("#listDestinations");
	

    $.get("http://localhost:8080/api/destinations",function(resp){
		resp.forEach( d => appendToListDestination(d));
    });


    $listeDestinations.on("click", "li button", function() {
    	let elemid = $(this).parent().attr('id');
    	
    	$.ajax({
		    type: "DELETE",
		    url: "http://localhost:8080/api/destinations/" + elemid.replace('destination-',''),
		    dataType: "json",
		    success: function(data){
		    	$("#"+elemid).remove();
		    }
		});	
    });

    $('#addbtnDestination').click(function(){
		let nom = $('#nom-input').val();
		let pays = $('#pays-input').val();

		
		$.ajax({
		    type: "POST",
		    url: "http://localhost:8080/api/destinations",
		    data: JSON.stringify({ "nom": nom, "pays" : pays }),
		    contentType: "application/json; charset=utf-8",
		    dataType: "json",
		    success: function(data){
		        appendToListDestination(data);
		    }
		});
		
		$('#nom-input').val('');
		$('#pays-input').val('');
		return false;
	})

    /* Ajoute un élément li dans la liste de destinations*/
	function appendToListDestination(destination) {
		$listeVoyages.append(`<li id="destination-${destination.id}" class="list-group-item">${destination.nom} - ${destination.pays} <button class="btn btn-danger btn-xs" data-title="Delete" data-toggle="modal" data-target="#delete" >X</button> </li>`);
	}

});

