package dal.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;

import exceptions.FatalException;
import exceptions.UncheckedSqlException;
import ihm.AppConfig;

/**
 * @author rachid.
 *
 */

public class DalServicesImpl implements DalServices, DalBackendServices {
  private final ThreadLocal<Connection> connectionByThread;
  private final BasicDataSource dataSource;
  private final AppConfig dbConfig;
  private final String init;
  private final String schema;
  private final Logger logger;

  /**
   * Constructeur du service SQL.
   * 
   * @param dbConfig Lien vers le fichier properties.
   */
  public DalServicesImpl(AppConfig dbConfig) {
    this.dbConfig = dbConfig;
    this.connectionByThread = new ThreadLocal<Connection>();
    this.logger = Logger.getLogger("reportsLogger");
    logger.info("Setting up data source.");
    dataSource = new BasicDataSource();
    dataSource.setDriverClassName(dbConfig.getValueFor("driverClassName"));
    dataSource.setUrl(dbConfig.getValueFor("url"));
    dataSource.setUsername(dbConfig.getValueFor("username"));
    dataSource.setPassword(dbConfig.getValueFor("password"));
    dataSource.setDefaultAutoCommit(true);
    logger.info("Done.");
    schema = "SELECT schema_name FROM information_schema.schemata WHERE schema_name = '"
        + dbConfig.getValueFor("schema") + "';";
    init = dbConfig.getValueFor("initFile");
    if (!schemaCreated()) {
      throw new FatalException("Le schéma " + dbConfig.getValueFor("schema") + " n'existe pas");
    }
  }


  private boolean schemaCreated() {
    PreparedStatement ps;
    ResultSet rs;
    this.openConnection();
    try {
      ps = this.prepareStatement(schema);
      rs = ps.executeQuery();
      if (rs.next()) {
        if (rs.getString(1).equalsIgnoreCase("pae")) {
          logger.info("Schema déjà init");
          return true;
        }
      }
      return true;
    } catch (Exception exc) {
      exc.printStackTrace();
      return false;
    } finally {
      this.closeConnection();
    }
  }



  /**
   * Prépare le statement sur la bonne connexion.
   * 
   * @param stringPs String le statement.
   * @return le PrepareStatement du string en paramètre.
   */
  @Override
  public PreparedStatement prepareStatement(String stringPs) throws SQLException {
    if (!this.connectionDejaAffectee()) {
      throw new UncheckedSqlException("Il faut d'abord ouvrir une connexion");
    }
    return connectionByThread.get().prepareStatement(stringPs);
  }

