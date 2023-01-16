package com.example.flipr;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity3 extends AppCompatActivity {

    TextView name, email, one,two,three,four,five,six,heading;
    String nse1[] = {"146.75", "1059.60", "3139.05", "2444.10", "119.95"};
    String nse2[] = {"-0.30(-0.20%", "-8.15(-0.76%)", "-23.00(-0.73%)", "-23.50(-0.95%)", "-0.50(-0.42%)"};
    String nse4[] = {"146.30 - 147.75", "1051.00 - 1074.70", "3100.00 - 3179.90", "2427.00 - 2479.65", "118.80 - 121.45"};
    String nse5[] = {"93.20 - 169.45", "860.00 - 1185.25", "2159.55 - 3889.65", "2180.00 - 2856.15", "82.70 - 138.67"};
    String nse6[] = {"7634945", "825308", "653373", "6287407", "44362359"};

    String bse1[] = {"146.65", "1060.00", "3139.35", "2444.70", "119.95"};
    String bse2[] = {"-0.45(-.31%)", "-8.25(-0.77%)", "-25.45(-0.80%)", "-22.65(-0.92%)", "-0.50(-0.42%)"};
    String bse4[] = {"146.35 - 147.7", "1050.65- 1074.70", "3101.00 - 3179.00", "2428.00 - 2479.35", "119.80 - 121.95"};
    String bse5[] = {"93.20 - 168.45", "861.00 - 1186.25", "2157.55 - 3887.65", "2184.00 - 2855.15", "83.70 - 139.67"};
    String bse6[] = {"324394", "26358", "10286", "140006", "1597301"};

    Button b1,b2,b3,b4,b5, button2;

    Button signout;
    Spinner indices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        name= findViewById(R.id.name);
        one= findViewById(R.id.one);
        two= findViewById(R.id.two);
        three= findViewById(R.id.three);
        three.setText("As on 16 Jan, 2023 16:10 IST");
        four= findViewById(R.id.four);
        five= findViewById(R.id.five);
        six= findViewById(R.id.six);
        heading = findViewById(R.id.textView6);
        signout = findViewById(R.id.signout);
        b1 = findViewById(R.id.button5);
        b2 = findViewById(R.id.button6);
        b3 = findViewById(R.id.button7);
        b4 = findViewById(R.id.button8);
        b5 = findViewById(R.id.button9);
        button2 = findViewById(R.id.button2);


        AccessToken accessToken = AccessToken.getCurrentAccessToken();

        GraphRequest request = GraphRequest.newMeRequest(
                accessToken,
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject object,
                            GraphResponse response) {
                        // Application code
                        try {
                            String fullname = object.getString("name");
                            name.setText(fullname);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link");
        request.setParameters(parameters);
        request.executeAsync();

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logOut();
                startActivity(new Intent(MainActivity3.this, MainActivity.class));
                finish();
            }
        });

        //spinner
        indices = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.indices, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        indices.setAdapter(adapter);

        indices.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if(selectedItem.equals("NSE"))
                {
                    heading.setText("NIFTY");
                    one.setText("17894.85");
                    two.setText("-61.75(-0.34%)");
                    four.setText("Day Range 17853.65 - 18049.65");
                    five.setText("52 Week Range 15183.40 - 18887.60");
                    six.setText("");

                    button2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String url = "https://www.moneycontrol.com/indian-indices/nifty-50-9.html";
                            Toast.makeText(MainActivity3.this, url, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse(url));
                            startActivity(intent);
                        }
                    });
                }
                else
                {
                    heading.setText("SENSEX");
                    one.setText("60092.97");
                    two.setText("-168.21(-0.28%)");
                    four.setText("Day Range 59963.83 - 60586.77");
                    five.setText("52 Week Range 50921.22 - 63583.07");
                    six.setText("");

                    button2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String url = "https://www.moneycontrol.com/indian-indices/sensex-4.html";
                            Toast.makeText(MainActivity3.this, url, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse(url));
                            startActivity(intent);
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        String selected = indices.getSelectedItem().toString();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                heading.setText("Ashok Leyland");
                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String url = "https://www.moneycontrol.com/india/stockpricequote/auto-lcvshcvs/ashokleyland/AL";
                        Toast.makeText(MainActivity3.this, url, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(url));
                        startActivity(intent);
                    }
                });
                if(selected=="BSE")
                {
                    one.setText(bse1[0]);
                    two.setText(bse2[0]);
                    four.setText("Day Range "+bse4[0]);
                    five.setText("52 Week Range "+bse5[0]);
                    six.setText("Volume "+bse6[0]);

                }
                else
                {
                    one.setText(nse1[0]);
                    two.setText(nse2[0]);
                    four.setText("Day Range "+nse4[0]);
                    five.setText("52 Week Range "+nse5[0]);
                    six.setText("Volume "+nse6[0]);

                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                heading.setText("Cipla");
                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String url = "https://www.moneycontrol.com/india/stockpricequote/pharmaceuticals/cipla/C";
                        Toast.makeText(MainActivity3.this, url, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(url));
                        startActivity(intent);
                    }
                });
                if(selected=="BSE")
                {
                    one.setText(bse1[1]);
                    two.setText(bse2[1]);
                    four.setText("Day Range "+bse4[1]);
                    five.setText("52 Week Range "+bse5[1]);
                    six.setText("Volume "+bse6[1]);
                }
                else
                {
                    one.setText(nse1[1]);
                    two.setText(nse2[1]);
                    four.setText("Day Range "+nse4[1]);
                    five.setText("52 Week Range "+nse5[1]);
                    six.setText("Volume "+nse6[1]);
                }
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                heading.setText("Eicher Motors");
                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String url = "https://www.moneycontrol.com/india/stockpricequote/auto-lcvshcvs/eichermotors/EM";
                        Toast.makeText(MainActivity3.this, url, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(url));
                        startActivity(intent);
                    }
                });
                if(selected=="BSE")
                {
                    one.setText(bse1[2]);
                    two.setText(bse2[2]);
                    four.setText("Day Range "+bse4[2]);
                    five.setText("52 Week Range "+bse5[2]);
                    six.setText("Volume "+bse6[2]);
                }
                else
                {
                    one.setText(nse1[2]);
                    two.setText(nse2[2]);
                    four.setText("Day Range "+nse4[2]);
                    five.setText("52 Week Range "+nse5[2]);
                    six.setText("Volume "+nse6[2]);
                }
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                heading.setText("Reliance");
                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String url = "https://www.moneycontrol.com/india/stockpricequote/refineries/relianceindustries/RI";
                        Toast.makeText(MainActivity3.this, url, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(url));
                        startActivity(intent);
                    }
                });
                if(selected=="BSE")
                {
                    one.setText(bse1[3]);
                    two.setText(bse2[3]);
                    four.setText("Day Range "+bse4[3]);
                    five.setText("52 Week Range "+bse5[3]);
                    six.setText("Volume "+bse6[3]);
                }
                else
                {
                    one.setText(nse1[3]);
                    two.setText(nse2[3]);
                    four.setText("Day Range "+nse4[3]);
                    five.setText("52 Week Range "+nse5[3]);
                    six.setText("Volume "+nse6[3]);
                }
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                heading.setText("Tata Steel");
                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String url = "https://www.moneycontrol.com/india/stockpricequote/ironsteel/tatasteel/TIS";
                        Toast.makeText(MainActivity3.this, url, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(url));
                        startActivity(intent);
                    }
                });
                if(selected=="BSE")
                {
                    one.setText(bse1[4]);
                    two.setText(bse2[4]);
                    four.setText("Day Range "+bse4[4]);
                    five.setText("52 Week Range "+bse5[4]);
                    six.setText("Volume "+bse6[4]);
                }
                else
                {
                    one.setText(nse1[4]);
                    two.setText(nse2[4]);
                    four.setText("Day Range "+nse4[4]);
                    five.setText("52 Week Range "+nse5[4]);
                    six.setText("Volume "+nse6[4]);
                }
            }
        });
    }
}
