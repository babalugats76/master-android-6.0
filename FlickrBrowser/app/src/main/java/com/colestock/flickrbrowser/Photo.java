package com.colestock.flickrbrowser;

public class Photo {
    private String title;
    private String author;
    private String authorId;
    private String link;
    private String tags;
    private String media;

    public Photo(String title, String author, String authorId, String link, String tags, String media) {
        this.title = title;
        this.author = author;
        this.authorId = authorId;
        this.link = link;
        this.tags = tags;
        this.media = media;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getAuthorId() {
        return authorId;
    }

    public String getLink() {
        return link;
    }

    public String getTags() {
        return tags;
    }

    public String getMedia() {
        return media;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", authorId='" + authorId + '\'' +
                ", link='" + link + '\'' +
                ", tags='" + tags + '\'' +
                ", media='" + media + '\'' +
                '}';
    }
}
