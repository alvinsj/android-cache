/**
 * Copyright (c) 2012 Alvin S.J. Ng
 * 
 * Permission is hereby granted, free of charge, to any person obtaining 
 * a copy of this software and associated documentation files (the 
 * "Software"), to deal in the Software without restriction, including 
 * without limitation the rights to use, copy, modify, merge, publish, 
 * distribute, sublicense, and/or sell copies of the Software, and to 
 * permit persons to whom the Software is furnished to do so, subject 
 * to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be 
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT 
 * WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF 
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR 
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT 
 * SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE 
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER 
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, 
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR 
 * IN CONNECTION WITH THE SOFTWARE OR 
 * THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 * 
 * @author 		Alvin S.J. Ng <email.to.alvin@gmail.com>
 * @copyright	2013	Alvin S.J. Ng
 * 
 */
package com.stepsdk.android.cache;

import android.content.Context;
import android.util.Log;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class CacheStore {
    private CacheStoreOpenHelper dbHelper;
    
    public abstract String storeName();
    
    public CacheStore(Context context){
        dbHelper = new CacheStoreOpenHelper(context, storeName());
    }
    
    public boolean put(Cachable c){
        dbHelper.put(c.cacheType(), c.cacheId(), hashMapToJSONString(c.toCache()) );
        return true;
    }
    
    public boolean put(String type, String key, String c){
        dbHelper.put(type, key, c);
        return true;
    }
    
    public String get(String type, String key){
        String c = dbHelper.get(type, key);
        return c;
    }
    
    public Cachable getMatched(Cachable c, String type, String key){
        String r =  dbHelper.get(type, key);

        return c.fromCache(type, key, jsonStringToHashMap(r));
    }
    
    public Cachable getLatest(Cachable c, String type, String key){
        String r = dbHelper.get(type, key); 
        if( r == null )
        	r = dbHelper.getLatest(type);

        return c.fromCache(type, key, jsonStringToHashMap(r));
    }
    
    private String hashMapToJSONString(HashMap<String, String> map){
        Iterator<Entry<String, String>> iter = map.entrySet().iterator();
        JSONObject holder = new JSONObject();
        
        while(iter.hasNext()) {
        	Entry<String,String> pairs = (Map.Entry<String, String>) iter.next();
            String key = (String) pairs.getKey();
            String value = (String) pairs.getValue();
            try {
                holder.put(key, value);
            } catch (JSONException e) {
                Log.e("hashMapToJSONString", "There was an error packaging JSON", e );
            }
        }
        return holder.toString();
    }
    
    private HashMap<String, String> jsonStringToHashMap(String jsonString){
        HashMap<String, String> map = new HashMap<String, String>();
        JSONObject holder;
        try {
        	holder = new JSONObject(jsonString);
            Iterator<String> iter = (Iterator<String>) holder.keys();
            
            while(iter.hasNext()) {
            	String key = (String) iter.next();
            	String value = (String) holder.get(key);
            	map.put(key, value);
            }

        } catch(JSONException e) {
            Log.e("jsonStringToHashMap", "There was an error unpackaging JSON", e );
        }
        
        return map;
    }

    public void remove(String type, String cacheId) {
        dbHelper.delete(type, cacheId);
    }

    public void clear() {
        dbHelper.drop();
    }
   
}
