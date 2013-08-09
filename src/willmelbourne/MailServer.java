package src.willmelbourne;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

/**
 * A simple model of a mail server. The server is able to receive
 * mail items for storage, and deliver them to clients on demand.
 * @author David J. Barnes and Michael Kolling (edited by William Melbourne)
 * @version 2010.10.10
 */
public class MailServer
{
    // Storage for the arbitrary number of mail items to be stored
    // on the server.
    private HashMap<String, List<MailItem>> mailBoxes;

    /**
     * Construct a mail server.
     */
    public MailServer()
    {
        mailBoxes = new HashMap<String, List<MailItem>>();
    }
    
    /**
     * Return how many mail items are waiting for a user.
     * @param who The user to check for.
     * @return How many items are waiting.
     */
    public int howManyMailItems(String who)
    {
       who=formatName(who);
       if( mailBoxes.containsKey(who)) {
           List<MailItem> tempMailBox=mailBoxes.get(who);
           int count = tempMailBox.size();
           return count;
        }
        // if a mail box doesn't exist for this user, then create one
        else {
           newUser(who);
           return 0;
        }
    }

    /**
     * Return the next mail item for a user or null if there
     * are none.
     * @param who The user requesting their next item.
     * @return The user's next item.
     */
    public MailItem getNextMailItem(String who)
    {
        who=formatName(who);
        if (mailBoxes.containsKey(who)) {
            List<MailItem> tempMailBox = mailBoxes.get(who);
            Iterator<MailItem> it = tempMailBox.iterator();
                while(it.hasNext()) {
                    MailItem item = it.next();
                    it.remove();
                    return item;
                }
                return null;
         }
         // if a mail box doesn't exist for this user, then create one
         else {
               newUser(who);
               return null;
        }  
    }

    /**
     * Add the given mail item to the message list.
     * @param item The mail item to be stored on the server.
     */
    public void post(MailItem item)
    {
        String recipient = formatName(item.getTo());
        if( mailBoxes.containsKey(recipient)) {
            // if there is already a server for that recipient then add mail to mailbox
            List<MailItem> tempMailBox=mailBoxes.get(recipient);
            tempMailBox.add(item);
        }
        else {
            // else if there is no server for that recipient then add mail to a new mailbox
            newUserWithMail(recipient, item);
        }
    }
    
    /** @return the properly-formatted name. */
    public static String formatName(String name)
    {
        String inputLine = name.trim().toLowerCase();
        String formattedName = inputLine.substring(0,1).toUpperCase() + inputLine.substring(1);
        return formattedName;
    }
    
     /**
    * print out all messages sorted by recipient, if recipient has no messages then print "no mail"
    */
    public void printMessagesSortedByRecipient()
    {
        for (Object key: mailBoxes.keySet()) {
            List<MailItem> tempMailBox = mailBoxes.get(key);
            System.out.println();
            System.out.println("XXXXXXXXXXXXX");
            System.out.println("MailBox: "+key);     
            if(tempMailBox.size() > 0) {
                for(MailItem item : tempMailBox) { 
                        item.print() ;
                }
            }
            else {
                System.out.println("No mail");
            }
        }
   }
   
   private void newUser(String recipient)
   {
        recipient=formatName(recipient);
        mailBoxes.put(recipient,new ArrayList<MailItem>());
    }
    
   private void newUserWithMail(String recipient, MailItem item)
   {
        recipient=formatName(recipient);
        ArrayList<MailItem> tempMailBox = new ArrayList<MailItem>();
        tempMailBox.add(item);
        mailBoxes.put(recipient, tempMailBox);
    }
}
