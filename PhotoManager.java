
package photoapp;
public class PhotoManager {
    private LinkedList<Photo> photoCollection;

    // Constructor
    public PhotoManager() {
        photoCollection = new LinkedList<Photo>();
    }

    // Add a photo to the collection
    public void addPhoto(Photo img) {
        if (!isAlreadyExists(img.getPath())) {
            photoCollection.insert(img);
        }
    }

    // Remove a photo by path
    public void deletePhoto(String path) {
        if (photoCollection.empty()) return;

        photoCollection.findFirst();
        while (!photoCollection.last()) {
            if (photoCollection.retrieve().getPath().equalsIgnoreCase(path)) {
                photoCollection.remove();
                return;
            }
            photoCollection.findNext();
        }

        // Check last item
        if (photoCollection.retrieve().getPath().equalsIgnoreCase(path)) {
            photoCollection.remove();
        }
    }

    // Return all stored photos
    public LinkedList<Photo> getPhotos() {
        return photoCollection;
    }

    // Check if a photo already exists
    private boolean isAlreadyExists(String path) {
        if (photoCollection.empty()) return false;

        photoCollection.findFirst();
        while (!photoCollection.last()) {
            if (photoCollection.retrieve().getPath().equalsIgnoreCase(path)) {
                return true;
            }
            photoCollection.findNext();
        }

        return photoCollection.retrieve().getPath().equalsIgnoreCase(path);
    }
}
