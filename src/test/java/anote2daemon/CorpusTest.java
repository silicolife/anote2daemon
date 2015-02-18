package anote2daemon;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import pt.uminho.anote2.core.document.IPublication;

import com.silicolife.anote2daemon.service.corpora.CorpusService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:dispatcher-servlet.xml")
@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
public class CorpusTest {

	@Autowired
	private CorpusService corpusService;

	public CorpusTest() {
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
		getAllPublications(1L);
	}
	
	
	private void getAllPublications(Long queryId){
		
		Date begin = new Date();
		
		System.out.println(new Date().getTime());
		List<IPublication> publications = corpusService.getCorpusPublications(queryId);
		for(IPublication pub : publications){
			System.out.println(pub.getTitle());
		}
		
		Date end = new Date();
		
		System.out.println(end.getTime() - begin.getTime());
	}
}
