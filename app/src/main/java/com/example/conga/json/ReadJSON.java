package com.example.conga.json;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ConGa on 7/03/2016.
 */
public class ReadJSON extends Activity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jsonlistview);

        listView = (ListView) findViewById(R.id.listView);
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()

        .cacheInMemory(true)
                .cacheOnDisk(true)

        .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())

        .defaultDisplayImageOptions(defaultOptions)
        .build();
        ImageLoader.getInstance().init(config);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new ReadJSONAsync().execute("http://jsonparsing.parseapp.com/jsonData/moviesData.txt");
            }
        });
    }
 class ReadJSONAsync extends AsyncTask<String, Integer, List<Movie>> {
     //   Context context;

        @Override
        protected List<Movie> doInBackground(String... strings) {
            HttpURLConnection connection = null;
            BufferedReader bufferedReader = null;
            try {
                URL url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                // buffer la  chua cai chuoi ma minh get dc tu file lay tren mang
                // dung buffer reader de doc file
                InputStream stream = connection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                bufferedReader = new BufferedReader(new InputStreamReader(stream));
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    buffer.append(line);

                }
                String finalJson = buffer.toString();
                JSONObject parentObject = new JSONObject(finalJson);
             //   StringBuffer stringBuffer =new StringBuffer();
                JSONArray parentArray = parentObject.getJSONArray("movies");
                Gson gson = new Gson();
                List<Movie> movieList = new ArrayList<>();
                for (int i = 0; i < parentArray.length(); i++) {
                    JSONObject jsonObject = parentArray.getJSONObject(i);

                    Movie movie = gson.fromJson(jsonObject.toString(),Movie.class);
//                    Movie movie = new Movie();
//                    movie.setMovie(jsonObject.getString("movie"));
//                    movie.setYear(jsonObject.getInt("year"));
//                    movie.setRating((float)jsonObject.getDouble("rating"));
//                    movie.setDuration(jsonObject.getString("duration"));
//                    movie.setDirector(jsonObject.getString("director"));
//                    movie.setTagline(jsonObject.getString("tagline"));
//                    movie.setImage(jsonObject.getString("image"));
//                    movie.setStory(jsonObject.getString("story"));
//                    List<Movie.Cast> castList = new ArrayList<>();
//                    for( int j = 0 ; j < jsonObject.getJSONArray("cast").length() ; j++){
//                        JSONObject castObject = jsonObject.getJSONArray("cast").getJSONObject(j);
//                        Movie.Cast cast =new Movie.Cast();
//                        cast.setName(castObject.getString("name"));
//                        castList.add(cast);
//                    }
//                    movie.setCastList(castList);
                    movieList.add(movie);


                }

                return movieList;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                connection.disconnect();
                try {
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Movie> s) {
            super.onPostExecute(s);
        //    Toast.makeText(getApplicationContext(), (CharSequence) s, Toast.LENGTH_SHORT).show();
            MovieAdapter movieAdapter = new MovieAdapter(getApplicationContext(), R.layout.rowlistview, s);
            listView.setAdapter(movieAdapter);

        }

    }

   public  class MovieAdapter extends ArrayAdapter{
        private  List<Movie> listMovie;
        private LayoutInflater inflater;
        int resource;
        Context context;


        public MovieAdapter(Context context, int resource, List<Movie> objects) {
            super(context, resource, objects);
            this.context =context;
            listMovie = objects;
            this.resource = resource;
            inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);

        }



        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //   return super.getView(position, convertView, parent);
            if( convertView == null){
                convertView = inflater.inflate(resource, null);
            }
            ImageView image = (ImageView) convertView.findViewById(R.id.imageView);
            TextView textViewMovie = (TextView)convertView.findViewById(R.id.tvMovie);
            TextView textViewTagLine = (TextView)convertView.findViewById(R.id.tvTagline);
            TextView textViewYear = (TextView)convertView.findViewById(R.id.tvYear);
            TextView textViewDuration = (TextView)convertView.findViewById(R.id.tvDuration);
            TextView textViewDirector  = (TextView)convertView.findViewById(R.id.tvDirector);
            TextView textViewCast  = (TextView)convertView.findViewById(R.id.tvCast);
            TextView textViewStory  = (TextView)convertView.findViewById(R.id.tvStory);
            RatingBar ratingMovie = (RatingBar)convertView.findViewById(R.id.ratingMovi);
           final ProgressBar progressBar = (ProgressBar) convertView.findViewById(R.id.progressBar);
            ImageLoader.getInstance().displayImage(listMovie.get(position).getImage(), image, new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String imageUri, View view) {
                    progressBar.setVisibility(View.VISIBLE);
                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onLoadingCancelled(String imageUri, View view) {
                    progressBar.setVisibility(View.GONE);
                }
            });
            textViewMovie.setText(listMovie.get(position).getMovie());
            textViewTagLine.setText(listMovie.get(position).getTagline());
            textViewYear.setText("Year" + listMovie.get(position).getYear());
            textViewDuration.setText(listMovie.get(position).getDuration());
            textViewDirector.setText(listMovie.get(position).getDirector());
            ratingMovie.setRating(listMovie.get(position).getRating() / 2);
            textViewStory.setText(listMovie.get(position).getStory());
            StringBuffer stringBuffer = new StringBuffer();
            for(Movie.Cast cast : listMovie.get(position).getCastList()){
                stringBuffer.append(cast.getName()+",");
            }
            textViewCast.setText(stringBuffer);

            return convertView;
        }

    }


}