var invitations = (function() {
	
	var tableEntreprises;
	var tableEntreprisesNonSelectionnees;
	var tableEntreprisesInvitees;
	
	var mois = new Array("", "Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Décembre");
	var notificationAjoutEntreprise = $("#notificationAjoutEntreprise");
	
	// Si les tableaux ont déjà été initialisés par dataTable
	var initEntreprises = false;
	var initEntreprisesNonSelectionnees = false;
	var initEntreprisesInvitees = false;
	// Si cloturée ou pas
	var etatJE;
	
	// Objet qui représente la petite fenêtre contextuelle qui affiche des infos concernant une société
	var contextualWindowObject = {
		contextualWindow : $("#contextualWindow"),
		containerImageLoading : $("#containerImageLoading"), // div qui contient l'image de chargement
		containerNotif : $("#containerNotif"),
		containerTable : $("#containerTable"),
	}

	// Permet de se rendre sur la page des participations
	$("#btnInvitations").click(function() {
		// On update l'URL / On récupère la nouvelle url / On affiche la page actuelle
		window.history.pushState({}, "JE", "/?Invitations")
		var url = utilitaires.getUrl();
		utilitaires.afficherPage(url);
	});

	// Exporte la liste des entreprises et envoit les infos au backend
	$("#exportList").click(function() {
		exportList();
	});
	
	// Exporte la liste des entreprises séléctionnées PLUS celles déjà invitées et envoit les infos au backend
	$("#exportCompleteList").click(function() {
		exportList(true);
	});

	// Quand on clique sur le bouton "Ajouter des entreprises", on supprime une éventuelle ancienne notif
	$("#ajouterEntreprisesNonSelectionnees").click(function() {
		notificationAjoutEntreprise.css("display", "none");
	});

	// Quand on clique pour ajouter les entreprises non sélectionnées au tableau des préselections
	$("#addToPreselection").click(function() {
		var tabInput = $("#tableEntreprisesNonSelectionnees input:checked").each(function() {
			var data = tableEntreprisesNonSelectionnees.row($(this).parents('tr')).data();
			tableEntreprises.row.add(data).draw();
			tableEntreprisesNonSelectionnees.row($(this).parents('tr')).remove().draw();
		});
		if (tabInput.length === 0) {
			notificationAjoutEntreprise.removeClass("alert-success");
			notificationAjoutEntreprise.addClass("alert-danger");
			notificationAjoutEntreprise.text("Aucune entreprise sélectionnée.");
			notificationAjoutEntreprise.css("display", "block");
		} else {
			notificationAjoutEntreprise.removeClass("alert-danger");
			notificationAjoutEntreprise.addClass("alert-success");
			notificationAjoutEntreprise.text("Les entreprises ont été ajoutées au tableau des présélections.");
			notificationAjoutEntreprise.css("display", "block");
		}
	});
	
	
	/*******************************************   FONCTIONS   *********************************************/
	
	// Fonction qui permet de lancer le mécanisme afin d'exporter la liste des entreprises.
	// Le parametre completeList est un boolean qui permet de savoir si on doit également exporter les entreprises déjà invitées
	function exportList(completeList){
		var tabTemp = [];
		// On récupère les entreprises checked du tableau des entreprises préselectionnées
		$("input:checked", tableEntreprises.rows().nodes()).each(function() {
			tabTemp.push($(this).val());
		});
		
		if(completeList){
			// On récupère les entreprises dans le tableau des entreprises invitées
			$(tableEntreprisesInvitees.rows().data()).each(function(){
				tabTemp.push(this["nomEntreprise"]);
			});
		}
		
		utilitaires.appelAjax({
			data : {
				'req' : 'invitation',
				"entreprises" : JSON.stringify(tabTemp)
			},
			success : function(s) {
				var csv = s.replace(/"/g, "");
				csv = csv.replace(/\\r/g, "\r");
				download(csv, "Invitations.csv", "text/csv");
				$.gritter.add({
					title : 'Succès',
					text : "Les entreprises ont été invitées !",
					time : 3500,
					class_name : 'gritter-light'
				});
				// On reload le tableau des entreprises préselectionées et celui des entreprises invitées
				tableEntreprises.ajax.reload();
				tableEntreprisesInvitees.ajax.reload();
			},
			error : function(e) {
				$.gritter.add({
					title : 'Erreur',
					text : e.responseText,
					time : 3500,
					class_name : ''
				});
			}
		});
	}

	// Permet de cocher/décocher toutes les checkbox du tableau des entreprises présélectionnées
	function checkAllInputForTableEntreprises() {
		$("#tableEntreprises-invitations input[type=checkbox]").each(function() {
			if ($(this).is(":checked")) {
				$(this).prop("checked", false);
			} else {
				$(this).prop("checked", true);
			}
		});
	}
	
	// Affiche une fenetre contextuelle (à une position relative à celle de la souris) contenant les informations sur les participations d'une société
	function attachContextualWindow(event, objThis, direction, idEntreprise) {
		if(direction === "left"){
			// Affiche la fenetre contextuelle sur la gauche de la page
			contextualWindowObject.contextualWindow.css("top", event.pageY - 20);
			contextualWindowObject.contextualWindow.css("left", event.pageX - 400);
		}else{
			// Affiche la fenetre contextuelle sur la droite de la page
			contextualWindowObject.contextualWindow.css("top", event.pageY - 20);
			contextualWindowObject.contextualWindow.css("left", event.pageX - 10);
		}
		
		contextualWindowObject.contextualWindow.css("display", "block");

		utilitaires.appelAjax({
			data : {
				"req" : "historiqueParticipations",
				"idEntreprise" : idEntreprise
			},
			success : function(s) {
				var participations = s;
				// On vide le tableau et on fait l'appel ajax pr chercher les informations concernant l'entreprises idEntreprise
				$("#contextualWindow tbody > tr").remove();
				for (var i = 0; i < participations.length; i++) {
					var etat;
					if (participations[i].annulee)
						etat = '<td><span class="label label-danger">Annulée</span></td>';
					else {
						if (participations[i].etat === "invitee") {
							etat = '<td><span class="label label-primary">Invitée</span></td>';
						} else if (participations[i].etat === "refusee") {
							etat = '<td><span class="label label-default">Refusée</span></td>';
						} else if (participations[i].etat === "confirmee") {
							etat = '<td><span class="label label-success">Confirmée</span> ';
							if (participations[i].evolution === "payee") {
								etat += '<span class="label label-success">Payée</span></td>';
							} else if (participations[i].evolution === "facturee") {
								etat += '<span class="label label-warning">Facturée</span></td>';
							} else {
								etat += "</td>";
							}
						}
					}
					$("#contextualWindow tbody").append("<tr><td>" + participations[i].dateJe.dayOfMonth + "/" + participations[i].dateJe.monthValue + "/" + participations[i].dateJe.year + "</td>" + etat + "</tr>")
				}
				contextualWindowObject.containerImageLoading.css("display", "none");
				if (participations.length === 0) {
					contextualWindowObject.containerNotif.css("display", "block");
				} else
					contextualWindowObject.containerTable.css("display", "block");
			}
		});
	}

	// Quand on quitte la div contextuelle qui affiche les participations des entreprises non selectionnées
	contextualWindowObject.contextualWindow.mouseleave(function() {
		contextualWindowObject.containerNotif.css("display", "none");
		contextualWindowObject.containerImageLoading.css("display", "block");
		contextualWindowObject.contextualWindow.css("display", "none")
		contextualWindowObject.containerTable.css("display", "none");
	});
	
	// Charge les entreprises d'au moins 4 ans et ayant déjà participées au moins une fois et les affiche
	function chargerEntreprises() {
		if (!initEntreprises) {
			initEntreprises = true;
			tableEntreprises = $('#tableEntreprises-invitations').DataTable({
				"bInfo" : false,
				"lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
				"language": {
		            "url": "../json/french.json"
		        },
				"ajax" : {
					"url" : '/all?req=selectionEntreprises',
					"type" : 'POST',
					"dataSrc" : "",
				},
				"aoColumns" : [
					{
						"mData" : "nomEntreprise"
					},
					{
						"mData" : "rue",
						"mRender" : function(data, type, full) {
							return full.rue + " " + full.numero + ", " + full.commune + " " + full.codePostal;
						}
					},
					{
						"mData" : "dateCreation",
						"mRender" : function(data, type, full) {
							return full.dateCreation.dayOfMonth + " " + mois[full.dateCreation.monthValue] + " " + full.dateCreation.year;
						}
					},
					{
						"mData" : "boite",
						"mRender" : function(data, type, full) {
							return '<input type="checkbox" name="selection" value="' + full.nomEntreprise + '" />' + '<span class="fa fa-info-circle" onmouseenter="invitations.attachContextualWindow(event, this, ' +"'left'"+ ',' + full.idEntreprise
							+ ')" style="margin-left: 10px; margin-top: 4px; vertical-align:top;"></span>' ;
						}
					} 
				]
			});

			$("#rechercheEntreprise-invitations").on("keyup", function() {
				tableEntreprises.search(this.value).draw();
			});
		} else {
			tableEntreprises.ajax.reload();
		}
	}

	// Charge les entreprises qui n'entrent pas dans les critères de selection (4 ans et déjà payé une participations ou nouvelles créee dans l'année écoulée)
	function chargerEntreprisesNonSelectionnees() {
		if (!initEntreprisesNonSelectionnees) {
			initEntreprisesNonSelectionnees = true;
			tableEntreprisesNonSelectionnees = $('#tableEntreprisesNonSelectionnees').DataTable({
				"bInfo" : false,
				"lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
				"language": {
		            "url": "../json/french.json"
		        },
				"ajax" : {
					"url" : '/all?req=entreprisesNonSelectionnees',
					"type" : 'POST',
					"dataSrc" : "",
				},
				"aoColumns" : [
					{
						"mData" : "nomEntreprise"
					},
					{
						"mData" : "rue",
						"mRender" : function(data, type, full) {
							return full.rue + " " + full.numero + ", " + full.commune + " " + full.codePostal;
						}
					},
					{
						"mData" : "dateCreation",
						"mRender" : function(data, type, full) {
							return full.dateCreation.dayOfMonth + " " + mois[full.dateCreation.monthValue] + " " + full.dateCreation.year;
						}
					},
					{
						"mData" : "boite",
						"mRender" : function(data, type, full) {
							return '<input type="checkbox" name="selection" />' + '<span class="fa fa-info-circle" onmouseenter="invitations.attachContextualWindow(event, this,' +"'left'"+ ',' + full.idEntreprise
									+ ')" style="margin-left: 10px; margin-top: 4px; vertical-align:top;"></span>';
						}
					} 
				]
			});

			$("#rechercheEntrepriseNonSelectionnee").on("keyup", function() {
				tableEntreprisesNonSelectionnees.search(this.value).draw();
			});
		} else {
			tableEntreprisesNonSelectionnees.ajax.reload();
		}
	}

	// Charge les entreprises invitées pour la journée sélectionnée
	function chargerEntreprisesInvitees() {
		if(!initEntreprisesInvitees){
			initEntreprisesInvitees = true;
			tableEntreprisesInvitees = $('#tableEntreprisesInvitees').DataTable({
				"bInfo" : false,
				"lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
				"language": {
		            "url": "../json/french.json"
		        },
				"ajax" : {
					"url" : '/all?req=entreprisesInvitees',
					"type" : 'POST',
					"dataSrc" : function(json){
						// Si il y a des données, on affiche alors le tableau des entreprises invitées
						if(json.length > 0){
							$("#rowTablesEntreprisesInvitees").css("display", "block");
							if(etatJE["cloturee"] === false){
								$("#rowButtons2").css("display", "block");
							}
						}
						else{
							$("#rowButtons2, #rowTablesEntreprisesInvitees").css("display", "none");
							// On affiche le message ci dessous uniquement si la journée que l'utilisateur visualise a été cloturée
							if(etatJE["cloturee"] === true){
								setTimeout(function(){
									$.gritter.add({
										title : 'Attention',
										text : "Il n y a eu aucune invitation pour la journée sélectionnée !",
										time : 3500,
										class_name : ''
									});
								},500);
							}
						}
						return json;
					},
					"data" : function () {
						return {"idJE" : $('#sidebar select').val()};
				    }
				},
				"aoColumns" : [
					{
						"mData" : "nomEntreprise"
					},
					{
						"mData" : "rue",
						"mRender" : function(data, type, full) {
							return full.rue + " " + full.numero + ", " + full.commune + " " + full.codePostal;
						}
					},
					{
						"mData" : "dateCreation",
						"mRender" : function(data, type, full) {
							return full.dateCreation.dayOfMonth + " " + mois[full.dateCreation.monthValue] + " " + full.dateCreation.year;
						}
					}
				]
			});
			
			$("#rechercheEntrepriseInvitees").on("keyup", function() {
				tableEntreprisesInvitees.search(this.value).draw();
			});
		}
		else{
			tableEntreprisesInvitees.ajax.reload();
		}
	}
	
	// Permet le téléchargement d'un csv
	function download(strData, strFileName, strMimeType) {
		var D = document,
		a = D.createElement("a");
		strMimeType= strMimeType || "application/octet-stream";

	    if (navigator.msSaveBlob) { // IE10
	    	return navigator.msSaveBlob(new Blob([strData], {type: strMimeType}), strFileName);
	    } /* end if(navigator.msSaveBlob) */

	    if ('download' in a) { //html5 A[download]
	    	a.href = "data:" + strMimeType + ";charset=UTF-8,\ufeff" + encodeURIComponent(strData);
	    	a.setAttribute("download", strFileName);
	    	D.body.appendChild(a);
	    	setTimeout(function() {
	    		a.click();
	    		D.body.removeChild(a);
	    	}, 66);
	    	return true;
	    } /* end if('download' in a) */


	    //do iframe dataURL download (old ch+FF):
	    var f = D.createElement("iframe");
	    D.body.appendChild(f);
	    f.src = "data:" +  strMimeType   + ";charset=UTF-8,\ufeff" + encodeURIComponent(strData);

	    setTimeout(function() {
	    	D.body.removeChild(f);
	    }, 333);
	    return true;
	}
	
	// Affiche le contenu de la page invitations
	function afficherPage() {
		// On effectue une requete ajax afin de connaitre l'état de la JE sélectionée dans le select
		utilitaires.appelAjax({
			"data" : {
				"req" : "verifierJourneeActuelle",
				"idJournee" : $('#sidebar select').val()
			},
			success : function(s) {
				etatJE = s;
				$("#invitations").css("display", "block");
				document.title = "Invitations";
				// Charger les entreprises invitées si il y en a
				chargerEntreprisesInvitees();
				
				// Si la journée n'est pas cloturée on affiche les entreprises sélectionnées et non sélectionnées afin de pouvoir les inviter
				if(etatJE["cloturee"] === false){
					chargerEntreprisesNonSelectionnees();
					chargerEntreprises();
					// On met un setTimout afin de laisser le temps au tableau d'être vider et rechargé av les nouvelles données
					setTimeout(function(){$("#invitations .row:first, #rowButtons").css("display", "block")
					},70);
				}else{
					// On cache le tableau des entreprises préselectionées ainsi que les divers boutons
					$("#invitations .row:first, #rowButtons, #rowButtons2").css("display", "none");
				}
			},
			error : function(e){
				console.log(e.responseText);
			}
		});
	}

	return {
		afficherPage : afficherPage,
		checkAllInputForTableEntreprises : checkAllInputForTableEntreprises,
		attachContextualWindow : attachContextualWindow,
	}

})()