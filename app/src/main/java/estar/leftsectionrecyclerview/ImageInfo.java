package estar.leftsectionrecyclerview;

import estar.leftsection.SupperSection;

/**
 * Created by xueliang on 2018/3/14.
 */

public class ImageInfo extends SupperSection {

    private boolean isSection;
    private String sectionText;

    public ImageInfo(boolean isSection, String sectionText) {
        this.isSection = isSection;
        this.sectionText = sectionText;
    }

    @Override
    public boolean isSection() {
        return isSection;
    }

    @Override
    public String getSectionText() {
        return sectionText;
    }
}
