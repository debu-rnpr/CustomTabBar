# CustomTabBar

Basic useful feature list:

 * Tabhost like look and feel
 * Listeners to notify tab click

Add the following to the project build.gradle file

```
buildscript {
    dependencies {
        classpath 'com.github.dcendents:android-maven-gradle-plugin:1.3
    }
}

allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```

Add the following to the app build.gradle file

```
dependencies {
    compile 'com.github.debu-rnpr:CustomTabBar:1.0.1'
}
```

Create custom drawable for each tab like - 

```
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:drawable="@drawable/tabbar_item_select" android:state_selected="true" />
    <item android:drawable="@drawable/tabbar_item" android:state_selected="false" />
</selector>

```

Now create an array with all the drawable name in strings.xml like(tabs will be displayed in the same order) - 

```
<array name="SelectorList">
        <item>selector_home</item>
        <item>selector_camera</item>
        <item>selector_meme</item>
        <item>selector_profile</item>
    </array>

```

Finally add this view in xml as -

```
  <com.debu.customtabbar.CustomTabView
        android:id="@+id/footer"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_alignParentBottom="true"
        android:orientation="horizontal"
        app:tabSelectors="@array/SelectorList"
        />
```
Now using orientation we can create a horizontal or vertical tab view

Code below demonstrates the use of this library, you can use activity of fragment 

```
public class MainActivity extends AppCompatActivity implements 	 CustomTabView.CustomTabCallback{
    public CustomTabView tabs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabs = (CustomTabView) findViewById(R.id.footer);
        tabs.setOnTabClickedListener(this);
        tabs.setSelection(0);
    }

    @Override
    public void onTabPressed(int tab) {
        //////your code on particular tab click
    }
