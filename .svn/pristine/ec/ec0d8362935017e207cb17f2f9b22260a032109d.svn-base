package ihm;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.owlike.genson.Genson;

import business.dto.UtilisateurDto;
import business.factories.BizFactory;
import business.ucc.interfaces.UtilisateurUcc;
import exceptions.FatalException;
import exceptions.ForbiddenAccessException;
import exceptions.NotImplementedException;

public class UtilisateurIhm {
  private final Logger logger;
  private Dealer dealer;
  private UtilisateurUcc utilisateurUcc;
  private BizFactory bizFactory;
  private Genson genson;
  private String jwtSecret;
  private UtilisateurDto utilisateurDto;

  /**
   * Constructeur de la classe UtilisateurIhm.
   * 
   * @param dealer Dealer passé en paramètre pour initialiser les attributs.
   */
  public UtilisateurIhm(Dealer dealer) {
    // TODO Auto-generated constructor stub
    this.dealer = dealer;
    this.utilisateurUcc = this.dealer.getUtilisateurUcc();
    this.bizFactory = this.dealer.getBizFactory();
    this.jwtSecret = this.dealer.getJwtSecret();
    this.genson = new Genson();
    this.utilisateurDto = bizFactory.getUtilisateurDto();
    this.logger = Logger.getLogger("reportsLogger");
  }

  /**
   * Redirige l'action en fonction de l'attribut action de l'HttpServletRequest.
   * 
   * @param req HttpServletRequest.
   * @param resp HttpServletResponse.
   * @throws IOException exception lancée par la méthode inscrire.
   */
  public void operer(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    utilisateurDto = getUtilisateurDto(req);
    Object response = null;
    switch (req.getParameter("req")) {
      case "connecterUtilisateur":
        response = connecter(req, resp);
        break;
      case "inscrireUtilisateur":
        response = inscrire(req, resp);
        break;
      case "deconnecterUtilisateur":
        response = deconnecter(req, resp);
        break;
      case "estConnecte":
        response = getUserConnecte(req, resp);
        break;
      /*
       * case "backup": backup(); break;
       */
      default:
        throw new NotImplementedException();
    }
    writeResponse(response, resp);
  }
  /*
   * private void backup() { this.utilisateurUcc.backup(); }
   */

  private void writeResponse(Object response, HttpServletResponse resp) throws IOException {
    if (resp == null) {
      throw new FatalException("HttpServletResponse ne peut être null");
    }
    if (response != null) {
      resp.setCharacterEncoding("UTF-8");
      logger.info("Instance de la classe " + response.getClass().getCanonicalName()
          + " retournée au front-end : " + response.toString());
      resp.getOutputStream().print(genson.serialize(response));
    } else {
      logger.info("Rien à écrire, seul le status code a été renvoyé #QLF");
    }
    resp.setStatus(200);
  }

  private void setPourRenvoi(UtilisateurDto utilisateurDto) {
    utilisateurDto.setMdp("");
    utilisateurDto.setDateInscription(null);
  }

  private Object inscrire(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    logger.info("***Méthode inscrire***");
    UtilisateurDto retour = utilisateurUcc.inscrireUtilisateur(utilisateurDto);
    logger.info("Client inscrit : " + retour.getPseudo() + " - " + retour.getPrenom() + " "
        + retour.getNom() + " - " + retour.getEmail());
    setPourRenvoi(retour);
    logger.info("***Fin de la méthode inscrire***");
    return retour;
  }

  private void setCookie(HttpServletResponse resp, String token) {
    Cookie cookie = new Cookie("utilisateur", token);
    cookie.setPath("/");
    cookie.setMaxAge(60 * 30);
    resp.addCookie(cookie);
  }

  private Object connecter(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    logger.info(utilisateurDto.toString());
    UtilisateurDto retour = utilisateurUcc.connecterUtilisateur(utilisateurDto);
    logger.info(retour.toString());
    logger.info("Id user = " + retour.getId());
    logger.info("Client voulant se connecter : " + retour.getPseudo() + " - " + retour.getMdp()
        + " - " + retour.isResponsable());
    String token = genererToken2(req, retour);
    setCookie(resp, token);
    logger.info("cookie : " + token);
    setSession(req, token, retour);
    return retour;
  }

  private void setSession(HttpServletRequest req, String token2check,
      UtilisateurDto utilisateurCo) {
    req.getSession().setAttribute("pseudo", utilisateurCo.getPseudo());
    req.getSession().setAttribute("id", token2check);
    req.getSession().setAttribute("utilisateur", genson.serialize(utilisateurCo));
    // req.getSession().setAttribute("id", utilisateur);

  }

