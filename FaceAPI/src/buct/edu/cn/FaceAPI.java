package buct.edu.cn;

import java.io.FileInputStream;
import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class FaceAPI {

	public static void main(String[] args) {
		
		System.out.println("Begin FaceAPI Test.");
		
		//UsePictureURL();
		UseLocalPicture();
	}
	/**
	 * Use Picture URL
	 */
	public static void UsePictureURL()
	{
		HttpClient httpclient = HttpClients.createDefault();

        try
        {
            URIBuilder builder = new URIBuilder("https://api.cognitive.azure.cn/face/v1.0/detect");

            builder.setParameter("returnFaceId", "true");
            builder.setParameter("returnFaceLandmarks", "false");
            builder.setParameter("returnFaceAttributes", "age");

            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            request.setHeader("Content-Type", "application/json");
            request.setHeader("Ocp-Apim-Subscription-Key", "4fe95dd8ec4247a69cb2a001efda06c6");

            // Request body
            StringEntity reqEntity = new StringEntity("{\"url\":\"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1494416315487&di=5e05a310f8c7b3fec011901ff3d13f93&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fbaike%2Fpic%2Fitem%2F4034970a304e251ff1e3819aa486c9177f3e53bf.jpg\"}"); //URL图片地址
            request.setEntity(reqEntity);

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
	
	/**
	 * use local picture
	 */
	public static void UseLocalPicture()
	{
		HttpClient httpclient = HttpClients.createDefault();

        try
        {
            URIBuilder builder = new URIBuilder("https://api.cognitive.azure.cn/face/v1.0/detect");

            builder.setParameter("returnFaceId", "true");
            builder.setParameter("returnFaceLandmarks", "false");
            builder.setParameter("returnFaceAttributes", "age");

            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            request.setHeader("Content-Type", "application/octet-stream");
            request.setHeader("Ocp-Apim-Subscription-Key", "4fe95dd8ec4247a69cb2a001efda06c6");

            // Request body
            String pic_path = "C:\\Users\\yuvmtest\\Desktop\\test.jpg";  //本地图片地址
            
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
