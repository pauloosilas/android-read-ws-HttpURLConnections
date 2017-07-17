package br.com.sumpaulo.async.Parsers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.sumpaulo.async.POJO.Usuario;

/**
 * Created by paulo on 16/07/17.
 */

public class UsuarioJsonParser {

    public static List<Usuario> parser(String content){
        try{
            JSONArray jsonArray = new JSONArray(content);
            List<Usuario> usuarioList = new ArrayList<>();

            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Usuario usuario = new Usuario();

                usuario.setUsuarioId(jsonObject.getInt("usuarioid"));
                usuario.setNome(jsonObject.getString("nombre"));
                usuario.setTwitter(jsonObject.getString("twitter"));

                usuarioList.add(usuario);
            }
            return usuarioList;

        }catch(JSONException e){
            e.printStackTrace();
            return null;
        }
    }
}
