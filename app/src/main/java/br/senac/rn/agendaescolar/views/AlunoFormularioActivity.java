package br.senac.rn.agendaescolar.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import br.senac.rn.agendaescolar.daos.AlunoDao;
import br.senac.rn.agendaescolar.models.Aluno;

public class AlunoFormularioActivity extends AppCompatActivity {

    private EditText etNome, etEndereco, etFone, etSite;
    private RatingBar rbNota;
    private Button btCadastrar,btEditar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aluno_formulario);
        inicializarComponentes();
        definirEventos();
    }

    private void inicializarComponentes(){
        etNome = (EditText) findViewById(R.id.formulario_nome);
        etEndereco = (EditText) findViewById(R.id.formulario_endereco);
        etFone = (EditText) findViewById(R.id.formulario_fone);
        etSite = (EditText) findViewById(R.id.formulario_site);
        rbNota = (RatingBar) findViewById(R.id.formulario_nota);
        btCadastrar = (Button) findViewById(R.id.formulario_cadastrar);





        Intent intent = getIntent();
        Aluno aluno = (Aluno) intent.getSerializableExtra("aluno");
        if(aluno != null){
            teste(aluno);
            //preencherCampos(aluno);

        }

    }

    private void teste(final Aluno aluno){
        setContentView(R.layout.activity_aluno_editar);
        etNome = (EditText) findViewById(R.id.formulario_nome);
        etEndereco = (EditText) findViewById(R.id.formulario_endereco);
        etFone = (EditText) findViewById(R.id.formulario_fone);
        etSite = (EditText) findViewById(R.id.formulario_site);
        rbNota = (RatingBar) findViewById(R.id.formulario_nota);
        btEditar = (Button) findViewById(R.id.formulario_editar);

        preencherCampos(aluno);

        btEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editar(aluno);
            }
        });
    }

    private void definirEventos(){
        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastrar();
            }
        });
        /*btEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editar();
            }
        });*/
    }

    private void cadastrar(){
        Toast.makeText(this,"Aluno Cadastrado", Toast.LENGTH_LONG).show();

        AlunoDao dao = new AlunoDao(this);
        Aluno aluno = new Aluno();
        aluno.setNome(etNome.getText().toString());
        aluno.setEndereco(etEndereco.getText().toString());
        aluno.setFone(etFone.getText().toString());
        aluno.setSite(etSite.getText().toString());
        aluno.setNota(Double.valueOf(rbNota.getProgress()));
        dao.inserir(aluno);
        finish();


        //Intent intentChamaLista = new Intent(this, AlunoListaActivity.class);
        //startActivity(intentChamaLista);
    }

    private void editar(Aluno aluno){
        AlunoDao dao = new AlunoDao(this);
        //Aluno aluno = new Aluno();
        //aluno.setId(aluno.getId());
        aluno.setNome(etNome.getText().toString());
        aluno.setEndereco(etEndereco.getText().toString());
        aluno.setFone(etFone.getText().toString());
        aluno.setSite(etSite.getText().toString());
        aluno.setNota(Double.valueOf(rbNota.getProgress()));
        dao.editar(aluno);
        finish();
    }

    private void limparCampos(){
        etNome.setText("");
        etEndereco.setText("");
        etFone.setText("");
        etSite.setText("");
        rbNota.setProgress(0);
    }

    private void preencherCampos(Aluno aluno){
        etNome.setText(aluno.getNome());
        etEndereco.setText(aluno.getEndereco());
        etFone.setText(aluno.getFone());
        etSite.setText(aluno.getSite());
        rbNota.setProgress(aluno.getNota().intValue());
    }

}