  private String genererToken2(HttpServletRequest req, UtilisateurDto retour) {
    Map<String, Object> claims = new HashMap<String, Object>();
    claims.put("pseudo", retour.getPseudo());
    claims.put("utilisateur", genson.serialize(retour));
    claims.put("ip", req.getParameter("remoteAddr"));
    return new JWTSigner(jwtSecret).sign(claims);
  }

  private boolean checkSession(HttpServletRequest req) {
    return req.getSession().getAttribute("id") != null
        && !req.getSession().getAttribute("id").toString().isEmpty();
  }


  private Object deconnecter(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    req.getSession().invalidate();
    // Suppression de l'utilisateur du set
    String idUtilisateur = (String) req.getSession().getAttribute("id");
    logger.info("id : " + idUtilisateur);
    Cookie ckie = new Cookie("utilisateur", null);
    ckie.setMaxAge(0);
    resp.addCookie(ckie);
    resp.setStatus(200);
    return "ok";
  }

  private UtilisateurDto getUtilisateurDto(HttpServletRequest req) {
    Map<String, Object> userJson = new HashMap<String, Object>();
    logger.info("req.getParameter(\"utilisateur\") " + req.getParameter("utilisateur"));
    if (req.getParameter("utilisateur") != null) {
      userJson = genson.deserialize(req.getParameter("utilisateur"), Map.class);
    }
    if (userJson.get("pseudo") != null) {
      utilisateurDto.setPseudo((String) userJson.get("pseudo"));
    }
    if (userJson.get("mdp") != null) {
      utilisateurDto.setMdp((String) userJson.get("mdp"));
    }
    if (userJson.get("confirmerMdp") != null) {
      utilisateurDto.setMdpConfirme((String) userJson.get("confirmerMdp"));
    }
    if (userJson.get("email") != null) {
      utilisateurDto.setEmail((String) userJson.get("email"));
    }
    if (userJson.get("nom") != null) {
      utilisateurDto.setNom((String) userJson.get("nom"));
    }
    if (userJson.get("prenom") != null) {
      utilisateurDto.setPrenom((String) userJson.get("prenom"));
    }
    return utilisateurDto;
  }

  private UtilisateurDto getUserConnecte(HttpServletRequest req, HttpServletResponse resp) {
    logger.info("checksession : " + checkSession(req));
    if (checkSession(req)) {
      utilisateurDto =
          deserializeUtilisateur((String) req.getSession().getAttribute("utilisateur"));
      String token = genererToken2(req, utilisateurDto);
      setCookie(resp, token);
      return deserializeUtilisateur((String) req.getSession().getAttribute("utilisateur"));
    }
    String token = null;
    Cookie[] cookies = req.getCookies();
    if (cookies != null) {
      for (Cookie ckie : cookies) {
        if ("utilisateur".equals(ckie.getName()) && ckie.getSecure()) {
          token = ckie.getValue();
          logger.info("tok:" + token);
        } else if ("utilisateur".equals(ckie.getName()) && token == null) {
          token = ckie.getValue();
        }
      }
    }
    if (token != null) {
      String userId = null;
      try {
        Map<String, Object> decodedPayload = new JWTVerifier(jwtSecret).verify(token);
        userId = (String) decodedPayload.get("utilisateur");
      } catch (Exception exception) {
        exception.printStackTrace();
      }
      if (userId != null) {
        logger.info("Cookie ok : " + userId);
        setSession(req, token, deserializeUtilisateur(userId));
        return deserializeUtilisateur(userId);
      }
    }
    String mess = "Un utilisateur non connecté ne peut accéder à cette fonctionnalité.";
    throw new ForbiddenAccessException(mess);
  }

  private UtilisateurDto deserializeUtilisateur(String toDeserialize) {
    Map<String, Object> temp = genson.deserialize(toDeserialize, Map.class);
    logger.info(temp.toString());
    utilisateurDto.setId(((Long) temp.get("id")).intValue());
    utilisateurDto.setEmail((String) temp.get("email"));
    utilisateurDto.setMdp((String) temp.get("mdp"));
    utilisateurDto.setPseudo((String) temp.get("pseudo"));
    utilisateurDto.setNom((String) temp.get("nom"));
    utilisateurDto.setPrenom((String) temp.get("prenom"));
    utilisateurDto.setResponsable((boolean) temp.get("responsable"));
    return utilisateurDto;
  }
}
