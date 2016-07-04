package com.example.conga.json;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ConGa on 7/03/2016.
 */
public class ReadJSONLocal extends Activity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jsonlistview);
        listView = (ListView) findViewById(R.id.listView);
        String json = null;
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(loadJsonFromAssets());
            JSONArray jsonArray = jsonObject.getJSONArray("contacts");
            Gson gson = new Gson();
            List<Contact> arrayList = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject son = jsonArray.getJSONObject(i);
                Contact contact = gson.fromJson(son.toString(),Contact.class);
//                Contact contact = new Contact();
//                contact.setName(son.getString("name"));
//                contact.setAddress(son.getString("address"));
//                contact.setEmail(son.getString("email"));
//                contact.setGender(son.getString("gender"));
//                arrayList.add(son.getString("id"));
//                arrayList.add(son.getString("name"));
//                arrayList.add(son.getString("email"));
//                arrayList.add(son.getString("address"));
//                arrayList.add(son.getString("gender"));
//                JSONObject phone = son.getJSONObject("phone");
//                arrayList.add(phone.getString("mobile"));
//                arrayList.add(phone.getString("home"));
//                arrayList.add(phone.getString("office"));
                arrayList.add(contact);

            }
//            ArrayAdapter<String> adapter = new ArrayAdapter<String>
//                    (ReadJSONLocal.this, android.R.layout.simple_list_item_1, arrayList);
//            listView.setAdapter(adapter);
            ContactsAdapter contactsAdapter = new ContactsAdapter(getApplicationContext(), R.layout.jsonlocallistview, arrayList);
            listView.setAdapter(contactsAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String loadJsonFromAssets() throws IOException {
        String json = null;
        byte[] Stringbuffer;
        try {
            InputStream inputStream = getApplicationContext().getAssets().open("myfile.json");
            int size = inputStream.available();
            Stringbuffer = new byte[size];
            inputStream.read(Stringbuffer);
            inputStream.close();
            json = new String(Stringbuffer, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }

    class ContactsAdapter extends ArrayAdapter {
        private List<Contact>listContact;
        private Context context;
        private LayoutInflater inflaler;
        int resource;

        public ContactsAdapter(Context context, int resource, List<Contact> listContacts) {
            super(context, resource, listContacts);
           listContact =listContacts;
            this.resource = resource;
            this.context = context;
            inflaler = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //   return super.getView(position, convertView, parent);
            if (convertView == null) {
                convertView = inflaler.inflate(resource, null);
            }
            //   TextView tvId = (TextView) convertView.findViewById(R.id.textViewId);
            TextView tvName = (TextView) convertView.findViewById(R.id.textViewName);
            TextView tvEmail = (TextView) convertView.findViewById(R.id.textViewEmail);
            TextView tvAddress = (TextView) convertView.findViewById(R.id.textViewaddress);
            TextView tvGender = (TextView) convertView.findViewById(R.id.textViewGender);
            //  TextView tvMobile= (TextView) convertView.findViewById(R.id.textViewMobile);
            tvName.setText("Name:" +listContact.get(position).getName());
            tvEmail.setText("Email:" + listContact.get(position).getEmail());
            tvAddress.setText("Address:"+ listContact.get(position).getAddress());
            tvGender.setText("Gender: " +listContact.get(position).getGender());
            //tvMobile.setText(listContact.get(position).getPhone_mobile());
//            TextView tvHome = (TextView) convertView.findViewById(R.id.textViewHome);
//            TextView tvId = (TextView) convertView.findViewById(R.id.textViewId);
            return convertView;

        }
    }

}
