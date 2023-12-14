package com.example.confirmation;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity {

    private DatePicker datePicker;
    private Button procedimentoButton;
    private Button salvarDadosButton;

    private String[] procedimentos = {"Limpeza de pele", "Botox", "Peeling"};
    private int procedimentoSelecionado = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        datePicker = findViewById(R.id.datePicker);
        procedimentoButton = findViewById(R.id.procedimentoButton);
        salvarDadosButton = findViewById(R.id.salvarDadosButton);

        procedimentoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProcedimentoDialog();
            }
        });

        salvarDadosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarDados();
            }
        });
    }

    private void showProcedimentoDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Selecione um procedimento")
                .setSingleChoiceItems(procedimentos, procedimentoSelecionado, (dialog, which) -> {
                    procedimentoSelecionado = which;
                    procedimentoButton.setText(procedimentos[which]);
                    dialog.dismiss();
                });
        builder.create().show();
    }

    private void salvarDados() {
        int dia = datePicker.getDayOfMonth();
        int mes = datePicker.getMonth() + 1;
        int ano = datePicker.getYear();

        if (procedimentoSelecionado != -1) {
            String procedimento = procedimentos[procedimentoSelecionado];
            String dataFormatada = String.format(Locale.getDefault(), "%02d/%02d/%04d", dia, mes, ano);

            String resultado = String.format(Locale.getDefault(),
                    "Seu procedimento %s foi agendado para o dia %s", procedimento, dataFormatada);

            showCustomDialog(resultado);
        } else {
            showCustomDialog("Selecione um procedimento");
        }
    }

    private void showCustomDialog(String mensagem) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.custom_dialog_layout, null);

        TextView messageTextView = dialogView.findViewById(R.id.messageTextView);
        Button finalizarButton = dialogView.findViewById(R.id.finalizarButton);

        messageTextView.setText(mensagem);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);

        AlertDialog dialog = builder.create();
        dialog.show();

        finalizarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                finish();
            }
        });
    }
}
