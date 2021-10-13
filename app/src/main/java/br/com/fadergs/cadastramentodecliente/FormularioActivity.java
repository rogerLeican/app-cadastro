package br.com.fadergs.cadastramentodecliente;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FormularioActivity extends AppCompatActivity {

    private EditText etNome;
    private EditText etTelefone;
    private Spinner spEstado;
    private Button btnSalvar;
    private String acao;
    private Cliente cliente;
    private static final String INSERIR = "inserir";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        etNome = findViewById(R.id.etNome);
        etTelefone = findViewById(R.id.etTelefone);
        spEstado = findViewById(R.id.spEstado);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(view -> salvar());
        acao = getIntent().getStringExtra("acao");
        if (acao.equals("editar")) {
            carregarFormulario();
        }
    }

    private void salvar() {
        String nome = etNome.getText().toString();

        if (nome.isEmpty() || spEstado.getSelectedItemPosition() == 0) {
            Toast.makeText(this,
                    "todos os campos devem ser infomados",
                    Toast.LENGTH_SHORT)
                    .show();
        }
        if (acao.equals(INSERIR)) {
            cliente = new Cliente();
        }

        cliente.setNome(nome);
        cliente.setTelefone(etTelefone.getText().toString());
        cliente.setEstado(spEstado.getSelectedItem().toString());
        if (acao.equals(INSERIR)) {
            ClienteDAO.inserir(this, cliente);
            etNome.setText("");
            etTelefone.setText("");
            spEstado.setSelection(0, true);
        }

        if (!acao.equals(INSERIR)) {
            ClienteDAO.editar(this, cliente);
            finish();
        }
    }

    private void carregarFormulario() {
        int id = getIntent().getIntExtra("idCliente", 0);
        cliente = ClienteDAO.buscaClientePorId(this, id);
        etNome.setText(cliente.getNome());
        etTelefone.setText(cliente.getTelefone());

        String[] estados = getResources().getStringArray(R.array.estados);
        for (int i = 1; i < estados.length; i++) {
            if (cliente.getEstado().equals(estados[i])) {
                spEstado.setSelection(i);
                break;
            }
        }
    }
}