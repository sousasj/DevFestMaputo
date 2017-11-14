package mozdevz.org.devfestmaputo.Model;

/**
 * Created by SJ on 12/4/2016.
 */

public class Team {
    private String name;
    private String photoUrl;
    private String title;

    public Team() {
    }

    public Team(String name, String photoUrl, String title) {
        this.setName(name);
        this.setPhotoUrl(photoUrl);
        this.setTitle(title);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
