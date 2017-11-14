package mozdevz.org.devfestmaputo.Model;

/**
 * Created by SJ on 12/4/2016.
 */

public class Session {
    private String complexity;
    private String description;
    private String language;
    private String presentation;
    private String title;

    public Session() {
    }

    public Session(String complexity, String description, String language, String presentation, String title) {
        this.complexity = complexity;
        this.description = description;
        this.language = language;
        this.presentation = presentation;
        this.title = title;
    }

    public String getComplexity() {
        return complexity;
    }

    public void setComplexity(String complexity) {
        this.complexity = complexity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
