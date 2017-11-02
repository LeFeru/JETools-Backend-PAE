var JE = (function(){
	
	$( "#datepicker" ).datepicker();
	$( "#datepicker" ).datepicker("option", "dateFormat", "yy-mm-dd");
	
	// Permet de se rendre sur la page des participations
	$("#btnJE").click(function(){
		// On update l'URL / On récupère la nouvelle url / On cache la page précédente et on affiche l'actuelle
		$("#sidebar").css("display","none");
		$("#sidebar").css("display","block");
		window.history.pushState({}, "JE", "/?JE");
		var url = utilitaires.getUrl();
		utilitaires.afficherPage(url);
		$("#notificationCreationJE").css("display", "none");
		$("#champDate").val("");
	});
	
	// Une fois qu'on clique sur le bouton pour enregistrer la JE
	$("#enregistrerJE").click(function(){
		// aaaa-mm-jj (si via chrome)
		var dateJE = $("#JE input[name=dateJe]").val();
		
		// On effectue différents test afin de voir si le format de la date entré est correct
		if(dateJE.length == 0){
			$("#notificationCreationJE").css("display", "block");
			$("#notificationCreationJE").attr("class","alert alert-danger");
			$("#notificationCreationJE").html("<b>Erreur.</b> Veuillez spécifier une date.");
		}
		else if(!/^(\d{4})-(\d{1,2})-(\d{1,2})$/.test(dateJE) && !/^(\d{1,2})-(\d{1,2})-(\d{4})$/.test(dateJE) && !/^(\d{1,2})\/(\d{1,2})\/(\d{4})$/.test(dateJE)){ /* Format aaaa-mm-jj || jj-mm-aaaa */
			$("#notificationCreationJE").css("display", "block");
			$("#notificationCreationJE").attr("class","alert alert-danger");
			$("#notificationCreationJE").html("<b>Erreur.</b> Le format de date que vous avez entré est incorrect. Entrez une date au format <b>aaa-mm-jj</b>");
		}
		else{
			// On remplace les / si jamais il y en a par -
			dateJE = dateJE.replace(/\//g,"-");
			// On formate la date si besoin et on envoit les informations au backend afin de créer la JE
			if(/^(\d{1,2})-(\d{1,2})-(\d{4})$/.test(dateJE)){ /* Format jj-mm-aaaa */
				var tabDate = dateJE.split("-");
				dateJE = tabDate[2] + "-" + tabDate[1] + "-" + tabDate[0];
			}
			console.log(dateJE);
			var dateJson = {
					"dateJe" : dateJE
			}
			utilitaires.appelAjax({
				data : {
					'req' : 'creerJournee',
					'journee' : JSON.stringify(dateJson)
				},
				success : function(s) {
					s = s;
					var tabDate = dateJE.split("-");
					dateJE = tabDate[2] + "-" + tabDate[1] + "-" + tabDate[0];
					reloadJE();
					$("#notificationCreationJE").css("display", "block");
					$("#notificationCreationJE").attr("class","alert alert-success");
					$("#notificationCreationJE").html("<b>JE créée</b>. la journée d'entreprise en date du " + dateJE +" a été créée.");
				},
				// Sinon c'est qu'on a introduit des mauvais champs
				error : function(e) {
					$("#notificationCreationJE").css("display", "block");
					$("#notificationCreationJE").attr("class","alert alert-danger");
					$("#notificationCreationJE").html("<b>Erreur</b>. " + e.responseText);
				}
			});
		}
	});
	
	// Une fois qu'on clique sur le bouton pour cloturer une JE
	$("#cloturerJE").click(function(){
		utilitaires.appelAjax({
			data : {
				"req" : "cloturerJournee",
				"idJournee" : $("#nav-accordion select :selected").val()
			},
			success : function(){
				reloadJE();
				$.gritter.add({
					title : 'Succès',
					text : "La journée actuelle a bien été cloturée !",
					time : 3500,
					class_name : 'gritter-light'
				});
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
	});

	function reloadJE(){
		$("#sidebar select > option").remove();
		appelAjax({
			data : 'req=getJournees',
			success : function(s){
				var listeJE = s;
				for(je in listeJE){
					var date = "JE - "+ listeJE[je].dateJe.dayOfMonth +"/" + listeJE[je].dateJe.monthValue + "/" + listeJE[je].dateJe.year;
					if(listeJE[je].cloturee === true){
						date += "<i>(cloturée)</i>";
					}
					$("#sidebar select").append("<option value='"+listeJE[je].idJournee+"'>"+date+"</option>");
				}
				$("#sidebar select option:last").prop("selected",true);
			}
		});
	}
	
})()