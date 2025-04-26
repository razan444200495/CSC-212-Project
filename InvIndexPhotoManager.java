
package photoapp;

public class InvIndexPhotoManager {
    private BST<LinkedList<Photo>> tagIndex;

    // Constructor
    public InvIndexPhotoManager() {
        tagIndex = new BST<LinkedList<Photo>>();
    }

    // Add a new photo to the index
    public void addPhoto(Photo img) {
        LinkedList<String> tags = img.getTags();

        if (!tags.empty()) {
            tags.findFirst();
            while (!tags.last()) {
                addToIndex(tags.retrieve(), img);
                tags.findNext();
            }
            addToIndex(tags.retrieve(), img); // last tag
        }

        // Also index under a special "ALL" tag to support empty condition
        addToIndex("ALL", img);
    }

    // Helper to insert photo into the index for a given tag
    private void addToIndex(String tag, Photo img) {
        if (tagIndex.findkey(tag)) {
            LinkedList<Photo> list = tagIndex.retrieve();
            list.insert(img);
            tagIndex.update(tag, list);
        } else {
            LinkedList<Photo> newList = new LinkedList<Photo>();
            newList.insert(img);
            tagIndex.insert(tag, newList);
        }
    }

    // Remove a photo by path from all tags
    public void deletePhoto(String path) {
        String allTags = tagIndex.inOrder();
        String[] tagList = allTags.split(" AND ");

        for (String tag : tagList) {
            if (tagIndex.findkey(tag)) {
                LinkedList<Photo> list = tagIndex.retrieve();

                list.findFirst();
                while (!list.last()) {
                    if (list.retrieve().getPath().equalsIgnoreCase(path)) {
                        list.remove();
                        break;
                    }
                    list.findNext();
                }

                if (list.retrieve().getPath().equalsIgnoreCase(path)) {
                    list.remove();
                }

                if (list.getSize() == 0) {
                    tagIndex.removeKey(tag);
                } else {
                    tagIndex.update(tag, list);
                }
            }
        }
    }

    // Return the BST (inverted index)
    public BST<LinkedList<Photo>> getPhotos() {
        return tagIndex;
    }
}
