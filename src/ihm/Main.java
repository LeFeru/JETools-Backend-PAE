package ihm;

import java.lang.reflect.InvocationTargetException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.ResourceCollection;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * @author rachid.
 */

public class Main {

  /**
   * Démarage du serveur Jetty.
   * 
   * @param args tableau contenant les paramètres.
   * @throws ClassNotFoundException fichier properties.
   * @throws SecurityException Erreur Jetty.
   * @throws NoSuchMethodException Erreur Jetty.
   * @throws InvocationTargetException Erreur Jetty.
   * @throws IllegalArgumentException Erreur Jetty.
   * @throws IllegalAccessException Erreur Jetty.
   * @throws InstantiationException Erreur Jetty.
   */
  public static void main(String[] args)
      throws InstantiationException, IllegalAccessException, IllegalArgumentException,
      InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {

    String arg;
    if (args == null || args.length != 1) {
      arg = "";
    } else {
      arg = args[0];
    }
    Dealer dealer = new Dealer(arg);
    PropertyConfigurator.configure(dealer.getAppConfig().getLog4jProperties());
    Logger debugLogger = Logger.getLogger("debugLogger");
    Logger resultLogger = Logger.getLogger("reportsLogger");
    resultLogger.info("Démarrage de l'application");
    // debugLogger.debug("Sample debug message");
    // debugLogger.info("Sample info message");
    // debugLogger.warn("Sample warn message");
    // debugLogger.error("Sample error message");
    // debugLogger.fatal("Sample fatal message");

    ServletDispatcher servlet = new ServletDispatcher(dealer);

    WebAppContext context = new WebAppContext();

    ServletHolder holderDefault = new ServletHolder(servlet);
    holderDefault.setInitParameter("welcomeServlets", "true");
    context.addServlet(holderDefault, "/all/*");
    context.addServlet(holderDefault, "/");
    ResourceCollection ressources = new ResourceCollection(new String[] {"web", "webindex"});
    context.setBaseResource(ressources);
    context.setWelcomeFiles(new String[] {"/all"});
    context.setInitParameter("cacheControl", "no-store,nocache,must-revalidate");
    context.setClassLoader(Thread.currentThread().getContextClassLoader());
    context.setMaxFormContentSize(999999999);
    Server server = new Server(8080);
    server.setHandler(context);
    try {
      server.start();
      resultLogger.info("Serveur démarré avec succès");
    } catch (Exception exc) {
      debugLogger.fatal("Impossible de démarrer le serveur");
      debugLogger.fatal(exc.getMessage());
      for (StackTraceElement elt : exc.getStackTrace()) {
        debugLogger.fatal(elt);
      }
    }
  }
}
