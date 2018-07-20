package com.ria.sofascore.parser;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class HtmlParser {

    public ArrayList<String> getAllLinks(String document){
        var links = new ArrayList<String>();
        var doc = Jsoup.parse(document);
        Elements eventList = doc.getElementsByAttributeValue("class", "js-event-list-table-container event-list");
        eventList.forEach(el->{
            Elements tournaments = el.getElementsByAttributeValue("class", "js-event-list-tournament tournament");
            tournaments.forEach(t->{
                Elements tournamentCategory = t.getElementsByAttributeValue("class", "tournament__category");
                tournamentCategory.forEach(tc->{
                    if(tc.text().equals("ITF Мужчины")||
                            tc.text().equals("ITF Женщины")||
                            tc.text().equals("Челленджер")||
                            tc.text().equals("WTA")||
                            tc.text().equals("APT")){
                        Elements aClass = t.getElementsByAttributeValueContaining("class", "cell cell--event-list  pointer list-event js-event js-link");
                        aClass.forEach(ac->{
                            String href = ac.attr("href");
                            if(!links.contains(href))links.add(href);
                        });
                    }
                });
            });

        });
        return links;
    }

}
