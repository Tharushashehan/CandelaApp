package lk.candelalearning.candelalearning;

import android.app.Dialog;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
//import android.support.v4.app.Fragment;
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
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Fragment;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

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
    //static TextView textViewTimer;
    View vw;
    Dialog dialog;
    //Button DialogBackButton;
    LinearLayout DialogimageView5LinearLayAbort;
    //Button DialogCancelButton;



    //TextView textView2;

    //initializing WebView
    private WebView mwebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainActivity.context = getApplicationContext();
        //dialog = new Dialog(context);
        dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.dialog_layout);//popup view is the layout you created

        //DialogBackButton = (Button) dialog.findViewById(R.id.button);
        //DialogCancelButton = (Button) dialog.findViewById(R.id.button2);

        DialogimageView5LinearLayAbort = (LinearLayout) dialog.findViewById(R.id.imageView5LinearLay);

        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        //textViewTimer = (TextView)findViewById(R.id.textViewTimer);
        //progressBar.getProgressDrawable().setColorFilter(Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);
        //textView2 = (TextView)findViewById(R.id.textView2);

        //Here is the next button tat needs to be changed visibility
        //START
        next_button = (Button) findViewById(R.id.next_button);
        //END

        //Here we add necessary paper data to the system
        //START
        myDb = new DataBaseHelper(this);
        AddData add = new AddData();
        add.dataEnter(myDb);
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


        DialogimageView5LinearLayAbort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Here we move again to papers
                //START
                int Year_Id, index;
                String Year;
                String Subject = "Please select a paper from of a year";
                myDb = new DataBaseHelper(MainActivity.getAppContext());
                Cursor GradeCursorForPaperYear = myDb.getPaperYear();
                List<Person> persons = new ArrayList<>();

                try {
                    while (GradeCursorForPaperYear.moveToNext()) {
                        index = GradeCursorForPaperYear.getColumnIndexOrThrow("Year_Id");
                        Year_Id = Integer.parseInt(GradeCursorForPaperYear.getString(index));

                        index = GradeCursorForPaperYear.getColumnIndexOrThrow("Year");
                        Year = GradeCursorForPaperYear.getString(index);

                        persons.add(new Person("Paper of year " + Year, Year_Id));
                    }
                } finally {
                    GradeCursorForPaperYear.close();
                }

                PaperYearFragment Frag = new PaperYearFragment();
                Bundle arguments = new Bundle();
                arguments.putString("VALUE2", Subject);
                //arguments.putSerializable("VALUE3", (Serializable) persons);
                Persons pp = new Persons(persons);
                arguments.putParcelable("VALUE3", pp);
                Frag.setArguments(arguments);
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, Frag, "PaperYearFragment").addToBackStack("PaperYearFragment").commit();
                //END
                MainActivity.GetProgressBar().setVisibility(View.GONE);
                //MainActivity.getTextViewTimer().setVisibility(View.GONE);
                dialog.hide();
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }


        //List<Fragment> fragmentList = getSupportFragmentManager().getFragments();

        boolean handled = false;

        /*for(Fragment f : fragmentList) {
            if(f instanceof OnBackPressedListener) {
                handled = ((OnBackPressedListener)f).onBackPressedCheck();
                if(handled) {
                    break;
                }
            }
        }*/

        if(!handled) {
            super.onBackPressed();
        }else {
            Toast.makeText(context, "This is else", Toast.LENGTH_LONG).show();
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

            mwebView.loadUrl("file:///android_asset/index.html");
            next_button.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
            //textViewTimer.setVisibility(View.GONE);
            //textView2.setVisibility(View.GONE);
        } else if (id == R.id.nav_products_layout) {
            fab.setVisibility(View.VISIBLE);
            EmptyFragment empFra = new EmptyFragment();
            fragmentManager.beginTransaction().replace(R.id.content_frame , empFra).commit();
            mwebView.loadUrl("file:///android_asset/products.html");
            next_button.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
            //textViewTimer.setVisibility(View.GONE);
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
            //textViewTimer.setVisibility(View.GONE);
            fragmentManager.beginTransaction().replace(R.id.content_frame , GradeFrag, "GradeFragment").commit();
        } else if (id == R.id.nav_papers_layout) {
            fab.setVisibility(View.VISIBLE);
            EmptyFragment empFra = new EmptyFragment();
            fragmentManager.beginTransaction().replace(R.id.content_frame , empFra).commit();
            mwebView.loadUrl("file:///android_asset/comingsoon.html");
            next_button.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
            //textViewTimer.setVisibility(View.GONE);
            //textView2.setVisibility(View.GONE);
        } else if (id == R.id.nav_aboutus_layout) {
            fab.setVisibility(View.VISIBLE);
            EmptyFragment empFra = new EmptyFragment();
            fragmentManager.beginTransaction().replace(R.id.content_frame , empFra).commit();
            mwebView.loadUrl("file:///android_asset/aboutus/aboutus.html");
            next_button.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
            //textViewTimer.setVisibility(View.GONE);
            //textView2.setVisibility(View.GONE);
        } else if (id == R.id.nav_send) {
            fab.setVisibility(View.VISIBLE);
            EmptyFragment empFra = new EmptyFragment();
            fragmentManager.beginTransaction().replace(R.id.content_frame , empFra).commit();
            System.exit(0);
            next_button.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
            //textViewTimer.setVisibility(View.GONE);
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    FragmentManager fragmentManager = getFragmentManager();
                    SubjectFragment mySubjectFragment = (SubjectFragment)getFragmentManager().findFragmentByTag("SubjectFragment");
                    PaperYearFragment myPaperYearFragment = (PaperYearFragment)getFragmentManager().findFragmentByTag("PaperYearFragment");
                    McqRecyclerView_mcqFragment myMcqFragment = (McqRecyclerView_mcqFragment)getFragmentManager().findFragmentByTag("MCQFragment");
                    InstructionFragment myInsFragmnt = (InstructionFragment)getFragmentManager().findFragmentByTag("InstructionFragment");

                   if (mySubjectFragment != null && mySubjectFragment.isVisible()) {
                        fragmentManager.beginTransaction().replace(R.id.content_frame , mySubjectFragment.SetFragmentData() , "GradeFragment").addToBackStack("GradeFragment").commit();
                        progressBar.setVisibility(View.GONE);
                        //textViewTimer.setVisibility(View.GONE);
                    }else if (myPaperYearFragment != null && myPaperYearFragment.isVisible()) {
                        fragmentManager.beginTransaction().replace(R.id.content_frame , myPaperYearFragment.SetFragmentData() , "SubjectFragment").addToBackStack("SubjectFragment").commit();
                        progressBar.setVisibility(View.GONE);
                        //textViewTimer.setVisibility(View.GONE);
                   }else if (myInsFragmnt != null && myInsFragmnt.isVisible()) {
                       fragmentManager.beginTransaction().replace(R.id.content_frame , myPaperYearFragment.SetFragmentData() , "SubjectFragment").addToBackStack("SubjectFragment").commit();
                       progressBar.setVisibility(View.GONE);
                    }else if (myMcqFragment != null && myMcqFragment.isVisible()){
                        Window window = dialog.getWindow();
                        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT); //
                        dialog.show();
                    }else if (mwebView.canGoBack()) {
                    mwebView.goBack();
                    }else{
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
//    public static TextView getTextViewTimer(){
//        return MainActivity.textViewTimer;
//    }
    public static Button getnext_button(){
        return MainActivity.next_button;
    }
}
