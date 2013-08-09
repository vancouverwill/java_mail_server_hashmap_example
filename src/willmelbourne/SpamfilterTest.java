/**
 * The test class SpamfilterTest.
 *
 * @author  William Melbourne
 * @version 2010.10.10
 */
public class SpamfilterTest extends junit.framework.TestCase
{
	private Spamfilter filter;

    /**
     * Default constructor for test class SpamfilterTest
     */
    public SpamfilterTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    protected void setUp()
    {
		filter = new Spamfilter();
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
 * try safe subject
 */
	public void testSafePhrase()
	{
		assertEquals(true, filter.isNotSpam("super duper random"));
	}

/**
 * try subject with "spam" in the middle of the sentence(e.g. I hate spam)
 */
	public void testSafePhraseWithWordSpamInMiddle()
	{
		assertEquals(true, filter.isNotSpam("okay spam in middle"));
	}

/** 
 * try with spam at beginning of sentence 
 */
	public void testSpamAtBeginning()
	{
		assertEquals(false, filter.isNotSpam("spam is bad"));
	}

/**
 * try with spam at beginning of sentence capital letters
 */
	public void testSpamAtBeginningCapitals()
	{
		assertEquals(false, filter.isNotSpam("SPAM is bad"));
	}

/**
 * Negative test should come out false with subject with each of bad phrases in the middle ( "0nline Pharmacy", "Cheap Viagra", "Generic Viagra", "Fake Watches")
 */
	public void testContainsPhraseOnline()
	{
		assertEquals(false, filter.isNotSpam("blah blah 0nline Pharmacy oi oi"));
	}

/**
 * Negative test should come out false with subject with each of bad phrases in the middle "Cheap Viagra"
 */
	public void testContainsPhraseCheap()
	{
		assertEquals(false, filter.isNotSpam("blah blah Cheap Viagra oi oi"));
	}
	
/**
 * Negative test should come out false with subject with each of bad phrases in the middle "Generic Viagra"
 */
	public void testContainsPhraseGerneric()
	{
		assertEquals(false, filter.isNotSpam("blah blah Generic Viagra oi oi"));
	}

/**
 * Negative test should come out false with subject with each of bad phrases in the middle "Fake Watches"
 */
	public void testContainsPhraseFake()
	{
		assertEquals(false, filter.isNotSpam("blah blah Fake Watches oi oi"));
	}
	
/**
 * Negative test should come out false with subject with each of bad phrases in CAPITALS in the middle "0nline Pharmacy"
 */
	public void testcontainsCAPITALBadPhraseOnline()
	{
		assertEquals(false, filter.isNotSpam("blah blah 0NLINE PHARMACY oi oi"));
	}

/**
 * Negative test should come out false with subject with each of bad phrases in CAPITALS in the middle "Cheap Viagra"
 */
	public void testcontainsCAPITALBadPhraseCheap()
	{
		assertEquals(false, filter.isNotSpam("blah blah CHEAP VIAGRA oi oi"));
	}

/**
 * Negative test should come out false with subject with each of bad phrases in CAPITALS in the middle "Generic Viagra"
 */
	public void testcontainsCAPITALBadPhraseGerneric()
	{
		assertEquals(false, filter.isNotSpam("blah blah GENERIC VIAGRA oi oi"));
	}
	
/**
 * Negative test should come out false with subject with each of bad phrases in CAPITALS in the middle  "Fake Watches"
 */
	public void testcontainsCAPITALBadPhraseFake()
	{
		assertEquals(false, filter.isNotSpam("blah blah FAKE WATCHES oi oi"));
	}

/**
 * test with two bad phrases
 */
	public void testContainsTwoBadPhrase()
	{
		assertEquals(false, filter.isNotSpam("adsfadf Fake Watches fsddfs Generic Viagra sdafads"));
	}
	
/**
 * test with spam at beginning of word and bad phrase 
 */
	public void testBadPhraseInMiddleAndSpamAtBeginning()
	{
		assertEquals(false, filter.isNotSpam("spam adsfadf Fake Watches fsddfs Generic Viagra sdafads"));
	}
}






