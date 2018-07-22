package com.ria.sofascore.parser;

import com.ria.sofascore.models.Game;
import com.ria.sofascore.models.Player;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class MainPageParser {

    public ArrayList<Game> getAllLinks(String document){
        var games = new ArrayList<Game>();
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
                            tc.text().equals("ATP")){
                        Elements aClass = t.getElementsByAttributeValueContaining("class", "cell cell--event-list  pointer list-event js-event js-link");
                        for (int i=0;i<aClass.size();i++) {
                            Game game = new Game();
                            game.setGameURL(getURL(aClass.get(i)));
                            game.setCategoy(getCategory(t));
                            game.setStanding(getStanding(t));
                            game.setPlayers(getPlayers(t,i));
                            games.add(game);
                        }
                    }
                });
            });

        });
        games.forEach(g->{
            System.out.println(g.getGameURL());
            System.out.println(g.getCategoy());
            System.out.println(g.getStanding());
            g.getPlayers().forEach(p->{
                System.out.println(p.getName());
            });
            System.out.println();
        });
        return games;
    }

    private String getStanding(Element t) {
        Elements name = t.getElementsByAttributeValue("class", "tournament__name");
        for (Element element : name) {
            return element.text();
        }
        return "NULL";
    }

    private String getCategory(Element t) {
        Elements category = t.getElementsByAttributeValue("class", "tournament__category");
        for (Element element : category) {
            return element.text();
        }
        return "NULL";
    }

    private ArrayList<Player> getPlayers(Element t, int i) {
        ArrayList<Player> playersR = new ArrayList<>();
        Elements players = t.getElementsByAttributeValue("class", "cell__section--main s-tennisCell curb-width");
        Elements allElements = players.get(i).getElementsByAttributeValue("class","cell__content event-team  ");
        allElements.forEach(ae->{
            Player player = new Player();
            player.setName(ae.text());
            playersR.add(player);
        });
        return playersR;
    }

    private String getURL(Element ac) {
        return "https://www.sofascore.com"+ac.attr("href");
    }

}
