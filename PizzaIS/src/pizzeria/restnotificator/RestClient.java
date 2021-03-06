package pizzeria.restnotificator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class RestClient {
	private String url;

    private int responseCode;
    private String message;

    private String response;
    
    private String param;

    public String getResponse()
    {
        return response;
    }

    public String getErrorMessage()
    {
        return message;
    }

    public int getResponseCode()
    {
        return responseCode;
    }

    public RestClient(String url) {
        this.url = url;
    }

    
    public void SetPostParam(String value)
    {
        param = value;
    }


	public void Execute(RequestMethod method) throws Exception
    {
        switch (method)
        {
        case GET:
        {
//            // add parameters
//            String combinedParams = "";
//            if (!params.isEmpty())
//            {
//                combinedParams += "";
//                for (NameValuePair p : params)
//                {
//                    String paramString = p.getName() + "" + URLEncoder.encode(p.getValue(),"UTF-8");
//                    if (combinedParams.length() > 1)
//                    {
//                        combinedParams += "&" + paramString;
//                    }
//                    else
//                    {
//                        combinedParams += paramString;
//                    }
//                }
//            }
//
//            HttpGet request = new HttpGet(url + combinedParams);
//
//            // add headers
//            for (NameValuePair h : headers)
//            {
//                request.addHeader(h.getName(), h.getValue());
//            }
//
//            executeRequest(request, url);
            break;
        }
        case POST:
        {
            HttpPost request = new HttpPost(url);

            request.setHeader("Accept", "application/json");
            request.setHeader("Content-type", "application/json");

            request.setEntity(new StringEntity(param));
            
            executeRequest(request, url);
            break;
        }
        }
    }

    private void executeRequest(HttpUriRequest request, String url) throws Exception
    {

        HttpParams httpParameters = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParameters,15000);
        HttpConnectionParams.setSoTimeout(httpParameters, 15000);
        HttpClient client = new DefaultHttpClient(httpParameters);

        HttpResponse httpResponse;

            httpResponse = client.execute(request);
            responseCode = httpResponse.getStatusLine().getStatusCode();
            message = httpResponse.getStatusLine().getReasonPhrase();

            HttpEntity entity = httpResponse.getEntity();

            if (entity != null)
            {

                InputStream instream = entity.getContent();
                response = convertStreamToString(instream);

                // Closing the input stream will trigger connection release
                instream.close();
            }


    }

    private static String convertStreamToString(InputStream is)
    {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try
        {
            while ((line = reader.readLine()) != null)
            {
                sb.append(line + "\n");
            }
        }
        catch (IOException e)
        {

            e.printStackTrace();
        }
        finally
        {
            try
            {
                is.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
    public InputStream getInputStream(){
        HttpParams httpParameters = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParameters,15000);
        HttpConnectionParams.setSoTimeout(httpParameters, 15000);
        HttpClient client = new DefaultHttpClient(httpParameters);

        HttpResponse httpResponse;

        try
        {

               HttpPost request = new HttpPost(url);

            httpResponse = client.execute(request);
            responseCode = httpResponse.getStatusLine().getStatusCode();
            message = httpResponse.getStatusLine().getReasonPhrase();

            HttpEntity entity = httpResponse.getEntity();

            if (entity != null)
            {

                InputStream instream = entity.getContent();
                return instream;
             /*   response = convertStreamToString(instream);

                // Closing the input stream will trigger connection release
                instream.close();*/
            }

        }
        catch (ClientProtocolException e)
        {
            client.getConnectionManager().shutdown();
            e.printStackTrace();
        }
        catch (IOException e)
        {
            client.getConnectionManager().shutdown();
            e.printStackTrace();
        }
        return null;
    }
    public enum RequestMethod
    {
        GET,
        POST
    }
}
