var participations = (function(){
	
	//var objUtilitaires = utilitaires;
	var initParticipations = false;
	var initContacts = false;
	var tableParticipationContacts;
	var tableParticipations;
	var idEntreprises = {};
	// Si cloturée ou pas
	var etatJE;

	// Permet de se rendre sur la page des participations
	$("#btnParticipations").click(function(){
		// On update l'URL / On récupère la nouvelle url / On cache la page précédente et on affiche l'actuelle
		window.history.pushState({}, "Participations", "/?Participations")
		var url = utilitaires.getUrl();
		utilitaires.afficherPage(url);
	});


	// Charge les participations pour la JE sélectionnée 
	function chargerParticipations(){
		$("#participationsContacts").css("display","none");
		if(initParticipations)
			$("#tableParticipations").dataTable().fnDestroy();
		initParticipations = true;
		tableParticipations = $("#tableParticipations").DataTable({
			"bInfo" : false,
			"lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
			"language": {
				"url": "../json/french.json"
			},
			"ajax": {
				"url": '/all',
				"type": 'POST',
				"dataSrc" : "",
				"data" : function(){
					return {
						"req" : "participationsNonAnnulees",
						"idJournee" : $("#nav-accordion select :selected").val()
					}
				}
			},
			"aoColumns" : [
			{
				"mData" : "nomEntreprise",
				"mRender" : function(data,type,full){
					idEntreprises[full.idEntreprise] = full.nomEntreprise;
					return '<a href="javascript:void(0)" onclick="participations.afficherPersonnesContacts(\''+full.idEntreprise+'\')">'+full.nomEntreprise+'</a>';
				}
			},
			{
				"mData" :"etat",
				"mRender" : function(data,type,full){
					return '<select onchange="participations.modifierParticipation(this.value,'+full.numVersion+','+full.idParticipation+','+full.idEntreprise+')">'+creerSelectEtat(full.etat,full.idParticipation,full.evolution);
				}
			},
			{
				"mData" :"annulee",
				"mRender" : function(data,type,full){
					if(full.annulee)
						return '<i class="fa fa-check"></i>';
					return '<i class="fa fa-times"></i>';
				}
			},
			{
				"mData" : "idParticipation",
				"mRender" : function(data,type,full){
					if (full.annulee)
						return '<button class="btn btn-success btn-xs" onclick="participations.showInfoCompanyPartModal('+full.idEntreprise+')"><i class="fa fa-info-circle"></i></button> <button class="btn btn-theme04 btn-xs disabled"><i class="fa fa-ban"></i></button>';
					return '<button class="btn btn-success btn-xs" onclick="participations.showInfoCompanyPartModal('+full.idEntreprise+')"><i class="fa fa-info-circle"></i></button> <button class="btn btn-theme04 btn-xs" onclick="participations.annulerParticipation('+full.idParticipation+','+full.numVersion+')"><i class="fa fa-ban"></i></button>';
				}
			}
			],
			"columnDefs": [
			{ "width": "40%", "targets": 0 },
			{ "width": "40%", "targets": 1 },
			{ "width": "20%", "targets": 2 },
			{ "width": "20%", "targets": 3 }
			]
		});

		$("#rechercheEntreprisesPart").on("keyup",function(){
			tableParticipations.search(this.value).draw();
		});
		$("#rechercheContactsPart").on("keyup",function(){
			tableParticipationContacts.search(this.value).draw();
		});
	}

	function creerSelectEtat(etatCourant,idParticipation,evolution){
		var select = '';
		if(etatCourant === "invitee")
			select += '<option value="invitee" selected>Invitée</option>';
		if(etatCourant === "refusee")
			select += '<option value="refusee" selected>Refusée</option>';
		else if(etatCourant === "invitee")
			select += '<option value="refusee">Refusée</option>';
		if(etatCourant === "confirmee" && evolution === null)
			select += '<option value="confirmee" selected>Confirmée</option>';
		else if(etatCourant === "invitee")
			select += '<option value="confirmee">Confirmée</option>';
		if(etatCourant ==="confirmee" && evolution === "facturee")
			select += '<option value="facturee" selected>Facturée</option>';
		else if(etatCourant === "invitee" || (etatCourant === "confirmee" && evolution === null))
			select += '<option value="facturee">Facturée</option>';
		if(etatCourant === "confirmee" && evolution === "payee")
			select += '<option value="payee" selected>Payée</option></select>';
		else if(etatCourant === "invitee" || (etatCourant === "confirmee" && evolution === null) || (etatCourant === "confirmee" && evolution === "facturee"))
			select += '<option value="payee">Payée</option></select>';
		return select;
	}



	// Affiche les personnes de contact pour l'entreprise sélectionnée
	function afficherPersonnesContacts(idEntreprise){
		$(".nomSocietePart").text(idEntreprises[idEntreprise]);
		if(initContacts)
			$("#tableContactsPart").dataTable().fnDestroy();
		else
			initContacts = true;
		tableParticipationContacts = $("#tableContactsPart").DataTable({
			"bInfo" : false,
			"lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
			"language": {
				"url": "../json/french.json"
			},
			"ajax": {
				"url": '/all',
				"type": 'POST',
				"dataSrc" : "",
				"data" : function(){
					return {
						"req" : "listerContactsParEntreprise",
						"idJournee" : $("#nav-accordion select :selected").val(),
						"nomEntreprise" : idEntreprises[idEntreprise]
					}
				}
			},
			"aoColumns" : [
			{"mData" : "nom"},
			{"mData" :"prenom"},
			{
				"mData" : "idPersonne",
				"mRender" : function(data,type,full){
					if(!full.present)
						return '<input type="checkbox" value="'+full.idPersonne+'"/>';
					return '<i class="fa fa-check"></i>'
				}
			}
			]
		});
		$("#participationsContacts").css("display","block");
	}

	// Lorsqu'on sauve les personnes présentes
	$("#savePartContacts").click(function(){
		var personnes = [];
		$("#tableContactsPart input:checked").each(function(index){
			personnes[index] = $(this).val();
		});
		if(personnes.length > 0){

			utilitaires.appelAjax({
				data : {
					"req" : "indiquerPresence",
					"nomEntreprise" : $(".nomSocietePart").text(),
					"idPersonnes" : JSON.stringify(personnes),
					"idJournee" : $("#nav-accordion select :selected").val()
				},
				success : function(){
					$.gritter.add({
						title : 'Succès',
						text : "Les personnes sélectionnées ont été indiquées comme présentes !",
						time : 3500,
						class_name : 'gritter-light'
					});
					tableParticipationContacts.ajax.reload();
				},
				error : function(e){
					$.gritter.add({
						title : 'Erreur',
						text : e.responseText,
						time : 3500,
						class_name : ''
					});
				}
			});
		}
		else{
			$.gritter.add({
				title : 'Erreur',
				text : "Veuillez sélectionner au moins une personne.",
				time : 3500,
				class_name : ''
			});
		}
	});

	function showInfoCompanyPartModal(idEntreprise){
		$("#modalInfoCompanyPart tbody > tr").remove();
		utilitaires.appelAjax({
			data : {
				"req" : "historiqueParticipations",
				"idEntreprise" : idEntreprise
			},
			success : function(s){
				var participations = s;
				for(var i = 0; i < participations.length; i++){
					var etat;
					if(participations[i].annulee)
						etat = '<td><span class="label label-danger">Annulée</span></td>';
					else{
						if(participations[i].etat === "invitee"){
							etat = '<td><span class="label label-primary">Invitée</span></td>';
						}
						else if(participations[i].etat === "refusee"){
							etat = '<td><span class="label label-default">Refusée</span></td>';
						}
						else if(participations[i].etat === "confirmee"){
							etat = '<td><span class="label label-success">Confirmée</span> ';
							if(participations[i].evolution === "payee"){
								etat += '<span class="label label-success">Payée</span></td>';
							}
							else if(participations[i].evolution === "facturee"){
								etat += '<span class="label label-warning">Facturée</span></td>';
							}
							else{
								etat += "</td>";
							}
						}
					}
					$("#modalInfoCompanyPart tbody").append("<tr><td>"+participations[i].dateJe.dayOfMonth+"/"+participations[i].dateJe.monthValue+"/"+participations[i].dateJe.year+"</td>"+etat+"</tr>")
				}
				$("#modalInfoCompanyPart").modal("show");
			}
		});
	}

	// Annuler une participation
	function annulerParticipation(idParticipation,numVersion){
		utilitaires.appelAjax({
			data : {
				"req" : "annulerParticipation",
				"idJournee" :  $("#nav-accordion select :selected").val(),
				"idParticipation" : idParticipation,
				"numVersion" : numVersion
			},
			success : function(){
				$.gritter.add({
					title : 'Succès',
					text : "La participation a bien été annulée !",
					time : 3500,
					class_name : 'gritter-light'
				});
				tableParticipations.ajax.reload();
				$("#participationsContacts").css("display","none");
			},
			error : function(e){
				$.gritter.add({
					title : 'Erreur',
					text : e.responseText,
					time : 3500,
					class_name : ''
				});
			}
		});
	}

	// Modifie l'état d'une participation
	function modifierParticipation(etat,numVersion,idParticipation,idEntreprise){
		utilitaires.appelAjax({
			data : {
				"req" : "modifierParticipation",
				"idParticipation" : idParticipation,
				"idJournee" : $("#nav-accordion select :selected").val(),
				"numVersion" : numVersion,
				"nomEntreprise" : idEntreprises[idEntreprise],
				"etat" : etat
			},
			success : function(){
				$.gritter.add({
					title : 'Succès',
					text : "La participation a bien été modifiée !",
					time : 3500,
					class_name : 'gritter-light'
				});
				tableParticipations.ajax.reload();
				$("#participationsContacts").css("display","none");
			},
			error : function(e){
				$.gritter.add({
					title : 'Erreur',
					text : e.responseText,
					time : 3500,
					class_name : ''
				});
			}
		});
	}

	// Affiche le contenu de la page participations
	function afficherPage() {
		// On effectue une requete ajax afin de connaitre l'état de la JE sélectionée dans le select et afficher les éléments en conséquence
		$.ajax({
			"type" : 'POST',
			"url": '/all',
			"data" : {
				"req" : "verifierJourneeActuelle",
				"idJournee" : $('#sidebar select').val()
			},
			success : function(s) {
				etatJE = s;
				$("#participations").css("display", "block");
				document.title = "Participations";
				chargerParticipations();
			}
		});
	}


	return {
		chargerParticipations : chargerParticipations,
		afficherPersonnesContacts : afficherPersonnesContacts,
		annulerParticipation : annulerParticipation,
		modifierParticipation : modifierParticipation,
		afficherPage : afficherPage,
		showInfoCompanyPartModal : showInfoCompanyPartModal
	}

})()