package ehb.programmingproject.coursequiz.API;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class APIReader {

    public static boolean UserkeyExist(String userkey){
        boolean exist = false;

        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost("http://api.brielage.com:8081/test/userkey/");
            httpPost.setEntity(new StringEntity("{\"userkey\":\"" + userkey + "\"}", ContentType.APPLICATION_JSON));

            String response = new BasicResponseHandler().handleResponse(client.execute(httpPost));
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(response);

            if (jsonObject.get("success").equals(true)) {
                exist = true;
            }
            client.close();

        }catch(Exception e){
            e.printStackTrace();
        }

        return exist;
    }

    public static List<String> GetVakken(String userkey){
        List<String> vakken = new ArrayList<>();

        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost("http://api.brielage.com:8081/vak/list");
            httpPost.setEntity(new StringEntity("{\"userkey\":\"" + userkey + "\"}", ContentType.APPLICATION_JSON));

            String response = new BasicResponseHandler().handleResponse(client.execute(httpPost));
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(response);
            JSONArray jsonArray = (JSONArray) jsonObject.get("vakken");
            Iterator iterator = jsonArray.iterator();

            while (iterator.hasNext()) {
                JSONObject vak = (JSONObject) jsonArray.get(jsonArray.indexOf(iterator.next()));
                vakken.add(vak.get("naam").toString());
            }
            client.close();

        }catch(Exception e){
            e.printStackTrace();
        }

        return vakken;
    }
}
