package ihm;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.owlike.genson.Genson;

import business.dto.JourneeDto;
import business.dto.UtilisateurDto;
import business.factories.BizFactory;
import business.ucc.interfaces.JourneeUcc;
import exceptions.NotImplementedException;

public class JourneeIhm {
  private final Logger logger;
  private Dealer dealer;
  private JourneeUcc journeeUcc;
  private BizFactory bizFactory;
  private Genson genson;

  /**
   * Constructeur de la classe JourneeIhm.
   * 
   * @param dealer Dealer passé en paramètre pour initialiser les attriuts.
   */
  public JourneeIhm(Dealer dealer) {
    // TODO Auto-generated constructor stub
    this.dealer = dealer;
    this.journeeUcc = this.dealer.getJourneeUcc();
    this.bizFactory = this.dealer.getBizFactory();
    this.genson = new Genson();
    this.logger = Logger.getLogger("reportsLogger");
  }

  /**
   * Redirige l'action en fonction de l'attribut action de l'HttpServletRequest.
   * 
   * @param req HttpServletRequest.
   */
  public Object operer(HttpServletRequest req) throws IOException {
    JourneeDto journeeDto = getJourneeDto(req);
    switch (req.getParameter("req")) {
      case "creerJournee":
        return creerJournee(req, journeeDto);
      case "getJournees":
        return getJournees();
      case "verifierJourneeActuelle":
        return verifierJourneeActuelle(req);
      case "cloturerJournee":
        return cloturerJournee(req);
      default:
        throw new NotImplementedException();
    }
  }


  private Object cloturerJournee(HttpServletRequest req) {
    JourneeDto journee = getJourneeDto(req);
    journee.setIdJournee(journeeUcc.cloturerJournee(journee));
    logger.info("journée desactivée");
    // TODO Auto-generated method stub
    return journee;
  }

  private Object verifierJourneeActuelle(HttpServletRequest req) {
    JourneeDto result = journeeUcc.getJourneeCourante();
    if (Integer.parseInt(req.getParameter("idJournee")) == result.getIdJournee()) {
      return result;
    }
    JourneeDto clot = this.bizFactory.getJourneeDto();
    clot.setCloturee(true);
    clot.setIdJournee(Integer.parseInt(req.getParameter("idJournee")));
    return clot;
  }

  private JourneeDto getJourneeDto(HttpServletRequest req) {
    JourneeDto res = bizFactory.getJourneeDto();
    Map<String, Object> temp = new HashMap<>();
    if (req.getParameter("journee") != null) {
      temp = genson.deserialize(req.getParameter("journee"), Map.class);
    }
    if (temp.get("dateJe") != null) {
      res.setDateJe(LocalDate.parse((String) temp.get("dateJe")));
    }
    if (temp.get("idJournee") != null) {
      res.setIdJournee(Integer.parseInt("" + temp.get("idJournee")));
    }
    if (req.getParameter("idJournee") != null) {
      res.setIdJournee(Integer.parseInt(req.getParameter("idJournee")));
    }
    return res;
  }

  private List<JourneeDto> getJournees() throws IOException {
    ArrayList<JourneeDto> journees = journeeUcc.getJournees();
    // journees.forEach(System.out::println);
    return journees;
  }

  private Object creerJournee(HttpServletRequest req, JourneeDto journeeDto) throws IOException {
    // JourneeDto envoie = genson.deserialize(req.getParameter("journee"), JourneeDto.class);
    // Format yyyy-mm-dd
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
    journeeDto.setIdCreateur(createur);
    journeeDto = journeeUcc.creerJournee(journeeDto);
    return journeeDto;
  }



}
