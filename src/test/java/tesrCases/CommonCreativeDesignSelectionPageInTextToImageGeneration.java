package tesrCases;

import pageObjects.Creativedesign;
import testBase.BaseClass;

public class CommonCreativeDesignSelectionPageInTextToImageGeneration extends BaseClass{
	public void CreativeDesignSelectionPageInTextToImage(String brandname, String category, String abouturbrand, String tgname,
            String gender, String agegruop, String region, String brandlogo, String productname,
            String productdescription, String productlogo, String campaignname, String objective,
            String socialmedia, String size, String imagecontext, String focuselements,
            String imagestyle, String imageprompt) throws InterruptedException {
		Creativedesign cr = new Creativedesign(driver);
		 cr.contextofimage(imagecontext);
         cr.focusing(focuselements);
         cr.imagestyle(imagestyle);
         cr.submit();
         cr.generate(imageprompt);
         cr.generatef();
	}

}
