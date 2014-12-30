package banks.swift.dialog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import banks.swift.R;

/**
 * Created by pedro.sousa on 30/12/2014.
 */
public class DialogFragmentContato extends DialogFragment implements View.OnClickListener {

    public static final String TAG = "DialogFragmentContato";

    private EditText editTextMenssage;
    private Button buttonOk;
    private Button buttonCancel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_fragment_contato, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editTextMenssage = (EditText) view.findViewById(R.id.editTextMensagem);
        buttonOk = (Button) view.findViewById(R.id.buttonOk);
        buttonCancel = (Button) view.findViewById(R.id.buttonCancel);
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().setTitle(getString(R.string.enviar_mensagem));
        buttonOk.setOnClickListener(this);
        buttonCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonOk:
                String message = editTextMenssage.getText().toString();

                if (message != null && message.length() >= 10) {
                    editTextMenssage.setError(null);

                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("plain/text");
                    intent.putExtra(android.content.Intent.EXTRA_EMAIL,
                            new String[]{getActivity().getString(R.string.email_suporte)});

                    intent.putExtra(android.content.Intent.EXTRA_SUBJECT,
                            getString(R.string.titulo_email));

                    intent.putExtra(android.content.Intent.EXTRA_TEXT, message);

                    startActivity(intent);
                    dismiss();
                } else {
                    editTextMenssage.setError(getString(R.string.insira_uma_mensagem_com_mais_de_dez_caracteres));
                }
                break;

            case R.id.buttonCancel:
                dismiss();
                break;
        }
    }
}
