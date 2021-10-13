package br.com.fadergs.cadastramentodecliente;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private ListView lvClientes;
    private ArrayAdapter adapter;
    private List<Cliente> listaDeClientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvClientes = findViewById(R.id.lvClientes);
        carregarClientes();

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(view -> {

            Intent intention = new Intent(MainActivity.this, FormularioActivity.class);
            intention.putExtra("acao", "inserir");
            startActivity(intention);
        });

        lvClientes.setOnItemClickListener((parent, view, position, id) -> {
            int idCliente = listaDeClientes.get(position).getId();
            Intent intention = new Intent(MainActivity.this, FormularioActivity.class);
            intention.putExtra("acao", "editar");
            intention.putExtra("idCliente", idCliente);
            startActivity(intention);
        });
        lvClientes.setOnItemLongClickListener((parent, view, position, id) -> {
            excluir(position);
            return true;
        });
    }

    private void excluir(int posicao) {
        Cliente cliente = listaDeClientes.get(posicao);
        AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
        alerta.setTitle("EXCLUIR...");
        alerta.setIcon(android.R.drawable.ic_delete);
        alerta.setMessage("Deseja exclui o " + cliente.getNome() + " ?");
        alerta.setNeutralButton("canselar",null);
        alerta.setPositiveButton("SIM", (dialogInterface, i) -> {
            ClienteDAO.excluir(MainActivity.this,cliente.getId());
            carregarClientes();
        });
        alerta.show();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        carregarClientes();
    }

    private void carregarClientes() {

        this.listaDeClientes = ClienteDAO.listarTodos(this);

        if (listaDeClientes.isEmpty()) {
            Cliente fake = new Cliente("Lista vazia..", "", "");
            listaDeClientes.add(fake);
            lvClientes.setEnabled(false);
        }
        lvClientes.setEnabled(true);

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaDeClientes);
        lvClientes.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}