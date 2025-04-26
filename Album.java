package photoapp;

public class Album {
    private String albumName;
    private String condition;
    private PhotoManager manager;
    private int comparisons;

    // Constructor
    public Album(String name, String cond, PhotoManager pm) {
        this.albumName = name;
        this.condition = cond;
        this.manager = pm;
        comparisons = 0;
    }

    // Get album name
    public String getName() {
        return albumName;
    }

    // Get condition
    public String getCondition() {
        return condition;
    }

    // Get photo manager
    public PhotoManager getManager() {
        return manager;
    }

    // Get number of tag comparisons
    public int getNbComps() {
        return comparisons;
    }

    // Get all matching photos
    public LinkedList<Photo> getPhotos() {
        LinkedList<Photo> matching = new LinkedList<Photo>();
        LinkedList<Photo> allPhotos = manager.getPhotos();
        comparisons = 0;

        if (allPhotos.empty()) return matching;

        allPhotos.findFirst();
        while (!allPhotos.last()) {
            Photo p = allPhotos.retrieve();
            if (matchesCondition(p.getTags())) {
                matching.insert(p);
            }
            allPhotos.findNext();
        }
        // Last photo
        if (matchesCondition(allPhotos.retrieve().getTags())) {
            matching.insert(allPhotos.retrieve());
        }

        return matching;
    }

    // Check if photo satisfies all tags in condition
    private boolean matchesCondition(LinkedList<String> photoTags) {
        if (condition.trim().equals("")) return true;

        String[] requiredTags = condition.split(" AND ");
        for (String tag : requiredTags) {
            boolean found = false;

            if (!photoTags.empty()) {
                photoTags.findFirst();
                while (!photoTags.last()) {
                    comparisons++;
                    if (photoTags.retrieve().equalsIgnoreCase(tag)) {
                        found = true;
                        break;
                    }
                    photoTags.findNext();
                }
                comparisons++;
                if (!found && photoTags.retrieve().equalsIgnoreCase(tag)) {
                    found = true;
                }
            }

            if (!found) return false;
        }
        return true;
    }
}
