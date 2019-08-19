package com.ice.spider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.util.*;

/**
 * 聚财村借贷人信息爬取
 * @ClassName SpiderTest
 * @Description TODO
 * @Author liubin
 * @Date 2019/8/1 10:32 AM
 **/
public class SpiderTest {
    public static Map<String,String> cityUrl = new HashMap<>();
    static{
        cityUrl.put("杭州","https://api.jucaicun.com/3F30C9D673490585DDA72BCA9DF2657E/recruit/list?os=Android+8.1.0&city=%E6%9D%AD%E5%B7%9E&src=android&sign=L%2B3qztm7vI%2FXBQIKtqQD6p6r%2BtLv6pwdH5YDZUl32Vs97wM%2Bl6yTw0GSNPk9ywFgd84FJuhcAlTvDMwfuzJpO5duTV0L1HyfyJLciHQO27zhAbgIoxWS9KFWZwBE%2BXbFTGFfj7I29xmxC87UfLJ1MRMMqFIhuIAzlj%2FUUb38xYB6z1%2Br5R5QltqESThhuuq0%2F1ojUBv7d96CywHZNg3Pl5lSd5jqlhI564f2NQQJcEB7PiH%2FlvUufDazNzaIOBFXaA0o%2BGpg2hCtA56qdyTJf94wLViXayvhbj%2BO9nhJQJDGyGe9G3t5uuEdKO0Ah5HUGtjJFqRqcLhAeCuuAczUGA%3D%3D&tempo=01143304063010100003013050&kw=&usertoken=2%2FVMnND6cK1o3%2F30p2TzF%2BvpCjA%3DHPyF&uuid=866934038665637&userid=197417&appv=5.1.3&pagesize=20&model=vivo+X20A&page=");
        cityUrl.put("北京","https://api.jucaicun.com/3F30C9D673490585DDA72BCA9DF2657E/recruit/list?os=Android+8.1.0&city=%E5%8C%97%E4%BA%AC&src=android&sign=DuVTD3X8QTxLPWAM2t%2BAQilBqG%2FOwa7nLiCwGdGOpKTWK4fzc4pSkyHk3QMCLs7jFq6syvnZ9tipjzSBlSivDbh0T0Y4b%2BRcjF6RaCE6XRUnSozai%2BtNQSJ7yHn244%2B7YaenS3IW4hsYJdOVqraR34bFX43bGEGJRdJYJeWB%2B6TsE9GKzTAC7%2BoB8IU5zyz%2BlM0pxvZSy7rknnQnJU%2FJ1zdwGfVYfKFAGFAabGVfssB7vf4uYwj4C74US%2BIJvYU56%2FGrRItT6kZRoy6PE74U6UJQRgSokzFurPdOgmVmGwZ9ZzFtTilIDez0%2FjpnYgaSkoOUTfZhhQVf3QzK51FscA%3D%3D&tempo=01233340333132210700210916&kw=&usertoken=2%2FVMnND6cK1o3%2F30p2TzF%2BvpCjA%3DHPyF&uuid=866934038665637&userid=197417&appv=5.1.3&pagesize=20&model=vivo+X20A&page=");
        cityUrl.put("上海","https://api.jucaicun.com/3F30C9D673490585DDA72BCA9DF2657E/recruit/list?os=Android+8.1.0&city=%E4%B8%8A%E6%B5%B7&src=android&sign=eQjyNwtFt3vA5Vnm02nL7Ii1TtACJHM7w7aHo%2BROxwvx3LRoG8EENVH0Gqqmso%2BpLej19gt0RyTWNnC6PeVqb2JcN%2Fm%2FZv4jbTDkI%2BGPNC1PwqeKglB7oaLMkxmhXzTxJTUqeQlf1qWoOLRPReTI%2BFtc7bjNQC4V7GoR0MoiC3j82bwMEv9nGnw0JZh2LklD615q7NkanvZ6hdmcDk8Szxx9fw3F3j0M%2BkNcxIC7k5yNocjONX8Tcp%2FDDynuGw5DDRENEGWIxFhwzYvd0R8Se829fWD%2BkA9wMGJJfnb6jDaVMFbFFI%2BJz6qjwQn9lHkut69XMCXR41VsaKMsIXtC3A%3D%3D&tempo=01320613603150095300128072&kw=&usertoken=2%2FVMnND6cK1o3%2F30p2TzF%2BvpCjA%3DHPyF&uuid=866934038665637&userid=197417&appv=5.1.3&pagesize=20&model=vivo+X20A&page=");
        cityUrl.put("南京","https://api.jucaicun.com/3F30C9D673490585DDA72BCA9DF2657E/recruit/list?os=Android+8.1.0&city=%E4%B8%8A%E6%B5%B7&src=android&sign=eQjyNwtFt3vA5Vnm02nL7Ii1TtACJHM7w7aHo%2BROxwvx3LRoG8EENVH0Gqqmso%2BpLej19gt0RyTWNnC6PeVqb2JcN%2Fm%2FZv4jbTDkI%2BGPNC1PwqeKglB7oaLMkxmhXzTxJTUqeQlf1qWoOLRPReTI%2BFtc7bjNQC4V7GoR0MoiC3j82bwMEv9nGnw0JZh2LklD615q7NkanvZ6hdmcDk8Szxx9fw3F3j0M%2BkNcxIC7k5yNocjONX8Tcp%2FDDynuGw5DDRENEGWIxFhwzYvd0R8Se829fWD%2BkA9wMGJJfnb6jDaVMFbFFI%2BJz6qjwQn9lHkut69XMCXR41VsaKMsIXtC3A%3D%3D&tempo=01320613603150095300128072&kw=&usertoken=2%2FVMnND6cK1o3%2F30p2TzF%2BvpCjA%3DHPyF&uuid=866934038665637&userid=197417&appv=5.1.3&pagesize=20&model=vivo+X20A&page=");
        cityUrl.put("深圳","https://api.jucaicun.com/3F30C9D673490585DDA72BCA9DF2657E/recruit/list?os=Android+8.1.0&city=%E6%B7%B1%E5%9C%B3&src=android&sign=jS0RUjonNBC1qpJwDZIFe28Jyw7OxOCL3bfdKCjYsQwwgCz73TfRYoefqqFcDderuys%2FY4Ody%2Bd3L81TsCx7o05t4urLzxZFyWg6SEjw8KiMP4LEE3mdHzgratetTsZd2O3vPk5kQRhfSU10HxccCIJ%2B5mzCk%2FNysgzH%2BNH6EwvMLvWi4XxQfgl9SKnQBcTdsw4jVHSBnAEm%2FajIX%2BHsTqg0F3IdQMAvQiMacnNPx%2FLYw9UhcofuhtKUQe9RQ1ZNRnxP2NhhNJs0eIw9rWZd2lGJZjlFNgt8ZlXkA%2Fmx0498k8WKxoo1g4Bo9JsLgCYHnUWxoeL4hyDD7YbG9bb2UA%3D%3D&tempo=01506022244015007126050202&kw=&usertoken=2%2FVMnND6cK1o3%2F30p2TzF%2BvpCjA%3DHPyF&uuid=866934038665637&userid=197417&appv=5.1.3&pagesize=20&model=vivo+X20A&page=");
        cityUrl.put("广州","https://api.jucaicun.com/3F30C9D673490585DDA72BCA9DF2657E/recruit/list?os=Android+8.1.0&city=%E5%B9%BF%E5%B7%9E&src=android&sign=pw5nGrrS3zuxX5Ic%2FTy8fnf0hICYRcazrt9NQEJReXtPrC7%2BvaPbl1dhSL4OkkJk0M9M%2FKEhKOnjy6Ba2LJMGuM9Hfs%2FdxQeeYYj%2FGDLHnwyGdCfJIz9spfrmHWg882%2Bww35YhDCQWGAH5hKMs3X5BTh1WBcJIiLVN5A%2FQY9Ae684QjLvB%2BCnUuu1d3h9jjBUANkxr5Do%2BjJEqp2tTOCx6X4636bWPUehkGm7L6OoEKwaKVoeBqk7ZaaCvUHKH1vfW1TuWkzP%2Be5FvitC%2B8%2B5RSHxbl5iXgfjhdyFqBqUx6j2F59vCOxy%2F2W7AWCf4xXeAVvDni9llb0oy1ZXE3vrQ%3D%3D&tempo=10322404060451102106360390&kw=&usertoken=2%2FVMnND6cK1o3%2F30p2TzF%2BvpCjA%3DHPyF&uuid=866934038665637&userid=197417&appv=5.1.3&pagesize=20&model=vivo+X20A&page=");
        cityUrl.put("重庆","https://api.jucaicun.com/3F30C9D673490585DDA72BCA9DF2657E/recruit/list?os=Android+8.1.0&city=%E9%87%8D%E5%BA%86&src=android&sign=GDfjDHCdkBtkm0tv85vI%2FJzc5t%2B44%2FQJqtmX9%2Fwoy46j9CwTZI9qtA%2BkqjlTdQXQKfLaiEfVrNaADALfqqT1jvhKG8mKgbOnutIpDRrDHurc5MuumJWSS%2FG3Z4JJ7qKY8Zar5UKjn8jO%2FIcCNlCX%2BVJ1Jmvg8%2BFgyO%2FfUokPqQ5ZQAfCbvK8Od28r8XlOz81n%2FBVhELNVzGFXUixdGYqtdPss8%2FHfoNk39Z8NRK%2FXuXxkSNF%2Bycotra0ozzp%2BCZsARiFAzs5d341QNNPHs4tZuafP6VBn%2BhUNYpyt3L%2BlYTCy97xtvxPK8Q4RUfzsvOdUDv6yn%2BQvG6htAagwL3%2FTg%3D%3D&tempo=01415131330442110127012070&kw=&usertoken=2%2FVMnND6cK1o3%2F30p2TzF%2BvpCjA%3DHPyF&uuid=866934038665637&userid=197417&appv=5.1.3&pagesize=20&model=vivo+X20A&page=");
        cityUrl.put("成都","https://api.jucaicun.com/3F30C9D673490585DDA72BCA9DF2657E/recruit/list?os=Android+8.1.0&city=%E6%88%90%E9%83%BD&src=android&sign=n7CYVHkXl9ncJA%2FJWeZwzhoyKaZLfjfmZi2OU0wgFKFOWyYD7%2Br1xiqt1HVHqkow9PbB5d2%2FVKHFlDBj4G48Bp2xUSwamArvPQZH7x2yXwTM%2BcWw1G3V3dCZ%2B%2FiRu3%2FUtlaWtHX2BVwXgn0a2QoFfdkDlXJYkPgQojYWKUx0xlrhDzvBc22WQPwo0%2F3eSidedihuaaDNFv%2B5qlXQLgQbZY5WaxoQ5VbYK5uAKOuFdAeXIqrAlwi%2BIQ2hSfDUhaP8hdfeHW5TQLapTYDjGO4MCv0rUNXu0SNsztJr%2BOGp8T3eDkVpdQB4eAwW%2BEHYxkERBx2Q1qKJqjMn2yA0cYzJHA%3D%3D&tempo=01236031334006202242041040&kw=&usertoken=2%2FVMnND6cK1o3%2F30p2TzF%2BvpCjA%3DHPyF&uuid=866934038665637&userid=197417&appv=5.1.3&pagesize=20&model=vivo+X20A&page=");
    }
    public static void main(String[] args) throws IOException {
        gongju();
    }


