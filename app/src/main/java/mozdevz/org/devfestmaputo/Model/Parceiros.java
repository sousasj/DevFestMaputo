package mozdevz.org.devfestmaputo.Model;

/**
 * Created by SJ on 12/4/2016.
 */

public class Parceiros {
    private String name;
    private String logoUrl;
    private String url;

    public Parceiros() {
    }

    public Parceiros(String name, String logoUrl, String url) {
        this.name = name;
        this.logoUrl = logoUrl;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
