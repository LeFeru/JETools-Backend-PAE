package ihm;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.owlike.genson.Genson;

import business.dto.EntrepriseDto;
import business.dto.ParticipationDto;
import business.dto.UtilisateurDto;
import business.factories.BizFactory;
import business.ucc.interfaces.EntrepriseUcc;
import exceptions.NotImplementedException;

public class EntrepriseIhm {
  private final Logger logger;
  private Dealer dealer;
  private EntrepriseUcc entrepriseUcc;
  private BizFactory bizFactory;
  private Genson genson;
  private EntrepriseDto entrepriseDto;

  /**
   * Constructeur de la classe EntrepriseIhm.
   * 
   * @param dealer Dealer passé en paramètre pour initialiser les attriuts.
   */
  public EntrepriseIhm(Dealer dealer) {
    // TODO Auto-generated constructor stub
    this.dealer = dealer;
    this.entrepriseUcc = this.dealer.getEntrepriseUcc();
    this.bizFactory = this.dealer.getBizFactory();
    this.genson = new Genson();
    this.entrepriseDto = bizFactory.getEntrepriseDto();
    this.logger = Logger.getLogger("reportsLogger");
  }

  /**
   * Redirige l'action en fonction de l'attribut action de l'HttpServletRequest.
   * 
   * @param req HttpServletRequest.
   * @return Object renvoyé à la servlet et ensuite au Front-End.
   */
  public Object operer(HttpServletRequest req) throws IOException {
    // EntrepriseDto entreprise = getEntrepriseDto(req);
    switch (req.getParameter("req")) {
      case "creerEntreprise":
        return creerEntreprise(req);
      case "afficherEntreprises":
        return afficherEntreprises(req);
      case "modifierEntreprise":
        return modifierEntreprise(req);
      case "chargerEntreprise":
        return chargerEntreprise(req);
      case "historiqueParticipations":
        return chargerHistorique(req);
      default:
        throw new NotImplementedException();
    }
  }

  private Object chargerHistorique(HttpServletRequest req) {
    EntrepriseDto entreprise = getEntrepriseDto(req);
    logger.info(entreprise);
    List<ParticipationDto> historique = entrepriseUcc.historiqueEntreprise(entreprise);
    logger.info(genson.serialize(historique));
    // TODO Auto-generated method stub
    return historique;
  }

  private EntrepriseDto getEntrepriseDto(HttpServletRequest req) {
    Map<String, Object> temp = new HashMap<>();
    if (req.getParameter("entreprise") != null) {
      temp = genson.deserialize(req.getParameter("entreprise"), Map.class);
      logger.info(temp);
    }
    if (req.getParameter("idEntreprise") != null) {
      entrepriseDto.setIdEntreprise(Integer.parseInt(req.getParameter("idEntreprise")));
    }
    if (req.getParameter("numVersion") != null) {
      entrepriseDto.setNumVersion(Integer.parseInt(req.getParameter("numVersion")));
    }
    if (temp.get("nomEntreprise") != null) {
      entrepriseDto.setNomEntreprise((String) temp.get("nomEntreprise"));
    }
    if (temp.get("rue") != null) {
      entrepriseDto.setRue((String) temp.get("rue"));
    }
    if (temp.get("numero") != null) {
      entrepriseDto.setNumero((String) temp.get("numero"));
    }
    if (temp.get("boite") != null) {
      entrepriseDto.setBoite((String) temp.get("boite"));
    }
    if (temp.get("codePostal") != null) {
      entrepriseDto.setCodePostal((String) temp.get("codePostal"));
    }
    if (temp.get("commune") != null) {
      entrepriseDto.setCommune((String) temp.get("commune"));
    }
    return entrepriseDto;
  }

  private ArrayList<EntrepriseDto> afficherEntreprises(HttpServletRequest req) throws IOException {
    ArrayList<EntrepriseDto> entreprises = entrepriseUcc.listeEntreprises();
    logger.info(genson.serialize(entreprises));
    return entreprises;
  }

  private void setPourRenvoi(EntrepriseDto entreprise) {
    entreprise.setDateCreation(null);
    entreprise.getCreateur().setMdp(null);
    entreprise.getCreateur().setEmail(null);
    entreprise.getCreateur().setDateInscription(null);
  }

  private Object creerEntreprise(HttpServletRequest req) throws IOException {
    entrepriseDto = getEntrepriseDto(req);
    Map<String, Object> temp =
        genson.deserialize((String) req.getSession().getAttribute("utilisateur"), Map.class);
    UtilisateurDto createur = bizFactory.getUtilisateurDto();
    createur.setId(((Long) temp.get("id")).intValue());
    createur.setEmail((String) temp.get("email"));
    createur.setMdp((String) temp.get("mdp"));
    createur.setPseudo((String) temp.get("pseudo"));
    createur.setNom((String) temp.get("nom"));
    createur.setPrenom((String) temp.get("prenom"));
    createur.setResponsable((boolean) temp.get("responsable"));
    entrepriseDto.setCreateur(createur);
    entrepriseDto.setDateCreation(LocalDateTime.now());
    entrepriseDto = entrepriseUcc.creerEntreprise(entrepriseDto);
    logger.info("Entreprise inscrite: " + entrepriseDto.getNomEntreprise());
    setPourRenvoi(entrepriseDto);
    return entrepriseDto;
  }

  private Object modifierEntreprise(HttpServletRequest req) throws IOException {
    EntrepriseDto entreprise = getEntrepriseDto(req);
    // entreprise.setIdEntreprise(Integer.valueOf(req.getParameter("idEntreprise")));
    // entreprise.setNumVersion(Integer.valueOf(req.getParameter("numVersion")));

    entreprise = entrepriseUcc.modifierEntreprise(entreprise);

    logger.info("entreprise modifiée");
    return entreprise;
  }

  private Object chargerEntreprise(HttpServletRequest req) throws IOException {
    EntrepriseDto retour = getEntrepriseDto(req);
    retour = entrepriseUcc.chargerEntreprise(retour.getIdEntreprise());
    return retour;
  }


}
