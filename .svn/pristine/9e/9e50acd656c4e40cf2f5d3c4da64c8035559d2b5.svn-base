package utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


public interface Utilitaire {

  public static Utilitaire getInstance() {
    return new UtilitaireImpl();
  }

  void info(String text);

  void afficherResultSet(ResultSet rs) throws SQLException;

  List<Map<String, String>> convertirResultSet(ResultSet rs);

  void checkObject(Object obj);

  void checkPositive(double pos);

  void checkNumerique(String str);

  void checkString(String str);

  void error(String text);

}