    private static void gongju() throws IOException {
        Set<JucaicunLender> userList = new LinkedHashSet<>();
        cityUrl.entrySet().forEach(city->{
            System.out.println("开始爬取，城市："+city.getKey());
            List<JucaicunLender> lender = getLender(city.getValue());
            userList.addAll(lender);
        });
        System.out.println(userList.size());
        BufferedWriter bufferedWriter = null;
        try {
            File f = new File("/Users/liubin/Desktop/jucaicun.json");
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f, true)));
            bufferedWriter.write(JSON.toJSONString(userList));
            bufferedWriter.flush();
        }catch (Exception e){
            System.out.println("aaaa");
        }finally {
            if(bufferedWriter!=null) {
                bufferedWriter.close();
            }
        }
    }

    private static List<JucaicunLender> getLender(String url) {
        List<JucaicunLender> userList = new ArrayList<>();
        for(int i=1;i<500;i++){
            String result = HttpUtils.doGetJSON(url+i);
            JSONObject jsonObject = JSON.parseObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            if(jsonArray==null||jsonArray.size()==0){
                break;
            }
            jsonArray.forEach(data->{
                JSONObject item = (JSONObject) data;
                String nickName = item.getString("nickName");
                String company = item.getString("company");
                String province = item.getString("province");
                String city = item.getString("city");
                String addr = item.getString("addr");
                String postName = item.getString("postName");
                String contact = item.getString("contact");
                JucaicunLender jucaicunLender = new JucaicunLender(nickName, company, province, city, addr, postName, contact);
                System.out.println(JSON.toJSONString(jucaicunLender));
                userList.add(jucaicunLender);
            });
        }
        return userList;
    }


    //    private static void renmai() {
