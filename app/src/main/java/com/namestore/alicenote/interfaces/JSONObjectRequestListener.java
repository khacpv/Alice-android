package com.namestore.alicenote.interfaces;


import org.json.JSONObject;


public interface JSONObjectRequestListener {
    void onSuccess(JSONObject response);

    void onError(JSONObject error);
}
