var annuaire = (function(){
	
	/********* PAGE D'ANNUAIRE *********/
	var mois=new Array("","Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Décembre");
	var tableEntreprises;
	var tableContacts;
	var initEntreprise = false;
	var initContact = false;
	
	// Permet de se rendre sur la page d'annuaire
	$("#btnAnnuaire").click(function(){
		// On update l'URL / On récupère la nouvelle url / On cache la page précédente et on affiche l'actuelle / On cache les box de notification
		window.history.pushState({}, "Annuaire", "/?Annuaire");
		var url = utilitaires.getUrl();
		utilitaires.afficherPage(url);
		cleanFormEntreprise();
		cleanFormContact();
	});

	/*


	PARTIE ENTREPRISES


	*/

	// Si on soumet le formulaire de création d'entreprise
	$("#modalAddCompany_submit").click(function(){
		if(checkEntreprise($("#modalAddCompany"),$("#notificationCreationEntreprise"))){
			utilitaires.appelAjax({
				data : {
					'req' : 'creerEntreprise',
					"entreprise" : JSON.stringify(utilitaires.formToJson($("#modalAddCompany")))
				},
				// L'entreprise a été créée
				success : function(s){
					$("#notificationCreationEntreprise").css("display", "block");
					$("#notificationCreationEntreprise").attr("class","alert alert-success");
					$("#notificationCreationEntreprise").html("<b>Entreprise créée</b>. l'entreprise portant le nom " + $("#modalAddCompany input[name=nomEntreprise]").val() +" a été créée.");
					$("#modalAddContact select").append("<option>"+$("#modalAddCompany input[name=nomEntreprise]").val()+"</option>");
					tableEntreprises.ajax.reload();
				},
				// Un des champs n'a pas le format voulu
				error : function(e){
					$("#notificationCreationEntreprise").css("display", "block");
					$("#notificationCreationEntreprise").attr("class","alert alert-danger");
					$("#notificationCreationEntreprise").html("<b>Erreur</b>. " + e.responseText);
				}
			});
		}
	});

	// Editer une entreprise
	function editEntreprise(id){
		utilitaires.appelAjax({
			data : {
				'req' : 'chargerEntreprise',
				'idEntreprise' : id
			},
			success : function(s){
				var societe = s;
				utilitaires.jsonToForm($("#modalUpdateCompany"),societe);
				$("#modalUpdateCompany_submit").unbind("click");
				$("#modalUpdateCompany_submit").click(function(){
					if(checkEntreprise($("#modalUpdateCompany"),$("#notificationUpdateCompany"))){
						utilitaires.appelAjax({
							data : {
								"req" : "modifierEntreprise",
								"idEntreprise" : id,
								"numVersion" : societe.numVersion,
								"entreprise" : JSON.stringify(utilitaires.formToJson($("#modalUpdateCompany")))
							},
							success : function(){
								tableEntreprises.ajax.reload();
								$("#notificationUpdateCompany").css("display","block");
								$("#notificationUpdateCompany").attr("class","alert alert-success");
								$("#notificationUpdateCompany").html("<b>Entreprise modifiée.</b> L'entreprise a été modifiée avec succès.");
							},
							error : function(e){
								$("#notificationUpdateCompany").css("display","block");
								$("#notificationUpdateCompany").attr("class","alert alert-danger");
								$("#notificationUpdateCompany").html("<b>Erreur.</b> "+e.responseText);
							}
						});
					}
				});
				$("#modalUpdateCompany").modal("show");
				$("#notificationUpdateCompany").css("display","none");
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

	// Accéder aux infos de la société
	function infoCompany(id){
		$("#modalInfoCompany tbody > tr").remove();
		utilitaires.appelAjax({
			data : {
				"req" : "historiqueParticipations",
				"idEntreprise" : id
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
					$("#modalInfoCompany tbody").append("<tr><td>"+participations[i].dateJe.dayOfMonth+"/"+participations[i].dateJe.monthValue+"/"+participations[i].dateJe.year+"</td>"+etat+"</tr>")
				}
				$("#modalInfoCompany").modal("show");
			}
		});
	}

	// Cleaner le formulaire entreprise
	function cleanFormEntreprise(){
		$("#modalAddCompany input").each(function(){
			$(this).val("");
		})
		$("#notificationCreationEntreprise").css("display", "none");
	}

	// Charges les entreprises dans le select du formulaire d'ajout de contact
	function chargerEntreprisesIntoSelect(){
		utilitaires.appelAjax({
			data : 'req=afficherEntreprises',
			success : function(s){
				var tableau = s;
				for(var i = 0; i < tableau.length; i++){
					$("#modalAddContact select[name=entrepriseContact]").append("<option>"+tableau[i].nomEntreprise+"</option>");
				}
			}
		});
	}

	// Charger les entreprises dans le tableau
	function chargerEntreprises(){
		// Si le select du formulaire d'ajout d'un contact est vide
		if($("#modalAddContact select[name=entrepriseContact]").val() === null)
			chargerEntreprisesIntoSelect();
		if(!initEntreprise){
			initEntreprise = true;
			tableEntreprises = $('#tableEntreprises').DataTable({
				"bInfo" : false,
				"lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
				"language": {
					"url": "../json/french.json"
				},
				"ajax": {
					"url": '/all?req=afficherEntreprises',
					"type": 'POST',
					"dataSrc" : "",
				},
				"aoColumns" : [
				{"mData" : "nomEntreprise"},
				{
					"mData" :"rue",
					"mRender" : function(data,type,full){
						return full.rue+" "+full.numero+((full.boite!=null)?("/"+full.boite):"")+", "+full.commune+" "+full.codePostal;
					}
				},
				{
					"mData" : "dateCreation",
					"mRender" : function(data,type,full){
						return full.dateCreation.dayOfMonth+" "+mois[full.dateCreation.monthValue]+" "+full.dateCreation.year;
					}
				},
				{
					"mData" : "boite",
					"mRender" : function(data,type,full){
						return '<button class="btn btn-primary btn-xs" onclick="annuaire.editEntreprise('+full.idEntreprise+')"><i class="fa fa-pencil"></i></button> '+
						'<button class="btn btn-success btn-xs" onclick=\'annuaire.infoCompany('+full.idEntreprise+')\'><i class="fa fa-info-circle"></i></button>';
					}
				}
				]
			});

			$("#rechercheEntreprise").on("keyup",function(){
				tableEntreprises.search(this.value).draw();
			});
		}
		else{
			tableEntreprises.ajax.reload();
		}
	}

	// Vérification des parametres pour création/modification d'entreprise
	function checkEntreprise(formulaire, notifBox){
		// Si des champs sont vides, on le notifie
		if(formulaire.find("input[name=nomEntreprise]").val().length == 0 || formulaire.find("input[name=rue]").val().length == 0 || formulaire.find("input[name=numero]").val().length == 0 || formulaire.find("input[name=codePostal]").val().length == 0 || formulaire.find("input[name=commune]").val().length == 0){
			notifBox.css("display","block");
			notifBox.attr("class","alert alert-danger");
			notifBox.html("<b>Erreur.</b> Veillez à remplir tous les champs avant de réessayer.");
			return false;
		}
		// Si le nom fait plus de 150 caractères
		else if (!/^.{1,150}$/.test(formulaire.find("input[name=nomEntreprise]").val())){
			notifBox.css("display","block");
			notifBox.attr("class","alert alert-danger");
			notifBox.html("<b>Erreur.</b> Le format du nom n'est pas valide. (max. 150 caractères)");
			return false;
		}
		// Si la rue fait plus de 150 caractères
		else if (!/^.{1,150}$/.test(formulaire.find("input[name=rue]").val())){
			notifBox.css("display","block");
			notifBox.attr("class","alert alert-danger");
			notifBox.html("<b>Erreur.</b> Le format de la rue n'est pas valide. (max. 150 caractères)");
			return false;
		}
		// Si le numéro n'est pas seulement composé de chiffres et de lettres
		else if (!/^[0-9a-zA-Z]{1,10}$/.test(formulaire.find("input[name=numero]").val())){
			notifBox.css("display","block");
			notifBox.attr("class","alert alert-danger");
			notifBox.html("<b>Erreur.</b> Le format du numéro n'est pas valide. (max. 10 caractères)");
			return false;
		}
		// Si la boîte n'est pas seulement composée de chiffres et de lettres
		else if (formulaire.find("input[name=boite]").val().length != 0 &&!/^[0-9a-zA-Z]{1,10}$/.test(formulaire.find("input[name=boite]").val())){
			notifBox.css("display","block");
			notifBox.attr("class","alert alert-danger");
			notifBox.html("<b>Erreur.</b> Le format de la boîte postale n'est pas valide. (max. 10 caractères)");
			return false;
		}
		// Si le code postal n'est pas seulement composé de chiffres et de lettres
		else if (!/^[ 0-9-a-zA-Z]{1,10}$/.test(formulaire.find("input[name=codePostal]").val())){
			notifBox.css("display","block");
			notifBox.attr("class","alert alert-danger");
			notifBox.html("<b>Erreur.</b> Le format du code postal n'est pas valide. (max. 10 caractères)");
			return false;
		}
		// Si la commune fait plus de 75 caractères
		else if (!/^.{1,75}$/.test(formulaire.find("input[name=commune]").val())){
			notifBox.css("display","block");
			notifBox.attr("class","alert alert-danger");
			notifBox.html("<b>Erreur.</b> Le format de la commune n'est pas valide. (max. 75 caractères)");
			return false;
		}
		return true;
	}

	// Cleaner le formulaire entreprise quand on appuie sur le button
	$("#modalAddCompany_reset").click(cleanFormEntreprise);




	/*


	FIN DE LA PARTIE ENTREPRISES


	*/



	/*


	PARTIE PERSONNES DE CONTACT


	*/


	// Si on soumet le formulaire de création de contact
	$("#modalAddContact_submit").click(function(){
		if(checkPersonneContact($("#modalAddContact"),$("#notificationCreationContact"))){
			utilitaires.appelAjax({
				data : {
					'req' : 'creerPersonneContact',
					'personneContact' : JSON.stringify(utilitaires.formToJson($("#modalAddContact")))
				},
				success : function(){
					tableContacts.ajax.reload();
					$("#notificationCreationContact").css("display","block");
					$("#notificationCreationContact").attr("class","alert alert-success");
					$("#notificationCreationContact").html("<b>Contact créé</b>. "+$("#modalAddContact input[name=prenom]").val()+" "+$("#modalAddContact input[name=nom]").val()+" a été créé.");

				},
				error : function(e){
					$("#notificationCreationContact").css("display","block");
					$("#notificationCreationContact").attr("class","alert alert-danger");
					$("#notificationCreationContact").html("<b>Erreur.</b> "+e.responseText);
				}
			});
		}
	});

	// Editer un contact
	function editContact(id){
		appelAjax({
			data : {
				req : 'chargerContact', 
				idPersonne : id 
			},
			success : function(s){
				var contact = s;
				utilitaires.jsonToForm($("#modalUpdateContact"),contact);
				$("#modalUpdateContact_submit").unbind("click");
				$("#modalUpdateContact_submit").click(function(){
					if(checkPersonneContact($("#modalUpdateContact"), $("#notificationUpdateContact"))){
						utilitaires.appelAjax({
							data : {
								'req' : 'modifierContact',
								'idPersonne' : id,
								'numVersion' : contact.numVersion,
								'personneContact' : JSON.stringify(utilitaires.formToJson($("#modalUpdateContact")))  
							},
							success : function(){
								tableContacts.ajax.reload();
								$("#notificationUpdateContact").css("display","block");
								$("#notificationUpdateContact").attr("class","alert alert-success");
								$("#notificationUpdateContact").html("<b>Contact modifié</b>. Le contact a été modifié avec succès.");
							},
							error : function(e){
								$("#notificationUpdateContact").css("display","block");
								$("#notificationUpdateContact").attr("class","alert alert-danger");
								$("#notificationUpdateContact").html("<b>Erreur</b>. "+e.responseText);
							}
						});
					}
				});
				$("#modalUpdateContact_desactivate").unbind("click");
				$("#modalUpdateContact_desactivate").click(function(){
					appelAjax({
						data : {
							'req' : 'desactiverContact',
							'idPersonne' : id,
							'numVersion' : contact.numVersion
						},
						success : function(){
							tableContacts.ajax.reload();
							$("#notificationUpdateContact").css("display","block");
							$("#notificationUpdateContact").attr("class","alert alert-success");
							$("#notificationUpdateContact").html("<b>Contact désactivé</b>. Le contact a été désactivé avec succès.");
						},
						error : function(e){
							$("#notificationUpdateContact").css("display","block");
							$("#notificationUpdateContact").attr("class","alert alert-danger");
							$("#notificationUpdateContact").html("<b>Erreur</b>. "+e.responseText);
						}
					});
				});
				$("#modalUpdateContact").modal("show");
				$("#notificationUpdateContact").css("display","none");
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

	// Cleaner le formulaire contact
	function cleanFormContact(){
		$("#modalAddContact input").each(function(){
			$(this).val("");
		})
		$("#notificationCreationContact").css("display", "none");
	}

	// Charger les contacts dans le tableau
	function chargerContacts(){
		if(!initContact){
			initContact = true;
			tableContacts = $("#tableContacts").DataTable({
				"bInfo" : false,
				"lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
				"language": {
					"url": "../json/french.json"
				},
				"order": [[4, "asc" ]],
				"ajax" : {
					"url" : "/all?req=afficherContacts",
					"type" : "POST",
					"dataSrc" : ""
				},
				"aoColumns" : [
				{"mData" : "nom"},
				{"mData" : "prenom"},
				{"mData" : "email"},
				{"mData" : "telephone"},
				{"mData" : "nomEntreprise"},
				{
					"mData" : "prenom",
					"mRender" : function(data,type,full){
						return '<button class="btn btn-primary btn-xs" onclick="annuaire.editContact('+full.idPersonne+');"><i class="fa fa-pencil"></i></button>';
					}
				}
				]
			});

			$("#rechercheContact").on("keyup",function(){
				tableContacts.search(this.value).draw();
			});
		}
		else{
			tableContacts.ajax.reload();
		}
	}

	// Vérification d'une personne de contact avant ajout/édition
	function checkPersonneContact(formulaire, infoBox){
		// S'il n'y a ni téléphone, ni email
		if(formulaire.find("input[name=telephone]").val().length == 0 && formulaire.find("input[name=email]").val().length == 0){
			infoBox.css("display","block");
			infoBox.attr("class","alert alert-danger");
			infoBox.html("<b>Erreur.</b> Veillez à fournir un email ou un numéro de téléphone.");
			return false;	
		}

		// S'il manque des champs
		else if(formulaire.find("input[name=nom]").val().length == 0 || formulaire.find("input[name=prenom]").val().length == 0){
			infoBox.css("display","block");
			infoBox.attr("class","alert alert-danger");
			infoBox.html("<b>Erreur.</b> Veillez à remplir tous les champs avant de réessayer.");
			return false;
		}

		// Si le nom n'est pas valide
		else if(!/^[a-zA-Zéèêç-]{2,20}( )?[a-zA-Zéèêç-]{0,20}$/.test(formulaire.find("input[name=nom]").val())){
			infoBox.css("display","block");
			infoBox.attr("class","alert alert-danger");
			infoBox.html("<b>Erreur.</b> Le format du nom n'est pas valide (2 à 47 caractères).");
			return false;
		}

		// Si le prénom n'est pas valide
		else if(!/^[a-zA-Zéèêç-]{2,20}( )?[a-zA-Zéèêç-]{0,20}$/.test(formulaire.find("input[name=prenom]").val())){
			infoBox.css("display","block");
			infoBox.attr("class","alert alert-danger");
			infoBox.html("<b>Erreur.</b> Le format du prénom n'est pas valide (2 à 47 caractères).");
			return false;
		}

		// Si l'email n'est pas valide
		else if(formulaire.find("input[name=email]").val().length != 0 && !/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(formulaire.find("input[name=email]").val())){
			infoBox.css("display","block");
			infoBox.attr("class","alert alert-danger");
			infoBox.html("<b>Erreur.</b> Le format de l'email n'est pas valide.");
			return false;
		}

		// Si le téléphone n'est pas valide
		else if(formulaire.find("input[name=telephone]").val().length != 0 && !/^(0|\+[0-9]{2}|00[0-9]{2})[0-9]{6,10}$/.test(formulaire.find("input[name=telephone]").val())){
			infoBox.css("display","block");
			infoBox.attr("class","alert alert-danger");
			infoBox.html("<b>Erreur.</b> Le numéro de téléphone n'est pas valide.");
			return false;
		}
		return true;
	}

	// Cleaner le formulaire contact quand on appuie sur le bouton
	$("#modalAddContact_reset").click(cleanFormContact);

	return{
		chargerEntreprises : chargerEntreprises,
		chargerContacts : chargerContacts,
		editContact : editContact,
		editEntreprise : editEntreprise,
		infoCompany : infoCompany
	}
	/********* FIN ANNUAIRE *********/
})()