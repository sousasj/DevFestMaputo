package mozdevz.org.devfestmaputo.Model;

/**
 * Created by SJ on 12/4/2016.
 */

public class Socials {
    private String name;
    private String link;
    private String icon;

    public Socials() {
    }

    public Socials(String name, String link, String icon) {
        this.name = name;
        this.link = link;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
