package photoapp;
public class Test {
    public static void main(String[] args) {
        PhotoManager storage = new PhotoManager();
        InvIndexPhotoManager searchEngine = new InvIndexPhotoManager();

        // Add custom images
        Photo lion = new Photo("lion.jpg", createTags("creature, roar, jungle, power"));
        Photo falcon = new Photo("falcon.jpg", createTags("creature, falcon, sky, wings, jungle"));
        Photo bee = new Photo("blue-bee.jpg", createTags("insect, bee, flower, buzz, blue"));

        storage.addPhoto(lion);
        storage.addPhoto(falcon);
        storage.addPhoto(bee);

        searchEngine.addPhoto(lion);
        searchEngine.addPhoto(falcon);
        searchEngine.addPhoto(bee);

        // Create albums with new names and conditions
        Album album1 = new Album("Roaring Jungle", "roar", storage);
        Album album2 = new Album("Creatures in Jungle", "creature AND jungle", storage);
        Album album3 = new Album("All Collection", "", storage);

        // Print results
        showAlbum(album1);
        showAlbum(album2);
        showAlbum(album3);

        System.out.println("\nDeleting 'lion.jpg'...\n");
        storage.deletePhoto("lion.jpg");

        // Reprint after deletion
        Album albumAfterDelete = new Album("Updated Collection", "", storage);
        showAlbum(albumAfterDelete);
    }

    private static LinkedList<String> createTags(String tagLine) {
        LinkedList<String> tagList = new LinkedList<>();
        String[] items = tagLine.split("\\s*,\\s*");
        for (String tag : items) {
            tagList.insert(tag);
        }
        return tagList;
    }

    private static void showAlbum(Album album) {
        System.out.println("\nAlbum: " + album.getName());
        System.out.println("Condition: " + album.getCondition());

        LinkedList<Photo> photos = album.getPhotos();
        if (photos.empty()) {
            System.out.println("No matching photos.");
            return;
        }

        photos.findFirst();
        while (!photos.last()) {
            System.out.println("- " + photos.retrieve().getPath());
            photos.findNext();
        }
        System.out.println("- " + photos.retrieve().getPath());

        System.out.println("Comparisons: " + album.getNbComps());
    }
}
