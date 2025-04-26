
package photoapp;
public class Photo {
    private String imagePath;
    private LinkedList<String> labelList;

    // Constructor
    public Photo(String filePath, LinkedList<String> tags) {
        this.imagePath = filePath;
        labelList = new LinkedList<String>();

        if (!tags.empty()) {
            tags.findFirst();
            while (!tags.last()) {
                labelList.insert(tags.retrieve());
                tags.findNext();
            }
            labelList.insert(tags.retrieve());
        }
    }

    // Return the full file name (the path) of the photo
    public String getPath() {
        return imagePath;
    }

    // Return all tags associated with the photo
    public LinkedList<String> getTags() {
        LinkedList<String> copyTags = new LinkedList<String>();

        if (!labelList.empty()) {
            labelList.findFirst();
            while (!labelList.last()) {
                copyTags.insert(labelList.retrieve());
                labelList.findNext();
            }
            copyTags.insert(labelList.retrieve());
        }

        return copyTags;
    }

    @Override
    public String toString() {
        String description = "Photo{Path=" + imagePath + ", Tags=";
        labelList.findFirst();
        while (!labelList.last()) {
            description += labelList.retrieve() + "; ";
            labelList.findNext();
        }
        description += labelList.retrieve() + "}";
        return description;
    }
}
