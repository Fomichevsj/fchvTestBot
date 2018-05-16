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

    private static boolean isUpdated = true;
    private JSONArray postsList;//Список постов
    private static URIBuilder uriBuilder;// Объект для составления запроса
    private static String lastId = "";
    private static String lastDate = "";

    /**
     *
     * @param pub группа в которой нужно искать
     */
    public BaseConnection(String pub) {
        uriBuilder = new URIBuilder();
        //na4kurnews
        VKInfoManager vkInfoManager = new VKInfoManager();
        uriBuilder.setScheme("https").setHost("api.vk.com").
                setPath("/method/wall.get")
                .setParameter("v", "5.52")
                .setParameter("domain",pub)
                //.setParameter("offset", "1")
                .setParameter("count","1")
                .setParameter("access_token",vkInfoManager.getToken());
        System.out.println(uriBuilder.toString());
    }

    public String getPost() {
        HttpResponse response = HttpConnectionAgent.connectResponse(uriBuilder);
        Integer status = response.getStatusLine().getStatusCode();

        if(status ==200) {
            StringWriter content = new StringWriter();

            try {
                IOUtils.copy(response.getEntity().getContent(), content);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Не получилось получить данный из вк");
            }

            JSONParser parser = new JSONParser();

            try {

                JSONObject jsonResp = (JSONObject) parser.parse(content.toString());
                jsonResp = (JSONObject) parser.parse(jsonResp.get("response").toString());
                postsList = (JSONArray) jsonResp.get("items");
                JSONObject unicPost = null;

                for (int i = 0; i < postsList.size(); i++) {
                    unicPost = (JSONObject) postsList.get(i);
                    if(lastDate.equals(unicPost.get("date").toString()) &&
                            lastId.equals(unicPost.get("id").toString())) {
                        isUpdated = false;
                    } else {
                        System.out.println("Новый пост :)");
                        isUpdated = true;
                    }
                    lastId = unicPost.get("id").toString();
                    lastDate = unicPost.get("date").toString();
                }
                return unicPost.get("text").toString();


            } catch (org.json.simple.parser.ParseException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Не смогли получить текст");
        return "ERROR!!!";
    }

    /**
     *
     * @return true если появился новый пост, false если изменений нет
     */
    public boolean isUpdated() {
        return isUpdated;
    }

}
