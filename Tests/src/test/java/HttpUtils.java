import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Component;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author wbfancy
 * @version 1.0
 * @description: TODO
 * @date 2022/3/24 15:29
 */

public class HttpUtils {
    private volatile int i = 0;
    public String post(){
        return null;
    }
    public void send(String method, String url, byte[] fileContent) throws IOException {
        URL urls = null;
        try {
            urls = new URL(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        HttpURLConnection httpURLConnection = null;
        System.out.println(url);
        OutputStream outputStream = null;
        try {

            httpURLConnection = (HttpURLConnection) urls.openConnection();

            httpURLConnection.setRequestProperty("Content-Type","multipart/form-data");

            httpURLConnection.setRequestMethod("PUT");

            httpURLConnection.setDoInput(true);

            httpURLConnection.setDoOutput(true);

            outputStream = httpURLConnection.getOutputStream();

            outputStream.write(fileContent,0,fileContent.length);

            outputStream.flush();

            outputStream.close();

            httpURLConnection.connect();
            int responseCode = httpURLConnection.getResponseCode();

            System.out.println(responseCode);
        } catch (IOException exception) {

            exception.printStackTrace();

        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }
    public String put(String uploadPath, String fileName, byte[] fileContent) throws IOException {
        send("PUT",uploadPath+fileName,fileContent);
        Integer statusCode = getStatusCode(fileName);
        return statusCode + "";
    }
    public Integer getStatusCode(String fileUrl) {
        Integer responseCode = null;
        try {
            URL url = new URL(fileUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.connect();
            responseCode = httpURLConnection.getResponseCode();
        } catch (IOException e) {
            e.getMessage();
            return -1;
        }
        return responseCode;
    }
}