  private boolean connectionDejaAffectee() {
    return this.connectionByThread.get() != null;
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public void startTransaction() {
    if (!this.connectionDejaAffectee()) {
      throw new UncheckedSqlException("Pas de connection ouverte");
    }
    Connection conn = this.connectionByThread.get();
    try {
      if (!conn.getAutoCommit()) {
        throw new UncheckedSqlException("Transaction déjà ouverte");
      }
      conn.setAutoCommit(false);
    } catch (SQLException exc) {
      throw new UncheckedSqlException(exc.getMessage());
    }
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public void commitTransaction() {
    if (!this.connectionDejaAffectee()) {
      throw new UncheckedSqlException("Pas de connection attribuée");
    }
    try {
      if (this.connectionByThread.get().getAutoCommit()) {
        throw new UncheckedSqlException("Pas de transaction ouverte");
      }
      this.connectionByThread.get().commit();
      this.connectionByThread.get().setAutoCommit(true);
    } catch (SQLException exc) {
      rollbackTransaction();
      throw new UncheckedSqlException(exc.getMessage());
    }
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public void rollbackTransaction() {
    if (!this.connectionDejaAffectee()) {
      throw new UncheckedSqlException("Pas de connection attribuée");
    }
    Connection conn = this.connectionByThread.get();
    try {
      if (conn.getAutoCommit()) {
        throw new UncheckedSqlException("Pas de transaction ouverte");
      }
      conn.rollback();
      conn.setAutoCommit(true);
    } catch (SQLException exc) {
      throw new UncheckedSqlException(exc.getMessage());
    }
  }


  @Override
  public void openConnection() {
    if (this.dataSource == null) {
      throw new FatalException("Le DataSource ne peut être null !");
    }
    if (this.dataSource.isClosed()) {
      throw new FatalException("Le DataSource a été fermé !");
    }
    if (this.connectionDejaAffectee()) {
      throw new UncheckedSqlException("Connection déjà ouverte");
    }
    try {
      Connection temp = this.dataSource.getConnection();
      temp.setAutoCommit(true);
      this.connectionByThread.set(temp);
    } catch (SQLException exception) {
      exception.printStackTrace();
      throw new UncheckedSqlException(exception.getMessage());
    }
  }


  @Override
  public void closeConnection() {
    if (!this.connectionDejaAffectee()) {
      throw new UncheckedSqlException("Pas de connection ouverte");
    }
    try {
      Connection temp = this.connectionByThread.get();
      temp.setAutoCommit(true);
      temp.close();
      this.connectionByThread.remove();
    } catch (SQLException exception) {
      throw new UncheckedSqlException(exception.getMessage());
    }
  }


  @Override
  public boolean hasAlreadyOpenedConnection() {
    return this.connectionDejaAffectee();
  }


  @Override
  public boolean hasAlreadyClosedConnection() {
    return !this.connectionDejaAffectee();
  }


  @Override
  public boolean hasOpenTransaction() {
    if (this.hasAlreadyClosedConnection()) {
      return false;
    }
    try {
      return !this.connectionByThread.get().getAutoCommit();
    } catch (SQLException exc) {
      return false;
    }
  }

  private String removeLastChar(String str) {
    return str.substring(0, str.length() - 1);
  }

  private String capitalizeFirstChar(String str) {
    return str.substring(0, 1).toUpperCase() + str.substring(1);
  }

  /**
   * Retient l'état de la base de données.
   * 
   * @return List contenant l'état de la base de données.
   */
  public List<String> backup() {
    try {
      List<String> tables = Arrays.asList(new String[] {"utilisateurs", "entreprises",
          "personnes_contact", "journees", "participations", "presences"});
      List<String> liste = new ArrayList<String>();
      int seq = 1;
      for (String table : tables) {
        System.out.println(this.dbConfig.getValueFor("backup"));
        PreparedStatement ps = this.prepareStatement("SELECT * FROM pae." + table + ";");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
          String line =
              "INSERT INTO " + this.dbConfig.getValueFor("schema") + "." + table + " VALUES(";
          for (int i = 1; i < rs.getMetaData().getColumnCount(); i++) {
            String elt = rs.getString(i);
            // System.out.println(rs.getMetaData().getColumnTypeName(i));
            if (elt == null || elt.equals("null")) {
              line += "NULL,";
            } else if (rs.getMetaData().getColumnTypeName(i).equals("bool")) {
              if (elt.equalsIgnoreCase("t")) {
                line += "TRUE,";
              } else {
                line += "FALSE,";
              }
            } else if (rs.getMetaData().getColumnTypeName(i).equalsIgnoreCase("varchar")
                || rs.getMetaData().getColumnTypeName(i).equalsIgnoreCase("date")
                || rs.getMetaData().getColumnTypeName(i).equalsIgnoreCase("timestamp")) {
              line += "'" + elt + "',";
            } else {
              line += elt + ",";
            }
          }
          line = removeLastChar(line);
          line += ");";
          liste.add(line);
          seq++;
        }
        String alter = "ALTER SEQUENCE pae." + table + "_id_" + removeLastChar(table)
            + "_seq RESTART WITH " + seq + ";";
        liste.add(alter);
        seq = 1;
      }
      liste.remove(liste.size() - 1);
      return liste;
    } catch (SQLException sqlexception) {
      throw new UncheckedSqlException("Echec backupEntreprises");
    }
  }
}


