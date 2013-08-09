package src.willmelbourne;

public class MailServerInitializer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MailServer remote = new MailServer();
		MailClient home = new MailClient(remote, "Will");
		
		MailItem letter1 = new MailItem("will", "james", "how you doing?");
		
	}

}
