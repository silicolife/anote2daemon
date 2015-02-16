package anote2daemon;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

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

import pt.uminho.anote2.carrot.linkage.datastructures.ClusterLabel;
import pt.uminho.anote2.carrot.linkage.datastructures.ClusterProcess;
import pt.uminho.anote2.core.cluster.IClusterLabel;
import pt.uminho.anote2.core.cluster.IClusterProcess;

import com.silicolife.anote2daemon.service.clustering.ClusteringService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:dispatcher-servlet.xml")
@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
public class ClusteringTest {

	@Autowired
	private ClusteringService clusteringService;

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
		
		createClustering();
	
	}

	private void createClustering() {
		Properties prop = new Properties();
		prop.put("key", "value");
		IClusterLabel label1 = new ClusterLabel(11L, "label1", 20.0, null);
		List<IClusterLabel> listLabels = new ArrayList<IClusterLabel>();
		listLabels.add(label1);
		IClusterProcess clustering_ = new ClusterProcess(23L, null, "processo1", null);
		clusteringService.createClustering(clustering_);
		
	}
}
