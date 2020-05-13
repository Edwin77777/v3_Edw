package com.example.projectV3S

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectV3S.Room.NewResRoom
import com.example.projectV3S.Room.NewresDAO
import com.example.projectV3S.UTILS.Constantes
import com.example.projectV3S.model.ReservasLocal
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

   // var idreserva : String? = Constantes.EMPTY

    var googleSignInClient: GoogleSignInClient? = null
    var escenar: String? = null
    var hor: String? = null
    var fech: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

      /*  var dato = intent?.extras
        idreserva = dato?.getString("Fecha")*/



        var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)

        //eliminar_reserva_enpro()
        val auth: FirebaseAuth
        auth = FirebaseAuth.getInstance()
        val user2 = auth.currentUser


        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("reservasuser").child(user2!!.uid)


        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for (snapshot in dataSnapshot.children) {
                    val verReserv = snapshot.getValue(ReservasLocal::class.java)
                    escenar = verReserv!!.escenario
                    fech = verReserv.fecha
                    hor = verReserv.hora

                    var reservasLocalList: MutableList<ReservasLocal> = ArrayList()

                    reservasLocalList.add(
                        ReservasLocal(
                            escenario = escenar,
                            fecha = fech,
                            hora = hor
                        )
                    )
                    var reservasRVAdapter = ReservasRVAdapter(
                        applicationContext,
                        reservasLocalList as ArrayList<ReservasLocal>
                    )

                    rv_reservas.adapter = reservasRVAdapter



                }



            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("quepasomal", "Failed to read value.", error.toException())
            }
        })






      /*  var reservasLocalList: MutableList<ReservasLocal> = ArrayList()

        reservasLocalList.add(ReservasLocal(hor, fech, escenar))*/

        /*

        reservasLocalList.add(
            ReservasLocal(
                getString(R.string.reserva1),
                "Aceptado",
                "15/04/2020",
                "14:00-15:00"
                //R.drawable.aceptado
            )

        )

        reservasLocalList.add(
            ReservasLocal(
                getString(R.string.reserva2),
                "Rechazado",
                "11/05/2020",
                "10:00-11:00"
              //  R.drawable.rechazado
            )

        )

        reservasLocalList.add(
            ReservasLocal(
                getString(R.string.reserva3),
                "Pendiente",
                "16/07/2020",
                "12:00-13:00"
                //R.drawable.pendiente
            )

        )

        reservasLocalList.add(
            ReservasLocal(
                getString(R.string.reserva4),
                "Cancelado",
                "19/07/2020",
                "7:00-8:00"
               // R.drawable.cancelado
            )

        )
        reservasLocalList.add(
            ReservasLocal(
                getString(R.string.reserva1),
                "Aceptado",
                "19/07/2020",
                "7:00-8:00"
              //  R.drawable.aceptado
            )

        )

        rv_reservas.setHasFixedSize(true)
        rv_reservas.layoutManager = LinearLayoutManager(
            applicationContext,
            RecyclerView.VERTICAL,
            false
        )*/

      /*  val reservasRVAdapter = ReservasRVAdapter(
            applicationContext,
            reservasLocalList as ArrayList
        )*/




        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG
            //    .setAction("Action", null).show()
            var intent = Intent(this, NewReservaStep1::class.java)
            startActivity(intent)
            //finish()

        }

    }

    /*
    private fun eliminar_reserva_enpro() {
        val newresDAO: NewresDAO = NewResRoom.database1.NewresDAO()
        val nuevaReservRoom = newresDAO.searchEscenari()
        if (nuevaReservRoom != null) {
            newresDAO.deleteReserva(nuevaReservRoom) //Borro Datos de Room
        }
    }
    */

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_overflow,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.mcerrar){


            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            FirebaseAuth.getInstance().signOut()
            googleSignInClient?.signOut()
            finish()
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onResume() {
        super.onResume()
        //eliminar_reserva_enpro()
    }



}
