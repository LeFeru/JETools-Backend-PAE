package ihm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.owlike.genson.Genson;

import business.dto.EntrepriseDto;
import business.dto.ParticipationDto;
import business.dto.PersonneContactDto;
import business.factories.BizFactory;
import business.ucc.interfaces.ParticipationUcc;
import exceptions.NotImplementedException;

public class ParticipationIhm {

  private Dealer dealer;
  private ParticipationUcc participationUcc;
  private BizFactory bizFactory;
  private Genson genson;
  private ParticipationDto participationDto;
  private List<EntrepriseDto> entrepriseDtos;
  private Logger logger;

  /**
   * Constructeur de la classe ParticipationIhm.
   * 
   * @param dealer Dealer passé en paramètre pour initialiser les attriuts.
   */
  public ParticipationIhm(Dealer dealer) {
    this.dealer = dealer;
    this.participationUcc = this.dealer.getParticipationUcc();
    this.bizFactory = this.dealer.getBizFactory();
    this.genson = new Genson();
    this.participationDto = bizFactory.getParticipationDto();
    this.entrepriseDtos = new ArrayList<>();
    this.logger = Logger.getLogger("reportsLogger");
  }

  /**
   * Redirige l'action en fonction de l'attribut action de l'HttpServletRequest.
   * 
   * @param req HttpServletRequest.
   * @return L'objet à parser et à renvoyer dans resp.
   */
  public Object operer(HttpServletRequest req) throws IOException {
    logger.info(req.toString());
    entrepriseDtos.clear();
    switch (req.getParameter("req")) {
      case "invitation":
        return inviter(req);
      case "selectionEntreprises":
        return selectionEntreprises(req);
      case "entreprisesNonSelectionnees":
        return entreprisesNonSelectionnees(req);
      case "entreprisesInvitees":
        return entreprisesInvitees(req);
      case "annulerParticipation":
        return annulerParticipation(req);
      case "participationsNonAnnulees":
        return participationNonAnnulees(req);
      case "modifierParticipation":
        return modifierParticipation(req);
      default:
        throw new NotImplementedException();
    }
  }

  private Object modifierParticipation(HttpServletRequest req) {
    ParticipationDto participation = getParticipationDto(req);
    participation = participationUcc.modifierParticipation(participation);
    logger.info("participation modifiée" + genson.serialize(participation));
    return participation;
  }

  private Object participationNonAnnulees(HttpServletRequest req) {
    ParticipationDto participation = getParticipationDto(req);
    List<ParticipationDto> liste = participationUcc.participationsNonAnnulees(participation);
    logger.info("liste participation non annulees :" + genson.serialize(liste));
    return liste;
  }

  private Object annulerParticipation(HttpServletRequest req) {
    ParticipationDto participation = getParticipationDto(req);
    participation = participationUcc.annulerParticipation(participation);
    logger.info("participation annulee");
    return participation;
  }

  private Object entreprisesInvitees(HttpServletRequest req) {
    List<EntrepriseDto> liste =
        participationUcc.entrepriseInvitees(Integer.parseInt(req.getParameter("idJE")));
    logger.info("entreprises invitées dude  :" + genson.serialize(liste));
    return liste;
  }

  private Object entreprisesNonSelectionnees(HttpServletRequest req) {
    List<EntrepriseDto> liste = participationUcc.listeEntrepriseNonSelectionnees();
    logger.info("selection entreprises non selectionnées " + genson.serialize(liste));
    return liste;
  }

  private Object selectionEntreprises(HttpServletRequest req) {
    List<EntrepriseDto> liste = participationUcc.listeSelectionEntreprises();
    logger.info("selection entreprises" + genson.serialize(liste));
    return liste;
  }

  private Object inviter(HttpServletRequest req) {
    deserializeEntreprise(req.getParameter("entreprises"));
    logger.info("entreprises ihm : " + req.getParameter("entreprises"));
    List<PersonneContactDto> listPersonnes = participationUcc.inviter(entrepriseDtos);
    return createFile(listPersonnes);
  }

  private String createFile(List<PersonneContactDto> listPersonnes) {
    StringBuilder sb = new StringBuilder();
    String csv1 = "Nom Entreprise;Nom personne de contact;";
    String csv2 = "Prénom personne de contact;Email personne de contact";
    sb.append(csv1 + csv2);
    for (PersonneContactDto personneContactDto : listPersonnes) {
      if (personneContactDto.getEmail() != null) {
        sb.append("\r");
        sb.append(personneContactDto.getNomEntreprise() + ";");
        sb.append(personneContactDto.getNom() + ";");
        sb.append(personneContactDto.getPrenom() + ";");
        sb.append(personneContactDto.getEmail());
      }
    }
    return sb.toString();
  }


  private ParticipationDto getParticipationDto(HttpServletRequest req) {
    if (req.getParameter("idParticipation") != null) {
      participationDto.setIdParticipation(Integer.parseInt(req.getParameter("idParticipation")));
    }
    if (req.getParameter("idJournee") != null) {
      participationDto.setIdJournee(Integer.parseInt(req.getParameter("idJournee")));
    }
    if (req.getParameter("idEntreprise") != null) {
      participationDto.setIdEntreprise(Integer.parseInt(req.getParameter("idEntreprise")));
    }
    if (req.getParameter("numVersion") != null) {
      participationDto.setNumVersion(Integer.parseInt(req.getParameter("numVersion")));
    }
    if (req.getParameter("nomEntreprise") != null) {
      participationDto.setNomEntreprise((String) req.getParameter("nomEntreprise"));
    }
    participationDto.setEvolution(null);
    if (req.getParameter("etat") != null) {
      String etat = (String) req.getParameter("etat");
      etat = etat.toLowerCase();
      if (etat.contains("é")) {
        etat = etat.replace('é', 'e');
      }
      if (etat.equals("payee") || etat.equals("facturee")) {
        participationDto.setEtat("confirmee");
        participationDto.setEvolution(etat);
      } else {
        participationDto.setEtat(etat);
      }
    }
    return participationDto;
  }

  private void deserializeEntreprise(String jsonEntreprises) {
    logger.info("jsonEntreprises " + jsonEntreprises);
    List<String> tmp = genson.deserialize(jsonEntreprises, ArrayList.class);
    for (String entreString : tmp) {
      // Si on envoie juste le nom de l'entrerpise
      EntrepriseDto entreprise = bizFactory.getEntrepriseDto();
      entreprise.setNomEntreprise(entreString);
      entrepriseDtos.add(entreprise);
      // Si on envoie map pour chaque entrerpise
      // Map<String, Object> temp = genson.deserialize(entreString,
      // Map.class);
      // EntrepriseDto entreprise = bizFactory.getEntrepriseDto();
      // if (temp.get("nomEntreprise") != null) {
      // entreprise.setNomEntreprise((String) temp.get("nomEntreprise"));
      // }
      // if (temp.get("rue") != null) {
      // entreprise.setRue((String) temp.get("rue"));
      // }
      // if (temp.get("numero") != null) {
      // entreprise.setNumero((String) temp.get("numero"));
      // }
      // if (temp.get("boite") != null) {
      // entreprise.setBoite((String) temp.get("boite"));
      // }
      // if (temp.get("codePostal") != null) {
      // entreprise.setCodePostal((String) temp.get("codePostal"));
      // }
      // if (temp.get("commune") != null) {
      // entreprise.setCommune((String) temp.get("commune"));
      // }
      // entrepriseDtos.add(entreprise);
    }
  }
}
