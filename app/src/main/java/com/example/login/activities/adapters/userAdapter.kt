package com.example.login.activities.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.login.R
import com.example.login.models.usuario
import com.example.login.models.usuarioItem
import retrofit2.Call

class userAdapter(
    val context: Context,
    val usuarioList: List<usuarioItem>
    ):RecyclerView.Adapter<userAdapter.ViewHolder>()
{
    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        var id: TextView
        var clogin: TextView
        var cnombre: TextView
        var bactivo: TextView
        var dfecreg: TextView
        var dfecmod: TextView
        var ccodigosap: TextView
        var idusureg: TextView
        var idusumod: TextView
        var idempleado: TextView
        init {
            id = itemView.findViewById(R.id.id)
            clogin = itemView.findViewById(R.id.clogin)
            cnombre = itemView.findViewById(R.id.cnombre)
            bactivo = itemView.findViewById(R.id.bactivo)
            dfecreg = itemView.findViewById(R.id.dfecreg)
            dfecmod = itemView.findViewById(R.id.dfecmod)
            ccodigosap = itemView.findViewById(R.id.ccodigosap)
            idusureg = itemView.findViewById(R.id.idusureg)
            idusumod = itemView.findViewById(R.id.idusumod)
            idempleado = itemView.findViewById(R.id.idempleado)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.activity_show_user_list,parent,  false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.id.text = "id: " + usuarioList[position].id.toString()
        holder.clogin.text ="clogin: " + usuarioList[position].clogin.toString()
        holder.cnombre.text = "cnombre: " + usuarioList[position].cnombre.toString()
        holder.bactivo.text = "bactivo: " + usuarioList[position].bactivo.toString()
        holder.dfecreg.text = "dfecreg: " + usuarioList[position].dfecreg.toString()
        holder.dfecmod.text = "dfecmod: " + usuarioList[position].dfecmod.toString()
        holder.ccodigosap.text = "ccodigosap: " + usuarioList[position].ccodigosap.toString()
        holder.idusureg.text = "idusureg: " + usuarioList[position].idusureg.toString()
        holder.idusumod.text = "idusumod: " + usuarioList[position].idusumod.toString()
        holder.idempleado.text = "idempleado: " + usuarioList[position].idempleado.toString()

    }

    override fun getItemCount(): Int {
        return usuarioList.size
    }
}