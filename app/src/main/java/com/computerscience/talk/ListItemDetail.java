public class ListItemDetail extends Activity {

@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_listResources);

    Intent intent = getIntent();
    int position = intent.getIntExtra("position", 0);

    // Here we turn your string.xml in an array
    String[] myKeys = getResources().getStringArray(R.array.sections);

    TextView myTextView = (TextView) findViewById(R.id.my_textview);
    myTextView.setText(myKeys[position]);


    }

}
