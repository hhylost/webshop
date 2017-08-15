package com.hhy.shop.biz.dal.user.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.xml.XMLSerializer;
import org.apache.commons.collections.CollectionUtils;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.hhy.shop.biz.dal.base.PageQuery;
import com.hhy.shop.biz.dal.user.UserDAO;
import com.hhy.shop.biz.dal.user.dataobject.UserDO;
import com.hhy.shop.biz.dal.user.query.UserPageQuery;

/**
 * Created by lenovo on 2016/10/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath*:META-INF/spring/biz-dao.xml" })
public class UserDAOTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDAOTest.class);
    @Resource
    private UserDAO userDAO;

    private Client client;

    /**
     * 增量查询mysql中的数据，组装成es需要的json数据
     */
    @Test
    public void bulkFileProduct() {
        UserDO userDO = userDAO.getUserName(34L);

        JSONObject json = new JSONObject();
        JSONObject metaJson = new JSONObject();
        metaJson.put("_index", "taotao");
        metaJson.put("_type", "user");
        metaJson.put("_id", "34");
        json.put("create", metaJson);

        JSONObject dataJson = new JSONObject();
        dataJson.put("username", userDO.getUsername());
        dataJson.put("password", userDO.getPassword());
        dataJson.put("phone", userDO.getPhone());
        dataJson.put("email", userDO.getEmail());
        dataJson.put("updated", userDO.getUpdated());
        dataJson.put("created", userDO.getCreated());
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

    //@Before
    public void init() throws Exception {
        Settings settings = Settings.settingsBuilder().put("cluster.name", "yypt-elsearch").build();
        client = TransportClient.builder().settings(settings).build()
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.118.202"), 9300));
    }

    /**
     * 增量更新es索引
     * 
     * @throws Exception
     */
    @Test
    public void esDataHub() throws Exception {

        // 从数据库中查询更新或者新增的数据
        UserPageQuery pageQuery = new UserPageQuery();
        pageQuery.setUsername("zhangsan");
        pageQuery.setIsDelete(0);
        // pageQuery.setGmtStartUpdate(df.parse("2015-04-07 10:33:37"));
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        List<UserDO> result = getUserDOList(pageQuery);

        System.out.println(JSONObject.toJSON(result));

        if (CollectionUtils.isNotEmpty(result)) {
            for (UserDO userDO : result) {
                // 更新索引
                IndexRequest indexRequest = new IndexRequest("taotao", "user", userDO.getId().toString())
                        .source(XContentFactory.jsonBuilder().startObject().field("username", userDO.getUsername())
                                .field("password", userDO.getPassword()).field("phone", userDO.getPhone())
                                .field("email", userDO.getEmail()).field("created", userDO.getCreated())
                                .field("updated", userDO.getUpdated()).endObject());

                // 更新索引
                UpdateRequest updateRequest = new UpdateRequest("taotao", "user", userDO.getId().toString())
                        .doc(XContentFactory.jsonBuilder().startObject().field("username", userDO.getUsername())
                                .field("password", userDO.getPassword()).field("phone", userDO.getPhone())
                                .field("email", userDO.getEmail()).field("created", userDO.getCreated())
                                .field("updated", userDO.getUpdated()).endObject())
                        .upsert(indexRequest);
                client.update(updateRequest).get();
            }
        }
        client.close();
    }

    /**
     * 增量删除es索引
     * 
     * @throws Exception
     */
    @Test
    public void esDataHubDel() throws Exception {

        // 从数据库中查询更新或者新增的数据
        UserPageQuery pageQuery = new UserPageQuery();
        pageQuery.setUsername("zhangsan");
        pageQuery.setIsDelete(1);
        // pageQuery.setGmtStartUpdate(df.parse("2015-04-07 10:33:37"));
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        List<UserDO> result = getUserDOList(pageQuery);

        System.out.println(JSONObject.toJSON(result));

        if (CollectionUtils.isNotEmpty(result)) {
            for (UserDO userDO : result) {
                DeleteResponse dResponse = client.prepareDelete("taotao", "user", userDO.getId().toString()).execute()
                        .actionGet();
                if (dResponse.isFound()) {
                    LOGGER.error("删除成功：{}", userDO.getId());
                } else {
                    LOGGER.error("删除失败：{}", userDO.getId());
                }
            }
        }
        client.close();
    }

    private List<UserDO> getUserDOList(UserPageQuery pageQuery) {

        List<UserDO> result = new ArrayList<UserDO>();
        int totalCount;
        int pageNo = 0;
        int pageCount;

        do {
            pageQuery.setPageIndex(pageNo);
            PageQuery<UserDO> pageQuery1 = userDAO.findByQueryCondition(pageQuery);
            totalCount = pageQuery1.getTotalRecord();
            if (totalCount == 0) {
                LOGGER.error("当前时间段内没有新更新的数据");
                break;
            }
            List<UserDO> list = pageQuery1.getDataList();
            pageCount = pageQuery1.getTotalPage();
            pageNo++;
            result.addAll(list);
        } while (pageNo <= pageCount);
        return result;
    }

    public void deleteFile(String filePath) {
        File f = new File(filePath);
        if (f.exists()) {
            if (f.isDirectory()) {
                File[] dirs = f.listFiles();
                for (File sbuFile : dirs) {
                    if (sbuFile.isDirectory()) {
                        deleteFile(sbuFile.getPath());
                    } else {
                        sbuFile.delete();
                    }
                }
            }
            f.delete();
        }
    }

    @Test
    public void deleteFileTest() {
        System.out.println(new Date().toString());
    }

    @Test
    public void test() {
        String s = "1&";
        System.out.println(s.indexOf("&"));
    }

    @Test
    public void deleteBielian() throws Exception {
        FileReader fr = new FileReader(new File("d:\\userId.txt"));
        BufferedReader br = new BufferedReader(fr);
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File("d:\\sql.txt")));
        String line = null;
        while ((line = br.readLine()) != null) {
            bw.write("DELETE from users where id =" + line + ";\n");
            bw.write("DELETE from user_profile where user_id =" + line + ";\n");
            bw.write("DELETE from user_ext_apps where user_id =" + line + ";\n");
            bw.write("DELETE from user_login_info where user_id =" + line + ";\n");
            bw.write("DELETE from user_patient where user_id =" + line + ";\n");
            bw.write("DELETE from user_app_device where user_id =" + line + ";\n");
        }
        bw.close();
        br.close();
        fr.close();
    }

    @Test
    public void groupBillingDataByExcpBatchCode() throws Exception{
        // 从数据库中查询更新或者新增的数据
        UserPageQuery pageQuery = new UserPageQuery();
        pageQuery.setUsername("zhangsan");
        pageQuery.setIsDelete(0);
        // pageQuery.setGmtStartUpdate(df.parse("2015-04-07 10:33:37"));
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        List<UserDO> billingList = getUserDOList(pageQuery);
        for(UserDO userDO : billingList){
            System.out.println(JSONObject.toJSON(userDO));
        }
        Map<String, List<UserDO>> resultMap = new HashMap<String, List<UserDO>>();

        try{
            for(UserDO tmExcpNew : billingList){

                if(resultMap.containsKey(tmExcpNew.getUsername())){//map中异常批次已存在，将该数据存放到同一个key（key存放的是异常批次）的map中
                    resultMap.get(tmExcpNew.getUsername()).add(tmExcpNew);
                }else{//map中不存在，新建key，用来存放数据
                    List<UserDO> tmpList = new ArrayList<UserDO>();
                    tmpList.add(tmExcpNew);
                    resultMap.put(tmExcpNew.getUsername(), tmpList);

                }

            }

        }catch(Exception e){
            throw new Exception("按照异常批次号对已开单数据进行分组时出现异常", e);
        }

        System.out.println(JSONObject.toJSON(resultMap));
    }

    @Test
    public void setSample() {
        UserDAOTest instance = null;
        UserDAOTest userDAOTest = (UserDAOTest) instance;
    }

}
