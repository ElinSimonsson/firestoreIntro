package com.example.firestoreintro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    val shoppingList = mutableListOf<Item>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = Firebase.firestore

        val item1 = Item("Gurka", false, "grönsak")
        val item2 = Item("Ost", false, "mejeri")
        val item3 = Item("Tomat", false, "grönsak")


       // db.collection("items").add(item3)



        val docRef = db.collection("items")
//        docRef.get().addOnSuccessListener { documentSnapshot ->
//            for (document in documentSnapshot.documents) {
//                val item = document.toObject<Item>()
//                if (item != null) {
//                    shoppingList.add(item)
//                }
//
//            }
//           printShoppinhList()
//        }
//        Log.d("!!!", "test")


        docRef.addSnapshotListener { snapshot, e ->
              Log.d("!!!", "items updated!")
            if (snapshot != null) {
                shoppingList.clear()
                for (document in snapshot.documents) {
                    val item = document.toObject<Item>()
                    if (item != null) {
                        shoppingList.add(item)
                    }
                }
            }
            printShoppinhList()
        }


        
    }

    fun printShoppinhList () {
        for (item in shoppingList) {
            Log.d("!!!", "${item.name}")
        }
    }

}




//        // Create a new user with a first and last name
//        val user = hashMapOf(
//            "first" to "Ada",
//            "last" to "Lovelace",
//            "born" to 1815
//        )
//
//// Add a new document with a generated ID
//        db.collection("users")
//            .add(user)
//            .addOnSuccessListener { documentReference ->
//                Log.d("!!!", "DocumentSnapshot added with ID: ${documentReference.id}")
//            }
//            .addOnFailureListener { e ->
//                Log.w("!!!", "Error adding document", e)
//            }
//        val city = hashMapOf(
//            "name" to "Los Angeles",
//            "state" to "CA",
//            "country" to "USA"
//        )
//
//        db.collection("cities").document("LA")
//            .set(city)
//            .addOnSuccessListener { Log.d("!!!", "DocumentSnapshot successfully written!") }
//            .addOnFailureListener { e -> Log.w("!!!", "Error writing document", e) }
