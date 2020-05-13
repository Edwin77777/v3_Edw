package com.example.projectV3S

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.projectV3S.Room.NewResRoom
import com.example.projectV3S.Room.NewresDAO
import com.example.projectV3S.UTILS.Constantes
import com.example.projectV3S.model.Reservas
import com.example.projectV3S.model.ReservasLocal
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_new_reserva_step3.*

class NewReservaStep3 : AppCompatActivity() {
    var num_particif2 : String = Constantes.EMPTY


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_reserva_step3)

        var dataf2 = intent?.extras
        num_particif2 = dataf2!!.getString("Numparticif1").toString()

        val newresDAO: NewresDAO = NewResRoom.database1.NewresDAO()
        val nuevaReservRoom = newresDAO.searchEscenari()

        tv_escen.text = nuevaReservRoom.escen
        tv_fech.text = nuevaReservRoom.fecha
        tv_hor.text = nuevaReservRoom.hora
        tv_part1.text = nuevaReservRoom.participante1
        tv_part2.text = nuevaReservRoom.participante2
        tv_part3.text = nuevaReservRoom.participante3
        tv_part4.text = nuevaReservRoom.participante4
        tv_part5.text = nuevaReservRoom.participante5
        tv_part6.text = nuevaReservRoom.participante6
        tv_part7.text = nuevaReservRoom.participante7
        tv_part8.text = nuevaReservRoom.participante8
        tv_part9.text = nuevaReservRoom.participante9
        tv_part10.text = nuevaReservRoom.participante10
        tv_part11.text = nuevaReservRoom.participante11
        tv_part12.text = nuevaReservRoom.participante12
        tv_part13.text = nuevaReservRoom.participante13
        tv_part14.text = nuevaReservRoom.participante14
        tv_part15.text = nuevaReservRoom.participante15
        tv_part16.text = nuevaReservRoom.participante16

        SetearTextView()

        bt_conti_to_main.setOnClickListener {

            Guardar_Newreser_Firebase()
        }

    }

    @SuppressLint("ResourceType")
    private fun SetearTextView() {
        tv_titul1.id = 1
        tv_part1.id = 2
        tv_titul2.id = 3
        tv_part2.id = 4
        tv_titul3.id = 5
        tv_part3.id = 6
        tv_titul4.id = 7
        tv_part4.id = 8
        tv_titul5.id = 9
        tv_part5.id = 10
        tv_titul6.id =11
        tv_part6.id = 12
        tv_titul7.id = 13
        tv_part7.id = 14
        tv_titul8.id = 15
        tv_part8.id = 16
        tv_titul9.id = 17
        tv_part9.id = 18
        tv_titul10.id = 19
        tv_part10.id = 20
        tv_titul11.id = 21
        tv_part11.id = 22
        tv_titul12.id = 23
        tv_part12.id = 24
        tv_titul13.id = 25
        tv_part13.id = 26
        tv_titul14.id = 27
        tv_part14.id = 28
        tv_titul15.id = 29
        tv_part15.id = 30
        tv_titul16.id = 31
        tv_part16.id = 32
        for (i in 1..(num_particif2.toInt())*2){

            findViewById<TextView>(i).visibility = View.VISIBLE
        }
    }

    private fun Guardar_Newreser_Firebase(){

        val newresDAO: NewresDAO = NewResRoom.database1.NewresDAO()
        val nuevaReservRoom = newresDAO.searchEscenari()

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("reservasuser")
        val idreserva = myRef.push().key


        val cancha = nuevaReservRoom.escen
        val fech =  nuevaReservRoom.fecha
        val hora = nuevaReservRoom.hora
        val part1 = nuevaReservRoom.participante1
        val part2 = nuevaReservRoom.participante2
        val part3 = nuevaReservRoom.participante3
        val part4 = nuevaReservRoom.participante4
        val part5 = nuevaReservRoom.participante5
        val part6 = nuevaReservRoom.participante6
        val part7 = nuevaReservRoom.participante7
        val part8 = nuevaReservRoom.participante8
        val part9 = nuevaReservRoom.participante9
        val part10 = nuevaReservRoom.participante10
        val part11 = nuevaReservRoom.participante11
        val part12= nuevaReservRoom.participante12
        val part13= nuevaReservRoom.participante13
        val part14 = nuevaReservRoom.participante14
        val part15= nuevaReservRoom.participante15
        val part16= nuevaReservRoom.participante16


        writeNewReser(hora, fech, cancha, part1, part2, part3, part4, part5, part6, part7, part8, part9, part10, part11, part12,
            part13 , part14, part15, part16)

        writeReserLocal(idreserva, hora, fech, cancha, part1, part2, part3, part4, part5, part6, part7, part8, part9, part10, part11, part12,
            part13 , part14, part15, part16)





      /*  if (!part1.isNullOrEmpty() && !part2.isNullOrEmpty() && !part3.isNullOrEmpty() && !part4.isNullOrEmpty() && !part5.isNullOrEmpty()
            && !part6.isNullOrEmpty() && !part7.isNullOrEmpty() && !part8.isNullOrEmpty() && !part9.isNullOrEmpty() && !part10.isNullOrEmpty()
            && !part11.isNullOrEmpty()&& !part12.isNullOrEmpty()&& !part13.isNullOrEmpty()&& !part14.isNullOrEmpty()&& !part15.isNullOrEmpty()&& !part16.isNullOrEmpty())
        {
            writeNewReser(cancha, fech, hora, part1, part2, part3, part4, part5, part6, part7, part8, part9, part10, part11, part12,
                part13 , part14, part15, part16)


        }*/



    }
        private fun writeNewReser(a: String?, b:String?, c: String?, d: String?,
                                 e: String?, f: String?, g: String?, h: String?, i: String?,j:String?,k:String?,
                                  l:String?, m:String?, n:String?, o:String?, p:String?, q:String?, r:String?, s:String?) {

            val auth: FirebaseAuth
            auth = FirebaseAuth.getInstance()
            val user1 = auth.currentUser

            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("reservas")


            val reservas = Reservas(
                hora = a, fecha = b, escenario = c, num_doc_part1 = d, num_doc_part2 = e, num_doc_part3 = f, num_doc_part4 = g,
                num_doc_part5 = h, num_doc_part6 = i, num_doc_part7 = j, num_doc_part8 = k, num_doc_part9 = l, num_doc_part10 = m,
                num_doc_part11 = n, num_doc_part12 = o, num_doc_part13 = p, num_doc_part14 = q, num_doc_part15 = r, num_doc_part16 = s
            )
           // val reservas = Reservas(
            //    hora = a, fecha = b, escenario = c, num_doc_part1 = d, num_doc_part2 = e, num_doc_part3 = f)

            myRef.child(b!!).child(c!!).child(user1!!.uid).setValue(reservas)


        }

    private fun writeReserLocal(z:String?, a: String?, b:String?, c: String?, d: String?,
                                e: String?, f: String?, g: String?, h: String?, i: String?,j:String?,k:String?,
                                l:String?, m:String?, n:String?, o:String?, p:String?, q:String?, r:String?, s:String?){

        val auth: FirebaseAuth
        auth = FirebaseAuth.getInstance()
        val user2 = auth.currentUser


        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("reservasuser")


        val reservasLocal = ReservasLocal(
            id = z, hora = a, fecha = b, escenario = c, num_doc_part1 = d, num_doc_part2 = e, num_doc_part3 = f, num_doc_part4 = g,
            num_doc_part5 = h, num_doc_part6 = i, num_doc_part7 = j, num_doc_part8 = k, num_doc_part9 = l, num_doc_part10 = m,
            num_doc_part11 = n, num_doc_part12 = o, num_doc_part13 = p, num_doc_part14 = q, num_doc_part15 = r, num_doc_part16 = s
        )
        myRef.child(user2!!.uid).child(z!!).setValue(reservasLocal).addOnSuccessListener {
            var intent = Intent(this, MainActivity::class.java)
           // intent.putExtra("idreserva",z)
           // startActivityForResult(intent, 2222)
            startActivity(intent)
            finish()
        }


    }

   /* override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 2222 && resultCode == Activity.RESULT_CANCELED){
            Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show()
        }
        if (requestCode == 2222 && resultCode == Activity.RESULT_OK){

            Toast.makeText(this, "BBIIIEENNNN", Toast.LENGTH_SHORT).show()

          /*  var datafhora = data?.extras
            horafHoradRVA = datafhora?.getString("horaselec").toString()
            et_new_horari_inten.setText(horafHoradRVA)*/

            //Log.d("oooe", horafHoradRVA)

        }

        super.onActivityResult(requestCode, resultCode, data)
    }*/

}


