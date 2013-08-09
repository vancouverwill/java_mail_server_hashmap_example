package src.willmelbourne;

/**
 * The test class MailServerTest.
 *
 * @author  William Melbourne
 * @version 2010.10.10
 */
public class MailServerTest extends junit.framework.TestCase
{
	private MailServer server;
	private MailClient will;
	private MailClient jim;
	private MailClient tom;

    /**
     * Default constructor for test class MailServerTest
     */
    public MailServerTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    protected void setUp()
    {
		server = new MailServer();
		will = new MailClient(server, "will");
		jim = new MailClient(server, "jim");
		tom = new MailClient(server, "tom_nomessages");
		jim.sendMailItem("will", "ola", "eh");
		jim.sendMailItem("will", "two", "deux");
		jim.sendMailItem("will", "three", "3");
		jim.sendMailItem("WILL", "4", "4");
		jim.sendMailItem("Will", "5", "5");
		
	}

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    protected void tearDown()
    {
    }

	
/**
 * Create 2 emails for one user and count to confirm it 2 are returned
 */
	public void testHowManyMailItems_positive_2emails1user()
	{
		jim.sendMailItem("tom_nomessages", "hola", "can't wait");
		jim.sendMailItem("tom_nomessages", "bingo", "i won!");
		assertEquals(2, server.howManyMailItems("tom_nomessages"));
	}

/**
 * create 0 emails and check 0 returned
 */	
	public void testHowManyMailItems_positive_0emails1user()
	{
	    assertEquals(0, server.howManyMailItems("tom_nomessages"));
	}

/**
 * create 2 emails for one user and 2 emails for another user and count to confirm there
 */
	public void testHowManyMailItems_positive_2emails2user()
	{
	    jim.sendMailItem("tom_nomessages", "hola", "can't wait");
		jim.sendMailItem("tom_nomessages", "bingo", "i won!");
		tom.sendMailItem("jim", "dsdf", "sdfasf");
		tom.sendMailItem("jim", "adfadf", "ads");
		int total=server.howManyMailItems("jim")+server.howManyMailItems("tom_nomessages");
		assertEquals(4, total);
	}
	
	/**
 * create 0 emails, with 0 users and check 0 returned
 */	
	public void testHowManyMailItems_positive_0emails0user()
	{
	    assertEquals(0, server.howManyMailItems("estoban"));
	}

/**
 * creat user and add one mail, check it returns the right item
 */
	public void testGetNextMailItemOneUserOneItem()
	{
		tom.sendMailItem("jim", "one", "one");
		assertEquals("one", server.getNextMailItem("jim").getSubject());
	}
	
/**
 * creat user and add no mail, check it returns null
 */
	public void testGetNextMailItemOneUserNoItem()
	{
		assertEquals(null, server.getNextMailItem("jim"));
	}

/**
 * with no user and no mail, check it returns null
 */
	public void testGetNextMailItemNoUserNoItem()
	{
		assertEquals(null, server.getNextMailItem("david"));
	}

/**
 * creat user and add one mail, check it returns the right item, all lower case name as expected(e.g william)
 */
	public void testGetNextMailItemOneUserOneItemAllLowerCase()
	{
		tom.sendMailItem("jim", "one", "one");
		assertEquals("one", server.getNextMailItem("jim").getSubject());
	}

/**
 * creat user and add one mail, check it returns the right item, all upper case name as expected(e.g. WILLIAM)
 */
	public void testGetNextMailItemOneUserOneItemAllUpperCase()
	{
		tom.sendMailItem("JIM", "one", "one");
		assertEquals("one", server.getNextMailItem("JIM").getSubject());
	}

/**
 * creat user and add one mail, check it returns the right item, mixture of case name as expected(e.g. William)
 */
	public void testGetNextMailItemOneUserOneItemMixtureCases()
	{
		tom.sendMailItem("Jim", "one", "one");
		assertEquals("one", server.getNextMailItem("Jim").getSubject());
	}
	
/**
 * post mail for new user, check mail has got through and check subject is now added
 */
	public void testPostNewUser()
	{
		MailClient mailClie1 = new MailClient(server, "simon");
		jim.sendMailItem("simon", "ola", "ola");
		assertEquals("ola", mailClie1.getNextMailItem().getSubject());
	}

/**
 * post mail for existing user, check mail has got through and check subject is now added
 */
	public void testPostExistingUser()
	{
		tom.sendMailItem("jim", "eh", "eh");
		assertEquals("eh", jim.getNextMailItem().getSubject());
	}


}

