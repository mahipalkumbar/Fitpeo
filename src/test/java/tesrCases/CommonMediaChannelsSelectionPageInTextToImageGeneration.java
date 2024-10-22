package tesrCases;

import pageObjects.MediaChannels;
import testBase.BaseClass;

public class CommonMediaChannelsSelectionPageInTextToImageGeneration extends BaseClass{
	public void MediaChannelsSelectionPageInTextToImage(String socialmedia, String size) throws InterruptedException {
		MediaChannels m = new MediaChannels(driver);
		m.socialmedia(socialmedia);
        m.selectSize(size);
        m.goToNext();
	}

}
