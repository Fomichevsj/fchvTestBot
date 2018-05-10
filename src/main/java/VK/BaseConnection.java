package VK;





import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.utils.URIBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


import java.io.IOException;
import java.io.StringWriter;

public class BaseConnection {

    private JSONArray postsList;//Список постов

    public BaseConnection() {
        URIBuilder uriBuilder = new URIBuilder();

        VKInfoManager vkInfoManager = new VKInfoManager();
        uriBuilder.setScheme("https").setHost("api.vk.com").
                setPath("/method/wall.get")
                .setParameter("v", "5.52")
                .setParameter("domain","na4kurnews")
                .setParameter("count","10")
                .setParameter("access_token",vkInfoManager.getToken());
        System.out.println(uriBuilder.toString());
        HttpResponse response = HttpConnectionAgent.connectResponse(uriBuilder);
        Integer status = response.getStatusLine().getStatusCode();

        if(status ==200)

        {
            StringWriter content = new StringWriter();

            try {
                IOUtils.copy(response.getEntity().getContent(), content);
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(-1);
            }

            JSONParser parser = new JSONParser();

            try {

                //System.out.println(content.toString());
                JSONObject jsonResp = (JSONObject) parser.parse(content.toString());
                jsonResp = (JSONObject) parser.parse(jsonResp.get("response").toString());
                //System.out.println(jsonResp.toString());
                JSONArray postsList = (JSONArray) jsonResp.get("items");
                JSONObject unicPost = null;

                for (int i = 1; i < postsList.size(); i++) {
                    System.out.println("||||||||||||||||||||||||||||||");
                    unicPost = (JSONObject) postsList.get(i);
                    System.out.println(unicPost.get("text"));
                    System.out.println("\n\n\n\n");
                }

            } catch (ParseException e) {
                e.printStackTrace();
                System.exit(-1);
            } catch (org.json.simple.parser.ParseException e) {
                e.printStackTrace();
            }
        }
    }

}
