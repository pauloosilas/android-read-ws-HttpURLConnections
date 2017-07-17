package br.com.sumpaulo.async;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.sumpaulo.async.POJO.Usuario;
import br.com.sumpaulo.async.Parsers.UsuarioJsonParser;
import br.com.sumpaulo.async.Parsers.UsuarioXMLParser;
import br.com.sumpaulo.async.adapters.MyAdapter;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button btn;
    ProgressBar progressBar;

    ListView listView;
    MyAdapter adapter;

    List<Usuario> usuarioList;
    List<MyTask> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.texto);
        textView.setMovementMethod(new ScrollingMovementMethod());
        btn = (Button) findViewById(R.id.button);
        listView = (ListView) findViewById(R.id.listview);

        progressBar = (ProgressBar) findViewById(R.id.progress);
        progressBar.setVisibility(View.INVISIBLE);




        taskList = new ArrayList<MyTask>();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(isOnLine())
                //   pedirDados("http://www.maloschistes.com/maloschistes.com/jose/usuarios.xml");
               // pedirDados("http://www.maloschistes.com/maloschistes.com/jose/webservice.php");
                   pedirDados("http://www.maloschistes.com/maloschistes.com/jose/webservice.php");
                else
                   Toast.makeText(MainActivity.this,"Sem conexao com internet", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void carregarDados(String dados){
       // textView.append("\n" + dados);
        adapter = new MyAdapter(getApplicationContext(), usuarioList);
        listView.setAdapter(adapter);

       if(usuarioList != null) {
           for (Usuario usuario : usuarioList) {
               textView.append(usuario.getNome() + " " + usuario.getTwitter() + "\n");

           }
       }



    }
    public void pedirDados(String url){
        MyTask myTask = new MyTask();

        myTask.execute(url);
      //  myTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public boolean isOnLine(){
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnectedOrConnecting()){
            return true;
        }
        return false;
    }

    public class MyTask extends AsyncTask<String , String , String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

           if(taskList.size() == 0)
                progressBar.setVisibility(View.VISIBLE);

         //   carregarDados("iniciando...");
        //    taskList.add(this);

        }

        @Override
        protected String doInBackground(String... params) {
         /*   for(int i = 0; i < 15; i++){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress("Numero " + i);
            }*/
            String content = HttpManager.getData(params[0], "pepito","pepito");
            return content;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
          //  carregarDados(values[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
         //   taskList.remove(this);

            //XML PARSER ------------------------------------------------------------
           // usuarioList = UsuarioXMLParser.parser(result);

            //JSON PARSER ------------------------------------------------------------



            if(result == null){
                Toast.makeText(MainActivity.this, "Nao foi possivel carregar os dados!", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.INVISIBLE);
                return;
            }
            usuarioList = UsuarioJsonParser.parser(result);
            carregarDados(result);
            progressBar.setVisibility(View.INVISIBLE);



        }
    }

}
