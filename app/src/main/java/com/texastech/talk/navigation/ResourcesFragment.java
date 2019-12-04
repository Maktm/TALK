package com.texastech.talk.navigation;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.texastech.talk.R;
import com.texastech.talk.database.AppDatabase;
import com.texastech.talk.database.Mood;
import com.texastech.talk.database.MoodDao;
import com.texastech.talk.database.Resources;
import com.texastech.talk.database.ResourcesDao;

import java.util.ArrayList;
import java.util.List;

public class ResourcesFragment extends Fragment {
    /**
     * Displays the resources that the user should be reading
     * based on their mood information.
     */
    public ResourcesFragment() {
        // Required.
    }

    public static ResourcesFragment newInstance() {
        return new ResourcesFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_resources, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Get the relevant articles
        List<Resources> articles = getRelevantResources(view);
        Log.d("Resources", String.format("Found %d articles for your mood", articles.size()));
        for (Resources res : articles) {
            Log.d("Resources", String.format("Found hyperlink %s", res.hyperlink));
        }

        // Depressed = 1, Sad = 2, Angry = 3, Scared = 4, Moderate = 5, Happy = 6
        final int MoodDepressed = 1;
        final int MoodSad = 2;
        final int MoodAngry = 3;
        final int MoodScared = 4;
        final int MoodModerate = 5;
        final int MoodHappy = 6;

        /**
         *
         */
        LinearLayout scrollableLinearLayout = new LinearLayout(view.getContext());
        scrollableLinearLayout.setOrientation(LinearLayout.VERTICAL);
        scrollableLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        ));

        // Display the articles in a CardView
        for (final Resources res : articles) {
            /**
             * <TextView
             *                     android:layout_width="match_parent"
             *                     android:layout_height="wrap_content"
             *                     android:layout_marginBottom="8dp"
             *                     android:text="Card title"
             *                     android:textColor="#000"
             *                     android:textSize="18sp" />
             */
            TextView textTitle = new TextView(view.getContext());
            textTitle.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            textTitle.setPadding(0, 0, 0, 8);
            textTitle.setText(res.title);
            textTitle.setTextColor(Color.WHITE);
            textTitle.setTextSize(20);

            /**
             * <TextView
             *                     android:layout_width="match_parent"
             *                     android:layout_height="wrap_content"
             *                     android:text="Card content"
             *                     android:textColor="#555" />
             */
            TextView contentText = new TextView(view.getContext());
            contentText.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            contentText.setText(res.content);
            contentText.setTextSize(14);
            contentText.setTextColor(Color.WHITE);

            /**
             * <LinearLayout
             *                 android:layout_width="match_parent"
             *                 android:layout_height="wrap_content"
             *                 android:orientation="vertical"
             *                 android:padding="16dp">
             *                 <TextView
             *                     android:layout_width="match_parent"
             *                     android:layout_height="wrap_content"
             *                     android:layout_marginBottom="8dp"
             *                     android:text="Card title"
             *                     android:textColor="#000"
             *                     android:textSize="18sp" />
             *                 <TextView
             *                     android:layout_width="match_parent"
             *                     android:layout_height="wrap_content"
             *                     android:text="Card content"
             *                     android:textColor="#555" />
             *             </LinearLayout>
             */
            LinearLayout textLayout = new LinearLayout(view.getContext());
            textLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            textLayout.setOrientation(LinearLayout.VERTICAL);
            textLayout.setPadding(16, 16, 16, 16);
            textLayout.addView(textTitle);
            textLayout.addView(contentText);

            /**
             * <Button
             *                     android:layout_width="wrap_content"
             *                     android:layout_height="wrap_content"
             *                     android:text="Learn more"/>
             */
            Button learnMoreBtn = new Button(view.getContext());
            RelativeLayout.LayoutParams buttonLayoutParams = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            buttonLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            learnMoreBtn.setLayoutParams(buttonLayoutParams);
            learnMoreBtn.setText("Learn more");
            learnMoreBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent hyperlinkIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(res.hyperlink));
                    startActivity(hyperlinkIntent);
                }
            });

            /**
             * <LinearLayout
             *                 android:layout_width="match_parent"
             *                 android:layout_height="wrap_content">
             *                 <Button
             *                     android:layout_width="wrap_content"
             *                     android:layout_height="wrap_content"
             *                     android:text="Learn more"/>
             *             </LinearLayout>
             */
            RelativeLayout buttonLayout = new RelativeLayout(view.getContext());
            buttonLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            buttonLayout.addView(learnMoreBtn);

            /**
             * <ImageView
             *                 android:layout_width="match_parent"
             *                 android:layout_height="160dp"
             *                 android:scaleType="centerCrop"
             *                 android:src="@drawable/abc_vector_test" />
             */
            ImageView image = new ImageView(view.getContext());
            image.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    200
            ));
            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
            int imageCode = 0x0;
            switch(res.mood) {
                case MoodDepressed:
                    imageCode = R.drawable.depressed_background;
                    break;
                case MoodSad:
                    imageCode = R.drawable.sad_background;
                    break;
                case MoodAngry:
                    imageCode = R.drawable.angry_background;
                    break;
                case MoodScared:
                    imageCode = R.drawable.scared_background;
                    break;
                case MoodModerate:
                    imageCode = R.drawable.moderate_background;
                    break;
                case MoodHappy:
                    imageCode = R.drawable.happy_background;
                    break;
            }
            image.setImageResource(imageCode);

            /**
             * <LinearLayout
             *             android:layout_width="match_parent"
             *             android:layout_height="wrap_content"
             *             android:orientation="vertical">
             */
            LinearLayout cardLayout = new LinearLayout(view.getContext());
            cardLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            cardLayout.setOrientation(LinearLayout.VERTICAL);
            cardLayout.addView(image);
            cardLayout.addView(textLayout);
            cardLayout.addView(buttonLayout);

            /**
             *    <androidx.cardview.widget.CardView
             *         android:id="@+id/card_view"
             *         android:layout_width="match_parent"
             *         android:layout_height="wrap_content"
             *         android:layout_margin="16dp"
             *         android:backgroundTint="#E6E6E6">
             */
            CardView card = new CardView(view.getContext());
            LinearLayout.LayoutParams cardLayoutParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            cardLayoutParams.setMargins(16, 16, 16, 16);
            card.setLayoutParams(cardLayoutParams);
            cardLayout.setOrientation(LinearLayout.VERTICAL);
            card.setPadding(20, 20, 20, 20);
            card.setBackgroundColor(Color.parseColor("#193C51"));
            card.addView(cardLayout);

            scrollableLinearLayout.addView(card);
        }

        ScrollView scrollView = new ScrollView(view.getContext());
        scrollView.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        ));
        scrollView.addView(scrollableLinearLayout);

        /**
         *    <androidx.cardview.widget.CardView
         *         android:id="@+id/card_view"
         *         android:layout_width="match_parent"
         *         android:layout_height="wrap_content"
         *         android:layout_margin="16dp"
         *         android:backgroundTint="#E6E6E6" ...
         */
        LinearLayout linearLayout = view.findViewById(R.id.resources_layout);
        linearLayout.addView(scrollView);
    }

    private List<Resources> getRelevantResources(View view) {
        /**
         * Returns the resources relevant to the user's mood.
         */
        AppDatabase database = AppDatabase.getDatabase(view.getContext());
        ResourcesDao resDao = database.resourcesDao();

        // Get last mood
        MoodDao moodDao = database.moodDao();
        List<Mood> allMoods = moodDao.getAll();
        Mood lastMood = new Mood(0, 5, 1);
        if (allMoods.size() > 0) {
            lastMood = allMoods.get(allMoods.size() - 1);
        }

        List<Resources> resources = new ArrayList<>();
        List<Resources> allArticles = resDao.getAll();
        for (Resources resource : allArticles) {
            if (resource.mood == lastMood.value) {
                resources.add(resource);
            }
        }

        return resources;
    }
}