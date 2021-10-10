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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        etNome = findViewById(R.id.etNome);
        etTelefone = findViewById(R.id.etTelefone);
        spEstado = findViewById(R.id.spEstado);
        Button btnSalvar = findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(view -> salvar());
    }

    private void salvar() {
        String nome = etNome.getText().toString();

        if (nome.isEmpty() || spEstado.getSelectedItemPosition() == 0) {
            Toast.makeText(this,
                    "todos os campos devem ser infomados",
                    Toast.LENGTH_SHORT)
                    .show();
        }
        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setTelefone(etTelefone.getText().toString());
        cliente.setEstado(spEstado.getSelectedItem().toString());
        ClienteDAO.inserir(this, cliente);
        etNome.setText("");
        etTelefone.setText("");
        spEstado.setSelection(0, true);

    }
}