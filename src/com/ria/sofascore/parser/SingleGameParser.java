package com.ria.sofascore.parser;

import com.ria.sofascore.models.Game;
import com.ria.sofascore.models.Set;
import com.ria.sofascore.models.SubSet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class SingleGameParser {

    public void parse(String document, Game game){
        ArrayList<Set> sets1 = new ArrayList<>();
        ArrayList<Set> sets2 = new ArrayList<>();
        Set set1 = null;
        Set set2 = null;
        Document dom = Jsoup.parse(document);
        Elements pbp_wrapper = dom.getElementsByAttributeValue("class", "pbp-wrapper");
        for (Element pbp_w : pbp_wrapper) {
            Elements allElementsPBP = pbp_w.children();
            for (Element e : allElementsPBP) {

                if (e.className().equals("pbp-set-title")) {
                    set1 = new Set();
                    set2 = new Set();
                    set1.setSetTitle(e.text());
                    set2.setSetTitle(e.text());

                }
                if(set1!=null&&!sets1.contains(set1))sets1.add(set1);
                if(set2!=null&&!sets2.contains(set2))sets2.add(set2);
                if (e.className().equals("pbp")) {
                    SubSet subSet1 = new SubSet();
                    SubSet subSet2 = new SubSet();
                    Elements pbp_game = e.getElementsByAttributeValue("class", "pbp__game");
                    for (Element pbpg : pbp_game) {
                        Elements pbp_g_c = pbpg.getElementsByAttributeValueContaining("class", "pbp__game-content");
                        for (Element pbpgc : pbp_g_c) {
                            if (pbpgc.elementSiblingIndex() == 0) {
                                subSet1.getScores().add(pbpgc.text());
                            } else if (pbpgc.siblingIndex() == 1) {
                                subSet2.getScores().add(pbpgc.text());
                            }
                        }
                    }
                    sets1.get(sets1.size()-1).getSubSets().add(subSet1);
                    sets2.get(sets2.size()-1).getSubSets().add(subSet2);
                }
            }
        }
        game.getPlayers().get(0).setSets(sets1);
        game.getPlayers().get(2).setSets(sets2);


    }
}
