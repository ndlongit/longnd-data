
// import the kaltura java client packages
import com.kaltura.client.*;
import com.kaltura.client.enums.*;
import com.kaltura.client.services.*;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.*;

// import other packages that are used in this example
import java.io.File;


class KalturaExample {

	// define partner data
	private static final  String USER_SECRET  ="8d57c8aadc0afbfa48eca935672185b8";   // replace with user secret string
	private static final  String ADMIN_SECRET ="1dd56b6b6c8880ae02eab15ee642bfb4";  // replace with admin secret string
	private static final  int    PARTNER_ID   = 619142;                   // replace with real partner ID


	public KalturaExample() {
		// nothing needs to be done
	}


	/**
	 * Create a new KalturaClient object with an initiated session.
	 * 
	 * @param partnerId Partner's ID number
	 * @param secret    Secret string (either user's or admin's, depending on 'isAdmin')
	 * @param isAdmin   Should client be an admin or a normal user ?
	 *
	 * @return          A configured client that you can use to call API services
	 *
	 * @throws KalturaApiException
	 */
	private KalturaClient getKalturaClient(int partnerId, String secret, boolean isAdmin) throws KalturaApiException
	{
		// set a new configuration object
		KalturaConfiguration config = new KalturaConfiguration();
		config.setPartnerId(partnerId);
		config.setEndpoint("http://www.kaltura.com");

		// get a new client object using our configuration
		KalturaClient client = new KalturaClient(config);

		// start a new session (admin/user) and recieve the ks (kaltura session) string
		String userId = "user's name";
		KalturaSessionType sessionType = (isAdmin ? KalturaSessionType.ADMIN : KalturaSessionType.USER);
		String ks = client.getSessionService().start(secret, userId, sessionType);

		// set the kaltura client to use the recieved ks as default for all future operations
		client.setSessionId(ks);

		// return the configured client
		return client;
	}



	/**
	 * Upload a media clip to the kaltura server's and create an entry for it.
	 *
	 * @param String fileName   File to upload.
	 * @param String entryName  Name for the new entry.
	 *
	 * @throws KalturaApiException
	 */
	private void addEntry(String fileName, String entryName) throws KalturaApiException
	{
		// create a new USER-session client
		KalturaClient client = getKalturaClient(PARTNER_ID, USER_SECRET, false);			

		// upload the new file and recieve the token that identifies it on the kaltura server
		File up = new File(fileName);
		String token = client.getBaseEntryService().upload(up);

		// create a new entry object with the required meta-data
		KalturaBaseEntry entry = new KalturaBaseEntry();
		entry.name = entryName;
		entry.type = KalturaEntryType.MEDIA_CLIP;

		// add the entry you created to the kaltura server, by attaching it with the uploaded file
		KalturaBaseEntry newEntry = client.getBaseEntryService().addFromUploadedFile(entry, token);

		// newEntry now contains the information of the new entry that was just created on the server
		System.out.println("New entry created successfuly with ID " + newEntry.id);
	}



	/**
	 * Get a list of entries on the kaltura server
	 * 
	 * @param mediaType  type of entries
	 * @param howMany    maximum numer of entries
	 *
	 * @throws KalturaApiException
	 */
	public void listEntries(KalturaMediaType mediaType, int howMany) throws KalturaApiException
	{
		// create a new ADMIN-session client
		KalturaClient client = getKalturaClient(PARTNER_ID, ADMIN_SECRET, true);

		// create a new mediaService object for our client
		KalturaMediaService mediaService = client.getMediaService();

		// create a new filter to filter entries - not mandatory
		KalturaMediaEntryFilter filter = new KalturaMediaEntryFilter();
		filter.mediaTypeEqual = mediaType;

		// create a new pager to choose how many and which entries should be recieved
		// out of the filtered entries - not mandatory
		KalturaFilterPager pager = new KalturaFilterPager();
		pager.pageSize  = howMany;

		// execute the list action of the mediaService object to recieve the list of entries
		KalturaMediaListResponse listResponse = mediaService.list(filter, pager);
//		String entryId = "0_1t8fhjro";
//        KalturaMediaEntry mediaEntry = mediaService.get(entryId );

		// loop through all entries in the reponse list and print their id.
		System.out.println("Entries list :");
		for (KalturaMediaEntry entry : listResponse.objects) {
			System.out.println("id:" + entry.id);
		}
	}


	public static void main (String[] args)
	{
		try {
			KalturaExample example = new KalturaExample();

			// add 2 new entries
			example.addEntry("example_file_1.flv", "example1");
			example.addEntry("example_file_2.flv", "example2");

			// list entries currently on the server (the new entries will probably not show up
			// because they are still being converted
			example.listEntries(KalturaMediaType.VIDEO, 10);

		}
		catch (KalturaApiException e) {
			e.printStackTrace();
		}          
	}


}
