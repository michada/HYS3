package es.uvigo.esei.daa;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

//import es.uvigo.esei.daa.dao.UsuariosDAOTest;
import es.uvigo.esei.daa.rest.EventosTest;
import es.uvigo.esei.daa.web.EventosWebTest;

@SuiteClasses({ /*UsuariosDAOTest.class,*/ EventosTest.class, EventosWebTest.class })
@RunWith(Suite.class)
public class PeopleTestSuite {
}
