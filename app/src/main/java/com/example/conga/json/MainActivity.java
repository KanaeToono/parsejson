package com.example.conga.json;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    // TextView textViewMonHoc;
// TextView textViewNoiHoc;
// TextView textViewWebsite;
// TextView textViewFanpage;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jsonlistview);
//        textViewMonHoc = (TextView) findViewById(R.id.textViewMonHoc);
//        textViewNoiHoc = (TextView) findViewById(R.id.textViewNoiHoc);
//        textViewFanpage = (TextView) findViewById(R.id.textViewFanpage);
//        textViewWebsite = (TextView) findViewById(R.id.textViewWebsite);
        listView = (ListView) findViewById(R.id.listView);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new ReadJSON().execute("http://khoapham.vn/KhoaPhamTraining/laptrinhios/jSON/demo3.json");
            }
        });
    }

    class ReadJSON extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... strings) {
            String string = getXmlFromUrl(strings[0]);
            return string;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject root = new JSONObject(s);
                //String kq = "";
                ArrayList<String> arrayList = new ArrayList<String>();
                JSONArray arrays = root.getJSONArray("danhsach");
                for (int i = 0; i < arrays.length(); i++) {
                    JSONObject son = arrays.getJSONObject(i);
                    arrayList.add(son.getString("khoahoc"));
                  //  kq = kq + son.getString("khoahoc");

                }
                //Toast.makeText(getApplicationContext(), kq, Toast.LENGTH_SHORT).show();
                ArrayAdapter<String> adapter = new ArrayAdapter<String>
                        (MainActivity.this, android.R.layout.simple_list_item_1, arrayList);
                listView.setAdapter(adapter);
//            textViewMonHoc.setText(root.getString("monhoc"));
//            textViewNoiHoc.setText(root.getString("noihoc"));
//            textViewWebsite.setText(root.getString("website"));
//            textViewFanpage.setText(root.getString("fanpage"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private String getXmlFromUrl(String urlString) {// tra cai noi dung duong dan ma minh truyn http vao
        String xml = null;
        try {
            // lay toan bo du lieu trong http do vao chuoi string
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(urlString);
            HttpResponse httpRespond = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpRespond.getEntity();
            xml = EntityUtils.toString(httpEntity, HTTP.UTF_8);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return xml;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
<!--   if (mArrayListRssItems.get(position).getLinkTag().contains("vnexpress.net")) { -->
//                            Connection.Response response=Jsoup.connect(mArrayListRssItems.get(position).getLinkTag()).timeout(100*10000)
//                                    .method(Connection.Method.POST)
//                                    .userAgent("Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36")
//                                    .ignoreHttpErrors(true).execute();
//                                    Map<String , String> cookies =response.cookies();
//                            Document document = Jsoup.connect(mArrayListRssItems.get(position).getLinkTag()).timeout(100*10000).
//                                    userAgent("Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36").
//                                    ignoreHttpErrors(true).method(Connection.Method.POST).cookies(cookies).
//                                    get();
//                           // Elements elements = document.select("div [class= fck_detail width_common]");
//                            Elements elements = document.select("html");
//                             result = elements.toString();
//                            Log.d(TAG, result);
//                            // lay ve cai title cua bai bao
//                            String title = document.title();
                            //ContentRss contentRss= new ContentRss(title, result,"" );
							  //      }
//                        if (mArrayListRssItems.get(position).getLinkTag().contains("www.24h.com")) {
//                            Document document = Jsoup.connect(mArrayListRssItems.get(position).getLinkTag()).
//                                    userAgent("Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36").get();
//                            Elements elements = document.select("div.text-conent");
//                           // result = elements.toString();
//                        }
//                        if (mArrayListRssItems.get(position).getLinkTag().contains("dantri.com.vn")) {
//                            Document document = Jsoup.connect(mArrayListRssItems.get(position).getLinkTag()).
//                                    userAgent("Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36").get();
//                            Elements elements = document.select("div.VCSortableInPreviewMode");
//                           // result = elements.toString();
//                        }
//                        if (mArrayListRssItems.get(position).getLinkTag().contains("vietnamnet.vn")) {
//
//                            Document document = Jsoup.connect(mArrayListRssItems.get(position).getLinkTag()).
//                                    userAgent("Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36").get();
//                            Elements elements = document.select("div.ArticleDetail");
//                          //  result = elements.toString();
//                        }
 // text.setHtmlFromString(result, new com.example.conga.tvo.htmltextview.HtmlTextView.RemoteImageGetter(null));
//                    text.setHtmlFromString(result, new HtmlTextView.RemoteImageGetter());
//                    Toast.makeText(mContext, "" + linkTag, Toast.LENGTH_SHORT).show();
//                    Toast.makeText(mContext, "" + text, Toast.LENGTH_SHORT).show();
//                    Log.d("error", text + "");

       try {
//
                        URL url = new URL(mArrayListRssItems.get(position).getLinkTag());
                        connection = (HttpURLConnection) url.openConnection();
                        connection.setReadTimeout(10000);
                        connection.setRequestMethod("POST");
                        connection.setConnectTimeout(10000);
                        connection.connect();
                        // kiem tra xem server co hoat dong hay khong
                        if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                            return "Server returned HTTP " + connection.getResponseCode()
                                    + " " + connection.getResponseMessage();
                        }
                        // loi dang xem video co cuoc goi , bi crash app ())))
                        //lay chieu dai cua noi dun
                        int fileLength = connection.getContentLength();

                        // download the file
                        input = connection.getInputStream();
                        output = new FileOutputStream("/sdcard/file_name.extension");

                        byte data[] = new byte[4096];
                        long total = 0;
                        int count;
                        while ((count = input.read(data)) != -1) {
                            // allow canceling with back button
                            if (isCancelled()) {
                                input.close();
                                return null;
                            }
                            total += count;
                            // publishing the progress....
                           // if (fileLength > 0) // only if total length is known
                               // publishProgress((int) (total * 100 / fileLength));
                           // output.write(data, 0, count);
                       //    file = new String();
                           // file.toString(data);
                        }
                    } catch (Exception e) {
                        return e.toString();
                    } finally {
                        try {
                            if (output != null)
                                output.close();
                            if (input != null)
                                input.close();
                        } catch (IOException ignored) {
                        }

                        if (connection != null)
                            connection.disconnect();
                    }

