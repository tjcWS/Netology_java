package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NasaAnswer {
    String date;
    String explanation;
    String hdurl;
    String media_typeate;
    String service_version;
    String title;
    String url;
    String copyright;

    public NasaAnswer(
            @JsonProperty("date") String date,
            @JsonProperty("explanation") String explanation,
            @JsonProperty("hdurl") String hdurl,
            @JsonProperty("media_type") String media_typeate,
            @JsonProperty("service_version") String service_version,
            @JsonProperty("title") String title,
            @JsonProperty("copyright") String copyright,
            @JsonProperty("url") String url)
    {
        this.date = date; //this это атрибуты класса
        this.explanation = explanation;
        this.hdurl = hdurl;
        this.media_typeate = media_typeate;
        this.service_version = service_version;
        this.title = title;
        this.url = url;
    }
}
