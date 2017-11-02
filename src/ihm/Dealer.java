package ihm;

import java.lang.reflect.InvocationTargetException;

import business.factories.BizFactory;
import business.ucc.interfaces.EntrepriseUcc;
import business.ucc.interfaces.JourneeUcc;
import business.ucc.interfaces.ParticipationUcc;
import business.ucc.interfaces.PersonneContactUcc;
import business.ucc.interfaces.PresenceUcc;
import business.ucc.interfaces.UtilisateurUcc;
import dal.dao.interfaces.EntrepriseDao;
import dal.dao.interfaces.JourneeDao;
import dal.dao.interfaces.ParticipationDao;
import dal.dao.interfaces.PersonneContactDao;
import dal.dao.interfaces.PresenceDao;
import dal.dao.interfaces.UtilisateurDao;
import dal.services.DalBackendServices;

public class Dealer {
  private String prodConfigFinal = "properties/prod.properties";
  private AppConfig appConfig;
  private DalBackendServices dalBackendServices;
  private BizFactory bizFactory;
  private UtilisateurDao utilisateurDao;
  private UtilisateurUcc utilisateurUcc;
  private EntrepriseDao entrepriseDao;
  private EntrepriseUcc entrepriseUcc;
  private JourneeDao journeeDao;
  private JourneeUcc journeeUcc;
  private PersonneContactDao personneContactDao;
  private PersonneContactUcc personneContactUcc;
  private ParticipationDao participationDao;
  private ParticipationUcc participationUcc;
  private PresenceDao presenceDao;
  private PresenceUcc presenceUcc;
  private String jwtSecret;

  /**
   * Constructeur de la classe Dealer.
   * 
   * @param arg la source
   * @throws ClassNotFoundException
   * @throws SecurityException
   * @throws NoSuchMethodException
   * @throws InvocationTargetException
   * @throws IllegalArgumentException
   * @throws IllegalAccessException
   * @throws InstantiationException
   */
  public Dealer(String arg)
      throws InstantiationException, IllegalAccessException, IllegalArgumentException,
      InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
    this.chargerAppConfigs(arg);
    this.dalBackendServices = this.newService(DalBackendServices.class);
    this.bizFactory = this.newFactory(BizFactory.class);
    this.utilisateurDao = this.newDao(UtilisateurDao.class);
    this.journeeDao = this.newDao(JourneeDao.class);
    this.entrepriseDao = this.newDao(EntrepriseDao.class);
    this.personneContactDao = this.newDao(PersonneContactDao.class);
    this.participationDao = this.newDao(ParticipationDao.class);
    this.presenceDao = this.newDao(PresenceDao.class);

    this.utilisateurUcc =
        this.newUcc(UtilisateurUcc.class, utilisateurDao, bizFactory, dalBackendServices);

    this.journeeUcc =
        this.newUcc(JourneeUcc.class, journeeDao, bizFactory, dalBackendServices, utilisateurDao);

    this.entrepriseUcc = this.newUcc(EntrepriseUcc.class, entrepriseDao, bizFactory,
        dalBackendServices, utilisateurDao, participationDao, journeeDao);

    this.personneContactUcc = this.newUcc(PersonneContactUcc.class, personneContactDao, bizFactory,
        dalBackendServices, entrepriseDao);

    this.participationUcc = this.newUcc(ParticipationUcc.class, participationDao, bizFactory,
        dalBackendServices, entrepriseDao, journeeDao, personneContactDao);

