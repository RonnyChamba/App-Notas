package com.example.universidad;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.universidad.entidades.Estudiante;

/**
 * Implementar  interfaz OnClickListener para procesar los eventos click de
 * los Button y los CheckBox
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * Definir variables que se enlazaran con los widgets de la vista
     */
    private EditText txtNombre;
    private EditText txtParcialUno;
    private EditText txtParcialDos;

    private Spinner spNivel;
    private Spinner spAsignatura;

    private TextView txvMensaje;
    private TextView txvMensajeRecuperacion;

    private Button btnCalcular;
    private Button btnSalir;

    private CheckBox chParcialUno;
    private CheckBox chParcialDos;

    private Estudiante estudiante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();
    }

    /**
     * Método para inicializar | relacionar las variables con los widgets
     */
    private void initWidgets() {

        btnCalcular = findViewById(R.id.btnCalcular);
        // Poner a la escucha los botones, pasamos la propia clase(this), está implementa OnClickListener
        btnCalcular.setOnClickListener(this);

        btnSalir = findViewById(R.id.btnSalir);
        btnSalir.setOnClickListener(this);

        chParcialUno = findViewById(R.id.chParcial1);
        // Poner a la escucha los CheckBox, pasamos la propia clase(this), está implementa OnClickListener
        chParcialUno.setOnClickListener(this);
        chParcialDos = findViewById(R.id.chParcial2);
        chParcialDos.setOnClickListener(this);


        txtNombre = findViewById(R.id.txtNombre);
        txtParcialUno = findViewById(R.id.txtParcialUno);
        txtParcialDos = findViewById(R.id.txtParcialDos);

        spNivel = findViewById(R.id.spNivel);
        spAsignatura = findViewById(R.id.spAsignatura);

        txvMensaje = findViewById(R.id.txvMensaje);
        txvMensajeRecuperacion = findViewById(R.id.txvMensajeRecuperacion);
    }

    /**
     * Método  implementado por OnClickListener, es invocado por cada control oyente pulsado,
     * En este caso será invocado cuando pulsemos en los Button o en los Checked
     *
     * @param view : El widget que desencadeno el evento Click
     */
    @Override
    public void onClick(View view) {

        // Verificar que tipo de widget desencadeno el evento: Button o Checked
        if (view instanceof CheckBox) enabledCheckBox(view);
        else if (view instanceof Button) clickBotones(view);
    }

    /**
     * Método verifica que Button fue pulsado
     *
     * @param view
     */
    private void clickBotones(View view) {

        if (view.getId() == btnCalcular.getId()) procesarDatos();
        else if (view.getId() == btnSalir.getId()) finish();
    }

    /**
     * Método utilizado para obtener los datos ingresdos en los distintos widget de la vista
     */
    private void procesarDatos() {

        if (validarVaciosYNumericos()) {

            estudiante = new Estudiante();

            estudiante.setNombre(txtNombre.getText().toString());
            estudiante.setAsignatura(spAsignatura.getSelectedItem().toString());
            estudiante.setNivel(spNivel.getSelectedItem().toString());
            estudiante.setNotaParcialUno(Double.parseDouble(txtParcialUno.getText().toString()));
            estudiante.setNotaParcialDos(Double.parseDouble(txtParcialDos.getText().toString()));
            calcularPromedio();
        }
    }

    /**
     * Método valida que los campos  {@link #txtParcialUno} {@link #txtParcialDos} no esten vacios,
     * además que los valores ingresados sean númericos.
     *
     * @return true: continua con el procesamiento de los datos<br>
     * false: No procesa los datos y muestra mensajes de alerta
     */
    private boolean validarVaciosYNumericos() {

        boolean estado = true;
        if (txtParcialUno.getText().toString().isEmpty()) {
            Toast.makeText(this, "Ingrese Parcial Uno", Toast.LENGTH_SHORT).show();
            estado = false;
        }
        if (txtParcialDos.getText().toString().isEmpty()) {
            Toast.makeText(this, "Ingrese Parcial Dos", Toast.LENGTH_SHORT).show();
            estado = false;
        }
        if (estado) {

            if (!txtParcialUno.getText().toString().matches("\\d*") ||
                    !txtParcialDos.getText().toString().matches("\\d*")) {
                Toast.makeText(this, "Ingrese valores númericos", Toast.LENGTH_SHORT).show();
                estado = false;
            }
        }
        return estado;

    }

    /**
     * Método para calcula la nota final
     */
    private void calcularPromedio() {

        double promedioFinal = estudiante.getNotaParcialUno() + estudiante.getNotaParcialDos();
        estudiante.setPromedio(promedioFinal);
        mostrarMensajes();
    }

    /**
     * Método para mostrar los mensajes en {@link #txvMensaje}, {@link #txvMensajeRecuperacion} y
     */
    private void mostrarMensajes() {

        txvMensaje.setText(String.format("Nota Final : %s", estudiante.getPromedio()));

        txvMensajeRecuperacion.setText(getSmsFinal());
        if (estudiante.getPromedio() > 7.99 && estudiante.getPromedio() < 13.99) {

            ((TextView) findViewById(R.id.txvMensajeRecuperacionNota)).setText(String.format("Nota mínima de recuperación es %s", getnotaRecuperacion()));
        } else ((TextView) findViewById(R.id.txvMensajeRecuperacionNota)).setText("");
    }

    /**
     * Método encargado de deteminar el mensaje a mostrar en {@link #txvMensaje}, dependiendo
     * del valor del promedio final
     *
     * @return : el mensaje a mostrar en {@link #txvMensaje}
     */
    private String getSmsFinal() {

        if (estudiante.getPromedio() > 13.99) return "Aprueba la Asignatura";
        else if (estudiante.getPromedio() > 7.99) return "Recuperación";
        else return "Pierde la Materia";
    }

    /**
     * Método que calcula la nota de recuperación
     *
     * @return double: nota de recuperación
     */
    private double getnotaRecuperacion() {
        double promedioNuevo = estudiante.getPromedio() / 2;
        return 14 - promedioNuevo;
    }

    /**
     * Método procesa los evento de click de los Checked
     *
     * @param view
     */
    private void enabledCheckBox(View view) {

        // Evaluar cual checked fue pulsado
        if (view == chParcialUno) enabledTextNotaUno(chParcialUno.isChecked());
        if (view == chParcialDos) enabledTextNotaDos(chParcialDos.isChecked());
    }

    /**
     * Habilidar  || Deshabilitar el widget {@link #txtParcialUno}
     *
     * @param enabled : el estado para   {@link #txtParcialUno}
     */
    private void enabledTextNotaUno(boolean enabled) {

        txtParcialUno.setFocusable(enabled);
        txtParcialUno.setEnabled(enabled);
        txtParcialUno.setCursorVisible(enabled);
    }

    /**
     * Habilidar  || Deshabilitar el widget {@link #txtParcialDos}
     *
     * @param enabled : el estado para   {@link #txtParcialDos}
     * @param enabled
     */
    private void enabledTextNotaDos(boolean enabled) {

        txtParcialDos.setFocusable(enabled);
        txtParcialDos.setEnabled(enabled);
        txtParcialDos.setCursorVisible(enabled);

    }

}