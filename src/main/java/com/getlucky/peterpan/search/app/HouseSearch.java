package com.getlucky.peterpan.search.app;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;

public class HouseSearch {
    public HouseSearch() {
        try {
            selectLocation();
            //test();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    // 금액 필터링



    public void selectLocation() throws Exception{

        int min = 20;
        int max = 30;
        int[] menuId = {2 , 48, 3, 49, 50, 51, 71, 76, 77, 73, 72};
        // 3 관악원룸 , 72 관악투룸
        Set<String> list = new HashSet<>();

        for(int i : menuId) {
            for(int j=1; j<=1; j++) {
                Document document = Jsoup.connect("https://cafe.naver.com/ArticleList.nhn?search.clubid=10322296&search.boardtype=L&search.menuid=" + i + "&search.marketBoardTab=D&search.specialmenutype=&userDisplay=50&search.page=" + j).get();

                // Elements element = document.select("div[id=main-area]");
                // Elements element = document.select("tbody");
                Elements elements = document.select("td[class=td_article]");
                for (Element e : elements) {
                    String articleNo = e.select("div[class=inner_number]").text();
                    String title = e.select("a[class=article]").text();
                    for(int k= min; k<=max; k++)
                    if (!articleNo.equals("") && title.contains("/"+k))
                        list.add("https://cafe.naver.com/kig/"+articleNo + ", "+title);
                }
            }
        }
        Iterator<String> iter = list.iterator();
        while(iter.hasNext()) {
            System.out.println(iter.next());
        }


    }




}