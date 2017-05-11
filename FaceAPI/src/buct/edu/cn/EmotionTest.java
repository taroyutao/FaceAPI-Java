package buct.edu.cn;

import java.io.FileInputStream;
import java.net.URI;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;



public class EmotionTest {

	public static void main(String[] args) {
		HttpClient httpclient = HttpClients.createDefault();

        try
        {
            URIBuilder builder = new URIBuilder("https://api.cognitive.azure.cn/emotion/v1.0/recognize");

            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            request.setHeader("Content-Type", "application/octet-stream");
            request.setHeader("Ocp-Apim-Subscription-Key", "88349e88746e4160a007bbc1c5a7d64b");

            // Request body
            String pic_path = "D:\\test.jpg";
            
            System.out.println(pic_path);
            
            FileInputStream is = new FileInputStream(pic_path); 
            int i = is.available(); // 得到文件大小  
            byte data[] = new byte[i]; 
            is.read(data); // 读数据  将数据读到data中	             
            is.close(); 
             
            ByteArrayEntity bae = new ByteArrayEntity(data);	                                    
            request.setEntity(bae);	 

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) 
            {
                System.out.println(EntityUtils.toString(entity));
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
		
		
		
		

	}

}