//        String url = "https://api.jucaicun.com/3F30C9D673490585DDA72BCA9DF2657E/userNew/search?os=Android+8.1.0&src=android&sign=MVa%2FTYMkl3Thsab%2BSV3hhGPaGREYQCyKiQlAQ5rhYUsIU%2BBEPvcxLq6iQvognTPa%2BMe7j2SEdpgsASwRC6kHT26kd3m2CraWOiv6ExCMReRfmkwVaO9gv0LO099%2Ft%2F%2Br99HBJK8XpgNi8BMJMvrDm51P%2F%2FZ%2FDAzCkZ6kn1NXOXAGBi%2FnITMSvPADNIUN4Oa0%2FeBUwkV4UiTP1XSO2ebApG8K7dHb%2FYdo2WSoVrD9u34q6l5O593qoodJJgSna2NgQa6rdmNfbIPEbK9cMn1tNgFhym3uJLbGJCxtNI8j4HFFGgjOodoVq%2Bc%2FhRU9ClneO%2BG%2Fs9HWn5PFod%2FnR0RWHg%3D%3D&tempo=10325140060300200054125210&usertoken=2%2FVMnND6cK1o3%2F30p2TzF%2BvpCjA%3DHPyF&uuid=866934038665637&userid=197417&uid=197417&appv=5.1.3&pagesize=20&model=vivo+X20A&page=1";
//        url = "https://api.jucaicun.com/3F30C9D673490585DDA72BCA9DF2657E/userNew/safeuserinfo";
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("uid","197417");
//        jsonObject.put("os","Android 8.1.0");
//        jsonObject.put("src","android");
//        jsonObject.put("appv","5.1.3");
//        jsonObject.put("sign","K7P+drR/QyvX0cIIx9VQ25ouYjDq54o3nMMEmNLge3MMkNP94fL9drfFAd22VJ3a22kntx9lsnbpbQWudOWfSHIK+yNn8pqwNK0irydpiz+CSKH4Zmtz6+XnSQQChgFyPzNSCitICypoy8giaYKeo+99AvyckuAcpW5xImlhQIcfTR2NCwQGL+JS2BOKgECHYP1XxpnVac5QFx9TSW8TVbSkvP5aQbeFboT3eWzUAPK2jogxNkOXEpNo67bBS1f/fS0Fqo7caXIP5yGeZhGPrhwC4LXakTBBwyNmW1Up0Tvj7da9n5BEii6+RW7w8TYprTOofQTdOmR02SrmEjjbwg==");
//        jsonObject.put("tempo","10506013331200114100160210");
//        jsonObject.put("model","vivo X20A");
//        jsonObject.put("targetUid","71");
//        jsonObject.put("userToken","2/VMnND6cK1o3/30p2TzF+vpCjA=HPyF");
//        jsonObject.put("uuid","866934038665637");
//        jsonObject.put("uid","197417");
//        jsonObject.put("userid","197417");
//        String s = HttpUtils.doPostJSON(url,jsonObject);
//        System.out.println(s);
//    }
}
