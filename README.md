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
