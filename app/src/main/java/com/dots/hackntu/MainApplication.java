package com.dots.hackntu;

/**
 * Created by AdrianHsu on 15/8/14.
 */

import android.app.Application;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import com.facebook.FacebookSdk;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.util.DrawerImageLoader;
import com.parse.Parse;
import com.parse.ParseFacebookUtils;
import com.squareup.picasso.Picasso;

public class MainApplication extends Application {


  //save our header or result
  public static AccountHeader headerResult = null;
  public static Drawer result = null;
  public static IProfile profile;

  @Override
  public void onCreate() {
    super.onCreate();
    FacebookSdk.sdkInitialize(getApplicationContext());

    Parse.enableLocalDatastore(this);
    Parse.initialize(this,
      "2ImQ5EPEqrFTzCIqTLTamFwn8U0XKAXzvcGDs1HP",
      "TgPxlsLChikw3Ig1BoOdGyiv642iaAvc6pLO89aj"
    );

    ParseFacebookUtils.initialize(this);

    //initialize and create the image loader logic
    DrawerImageLoader.init(new DrawerImageLoader.IDrawerImageLoader() {
      @Override
      public void set(ImageView imageView, Uri uri, Drawable placeholder) {
        Picasso.with(imageView.getContext()).load(uri).placeholder(placeholder).into(imageView);
      }

      @Override
      public void cancel(ImageView imageView) {
        Picasso.with(imageView.getContext()).cancelRequest(imageView);
      }

      @Override
      public Drawable placeholder(Context ctx) {
        return null;
      }
    });

  }
}
