import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewDebug;
import android.widget.ListAdapter;

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
import java.util.Arrays;
import java.util.List;
import com.example.peeyushrai.healthscanner.*;

/**
 * Created by peeyushrai on 3/18/18.
 * This Async task runs in the background to get the data from Nutitionix API, parse it and then set the value of the Text Field to display the data
 */

public class getData extends AsyncTask<Void, Void, Void> {
    String data = "";
    String dataParsed = "";
    String ingrList = "";
    String barcode = this.data;
    String parsedItemName;
    String parsedBrandName;
    String badParsed = "";
    String imageUrlString;
    Bitmap imgBitmap = null;
    public String[] parsedIngredients;
    private RecyclerView.Adapter ingrListAdapter;
    public ArrayList <String> NormalBadIngredients = new ArrayList <String> ();



    public static List<String> allBadIngredients = new ArrayList<String>(); // This will store all bad ingredients for all conditions for the user
    static ArrayList<String> conditionsIds = new ArrayList(MainActivity.db.getUserConditions(1));

    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected Void doInBackground(Void... voids) {
        try {

            for (int i = 0; i < conditionsIds.size(); i++) {
                List<String> ingr = MainActivity.db.getIngredients(Integer.parseInt(conditionsIds.get(i)));
                for (int j = 0; j < ingr.size(); j++) {
                    allBadIngredients.add(ingr.get(j));
                    //Log.i("Ingredient :", ingr.get(j));
                }
            }

            System.out.println(" All Bad Ingredients:" + allBadIngredients);

            //form URL to all the nutritionix API. This API searches the item using the barcode no. (also known as UPC code).
            // The app ID and appKey is required, and is currently hard coded in the URL


            // Vode for V2 API from Nutritionix

            String itemId = "";
            itemId = MainActivity.barcode;
            String url = "https://trackapi.nutritionix.com/v2/search/item?upc=" + itemId;

            URL callingUrl = new URL(url);
            HttpURLConnection conn =  (HttpURLConnection) callingUrl.openConnection();
            // Set Connection Method
            conn.setRequestMethod("GET");

            // Add Request Headers, App ID and App Key
            conn.setRequestProperty("x-app-id", "97231a09");
            conn.setRequestProperty("x-app-key", "bb3005678f642ad0795b5b84dfbc49bf");

            int responseCode = conn.getResponseCode();
            //System.out.println("Response Code:" + responseCode);

            // Checkig for the response code after making the HTTP API call. 200 is success.
            if (conn.getResponseCode() == 200) {
                InputStream inputStream = conn.getInputStream();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";
                // Reading the HTTP API response line by line. In this case this is a JSON response
                while (line != null) {
                    line = bufferedReader.readLine();
                    data = data + line;
                }

                //Log.i("JSON:", data);
                // Parsing the JSON to extract fields
                JSONObject jsonObject = new JSONObject(data);

                JSONArray food = (JSONArray) jsonObject.get("foods");
                //System.out.println(food);
                //System.out.println(food.length());
                JSONObject foodAttribs = (JSONObject) food.get(0);
                JSONObject fPhoto = (JSONObject) foodAttribs.get("photo");
                //System.out.println("Photo URL:" + fPhoto.get("thumb"));
                //System.out.println("Photo URL:" + fPhoto.get("highres"));
                parsedItemName = "Item Name: " + foodAttribs.get("nix_item_name");
                parsedBrandName = "Brand Name: " + foodAttribs.get("nix_brand_name");
                ingrList = foodAttribs.get("nf_ingredient_statement") + "\n";
                imageUrlString = fPhoto.get("thumb").toString();
                // Split the ingredients based on "," as a delimiter and add a line break at the end, so that each ingredient is printed in separate line
                parsedIngredients = ingrList.split(",");

                // Get the Product Image
                URL imgUrl = new URL(imageUrlString);
                imgBitmap = BitmapFactory.decodeStream((InputStream)imgUrl.getContent());
            }
            else {

                parsedIngredients = new String[1];
                parsedIngredients[0] = "No Data Found!";
                allBadIngredients.add(parsedIngredients[0]);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return null;

    }
    @Override
    protected void onPostExecute (Void aVoid){
        super.onPostExecute(aVoid);

        // Set the fields in the main activity
        //MainActivity.ingredients.setText(this.singleParsed);
        MainActivity.itemName.setText(parsedItemName);
        MainActivity.brandName.setText(parsedBrandName);
        //MainActivity.badIngredients.setText(badParsed);
        //MainActivity.badIngredientsLabel.setTextColor(Color.RED);
        //MainActivity.goodIngredientsLabel.setTypeface(null, Typeface.BOLD);
        //MainActivity.badIngredientsLabel.setTypeface(null, Typeface.BOLD);
        MainActivity.itemImage.setImageBitmap(imgBitmap);
        if (!ingrList.equals("No Data Found!")){
            //MainActivity.goodIngredientsLabel.setText("Good Ingredients");
        }
        if (badParsed != ""){
            //MainActivity.badIngredientsLabel.setText("Bad Ingredients");
        }

        List<String> ingredientsList;
        if (parsedIngredients.length != 0)
        {
            ingredientsList = Arrays.asList(parsedIngredients);
        }
        else
        {
            ingredientsList = new ArrayList<String>();
            ingredientsList.add("Sorry, We could not find this ingredient!");
        }

        ingrListAdapter = new IngredientsListAdapter(ingredientsList);
        MainActivity.ingrRecyclerView.setAdapter(ingrListAdapter);
    }

}

