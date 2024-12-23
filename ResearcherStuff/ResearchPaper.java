package ResearcherStuff;

import Entities.DiplomaProject;
import Enumerators.Format;
import People.User;

import java.io.Serializable;
import java.util.Date;

public class ResearchPaper implements Serializable {
    private String title;
    private Date datePublished;
    private User author;
    private int pages;
    private String publisher;
    private String citiation;
    private DiplomaProject diplomaProject;

    public ResearchPaper(String title, Date datePublished, User author, int pages, String publisher, String citiation, DiplomaProject diplomaProject) {
        this.title = title;
        this.datePublished = datePublished;
        this.author = author;
        this.pages = pages;
        this.publisher = publisher;
        this.citiation = citiation;
        this.diplomaProject = diplomaProject;
    }

    public String getTitle() {
        return title;
    }

    public Date getDatePublished() {
        return datePublished;
    }

    public User getAuthor() {
        return author;
    }

    public int getPages() {
        return pages;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getCitiation() {
        return citiation;
    }

    public DiplomaProject getDiplomaProject() {
        return diplomaProject;
    }

    public String getCitation(Format f) {
        if (f == Format.PLAIN_TEXT) {
            return String.format("%s, \"%s\", %s, %s.",
                    author.toString(), title, publisher, datePublished);
        } else if (f == Format.BIBTEX) {
            return String.format("@misc{citation_key, \n" +
                    "  author = {%s}, \n" +
                    "  title = {%s}, \n" +
                    "  publisher = {%s}, \n" +
                    "  year = {%s} \n" +
                    "}", author.toString(), title, publisher, datePublished.getYear() + 1900);
        } else {
            throw new IllegalArgumentException("Invalid format: " + f);
        }
    }
}
