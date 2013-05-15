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

import java.util.HashMap;

public interface Cachable{
    public abstract String cacheId();
    public abstract String cacheType();
        
    public HashMap<String, String> toCache();
    public Cachable fromCache(String type, String key, HashMap<String, String> cache);
    
    /*
    public String[] getCacheFields() {
        // TODO Auto-generated method stub
        return null;
    }

    private String[] mFields = getCacheFields();
    public HashMap<String, String> toCache(){
        
        HashMap<String, String> cache = new HashMap<String, String>();
        Class<?> c = this.getClass();
        
        try{
            for(int i=0; i<mFields.length; i++){
                Field f = c.getDeclaredField(mFields[i]);
                f.setAccessible(true);
                if(f.get(mFields[i]) != null)
                    cache.put(mFields[i], String.valueOf(f.get(mFields[i])));
            }
        }catch(Exception e){
            e.printStackTrace();
            System.exit(1); // catch bug
        }
        
        return cache;
    }
    
    public Cachable fromCache(HashMap<String, String> cache){
        
        Class<?> c = this.getClass();
        
        for(int i=0; i<mFields.length ; i++){
            try{
                
                Field f = c.getDeclaredField(mFields[i]);
                f.setAccessible(true);
                
                if(cache.get(mFields[i]) != null) {
                    if(f.getType()==int.class)
                        f.setInt(this, Integer.parseInt( cache.get(mFields[i]) ));
                    else if(f.getType() == String.class)
                        f.set(this, cache.get(mFields[i]) );
                    else if(f.getType() == long.class)
                        f.setLong(this, Long.parseLong( cache.get(mFields[i]) ));
                    else
                        throw new Exception();
                }
            
            }catch(Exception e){
                e.printStackTrace();
                System.exit(1); // catch bug
            }
        }
        return this;
    }
     */
        
}