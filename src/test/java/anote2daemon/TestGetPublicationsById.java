package anote2daemon;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=PersistenceConfig.class )
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class TestGetPublicationsById {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}


	@Test
	public void test() {

		
		// ApplicationContext context =
		// ApplicationContextUtils.getApplicationContext();
		// ((UsersLogged) v.getBean("userLogged")).setCurrentUserLogged(user);
		// publicationsAuxDao.findPublicationsById(1L);

	}

}
