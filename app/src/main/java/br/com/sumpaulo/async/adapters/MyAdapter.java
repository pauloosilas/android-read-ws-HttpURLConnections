package br.com.sumpaulo.async.adapters;

import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.sumpaulo.async.POJO.Usuario;
import br.com.sumpaulo.async.R;

/**
 * Created by paulo on 16/07/17.
 */

public class MyAdapter extends BaseAdapter {
   List<Usuario> usuarioList = new ArrayList<Usuario>();
   LayoutInflater layoutInflater;
    Context context;


    public  MyAdapter(Context context, List<Usuario> usuarioList){
        this.context = context;
        this.usuarioList = usuarioList;
        layoutInflater = LayoutInflater.from(this.context);

    }

    @Override
    public int getCount() {
        return this.usuarioList.size();
    }

    @Override
    public Object getItem(int i) {
        return usuarioList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view == null){

            view = layoutInflater.inflate(R.layout.item,viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) view.getTag();
        }

        Usuario usuario = (Usuario) getItem(position);
        viewHolder.title.setText(usuario.getNome());

        return view;
    }


    public class ViewHolder{
        TextView title;
        ImageView imageView;

        public ViewHolder(View view){
            title = (TextView) view.findViewById(R.id.titlePerfil);
        //    imageView = (ImageView) view.findViewById(R.id.perfil);
        }
    }
}
