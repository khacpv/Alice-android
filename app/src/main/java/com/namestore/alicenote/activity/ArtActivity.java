package com.namestore.alicenote.activity;

import android.os.Bundle;
import android.view.View;

import com.namestore.alicenote.core.CoreActivity;
import com.namestore.alicenote.core.CoreFragment;
import com.namestore.alicenote.fragment.art.ArtGroupFragment;
import com.namestore.alicenote.fragment.art.EditSubArtFragment;
import com.namestore.alicenote.fragment.art.SubArtFragment;

import java.util.ArrayList;

/**
 * Created by kienht on 11/9/16.
 */

public class ArtActivity extends CoreActivity {

    ArtGroupFragment artGroupFragment;
    SubArtFragment subArtFragment;
    EditSubArtFragment editSubArtFragment;

    ArrayList<CoreFragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        artGroupFragment = new ArtGroupFragment();
        subArtFragment = new SubArtFragment();
        editSubArtFragment = new EditSubArtFragment();

        fragments.add(artGroupFragment);
        fragments.add(subArtFragment);
        fragments.add(editSubArtFragment);

    }

    @Override
    public void onClick(View view) {

    }
}
