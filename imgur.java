public void uploadImage(Bitmap bitmap String clientId){
    
    
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
    byte[] imageBytes = baos.toByteArray();
    String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
    
    
        RequestQueue rq = Volley.newRequestQueue(MainActivity.reference);
        String url = "https://api.imgur.com/3/upload";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    
                    ////get the uploaded link////
                    
                    JSONObject jObject = new JSONObject(response);
                    JSONObject data1 = jObject.getJSONObject("data");
                    String link = data1.getString("link");
                    
                    ///////////////////////////
                    
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String >data = new HashMap<String, String>();
                data.put("image",encodedImage);
                return data;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization","Client-ID "+clientId);
                return headers;
            }
        };
        rq.add(stringRequest);
    }