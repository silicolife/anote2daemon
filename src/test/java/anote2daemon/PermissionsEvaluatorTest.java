package anote2daemon;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.silicolife.anote2daemon.model.core.dao.manager.QueriesManagerDao;
import com.silicolife.anote2daemon.model.core.entities.Queries;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:dispatcher-servlet.xml")
@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
public class PermissionsEvaluatorTest {


	private QueriesManagerDao queriesManagerDao;
	@Autowired
	public PermissionsEvaluatorTest(QueriesManagerDao queriesManagerDao){
		this.queriesManagerDao = queriesManagerDao;
	}

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
		
		Queries f = queriesManagerDao.getQueriesDao().findById(1L);
		Assert.assertNotNull(f);
		System.out.print(f.getOrganism());
	}

}
