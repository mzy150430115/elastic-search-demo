package com.utils;

import com.pojo.Content;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HtmlParseUtil {
    public static void main(String[] args) throws Exception {
       new HtmlParseUtil().parseJD("key").forEach(System.out::println);
    }

    public List<Content> parseJD(String keyword) throws Exception {
        //获得请求
        String url = "https://search.jd.com/Search?keyword=" + keyword;
        //解析网页 Jsoup返回document对象
        Document parse = Jsoup.parse(new URL(url), 30000);
        Element j_goodsList = parse.getElementById("J_goodsList");
        Elements li = j_goodsList.getElementsByTag("li");

        ArrayList<Content> goodsList = new ArrayList<>();
        for (Element element : li) {
            String img = element.getElementsByTag("img").eq(0).attr("data-lazy-img");
            String text = element.getElementsByClass("p-price").eq(0).text();
            String title = element.getElementsByClass("p-name").eq(0).text();
            Content content = new Content();
            content.setTitle(title);
            content.setImg(img);
            content.setPrice(text);
            goodsList.add(content);
        }
        return goodsList;
    }

}

