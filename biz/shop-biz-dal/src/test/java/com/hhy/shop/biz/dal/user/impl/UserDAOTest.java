package com.hhy.shop.biz.dal.user.impl;

import org.junit.Test;

import java.io.*;

/**
 * Created by lenovo on 2016/10/17.
 */
public class UserDAOTest {
    @Test
    public void generateSql(){

    }

    public void deleteFile(String filePath){
        File f = new File(filePath);
        if(f.exists()){
            if(f.isDirectory()){
                File[] dirs = f.listFiles();
                for(File sbuFile : dirs){
                    if(sbuFile.isDirectory()){
                        deleteFile(sbuFile.getPath());
                    }else {
                        sbuFile.delete();
                    }
                }
            }
            f.delete();
        }
    }

    @Test
    public void deleteFileTest(){
//        deleteFile("");
    }

    @Test
    public void test(){
        String s = "1&";
        System.out.println(s.indexOf("&"));
    }

    @Test
    public void deleteBielian() throws Exception{
        FileReader fr = new FileReader(new File("d:\\userId.txt"));
        BufferedReader br = new BufferedReader(fr);
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File("d:\\sql.txt")));
        String line = null;
        while ((line=br.readLine()) != null){
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
}
