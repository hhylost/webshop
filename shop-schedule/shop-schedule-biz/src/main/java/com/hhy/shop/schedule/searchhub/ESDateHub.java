package com.hhy.shop.schedule.searchhub;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;

import javax.annotation.Resource;

import net.sf.json.xml.XMLSerializer;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.hhy.shop.biz.dal.user.dataobject.UserDO;
import com.hhy.shop.biz.manager.user.UserManager;

/**
 * es数据导入
 *
 * @author hehy
 * @create 2017-05-27
 **/
public class ESDateHub {
    @Resource
    private UserManager userManager;

    /**
     * 增量查询mysql中的数据，组装成es需要的json数据
     */
    public void bulkProcess() {
        UserDO userDO = new UserDO();
        userDO.setId(37L);
        userDO.setEmail("898937510@qq.com");
        userDO.setPhone("1253262122");
        userDO.setUsername("hhy");

        JSONObject json = new JSONObject();
        JSONObject metaJson = new JSONObject();
        metaJson.put("_index", "taotao");
        metaJson.put("_type", "user");
        metaJson.put("_id", "37");
        json.put("create", metaJson);

        JSONObject dataJson = new JSONObject();
        dataJson.put("username", userDO.getUsername());
        dataJson.put("phone", userDO.getPhone());
        dataJson.put("email", userDO.getEmail());

        File f = new File("e:\\es_user.json");
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(f);
            fileWriter.write(json.toJSONString() + "\r\n" + dataJson.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String args[]) throws IOException, InterruptedException {
        long start = System.currentTimeMillis();
        ESDateHub esDateHub = new ESDateHub();
        try {
            esDateHub.bulkProcess();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() - start);
    }

    @Test
    public void esQuery() throws Exception {
        Settings settings = Settings.settingsBuilder().put("cluster.name", "yypt-elsearch").build();
        TransportClient client = TransportClient.builder().settings(settings).build()
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.118.202"), 9300));
        SearchRequestBuilder responsebuilder = client.prepareSearch("taotao").setTypes("user");
        SearchResponse response = responsebuilder.setQuery(QueryBuilders.matchPhraseQuery("username", "hhy")).setFrom(0)
                .setSize(10).setExplain(true).execute().actionGet();
        System.out.println(JSONObject.toJSON(response.getHits()));
    }

    @Test
    public void xml2json(){
        String xml = "";
        XMLSerializer xmlSerializer = new XMLSerializer();
        System.out.println(xmlSerializer.read(xml).toString());
    }
}
