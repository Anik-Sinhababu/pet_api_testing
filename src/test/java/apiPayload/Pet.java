package apiPayload;

import java.io.File;

public class Pet {

    int petID;
    File imageFile;
    String additionalMetadata;
    public int getPetID() {
        return petID;
    }
    public void setPetID(int petID) {
        this.petID = petID;
    }
    public File getImageFile() {
        return imageFile;
    }
    public void setImageFile(File imageFile) {
        this.imageFile = imageFile;
    }
    public String getAdditionalMetadata() {
        return additionalMetadata;
    }
    public void setAdditionalMetadata(String additionalMetadata) {
        this.additionalMetadata = additionalMetadata;
    }
    
}
