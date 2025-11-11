package com.example.siakadmobile

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter( private val listMahasiswa: ArrayList<data_mahasiswa>,
                           context: Context
) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    private val context: Context

    //ViewHolder Digunakan Untuk Menyimpan Referensi Dari View-View
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val NIM: TextView
        val Nama: TextView
        val Jurusan: TextView
        val JenisKelamin: TextView
        val Alamat: TextView
        val ListItem: LinearLayout

        init {//Menginisialisasi View yang terpasang pada layout RecyclerView kita
            NIM = itemView.findViewById(R.id.nimx)
            Nama = itemView.findViewById(R.id.namax)
            Jurusan = itemView.findViewById(R.id.jurusanx)
            JenisKelamin = itemView.findViewById(R.id.jenisKelamin)
            Alamat = itemView.findViewById(R.id.alamat)
            ListItem = itemView.findViewById(R.id.list_item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//Membuat View untuk Menyiapkan & Memasang Layout yang digunakan pada RecyclerView
        val V: View = LayoutInflater.from(parent.getContext()).inflate(
            R.layout.view_design, parent, false)
        return ViewHolder(V)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Mengambil Nilai/Value pada RecyclerView berdasarkan Posisi Tertentu
        val NIM: String? = listMahasiswa.get(position).nim
        val Nama: String? = listMahasiswa.get(position).nama
        val Jurusan: String? = listMahasiswa.get(position).jurusan
        val JenisKelamin: String? = listMahasiswa.get(position).jenisKelamin
        val Alamat: String? = listMahasiswa.get(position).alamat

        //Memasukan Nilai/Value kedalam View (TextView: NIM, Nama, Jurusan)
        holder.NIM.text = "NIM: $NIM"
        holder.Nama.text = "Nama: $Nama"
        holder.Jurusan.text = "Jurusan: $Jurusan"
        holder.JenisKelamin.text = "JenisKelamin: $JenisKelamin"
        holder.Alamat.text = "Alamat: $Alamat"

        holder.ListItem.setOnLongClickListener { view ->
            // Gunakan holder.bindingAdapterPosition instead of position
            val currentPosition = holder.adapterPosition
            if (currentPosition != RecyclerView.NO_POSITION) {
                val action = arrayOf("Update", "Delete")
                val alert = AlertDialog.Builder(view.context)
                alert.setItems(action) { dialog, i ->
                    when (i) {
                        0 -> {
                            /* Berpindah Activity pada halaman layout updateData dan mengambil data pada
                              listMahasiswa, berdasarkan posisinya untuk dikirim pada activity selanjutnya */
                            val bundle = Bundle()
                            bundle.putString("dataNIM", listMahasiswa[currentPosition].nim)
                            bundle.putString("dataNama", listMahasiswa[currentPosition].nama)
                            bundle.putString("dataJurusan", listMahasiswa[currentPosition].jurusan)
                            bundle.putString("dataJenisKelamin", listMahasiswa[currentPosition].jenisKelamin)
                            bundle.putString("dataAlamat", listMahasiswa[currentPosition].alamat)
                            bundle.putString("getPrimaryKey", listMahasiswa[currentPosition].key)
                            val intent = Intent(view.context, UpdateData::class.java)
                            intent.putExtras(bundle)
                            context.startActivity(intent)
                        }
                        1 -> {
                            // Tambahkan logic delete disini
                        }
                    }
                }
                alert.create()
                alert.show()
            }
            true
        }
    }

    override fun getItemCount(): Int {
        //Menghitung Ukuran/Jumlah Data Yang Akan Ditampilkan Pada RecyclerView
        return listMahasiswa.size
    }

    //Membuat Konstruktor, untuk menerima input dari Database
    init {
        this.context = context
    }
}