    this.presenceUcc = this.newUcc(PresenceUcc.class, presenceDao, bizFactory, dalBackendServices,
        entrepriseDao, journeeDao, personneContactDao, participationDao);
  }

  @SuppressWarnings("unchecked")
  private <T> T newService(Class<T> toReturn)
      throws InstantiationException, IllegalAccessException, IllegalArgumentException,
      InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
    return (T) Class.forName(appConfig.getValueFor(toReturn.getCanonicalName()))
        .getConstructor(AppConfig.class).newInstance(this.appConfig);
  }

  @SuppressWarnings("unchecked")
  private <T> T newFactory(Class<T> toReturn)
      throws InstantiationException, IllegalAccessException, IllegalArgumentException,
      InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
    return (T) Class.forName(appConfig.getValueFor(toReturn.getCanonicalName())).getConstructor()
        .newInstance();
  }

  @SuppressWarnings("unchecked")
  private <T> T newDao(Class<T> toReturn)
      throws InstantiationException, IllegalAccessException, IllegalArgumentException,
      InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
    return (T) Class.forName(appConfig.getValueFor(toReturn.getCanonicalName()))
        .getConstructor(DalBackendServices.class, BizFactory.class, AppConfig.class)
        .newInstance(this.dalBackendServices, this.bizFactory, this.appConfig);
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  private <T> T newUcc(Class<T> toReturn, Object... initargs)
      throws InstantiationException, IllegalAccessException, IllegalArgumentException,
      InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
    Class[] paramClass = new Class[initargs.length];
    for (int i = 0; i < paramClass.length; i++) {
      Class[] interfaces = initargs[i].getClass().getInterfaces();
      if (interfaces == null) {
        paramClass[i] = initargs[i].getClass();
      } else {
        paramClass[i] = initargs[i].getClass().getInterfaces()[0];
      }
    }
    return (T) Class.forName(appConfig.getValueFor(toReturn.getName())).getConstructor(paramClass)
        .newInstance(initargs);
  }

  private void chargerAppConfigs(String arg) {
    if (arg == null || arg.isEmpty()) {
      this.appConfig = new AppConfig(this.prodConfigFinal);
    } else {
      this.appConfig = new AppConfig(arg);
    }
    this.jwtSecret = this.appConfig.getValueFor("jwt_secret");
  }

  /**
   * Retourne le dalBackendServices.
   * 
   * @return the dalBackendServices.
   */
  public DalBackendServices getDalBackendServices() {
    return dalBackendServices;
  }

  /**
   * Retourne la bizFactory.
   * 
   * @return the bizFactory.
   */
  public BizFactory getBizFactory() {
    return bizFactory;
  }

  /**
   * Retourne l'utilisateurDao.
   * 
   * @return the utilisateurDao.
   */
  public UtilisateurDao getUtilisateurDao() {
    return utilisateurDao;
  }

  /**
   * Retourne l'utilisateurUcc.
   * 
   * @return the utilistateurUcc.
   */
  public UtilisateurUcc getUtilisateurUcc() {
    return utilisateurUcc;
  }

  /**
   * Retourne la journeeDao.
   * 
   * @return the journeeDao.
   */
  public JourneeDao getJourneeDao() {
    return journeeDao;
  }

  /**
   * Retourne la journeeUcc.
   * 
   * @return the journeeUcc.
   */
  public JourneeUcc getJourneeUcc() {
    return journeeUcc;
  }

  /**
   * Retourne l'entrepriseUcc.
   * 
   * @return the entrepriseUcc.
   */
  public EntrepriseUcc getEntrepriseUcc() {
    return entrepriseUcc;
  }

  /**
   * Retourne la PersonneContactUcc.
   * 
   * @return the personneContactUcc.
   */
  public PersonneContactUcc getPersonneContactUcc() {
    return personneContactUcc;
  }

  /**
   * Retourne la ParticipationUcc.
   * 
   * @return the participationUcc.
   */
  public ParticipationUcc getParticipationUcc() {
    return participationUcc;
  }

  /**
   * Retourne la ParticipationUcc.
   * 
   * @return the participationUcc.
   */
  public PresenceUcc getPresenceUcc() {
    return presenceUcc;
  }

  /**
   * Retourne le jwtSecret.
   * 
   * @return the journeeUcc.
   */
  public String getJwtSecret() {
    return this.jwtSecret;
  }

  /**
   * Retourne l'AppConfig.
   * 
   * @return the appConfig.
   */
  public AppConfig getAppConfig() {
    return this.appConfig;
  }



}
