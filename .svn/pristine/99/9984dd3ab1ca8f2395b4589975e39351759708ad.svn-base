package ihm;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.eclipse.jetty.servlet.DefaultServlet;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.owlike.genson.Genson;

import business.dto.UtilisateurDto;
import business.factories.BizFactory;
import exceptions.BizException;
import exceptions.FatalException;
import exceptions.ForbiddenAccessException;

/**
 * @author rachid.
 *
 */
public class ServletDispatcher extends DefaultServlet {
  /**
   * id auto généré.
   */
  private static final long serialVersionUID = -8796242374106109401L;
  private Logger resultLogger;
  private Logger debugLogger;
  private UtilisateurIhm utilisateurIhm;
  private JourneeIhm journeeIhm;
  private EntrepriseIhm entrepriseIhm;
  private PersonneContactIhm personneContactIhm;
  private ParticipationIhm participationIhm;
  private PresenceIhm presenceIhm;
  private String jwtSecret;
  private BizFactory bizFactory;
  private UtilisateurDto utilisateurConnecte;
  private Genson genson;
  private AppConfig appConfig;

  /**
   * Constructeur de la classe ServletDispatcher.
   * 
   * @param dealer Dealer passé en paramètre pour initialiser les attriuts.
   */
  public ServletDispatcher(Dealer dealer) {
    this.jwtSecret = dealer.getJwtSecret();
    this.utilisateurIhm = new UtilisateurIhm(dealer);
    this.journeeIhm = new JourneeIhm(dealer);
    this.entrepriseIhm = new EntrepriseIhm(dealer);
    this.personneContactIhm = new PersonneContactIhm(dealer);
    this.participationIhm = new ParticipationIhm(dealer);
    this.presenceIhm = new PresenceIhm(dealer);
    this.appConfig = dealer.getAppConfig();
    genson = new Genson();
    bizFactory = dealer.getBizFactory();
    utilisateurConnecte = bizFactory.getUtilisateurDto();
    this.resultLogger = Logger.getLogger("reportsLogger");
    this.debugLogger = Logger.getLogger("debugLogger");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    if (req.getServletPath().equalsIgnoreCase("/")) {

      resp.setContentType("text/html");
      resp.getOutputStream().write(Files.readAllBytes(Paths.get("webindex", "header.html")));
      // On envoit menu.html que si l'utilisateur est connecté
      try {
        utilisateurConnecte = getUserConnecte(req, resp);
        if (utilisateurConnecte == null) {
          // ignore
        } else {
          Path path = Paths.get("webindex", "menu.html");
          resp.getOutputStream().write(Files.readAllBytes(path));
        }
      } catch (ForbiddenAccessException cnae) {
        debugLogger.info("pas connecté");
      }
      resp.getOutputStream().write(Files.readAllBytes(Paths.get("webindex", "headerFin.html")));
      resp.getOutputStream().write(Files.readAllBytes(Paths.get("webindex", "loading.html")));
      resp.getOutputStream().write(Files.readAllBytes(Paths.get("webindex", "login_page.html")));
      resp.getOutputStream().write(Files.readAllBytes(Paths.get("webindex", "JE.html")));
      resp.getOutputStream().write(Files.readAllBytes(Paths.get("webindex", "invitations.html")));
      resp.getOutputStream()
          .write(Files.readAllBytes(Paths.get("webindex", "participations.html")));
      resp.getOutputStream().write(Files.readAllBytes(Paths.get("webindex", "annuaire.html")));
      resp.getOutputStream().write(Files.readAllBytes(Paths.get("webindex", "footer.html")));
      resp.setStatus(200);
    } else {
      super.doGet(req, resp);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String action = req.getParameter("req");
    Object response = null;
    String droits = this.appConfig.getValueFor(action + "Droits");
    String ihm = this.appConfig.getValueFor(action + "Ihm");
    resultLogger.info("Action: " + action + " -> Droits: " + droits + " -> IHM: " + ihm);
    resp.setCharacterEncoding("UTF-8");
    resp.setContentType("application/json");
    try {
      if (droits == null) {
        // Il ne faut pas être connecté
        // ignore
      } else if (droits.equals("auth")) {
        // Il faut être connecté
        checkEstConnecte(req, resp);
        resultLogger.info("user co: " + utilisateurConnecte);
      } else if (droits.equals("responsable")) {
        // Il faut être connecté et responsable
        checkEstConnecte(req, resp);
        resultLogger.info("user co: " + utilisateurConnecte);
        checkEstResponsable();
      }
      if (ihm == null) {
        resp.getWriter().println("Fonctionnalitée inconnue");
        resp.setStatus(400);
        return;
      }
      switch (ihm) {
        case "utilisateurIhm":
          this.utilisateurIhm.operer(req, resp);
          return;
        case "journeeIhm":
          response = this.journeeIhm.operer(req);
          break;
        case "entrepriseIhm":
          response = this.entrepriseIhm.operer(req);
          break;
        case "personneContactIhm":
          response = this.personneContactIhm.operer(req);
          break;
        case "participationIhm":
          response = this.participationIhm.operer(req);
          break;
        case "presenceIhm":
          response = this.presenceIhm.operer(req);
          break;
        default:
          break;
      }
      writeResponse(response, resp);
    } catch (BizException bizException) {
      debugLogger
          .error(bizException.getClass().getCanonicalName() + " : " + bizException.getMessage());
      debugLogger.error(bizException.getMessage());
      for (StackTraceElement elt : bizException.getStackTrace()) {
        debugLogger.error(elt);
      }
      resp.getOutputStream().print(bizException.getMessage());
      resp.setStatus(400);
    } catch (Exception exception) {
      debugLogger.error(exception.getClass().getCanonicalName() + " : " + exception.getMessage());
      debugLogger.error(exception.getMessage());
      for (StackTraceElement elt : exception.getStackTrace()) {
        debugLogger.error(elt);
      }
      resp.getOutputStream().print(exception.getMessage());
      resp.setStatus(500);
    }
  }


  private void writeResponse(Object response, HttpServletResponse resp) throws IOException {
    if (resp == null) {
      throw new FatalException("HttpServletResponse ne peut être null");
    }
    if (response != null) {
      resultLogger.info("Instance de la classe " + response.getClass().getCanonicalName()
          + " retournée au front-end : " + response.toString());
      resp.getOutputStream().print(genson.serialize(response));
    } else {
      resultLogger.info("Rien à écrire, seul le status code a été renvoyé #QLF");
    }
    resp.setStatus(200);
  }

  private void checkEstResponsable() {
    if (!utilisateurConnecte.isResponsable()) {
      String mess = "Un utilisateur non responsable ne peut accéder à cette fonctionnalité.";
      throw new ForbiddenAccessException(mess);
    }

  }

  private void checkEstConnecte(HttpServletRequest req, HttpServletResponse resp) {
    utilisateurConnecte = getUserConnecte(req, resp);
    if (utilisateurConnecte == null) {
      String mess = "Un utilisateur non connecté ne peut accéder à cette fonctionnalité.";
      throw new ForbiddenAccessException(mess);
    }
  }

  private UtilisateurDto getUserConnecte(HttpServletRequest req, HttpServletResponse resp) {
    if (req.getSession().getAttribute("id") != null) {
      UtilisateurDto utilisateurDto;
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
          resultLogger.info("tok:" + token);
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
        utilisateurConnecte = deserializeUtilisateur(userId);
        req.getSession().setAttribute("pseudo", utilisateurConnecte.getPseudo());
        req.getSession().setAttribute("id", token);
        req.getSession().setAttribute("utilisateur", userId);
        return utilisateurConnecte;
      }
    }
    String mess = "Un utilisateur non connecté ne peut accéder à cette fonctionnalité.";
    throw new ForbiddenAccessException(mess);
  }


