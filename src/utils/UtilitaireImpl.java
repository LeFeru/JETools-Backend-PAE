package utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public final class UtilitaireImpl implements Utilitaire {
  private final DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");

  private Logger logger = Logger.getLogger(this.getClass());

  /*
   * (non-Javadoc)
   * 
   * @see utils.Utilitaire#info(java.lang.String)
   */
  @Override
  public void info(String text) {
    logger.info("[" + LocalDateTime.now().format(format) + "] " + text);
  }

  /*
   * (non-Javadoc)
   * 
   * @see utils.Utilitaire#error(java.lang.String)
   */
  @Override
  public void error(String text) {
    logger.error("[" + LocalDateTime.now().format(format) + "] " + text);
  }

  /*
   * (non-Javadoc)
   * 
   * @see utils.Utilitaire#afficherResultSet(java.sql.ResultSet)
   */
  @Override
  public void afficherResultSet(ResultSet rs) throws SQLException {
    if (rs == null) {
      logger.info("Pas de résultat");
      return;
    }
    ResultSetMetaData rsmd = rs.getMetaData();

    int nbColonnes = rsmd.getColumnCount();
    ArrayList<ArrayList<String>> resultat = new ArrayList<ArrayList<String>>();
    int[] tailleMaxColonne = new int[nbColonnes];
    ArrayList<String> ligne = new ArrayList<String>(nbColonnes);
    resultat.add(new ArrayList<String>(nbColonnes));
    for (int i = 1; i <= nbColonnes; i++) {
      resultat.get(0).add(rsmd.getColumnName(i));
      tailleMaxColonne[i - 1] = rsmd.getColumnName(i).length();
    }
    int rowCount = 1;
    while (rs.next()) {
      resultat.add(new ArrayList<String>());
      for (int i = 1; i <= nbColonnes; i++) {
        ligne = resultat.get(rowCount);
        ligne.add(rs.getString(i));
        if (ligne.get(i - 1).length() > tailleMaxColonne[i - 1]) {
          tailleMaxColonne[i - 1] = ligne.get(i - 1).length();

        }
      }
      rowCount++;
    }
    if (rowCount == 1) {
      logger.info("Il n'y a aucune donnée !");
      return;
    }
    int nbLignes = resultat.size();
    String mot = "";
    int tailleMot = 0;
    int tailleMax = 0;
    String temp = "";
    for (int i = 0; i < nbLignes; i++) {
      ligne = resultat.get(i);
      for (int j = 0; j < nbColonnes; j++) {
        mot = ligne.get(j);
        temp += mot;
        tailleMot = mot.length();
        tailleMax = tailleMaxColonne[j];
        StringBuffer buf = new StringBuffer();
        for (int k = 0; k < tailleMax - tailleMot; k++) {
          buf.append(" ");
          // temp += " ";
        }
        buf.append(" | ");
        // temp += " | ";
        temp = buf.toString();
      }

      System.out.println(temp);
      temp = "";
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see utils.Utilitaire#convertirResultSet(java.sql.ResultSet)
   */
  @Override
  public List<Map<String, String>> convertirResultSet(ResultSet rs) {
    try {
      ResultSetMetaData rsmd = rs.getMetaData();
      List<String> columns = new ArrayList<String>(rsmd.getColumnCount());
      for (int i = 1; i <= rsmd.getColumnCount(); i++) {
        columns.add(rsmd.getColumnName(i));
      }
      List<Map<String, String>> data = new ArrayList<Map<String, String>>();
      while (rs.next()) {
        Map<String, String> row = new HashMap<String, String>(columns.size());
        for (String col : columns) {
          row.put(col, rs.getString(col));
        }
        data.add(row);
      }
      return data;
    } catch (Exception exc) {
      return null;
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see utils.Utilitaire#checkObject(java.lang.Object)
   */
  @Override
  public void checkObject(Object obj) {
    if (obj == null) {
      throw new NullPointerException("L'objet ne peut pas être null");
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see utils.Utilitaire#checkString(java.lang.String)
   */
  @Override
  public void checkString(String str) {
    checkObject(str);
    if (str.matches("\\s*")) {
      throw new IllegalArgumentException("La chaîne ne peut pas être vide");
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see utils.Utilitaire#checkNumerique(java.lang.String)
   */
  @Override
  public void checkNumerique(String str) {
    checkString(str);
    try {
      Long.parseLong(str);
    } catch (NumberFormatException exc) {
      throw new IllegalArgumentException("La chaîne doit être un nombre valide");
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see utils.Utilitaire#checkPositive(double)
   */
  @Override
  public void checkPositive(double nombre) {
    if (nombre <= 0) {
      throw new IllegalArgumentException("La valeur ne peut pas être négative ou nulle");
    }

  }
}

