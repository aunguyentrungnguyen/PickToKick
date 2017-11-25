package picktokick.devfest.picktokick.activitys;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

import picktokick.devfest.picktokick.R;
import picktokick.devfest.picktokick.adapter.HeaderAdapter;
import picktokick.devfest.picktokick.objects.Member;
import picktokick.devfest.picktokick.objects.MessageObj;

public class ChatActivity extends AppCompatActivity {
    private FloatingActionButton btnSend;
    private EditText edtContentMessage;
    private TextView txtChatConversation;
    private String roomName;

    private DatabaseReference root;
    private String temp_key;
    private RecyclerView recyclerView;
    private HeaderAdapter mAdapter;
    private ArrayList<MessageObj> messageList;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        getDataFromAdapter();

        messageList = new ArrayList<>();
        //listName = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.rcListMessage);

        mAdapter = new HeaderAdapter(this,messageList);
//        mAdapter = new HeaderAdapter(this,listName);
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        mLayoutManager.setStackFromEnd(true);
//        mLayoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setAdapter(mAdapter);

        prepareMovieData();
    }

    private void prepareMovieData() {
        MessageObj message = new MessageObj("An",null,null,"Xin Chao",null,false);
        MessageObj message2 = new MessageObj("An",null,null,"Xin Chao",null,true);
        messageList.add(message);
        messageList.add(message2);
        mAdapter.notifyDataSetChanged();

    }

    private void getDataFromAdapter() {
        List<Member> memberList = (List<Member>) getIntent().getSerializableExtra("listMember");
        Log.e("TAG", String.valueOf(memberList.size()));
    }
}
