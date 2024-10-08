package tesrCases;

import pageObjects.MediaChannels;
import testBase.BaseClass;

public class CommonMediaChannelsSelectionPageInTextToImageGeneration extends BaseClass{
	public void MediaChannelsSelectionPageInTextToImage(String brandname, String category, String abouturbrand, String tgname,
            String gender, String agegruop, String region, String brandlogo, String productname,
            String productdescription, String productlogo, String campaignname, String objective,
            String socialmedia, String size, String imagecontext, String focuselements,
            String imagestyle, String imageprompt) throws InterruptedException {
		MediaChannels m = new MediaChannels(driver);
		 m.socialmedia(socialmedia);
         m.selectSize(size);
        // m.nexts();
	}

}
