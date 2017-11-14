package mozdevz.org.devfestmaputo.Model;

/**
 * Created by SJ on 12/4/2016.
 */

public class Speaker {
    private String name;
    private String bio;
    private String company;
    private String companyLogo;
    private String country;
    private boolean featured;
    private String photoUrl;
    private String title;
    private String shortBio;

    public Speaker() {
    }

    public Speaker(String name, String bio, String company, String companyLogo, String country, boolean featured, String photoUrl, String title, String shortBio) {
        this.name = name;
        this.bio = bio;
        this.company = company;
        this.companyLogo = companyLogo;
        this.country = country;
        this.featured = featured;
        this.photoUrl = photoUrl;
        this.title = title;
        this.shortBio = shortBio;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public String getShortBio() {
        return shortBio;
    }

    public void setShortBio(String shortBio) {
        this.shortBio = shortBio;
    }

    public boolean isFeatured() {
        return featured;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }
}
