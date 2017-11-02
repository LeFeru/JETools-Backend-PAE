package ihm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import exceptions.FatalException;
import exceptions.PropertiesFileNotFoundException;

/**
 * @author rachid.
 *
 */
public class AppConfig {
  private String source;
  private Properties properties;
  private HashSet<String> keys;

  {
    this.properties = new Properties();
    this.keys = new HashSet<String>();
  }

  /**
   * Contructeur de l'AppConfig.
   * 
   * @param source String nom du fichier properties à ouvrir.
   */
  public AppConfig(String source) {
    this.source = source;
    File fileSource = new File(this.source);
    if (!fileSource.exists() || !fileSource.isFile()) {
      throw new PropertiesFileNotFoundException("Le fichier par défaut " + this.source
          + " n'existe pas ou n'est pas dans le dossier properties.");
    }
    this.chargerProperties();
  }


  private void chargerProperties() {
    FileInputStream input = null;
    try {
      input = new FileInputStream(this.source);
      checkKeysProperties(this.source);
      properties.load(input);
    } catch (FileNotFoundException fileNotFoundException) {
      throw new PropertiesFileNotFoundException(fileNotFoundException.getMessage());
    } catch (IOException ioException) {
      ioException.printStackTrace();
      throw new FatalException(ioException.getMessage());
    } finally {
      if (input != null) {
        try {
          input.close();
        } catch (IOException ignore) {
          System.out.println("Problème de fermeture du fichier properties: " + this.source);
        }
      }
    }
  }

  private void checkKeysProperties(String source) {
    try {
      /*
       * Scanner scan = new Scanner(new File(source)); while (scan.hasNextLine()) { String line =
       * scan.nextLine(); if (line.isEmpty() || line.contains("#")) { continue; } String tab[] =
       * line.split("="); if (keys.contains(tab[0])) { throw new FatalException( "Le fichier de
       * properties indiqué ne respecte pas les contraintes d'unicités des clés, conflit de clés
       * avec '"+ tab[0] + "'"); } keys.add(tab[0]); }
       * 
       * Reader reader = new InputStreamReader(new FileInputStream(new File(source)), "UTF-8");
       * 
       */
      InputStreamReader isr =
          new InputStreamReader(new FileInputStream(source), StandardCharsets.UTF_8);
      BufferedReader br = new BufferedReader(isr);
      while (br.ready()) {
        String line = br.readLine();
        if (line.isEmpty() || line.contains("#")) {
          continue;
        }
        String[] tab = line.split("=");
        if (keys.contains(tab[0])) {
          String mess1 = "Le fichier de properties indiqué ne respecte pas les contraintes ";
          String mess2 = "d'unicités des clés, conflit de clés avec '" + tab[0] + "'";
          throw new FatalException(mess1 + mess2);
        }
        keys.add(tab[0]);
      }
      br.close();
    } catch (Exception exception) {
      throw new FatalException(exception.getMessage());
    }
  }


  /**
   * Cherche la valeur de la key dans le fichier properties et le renvoie.
   * 
   * @param key String nom du champ recherché dans le properties.
   * @return nom du champ correspondant à key dans le fichier properties.
   */
  public String getValueFor(String key) {
    if (!this.properties.containsKey(key)) {
      return null;
    }
    return this.properties.getProperty(key);
  }

  /**
   * Retourne l'ensemble des clés du fichier properties.
   * 
   * @return Set l'ensemble des clés du fichier properties.
   */
  public Set<Object> getAllKeys() {
    if (this.properties.isEmpty()) {
      return null;
    }
    return this.properties.keySet();
  }

  /**
   * Retourne le logger qui est dans le properties.
   * 
   * @return le logger.
   */
  public Properties getLog4jProperties() {
    Properties log4jProperties = new Properties();
    for (String key : this.keys) {
      if (key.startsWith("log4j.")) {
        log4jProperties.put(key, this.getValueFor(key));
      }
    }
    return log4jProperties;
  }


}
