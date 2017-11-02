var utilitaires = (function(){
	
	// On définit une seule requête ajax qu'on réutilisera dans le code
	appelAjax = function(config){
		$.extend(config, {
			type: 'POST',
			url: '/all'
		});
		$.ajax(config);
	}
	
	// On récupère l'url courante lors du raffraichissement de la page
	var url = getUrl();
	
	// Evenement qui se déclenche quand on clique sur précédent ou suivant et met à jour l'affichage
	window.onpopstate = function(e){
		var url = getUrl();
		afficherPage(url);
		// On enlève la classe active aux différents liens du menu
		$("ul.sidebar-menu li ul.sub li a").removeClass("active");
		// On met à jour le menu selon l'url
		if(url === "Participations"){
			setTimeout(function(){
				if(!$("#btnSuivi").is(".active")){
					$("#btnSuivi").click()
				}
				$("#btnParticipations").addClass("active");
			},200);
		}
		else if(url === "Invitations"){
			setTimeout(function(){
				if(!$("#btnSuivi").is(".active")){
					$("#btnSuivi").click()
				}
				$("#btnInvitations").addClass("active");
			},200);
		}
		else if(url === "JE"){
			setTimeout(function(){
				if(!$("#btnGestion").is(".active")){
					$("#btnGestion").click();
				}
				$("#btnJE").addClass("active");
			},200);
		}
		else if(url === "Annuaire"){
			setTimeout(function(){
				if(!$("#btnGestion").is(".active")){
					$("#btnGestion").click()
				}
				$("#btnAnnuaire").addClass("active");
			},200);
		}
	};
	
	// On crée la variable ici afin d'élargir sa portée
	var utilisateur;
	// Vérifie si une session existe
	appelAjax({
		data : {
			"req" : "estConnecte"
		},
		success : function(s){
			//  On charge le select du menu, et une fois celui ci chargé, le reste sera chargé également dans chargerMenuJE
			utilisateur = s;
			chargerMenuJE();
		},
		error : function(e){
			// On met l'image de fond lors de l'écran de connexion / On affiche la box de connexion / On affiche le bouton d'inscription
			$("#container").addClass("sidebar-closed");
			$.backstretch("../theme/img/wall.jpg", {speed: 500});
			$("#login_page").css("display", "block");
			connexion.connexion.switchInscription.css("display", "block");
		}
	});

	// Déconnecte l'utilisateur
	$('#deconnexion').on('click',function(){
		utilitaires.appelAjax({
			data : {
				"req" : "deconnecterUtilisateur"
			},
			success : function(s){
				$(location).attr('href', '?');
			},
			error : function(e){

			}
		});
	});
	
	// Quand le select change
	$("#sidebar select").change(function(){
		var url = getUrl();
		if(url === "Participations"){
			participations.afficherPage();
		}
		else if(url === "Invitations"){
			invitations.afficherPage()
		}
		$.gritter.add({
			title : '',
			text : "Nouvelle journée sélectionnée.",
			time : 3500,
			class_name : 'gritter-light'
		});
	});
	
	//**************************************** Fonctions ****************************************
	
	/* Retourne l'url courante */
	function getUrl(){
		var tab = location.search.split("?");
		var url = tab[1];
		return url;
	}
	
	/* Charge les différentes JE dans le select du Menu*/
	function chargerMenuJE(){
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
				
				// On fait le reste du travail
				ouvrirMenu(url);
				$("#sidebar #nav-accordion h5").text(utilisateur["pseudo"]);
				afficherPage(url);
			},
			error : function(e){
				window.history.pushState({}, "JE", "/?JE");
				var url = getUrl();
				// On fait le reste du travail
				ouvrirMenu(url);
				$("#sidebar #nav-accordion h5").text(utilisateur["pseudo"]);
				afficherPage(url);
			}
		});
	}
	
	/* Ouvre le menu et affiche le bouton qui le gère */
	function ouvrirMenu(url){
		$("#container").removeClass("sidebar-closed");
		$(".sidebar-toggle-box").css("visibility","visible");
		
		// On supprime le bouton des invitations du menu si la personne co n'est pas responsable
		if(utilisateur["responsable"] === false)
			$("#btnInvitations").remove();
		
		// On met à jour le menu selon l'url passée en paramètre
		if(url === "Participations"){
			setTimeout(function(){
				$("#btnSuivi").click()
				$("#btnParticipations").addClass("active");
				document.title = "Participations";
			},200);
		}
		else if(url === "Invitations"){
			setTimeout(function(){
				$("#btnSuivi").click()
				$("#btnInvitations").addClass("active");
				document.title = "Invitations";
			},200);
		}
		else if(url === "JE"){
			setTimeout(function(){
				$("#btnGestion").click()
				$("#btnJE").addClass("active");
				document.title = "JE";
			},200);
		}
		else if(url === "Annuaire"){
			setTimeout(function(){
				$("#btnGestion").click()
				$("#btnAnnuaire").addClass("active");
				document.title = "Annuaire";
			},200);
		}
	}

	/* Affiche la bonne page selon l'url */
	function afficherPage(url){
		afficherFileAriane(url);
		cacherPage(url);
		switch(url){
			case "Participations":
				participations.chargerParticipations();
				$("#participations").css("display", "block");
				document.title = "Participations";
				break;
			case "Invitations":
				if(utilisateur["responsable"] ===  true){
					invitations.afficherPage();
				}
				break;
			case "JE":
				$("#JE").css("display", "block");
				document.title = "JE";
				break;
			case "Annuaire":
				annuaire.chargerEntreprises();
				annuaire.chargerContacts();
				$("#annuaire").css("display", "block");
				document.title = "Annuaire";
				break;
		}
	}

	/* cache toutes les pages sauf la page courante (url) */
	function cacherPage(url){
		if(url !== "Participations")
			$("#participations").css("display", "none");
		if(url !== "Invitations")
			$("#invitations").css("display", "none");
		if(url !== "JE")
			$("#JE").css("display", "none");
		if(url !== "Annuaire")
			$("#annuaire").css("display", "none");
	}
	
	function afficherFileAriane(url){
		var divSectionActuelle = $("#sectionActuelle");
		var divPageActuelle = $("#pageActuelle");
		
		// On met à jour le file d'Ariane selon l'url passée en paramètre
		if(url === "Participations"){
			divSectionActuelle.removeClass("fa fa-cogs");
			divSectionActuelle.addClass("fa fa-desktop");
			divSectionActuelle.text(" Suivi");
			divPageActuelle.text(" / Participations");
		}
		else if(url === "Invitations"){
			if(utilisateur["responsable"] ===  true){
				divSectionActuelle.removeClass("fa fa-cogs");
				divSectionActuelle.addClass("fa fa-desktop");
				divSectionActuelle.text(" Suivi");
				divPageActuelle.text(" / Invitations");
			}
		}
		else if(url === "JE"){
			divSectionActuelle.removeClass("fa fa-desktop");
			divSectionActuelle.addClass("fa fa-cogs");
			divSectionActuelle.text(" Gestion");
			divPageActuelle.text(" / JE");
		}
		else if(url === "Annuaire"){
			divSectionActuelle.removeClass("fa fa-desktop");
			divSectionActuelle.addClass("fa fa-cogs");
			divSectionActuelle.text(" Gestion");
			divPageActuelle.text(" / Annuaire");
		}
	}

	/* Convertit un formulaire en JSON */
	function formToJson(src) {
		var o = {};
		src.find('input, select').each(function() {
			o[$(this).context.name] = $(this).val().trim();
		});
		return o;
	}

	/* Convertit un JSON en formulaire */
	function jsonToForm(src, o) {
		src.find('input').each(function() {
			$(this).val(o[$(this).context.name]);
		});
	}
	
	return{
		appelAjax : appelAjax,
		getUrl : getUrl,
		afficherPage : afficherPage,
		cacherPage : cacherPage,
		chargerMenuJE : chargerMenuJE,
		ouvrirMenu : ouvrirMenu,
		formToJson : formToJson,
		jsonToForm : jsonToForm
	}
})()