package com.utils;

import com.pojo.Content;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HtmlTest {
    public static void main(String[] args) throws Exception {
        String url = "https://www.meituan.com/meishi/1287610858/";
        //解析网页 Jsoup返回document对象
        Document parse = Jsoup.parse(new URL(url), 30000);
        Elements div = parse.select("div");
        System.out.println(div.html());


    }

    public List<Content> parseMT(String keyword) throws Exception {
        //获得请求
        String url = "https://www.meituan.com/meishi/1287610858/";
        //解析网页 Jsoup返回document对象
        Document parse = Jsoup.parse(new URL(url), 30000);
        Element j_goodsList = parse.getElementById("app");
        Elements comment = parse.getElementsByClass("com-cont");
        Elements li = j_goodsList.getElementsByTag("li");

        ArrayList<Content> goodsList = new ArrayList<>();
        for (Element element : li) {
            String text = element.getElementsByClass("p-price").eq(0).text();
            String title = element.getElementsByClass("p-name").eq(0).text();
            Content content = new Content();
            content.setTitle(title);
            content.setPrice(text);
            goodsList.add(content);
        }
        return goodsList;
    }
}
