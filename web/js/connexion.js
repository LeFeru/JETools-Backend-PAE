var connexion = (function(){
	
	var connexion = {
		switchInscription : $("#btnHeaderRegister"),
		boite : $("#loginBox"),
		btnConnexion : $("#connexion"),
		notification : $("#notificationLogin"),
		login : $("#loginBox input[name=pseudo]"),
		mdp : $("#loginBox input[name=mdp]"),
	}
	
	// Passage du mode connexion au mode inscription
	connexion.switchInscription.click(function() {
		document.title = "Inscription";
		connexion.boite.css("display", "none");
		connexion.switchInscription.css("display", "none");
		inscription.inscription.boite.css("display", "block");
		inscription.inscription.switchConnexion.css("display", "block");
		// On réinitialise les champs du formulaire de connexion
		connexion.notification.css("display", "none");
		connexion.login.val("");
		connexion.mdp.val("");
	});

	// Traitement de la soumission du formulaire de connexion
	connexion.btnConnexion.on("click",function(e) {
		e.preventDefault();
		var login = connexion.login.val();
		var mdp = connexion.mdp.val();
		
		// Si un des champs est vide, on affiche un message d'erreur
		if (login.length == 0 || mdp.length == 0) {
			connexion.notification.html("<b>Erreur</b>, des champs sont manquants.");
			connexion.notification.css("display","block");
		} 
		else {
			// Si le login n'est pas d'un bon format
			if(!/^[a-zA-Z-_.0-9]{4,15}$/.test(login)){
				connexion.notification.html("<b>Erreur</b>, format du login incorrect.");
				connexion.notification.css("display","block");
			}
			// Si le mot de passe n'est pas d'un bon format
			else if (!/^((?=.*\d)(?=.*[A-Za-z&@"'(§è!çà)\-_^$¨*[\]ùµ%£´`,?;.:\/+=<>])\S{8,20})$/.test(mdp)){
				connexion.notification.html("<b>Erreur</b>, format du mot de passe incorrect.");
				connexion.notification.css("display","block");
			}
			else{
				utilitaires.appelAjax({
					data : {
						"req" : "connecterUtilisateur",
						"utilisateur" : JSON.stringify(utilitaires.formToJson($("#loginBox")))
					},
					// Si on a réussi une connexion, on affiche l'accueil et on ajoute un cookie
					success : function(reponse) {
						// On cache la boite de connexion et on affiche celle de la connexion réussie
						connexion.boite.css("display", "none");
						$("#connexion_reussie").css("display", "block");
						// On recharge la page  (permet notamment de virer le background img...)
						setTimeout(function(){
							$(location).attr('href', '?Participations');
						}, 1500);
					},
					// Sinon c'est qu'on a introduit des mauvais champs
					error : function(e) {
						//console.log(e.statusCode());
						connexion.notification.html("<b>Erreur</b>, combinaison login/mot de passe incorrecte.");
						connexion.notification.css("display","block");
					}
				});
			}
		}
	});

	return{
		"connexion" : connexion
	}
	
})();