  private UtilisateurDto deserializeUtilisateur(String toDeserialize) {
    Map<String, Object> temp = genson.deserialize(toDeserialize, Map.class);
    resultLogger.info(temp.toString());
    utilisateurConnecte.setId(((Long) temp.get("id")).intValue());
    utilisateurConnecte.setEmail((String) temp.get("email"));
    utilisateurConnecte.setMdp((String) temp.get("mdp"));
    utilisateurConnecte.setPseudo((String) temp.get("pseudo"));
    utilisateurConnecte.setNom((String) temp.get("nom"));
    utilisateurConnecte.setPrenom((String) temp.get("prenom"));
    utilisateurConnecte.setResponsable((boolean) temp.get("responsable"));
    return utilisateurConnecte;
  }

  private String genererToken2(HttpServletRequest req, UtilisateurDto retour) {
    Map<String, Object> claims = new HashMap<String, Object>();
    claims.put("pseudo", retour.getPseudo());
    claims.put("utilisateur", genson.serialize(retour));
    claims.put("ip", req.getParameter("remoteAddr"));
    return new JWTSigner(jwtSecret).sign(claims);
  }

  private void setCookie(HttpServletResponse resp, String token) {
    Cookie cookie = new Cookie("utilisateur", token);
    cookie.setPath("/");
    cookie.setMaxAge(60 * 30);
    resp.addCookie(cookie);
  }
}
