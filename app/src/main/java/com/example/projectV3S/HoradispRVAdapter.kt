package com.example.projectV3S


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectV3S.model.Reservas
import kotlinx.android.synthetic.main.item_horario.view.*
import java.util.ArrayList

class HoradispRVAdapter(
    private val context: Context, private val horariosdispolist : ArrayList<Reservas>,
    val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<HoradispRVAdapter.HoradispViewHolder>(){

    //private var horariosdispolist = emptyList<HoraDispo>()
    //private val context: Context

    /*init {
        this.horariosdispolist = horariosdispolist
        this.context = context
    }*/

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HoradispRVAdapter.HoradispViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.item_horario,parent,false)
        return HoradispViewHolder(itemView, context, onItemClickListener)

    }


    override fun getItemCount(): Int= horariosdispolist.size


    override fun onBindViewHolder(
        holder: HoradispRVAdapter.HoradispViewHolder,
        position: Int)
    {
        val horario: Reservas = horariosdispolist[position]
        holder.binHorariodisp(horario)
    }

    class HoradispViewHolder(
        itemView: View, context: Context, var onItemClickListener: OnItemClickListener
    ): RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private var context : Context
        private lateinit var horario : Reservas
        init{
            this.context=context
        }

        fun binHorariodisp(horario: Reservas) {
            this.horario = horario
            itemView.tv_hora_from_fire.text = horario.hora
            itemView.setOnClickListener(this)
            /*itemView.setOnClickListener {

                val hora = horario.hora

                Toast.makeText(context,horario.hora, Toast.LENGTH_SHORT).show()

                val intent = Intent(context, NewReservaStep1::class.java)
                intent.putExtra("horaselec",hora)
                getActivity(context)!!.startActivity(intent)
                //getActivity(context)!!.setResult(Activity.RESULT_OK, intent)
                //getActivity(context)!!.finish()

            }

            itemView.setOnLongClickListener{
                val hora = horario.hora
                Toast.makeText(context,hora, Toast.LENGTH_SHORT).show()
                return@setOnLongClickListener true
            }*/

        }

        override fun onClick(v: View?) {
            onItemClickListener.onItemClick(horario)
        }

    }

    interface OnItemClickListener{
        fun onItemClick(horario: Reservas)
    }



}