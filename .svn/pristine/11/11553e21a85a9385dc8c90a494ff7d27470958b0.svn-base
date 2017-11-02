var inscription = (function(){
	
	var inscription = {
		switchConnexion : $("#btnHeaderConnexion"),
		boite : $("#registerBox"),
		btnInscrire : $("#inscription"),
		pseudo : $("#registerBox input[name=pseudo]"),
		nom : $("#registerBox input[name=nom]"),
		prenom : $("#registerBox input[name=prenom]"),
		email : $("#registerBox input[name=email]"),
		mdp : $("#registerBox input[name=mdp]"),
		confirmerMdp : $("#registerBox input[name=confirmerMdp]"),
		notification : $("#notificationRegister")
	}

	// Passage du mode inscription au mode connexion
	inscription.switchConnexion.click(function() {
		document.title = "Connexion";
		inscription.boite.css("display", "none");
		inscription.switchConnexion.css("display", "none");
		connexion.connexion.boite.css("display", "block");
		connexion.connexion.switchInscription.css("display", "block");
		// On réinitialise le formulaire de la boite d'inscription
		$("#infobox-wrapper").css("display", "none");
		inscription.notification.css("display", "none");
		$(".incorrect").each(function(){
			$(this).removeClass("incorrect");
		});
	});
	
	// Si tous les champs du formulaire d'inscription sont corrects
	var allFieldsOk = false;
	
	//**************************** Les champs ont le focus ****************************//
	
	inscription.pseudo.focus(function(){
		if(inscription.pseudo.is(".incorrect")){
			afficherInfoBox("-367px", "-357px", "Le format du pseudo est incorrect. Saisissez 4 à 5 <br> caractères sans espaces.");
		}else{
			$("#infobox-wrapper").css("display", "none");
		}
		// On enlève la classe incorrect 
		inscription.pseudo.removeClass("incorrect");
	});
	
	inscription.nom.focus(function(){
		if(inscription.nom.is(".incorrect")){
			afficherInfoBox("-317px", "-307px", "Le format du nom est incorrect.");
		}else{
			$("#infobox-wrapper").css("display", "none");
		}
		// On enlève la classe incorrect 
		inscription.nom.removeClass("incorrect");
	});
	
	inscription.prenom.focus(function(){
		if(inscription.prenom.is(".incorrect")){
			afficherInfoBox("-267px", "-257px", "Le format du prénom est incorrect.");
		}else{
			$("#infobox-wrapper").css("display", "none");
		}
		// On enlève la classe incorrect 
		inscription.prenom.removeClass("incorrect");
	});
	
	inscription.email.focus(function(){
		if(inscription.email.is(".incorrect")){
			afficherInfoBox("-213px", "-203px", "Le format de l'email est incorrect.");
		}else{
			$("#infobox-wrapper").css("display", "none");
		}
		// On enlève la classe incorrect 
		inscription.email.removeClass("incorrect");
	});
	
	inscription.mdp.focus(function(){
		if(inscription.mdp.is(".incorrect")){
			afficherInfoBox("-160px", "-150px", "Le format du mot de passe est incorrect.<br>Saisissez 8 à 20 caractères dont un chiffre.");
		}else{
			$("#infobox-wrapper").css("display", "none");
		}
		// On enlève la classe incorrect 
		inscription.mdp.removeClass("incorrect");
	});
	
	inscription.confirmerMdp.focus(function(){
		if(inscription.confirmerMdp.is(".incorrect")){
			afficherInfoBox("-110px", "-100px", "Les mots de passe ne correspondent pas.");
		}else{
			$("#infobox-wrapper").css("display", "none");
		}
		// On enlève la classe incorrect 
		inscription.confirmerMdp.removeClass("incorrect");
	}) 
	
	
	//**************************** Les champs perdent le focus ****************************//
	
	inscription.pseudo.focusout(function(){
		// Si le pseudo n'est pas d'un bon format
		if (!checkParam("pseudo"))
			inscription.pseudo.addClass("incorrect");
	});
	
	inscription.nom.focusout(function(){
		// Si le nom n'est pas entre 2 et 47 lettres
		if(!checkParam("nom"))
			inscription.nom.addClass("incorrect");
	});
	
	inscription.prenom.focusout(function(){
		// Si le prénom n'est pas entre 2 et 47 lettres
		if(!checkParam("prenom"))
			inscription.prenom.addClass("incorrect");
	});
	
	inscription.email.focusout(function(){
		// Si l'email n'est pas d'un bon format
		if (!checkParam("email"))
			inscription.email.addClass("incorrect");
	});
	
	inscription.mdp.focusout(function(){
		// Si le mot de passe n'est pas d'un bon format
		if(!checkParam("mdp"))
			inscription.mdp.addClass("incorrect");
		if (!checkParam("confirmerMdp"))
			inscription.confirmerMdp.addClass("incorrect");
		else
			inscription.confirmerMdp.removeClass("incorrect");
	});
	
	inscription.confirmerMdp.focusout(function(){
		// Si la confirmation de mot de passe n'est pas la même
		if (!checkParam("confirmerMdp"))
			inscription.confirmerMdp.addClass("incorrect");
	})
	
	// Traitement de la soumission du formulaire d'inscription
	inscription.btnInscrire.on("click",function(e){
		$("#infobox-wrapper").css("display", "none");
		e.preventDefault();
		var pseudo = inscription.pseudo.val();
		var nom = inscription.nom.val();
		var prenom = inscription.prenom.val();
		var email = inscription.email.val();
		var mdp = inscription.mdp.val();
		var confirmerMdp = inscription.confirmerMdp.val();
		
		// Si il n y a plus de champ incorrect
		if($(".incorrect").length === 0){
			allFieldsOk = true;
		}
		
		// Si un des champs est vide
		if(pseudo.length == 0 || nom.length == 0 || prenom.length == 0 || email.length == 0 || mdp.length == 0 || confirmerMdp.length == 0){
			inscription.notification.html("<b>Erreur</b>, des champs sont manquants.");
			inscription.notification.css("display","block");
			var keys = Object.keys(inscription);
			for(var i=0; i<keys.length; i++){
				if(i > 2 && i < 9){
					if(inscription[keys[i]].val().length === 0 || !checkParam(keys[i]))
						inscription[keys[i]].addClass("incorrect");
				}
			}
		}
		else if(allFieldsOk){
			utilitaires.appelAjax({
				data : {
					"req" : "inscrireUtilisateur",
					"utilisateur" : JSON.stringify(utilitaires.formToJson($("#registerBox")))
				},
				// On a réussi notre inscription
				success : function(response){
					inscription.notification.attr("class","alert alert-success");
					inscription.notification.html("<b>Réussite</b>, inscription effectuée avec succès.");
					inscription.notification.css("display","block");
				},
				// On a soit le pseudo qui est déjà pris, soit une erreur inconnue
				error : function(e){
					inscription.notification.attr("class","alert alert-danger");
					inscription.notification.html("<b>Erreur</b>, "+e.responseText+".");
					inscription.notification.css("display","block");
				}
			});
		}
	});
	
	// Affiche la bulle d'information concernant les champs incorrects
	var afficherInfoBox = function(topInfoBox, topFlecheBox, text){
		$("#infobox-wrapper").css("display", "block");
		$(".infobox").css("top", topInfoBox);
		$(".infobox").html(text);
		$(".fleche-box").css("top", topFlecheBox);
	}
	
	// Test les divers paramètres du formulaire d'inscription
	var checkParam = function(param){
		switch(param){
			case "pseudo":
				var pseudo = inscription.pseudo.val();
				if (!/^[a-zA-Z-_.0-9]{4,15}$/.test(pseudo))
					return false;
				return true;
				break;
			case "nom":
				var nom = inscription.nom.val();
				if(!/^[a-zA-Zéèêç-]{2,47}$/.test(nom))
					return false;
				return true;
				break;
			case "prenom":
				var prenom = inscription.prenom.val();
				if(!/^[a-zA-Zéèêç-]{2,47}$/.test(prenom))
					return false;
				return true;
				break;
			case "email":
				var email = inscription.email.val();
				if (!/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(email))
					return false;
				return true;
				break;
			case "mdp":
				var mdp = inscription.mdp.val();
				if(!/^((?=.*\d)(?=.*[A-Za-z&@"'(§è!çà)\-_^$¨*[\]ùµ%£´`,?;.:\/+=<>])\S{8,20})$/.test(mdp))
					return false;
				return true;
				break;
			case "confirmerMdp":
				var mdp = inscription.mdp.val();
				var confirmerMdp = inscription.confirmerMdp.val();
				if (mdp !== confirmerMdp && confirmerMdp !== "")
					return false;
				return true;
				break;
		}
	}

	return{
		"inscription" : inscription
	}
	
})()