package com.twitter;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class ExecutorTest {

	private String filesData;
	private String approversData;
	private String expected;
	private Executor executor;

	@Before
	public void before() {
		executor = new Executor();
	}

	public ExecutorTest(String approversData, String filesData, String expected) {
		this.approversData = approversData;
		this.filesData = filesData;
		this.expected = expected;
	}

	@Parameterized.Parameters
	public static Collection<Object[]> testData() {
		return Arrays.asList(new Object[][] { { "mfox", "src/com/twitter/tweet/Tweet.java", "Approved" },
				{ "eclarke,alovelace", "src/com/twitter/follow/Follow.java", "Approved" },
				{ "eclarke", "src/com/twitter/follow/Follow.java", "Insufficient approvals" },
				{ "alovelace,ghopper", "src/com/twitter/follow/Follow.java,src/com/twitter/user/User.java",
						"Approved" },
				{ "mfox", "src/com/twitter/message/Message.java,src/com/twitter/message/Message.java",
						"Insufficient approvals" },
				{ "eclarke", "src/com/twitter/message/Message.java", "Approved" }, 
				{ "ghopper", "src/com/twitter/message/Message.java", "Approved" },
				{ "mfox", "src/com/twitter/message/Message.java", "Insufficient approvals" },
				{ "jerry", "src/com/twitter/message/Message.java", "Insufficient approvals" },
				{ "jerry,ghopper", "src/com/twitter/message/Message.java", "Approved" }});
	}

	@Test
	public void testExecutor() throws Exception {
		Assert.assertEquals(executor.execute(approversData, filesData), expected);
	}

}
