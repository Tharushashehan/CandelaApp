package lk.candelalearning.candelalearning;

import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //Here is the next button tat needs to be changed visibility
    //START
    static Button next_button;
    //END
    Integer FirstArrayIndex =0;
    DataBaseHelper myDb;
    //Context context = getActivity();
    static Context context = null;
    FloatingActionButton fab;
    static ProgressBar progressBar;
    static TextView textViewTimer;


    //TextView textView2;

    //initializing WebView
    private WebView mwebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainActivity.context = getApplicationContext();
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        textViewTimer = (TextView)findViewById(R.id.textViewTimer);
        //progressBar.getProgressDrawable().setColorFilter(Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);
        //textView2 = (TextView)findViewById(R.id.textView2);

        //Here is the next button tat needs to be changed visibility
        //START
        next_button = (Button) findViewById(R.id.next_button);
        //END

        //Here we add necessary paper data to the system
        //START

        myDb = new DataBaseHelper(this);

        if(myDb.numberOfRows("Question")<1){

            //ppr 01 question o1
            myDb.insertQuestion( 1, 1, 1, 2, "ශ්‍රී ලංකාවට නිදහස ලැබුනේ කවදාද?");
            myDb.insertAnswer(1, 1, "1948", 0);
            myDb.insertAnswer(2, 1, "1954", 1);
            myDb.insertAnswer(3, 1, "1978", 1);
            myDb.insertAnswer(4, 1, "1815", 1);

            //ppr 01 question o2
            myDb.insertQuestion( 2, 1, 1, 2, "කුරානය කුමන ආගමට අයත් වුවක්ද?");
            myDb.insertAnswer(5, 2, "සිංහල", 1);
            myDb.insertAnswer(6, 2, "මුස්ලිම්", 1);
            myDb.insertAnswer(7, 2, "ඉස්ලාම්", 0);
            myDb.insertAnswer(8, 2, "හින්දු", 1);

            //ppr 01 question o3
            myDb.insertQuestion( 3, 1, 1, 2, "ජනවාරි මාසයට සිංහල ක්‍රමයට කියන නම කුමක්ද ?");
            myDb.insertAnswer(9, 3, "නවම්", 1);
            myDb.insertAnswer(10, 3, "බක්", 1);
            myDb.insertAnswer(11, 3, "දුරුතු", 0);
            myDb.insertAnswer(12, 3, "උදුවප්", 1);

            //ppr 01 question o4
            myDb.insertQuestion( 4, 1, 1, 2, "අතීතයේ ජම්බුද්වීපය යනුවෙන් හැදින්වුයේ ?");
            myDb.insertAnswer(13, 4, "ලංකාව", 1);
            myDb.insertAnswer(14, 4, "මියන්මාරය", 1);
            myDb.insertAnswer(15, 4, "පකිස්ථානය", 1);
            myDb.insertAnswer(16, 4, "ඉන්දියාව", 0);

            //ppr 01 question o5
            myDb.insertQuestion( 5, 1, 1, 2, "17 යනු ?");
            myDb.insertAnswer(17, 5, "ප්‍රථමක සංක්‍යාවකි", 0);
            myDb.insertAnswer(18, 5, "ඉරට්ටේ සංක්‍යාවකි ", 1);
            myDb.insertAnswer(19, 5, "ග්‍රීක සංක්‍යාවකි", 1);
            myDb.insertAnswer(20, 5, "භාග සංක්‍යාවකි", 1);

            //ppr 01 question o6
            myDb.insertQuestion( 6, 1, 1, 2, "1/2 දශමක සංක්‍යා වලින් ?");
            myDb.insertAnswer(21, 6, "0.3", 1);
            myDb.insertAnswer(22, 6, "2.5", 1);
            myDb.insertAnswer(23, 6, "1", 1);
            myDb.insertAnswer(24, 6, "0.5", 0);

            //ppr 01 question 07
            myDb.insertQuestion( 7, 1, 1, 2, "මම පාසල් යමි යන්න ");
            myDb.insertAnswer(25, 7, "උත්තම පුරුෂ වේ", 0);
            myDb.insertAnswer(26, 7, "මද්යම පුරුෂ වේ", 1);
            myDb.insertAnswer(27, 7, "බහුවචන වේ", 1);
            myDb.insertAnswer(28, 7, "අතීත කාල වේ ", 1);

            //ppr 01 question o8
            myDb.insertQuestion( 8, 1, 1, 2, "විල්පතුව යනු");
            myDb.insertAnswer(29, 8, "බදියුදීන්ගේ රජදනිය වේ", 1);
            myDb.insertAnswer(30, 8, "ඉන්දියාවට අයත් ප්‍රාන්තයකි", 1);
            myDb.insertAnswer(31, 8, "අප්‍රිකාවේ වනාන්තරයකි ", 1);
            myDb.insertAnswer(32, 8, "තර්ජනයට ලක්වූ ලංකාවේ වටිනා වනාන්තරයකි", 0);

            //ppr 01 question o9
            myDb.insertQuestion( 9, 1, 1, 2, "අමල් නයන සමග කලේ කුමක්ද ?");
            myDb.insertAnswer(33, 9, "මල් කැඩීම ", 0);
            myDb.insertAnswer(34, 9, "බෝල ගැසීම", 1);
            myDb.insertAnswer(35, 9, "අල්ලන සෙල්ලම්", 1);
            myDb.insertAnswer(36, 9, "පාඩම්", 1);

            //ppr 01 question 10
            myDb.insertQuestion( 10, 1, 1, 2, "ශ්‍රී ලංකාවට අවරුදු තිහක් පවතී යුද්දයෙන් නිදහස ලැබුනේ කවදාද?");
            myDb.insertAnswer(37, 10, "1948", 1);
            myDb.insertAnswer(38, 10, "2015", 1);
            myDb.insertAnswer(39, 10, "2009", 0);
            myDb.insertAnswer(40, 10, "1978", 1);

            //ppr 01 question 11
            myDb.insertQuestion( 11, 1, 1, 2, "ශ්‍රී ලංකාවේ ජාතික පක්ෂියා කව්ද ?");
            myDb.insertAnswer(41, 11, "මොනරා", 1);
            myDb.insertAnswer(42, 11, "කොවුලා", 1);
            myDb.insertAnswer(43, 11, "කොහා", 1);
            myDb.insertAnswer(44, 11, "වලිකුකුළා", 0);

            //ppr 01 question 12
            myDb.insertQuestion( 12, 1, 1, 2, "ශ්‍රී ලංකාව යනු");
            myDb.insertAnswer(45, 12, "මැදපෙරදිග රටකි", 1);
            myDb.insertAnswer(46, 12, "සිංහල බෞද්ධ රටකි", 0);
            myDb.insertAnswer(47, 12, "යුරෝපා මහාද්වීපයේ රටකි", 1);
            myDb.insertAnswer(48, 12, "චීනයේ ප්‍රාන්තයකි", 1);

            //ppr 01 question 13
            myDb.insertQuestion( 13, 1, 1, 2, "ශ්‍රී ලංකාවේ ජාතික ගස කුමක්ද ?");
            myDb.insertAnswer(49, 13, "බෝ ගස", 1);
            myDb.insertAnswer(50, 13, "තේ ගස", 1);
            myDb.insertAnswer(51, 13, "පොල් ගස", 1);
            myDb.insertAnswer(52, 13, "න ගස", 0);

            //ppr 01 question 14
            myDb.insertQuestion( 14, 1, 1, 2, "ශ්‍රී ලංකාවේ ජාතික ක්‍රීඩාව කුමක්ද ?");
            myDb.insertAnswer(53, 14, "ක්‍රිකට්", 1);
            myDb.insertAnswer(54, 14, "පැසි පන්දු", 1);
            myDb.insertAnswer(55, 14, "දැල් පන්දු", 0);
            myDb.insertAnswer(56, 14, "බේස් බෝල්", 1);

            //ppr 01 question 15
            myDb.insertQuestion( 15, 1, 1, 2, "කළා වැව සාදන ලද්දේ ");
            myDb.insertAnswer(57, 15, "චතුර සේනාරත්න විසිනි ", 1);
            myDb.insertAnswer(58, 15, "දුටුගැමුණු රජු විසිනි ", 1);
            myDb.insertAnswer(59, 15, "ඉංග්‍රීසින් විසිනි ", 1);
            myDb.insertAnswer(60, 15, "දතුසේන රජු විසිනි", 0);

            //ppr 01 question 16
            myDb.insertQuestion( 16, 1, 1, 2, "හෝර්ටන් තැන්න යනු ");
            myDb.insertAnswer(61, 16, "සානුවකි", 0);
            myDb.insertAnswer(62, 16, "නගර සබාවකි", 1);
            myDb.insertAnswer(63, 16, "තේ වත්තකි", 1);
            myDb.insertAnswer(64, 16, "යාපනය දිස්ත්‍රිකකයට අයත්වේ ", 1);

            //ppr 01 question 17
            myDb.insertQuestion( 17, 1, 1, 2, "දුරකථනය සොයාගත්තේ ");
            myDb.insertAnswer(65, 17, "ග්‍රහම්බෙල්", 0);
            myDb.insertAnswer(66, 17, "විමල් වීරවංශ", 1);
            myDb.insertAnswer(67, 17, "ගුග්ලි එල්මෝ මාර්කෝනි", 1);
            myDb.insertAnswer(68, 17, "තෝමස් අලවා එඩිසන්", 1);

            //ppr 01 question 18
            myDb.insertQuestion( 18, 1, 1, 2, "ඉංග්‍රීසි හෝඩියේ අකුරු ගණන කීයද ?");
            myDb.insertAnswer(39, 18, "විස්සයි", 1);
            myDb.insertAnswer(70, 18, "විසිහයයි", 0);
            myDb.insertAnswer(71, 18, "විසිඅටයි", 1);
            myDb.insertAnswer(72, 18, "විසිපහයි", 1);

            //ppr 01 question 19
            myDb.insertQuestion( 19, 1, 1, 2, "ලීකෙලි නැටුම");
            myDb.insertAnswer(73, 19, "අවරුදු කෑමකි", 1);
            myDb.insertAnswer(74, 19, "වෙසක් සැරසිල්ලකි", 1);
            myDb.insertAnswer(75, 19, "පෙරහැර ජන නැටුමකි", 0);
            myDb.insertAnswer(76, 19, "ලංකාවේ ජාතික නැටුමයි", 1);

            //ppr 01 question 20
            myDb.insertQuestion( 20, 1, 1, 2, "වොල්ඩමෝඩ් යනු");
            myDb.insertAnswer(77, 20, "ලංකාවේ ජනාධිපති වේ", 1);
            myDb.insertAnswer(78, 20, "හරිපොටෙර් චිත්‍රපටියේ චරිතයකි", 0);
            myDb.insertAnswer(79, 20, "කතානායකට කියන තවත් නමකි", 1);
            myDb.insertAnswer(80, 20, "වොල්ඩමාන්ලන්තයේ වැසියන්ට කියන නමයි", 1);

        }else if(myDb.numberOfRows("User")<1){
            myDb.insertUser(1, "Default User", "+94712587166");

            myDb.insertGrade(1,4);
            myDb.insertGrade(2,5);
            myDb.insertGrade(3,6);
            myDb.insertGrade(4,7);
            myDb.insertGrade(5,8);
            myDb.insertGrade(6,9);
            myDb.insertGrade(7,10);
            myDb.insertGrade(7,11);

            myDb.insertSubject(1,5,"Common Knowledge");
            myDb.insertSubject(2,5,"Sinhala");
            myDb.insertSubject(3,5,"English");
            myDb.insertSubject(4,5,"History");

            myDb.insertYear(1,1,1,2018);
            myDb.insertYear(2,1,1,2017);
            myDb.insertYear(3,1,1,2016);
            myDb.insertYear(4,1,1,2015);
        }
        //END

        //final Integer[]  intArry = {1,2,3,4,5,6,7,8,9,10};


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Adding Candela Hotline to dialer", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Uri number = Uri.parse("tel:0117 670 650");
                        Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                        startActivity(callIntent);
                    }
                }, 2000);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //WebView
        mwebView = (WebView) findViewById(R.id.myWebView);
        WebSettings webSettings = mwebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //improve webView performance
        mwebView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        mwebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        mwebView.getSettings().setAppCacheEnabled(true);
        mwebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setUseWideViewPort(true);
        webSettings.setSavePassword(true);
        webSettings.setSaveFormData(true);
        webSettings.setEnableSmoothTransition(true);


        mwebView.loadUrl("file:///android_asset/index.html");
        //force links open in webview only
        mwebView.setWebViewClient(new MyWebviewClient());

        mwebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return false;
            }
        });
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getFragmentManager();

        if (id == R.id.nav_home_layout) {
            fab.setVisibility(View.VISIBLE);
            EmptyFragment empFra = new EmptyFragment();
            fragmentManager.beginTransaction().replace(R.id.content_frame , empFra).commit();
            for(Fragment fragment:getSupportFragmentManager().getFragments()){
                    if(fragment!=null)
                        getSupportFragmentManager().beginTransaction().remove(fragment).commit();
            }
            //This is to close the fragments before relevent one opens
            //START
            Fragment fragment = getSupportFragmentManager().findFragmentByTag("TAG_FRAGMENT");
            //Fragment = MCQFragment;
            if(fragment != null)
                getSupportFragmentManager().beginTransaction().remove(fragment).commit();
            //END
            mwebView.loadUrl("file:///android_asset/index.html");
            next_button.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
            textViewTimer.setVisibility(View.GONE);
            //textView2.setVisibility(View.GONE);
        } else if (id == R.id.nav_products_layout) {
            fab.setVisibility(View.VISIBLE);
            EmptyFragment empFra = new EmptyFragment();
            fragmentManager.beginTransaction().replace(R.id.content_frame , empFra).commit();
            //This is to close the fragments before relevent one opens
            //START
            Fragment fragment = getSupportFragmentManager().findFragmentByTag("TAG_FRAGMENT");
            if(fragment != null)
                getSupportFragmentManager().beginTransaction().remove(fragment).commit();
            //END
            mwebView.loadUrl("file:///android_asset/products.html");
            next_button.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
            textViewTimer.setVisibility(View.GONE);
            //textView2.setVisibility(View.GONE);
        } else if (id == R.id.nav_mcq) {
            fab.setVisibility(View.GONE);
            //mwebView.loadUrl("file:///android_asset/quiz/index.html");
            Integer FirstArrayIndex =0;
            int VALUE1 = 0;
            int index, Grade_Id;
            String Grade, QuestionData = "These are the Grades, Please Select Relevent Grade";


            Cursor GradeCursor = myDb.getAllGradeData();
            ArrayList<String> TmpArryLst = new ArrayList<String>();

            try{
                while (GradeCursor.moveToNext()) {
                    index = GradeCursor.getColumnIndexOrThrow("Grade_Id");
                    Grade_Id = Integer.parseInt(GradeCursor.getString(index));

                    index = GradeCursor.getColumnIndexOrThrow("Grade");
                    Grade = GradeCursor.getString(index);

                    TmpArryLst.add("Grade "+Grade);
                }
            }finally {
                GradeCursor.close();
            }

            String[] GradeArray = new String[TmpArryLst.size()];
            GradeArray = TmpArryLst.toArray(GradeArray);
            //mcqFragment MCQFragment = new mcqFragment();
            GradeFragment GradeFrag = new GradeFragment();
            Bundle arguments = new Bundle();
            arguments.putInt("VALUE1", VALUE1);
            arguments.putString("VALUE2", QuestionData);
            arguments.putStringArray("VALUE3", GradeArray); //bundle.getStringArray("VALUE3");
            GradeFrag.setArguments(arguments);

            mwebView.loadUrl("about:blank");

            progressBar.setVisibility(View.GONE);
            textViewTimer.setVisibility(View.GONE);
            fragmentManager.beginTransaction().replace(R.id.content_frame , GradeFrag, "TAG_FRAGMENT").commit();
        } else if (id == R.id.nav_papers_layout) {
            fab.setVisibility(View.VISIBLE);
            EmptyFragment empFra = new EmptyFragment();
            fragmentManager.beginTransaction().replace(R.id.content_frame , empFra).commit();
            //This is to close the fragments before relevent one opens
            //START
            Fragment fragment = getSupportFragmentManager().findFragmentByTag("TAG_FRAGMENT");
            if(fragment != null)
                getSupportFragmentManager().beginTransaction().remove(fragment).commit();
            //END
            mwebView.loadUrl("file:///android_asset/comingsoon.html");
            next_button.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
            textViewTimer.setVisibility(View.GONE);
            //textView2.setVisibility(View.GONE);
        } else if (id == R.id.nav_aboutus_layout) {
            fab.setVisibility(View.VISIBLE);
            EmptyFragment empFra = new EmptyFragment();
            fragmentManager.beginTransaction().replace(R.id.content_frame , empFra).commit();
            //This is to close the fragments before relevent one opens
            //START
            Fragment fragment = getSupportFragmentManager().findFragmentByTag("TAG_FRAGMENT");
            if(fragment != null)
                getSupportFragmentManager().beginTransaction().remove(fragment).commit();
            //END
            mwebView.loadUrl("file:///android_asset/aboutus/aboutus.html");
            next_button.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
            textViewTimer.setVisibility(View.GONE);
            //textView2.setVisibility(View.GONE);
        } else if (id == R.id.nav_send) {
            fab.setVisibility(View.VISIBLE);
            EmptyFragment empFra = new EmptyFragment();
            fragmentManager.beginTransaction().replace(R.id.content_frame , empFra).commit();
            //This is to close the fragments before relevent one opens
            //START
            Fragment fragment = getSupportFragmentManager().findFragmentByTag("TAG_FRAGMENT");
            if(fragment != null)
                getSupportFragmentManager().beginTransaction().remove(fragment).commit();
            //END
            System.exit(0);
            next_button.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
            textViewTimer.setVisibility(View.GONE);
            //textView2.setVisibility(View.GONE);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private class MyWebviewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (Uri.parse(url).getHost().equals("file:///android_asset/")) {
                //open url contents in webview
                return false;
            } else {
                //here open external links in external browser or app
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
                return true;
            }

        }
        //ProgressDialogue
        ProgressDialog pd = null;

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            pd=new ProgressDialog(MainActivity.this);
            pd.setTitle("Hang on");
            pd.setMessage("Boosting app speed...");
            pd.show();
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            pd.dismiss();
            super.onPageFinished(view, url);
        }
    }
    //goto previous page when pressing back button

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (mwebView.canGoBack()) {
                        mwebView.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    public static Context getAppContext() {
        return MainActivity.context;
    }
    public static ProgressBar GetProgressBar(){
        return MainActivity.progressBar;
    }
    public static TextView getTextViewTimer(){
        return MainActivity.textViewTimer;
    }
    public static Button getnext_button(){
        return MainActivity.next_button;
    }
}
