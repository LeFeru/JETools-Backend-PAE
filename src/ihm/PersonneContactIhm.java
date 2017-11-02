package ihm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.owlike.genson.Genson;

import business.dto.PersonneContactDto;
import business.factories.BizFactory;
import business.ucc.interfaces.PersonneContactUcc;
import exceptions.NotImplementedException;

public class PersonneContactIhm {
  private final Logger logger;
  private Dealer dealer;
  private PersonneContactUcc personneContactUcc;
  private BizFactory bizFactory;
  private Genson genson;
  private PersonneContactDto personneContactDto;

  /**
   * Constructeur PersonneContactIhm
   *
   * @param dealer Dealer passé en paramètre pour initialiser les attributs.
   */
  public PersonneContactIhm(Dealer dealer) {
    // TODO Auto-generated constructor stub
    this.dealer = dealer;
    this.personneContactUcc = this.dealer.getPersonneContactUcc();
    this.bizFactory = this.dealer.getBizFactory();
    this.genson = new Genson();
    this.personneContactDto = bizFactory.getPersonneContactDto();
    this.logger = Logger.getLogger("reportsLogger");
  }

  /**
   * 
   * @param req HttpServletRequest
   * @return L'objet à parser.
   * @throws IOException une IOException
   */
  public Object operer(HttpServletRequest req) throws IOException {
    switch (req.getParameter("req")) {
      case "creerPersonneContact":
        return creerPersonneContact(req);
      case "afficherContacts":
        return afficherContacts(req);
      case "chargerContact":
        return chargerContact(req);
      case "modifierContact":
        return modifierContact(req);
      case "desactiverContact":
        return desactiverContact(req);
      default:
        throw new NotImplementedException();
    }
  }

  private PersonneContactDto getPersonneDto(HttpServletRequest req) {
    Map<String, Object> temp = new HashMap<>();
    if (req.getParameter("personneContact") != null) {
      temp = genson.deserialize(req.getParameter("personneContact"), Map.class);
    }
    // if (temp.get("idPersonne") != null) {
    // personneContactDto.setIdPersonne(((Long) temp.get("nom")).intValue());
    // }
    if (req.getParameter("idPersonne") != null) {
      personneContactDto.setIdPersonne(Integer.parseInt(req.getParameter("idPersonne")));
    }
    if (req.getParameter("numVersion") != null) {
      personneContactDto.setNumVersion(Integer.parseInt(req.getParameter("numVersion")));;
    }
    if (temp.get("nom") != null) {
      personneContactDto.setNom((String) temp.get("nom"));
    }
    if (temp.get("prenom") != null) {
      personneContactDto.setPrenom((String) temp.get("prenom"));
    }
    if (temp.get("email") != null) {
      personneContactDto.setEmail((String) temp.get("email"));
    }
    if (temp.get("telephone") != null) {
      personneContactDto.setTelephone((String) temp.get("telephone"));
    }
    if (temp.get("entrepriseContact") != null) {
      String nomTemp = (String) temp.get("entrepriseContact");
      logger.info(nomTemp);
      personneContactDto.setNomEntreprise(nomTemp);
    }
    return personneContactDto;
  }



  private Object creerPersonneContact(HttpServletRequest req) throws IOException {
    PersonneContactDto personne = getPersonneDto(req);
    personne = personneContactUcc.creerPersonneContact(personne);
    logger.info("personne inscrite " + personne.getNom() + " " + personne.getPrenom());
    return personne;
  }

  private Object afficherContacts(HttpServletRequest req) throws IOException {
    ArrayList<PersonneContactDto> personnes = personneContactUcc.listeContacts();
    logger.info(genson.serialize(personnes));
    return personnes;
  }

  private Object chargerContact(HttpServletRequest req) throws IOException {
    // Integer idPersonne = Integer.valueOf(req.getParameter("idPersonne"));
    PersonneContactDto personne = getPersonneDto(req);
    PersonneContactDto retour = personneContactUcc.chargerContact(personne.getIdPersonne());
    return retour;
  }

  private Object modifierContact(HttpServletRequest req) throws IOException {
    PersonneContactDto personne = getPersonneDto(req);
    // logger.info(personne);
    // personne.setIdPersonne(Integer.valueOf(req.getParameter("idPersonne")));
    // personne.setNumVersion(Integer.valueOf(req.getParameter("numVersion")));
    personne = personneContactUcc.modifierContact(personne);

    /*
     * int idPersonne = personne.getIdPersonne(); if (idPersonne == -1) {
     * logger.info("infos obsoletes"); resp.setStatus(412);
     * resp.getWriter().write("rafrachissez la page les infos ont été modifiés entre temps"); } else
     * if (idPersonne <= -2) { logger.info("personne déjà existante"); resp.setStatus(412);
     * resp.getWriter().write("une personne possede déjà ce numero de telephone ou ce mail"); } else
     * {
     */
    logger.info("personne modifiée");
    return personne;
    // }

  }

  private Object desactiverContact(HttpServletRequest req) throws IOException {
    PersonneContactDto personne = getPersonneDto(req);
    logger.info(personne.getIdPersonne());
    personneContactDto.setIdPersonne(personneContactUcc.desactiverContact(personne));
    logger.info("personne désactivée");
    return personne;
  }

}
