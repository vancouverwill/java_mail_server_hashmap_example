package src.willmelbourne;

/**
 * Write a description of class Spamfilter here.
 * 
 * @author William Melbourne 
 * @version 2010.10.10
 */
public class Spamfilter
{
    // instance variables - replace the example below with your own
    private static final String spamAlert = "spam";
    private static final String[] spamPhrases = {
        "0nline Pharmacy", "Cheap Viagra", "Generic Viagra", "Fake Watches"
    };

    /**
     * Constructor for objects of class Spamfilter
     */
    public Spamfilter()
    {
        // nothing to do at the moment...
    }

   /**
    * checkforspam
    */
   public static boolean isNotSpam(String subject)
   {
       subject = subject.trim().toLowerCase();
        if (subject.startsWith(spamAlert)) {
            return false;
        }
        for (int i = 0; i < spamPhrases.length; i++) {
            if(subject.contains(spamPhrases[i].toLowerCase())){
                return false;
            }
        }
        return true;
   }
  
}
