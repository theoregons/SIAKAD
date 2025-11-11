package com.example.siakadmobile

class data_mahasiswa {
    var nim: String? = null
    var nama: String? = null
    var jurusan: String? = null
    var jenisKelamin: String? = null
    var alamat: String? = null
    var key: String? = null


    constructor() {}

    constructor(nim: String?, nama: String?, jurusan: String?, jenisKelamin: String?, alamat: String?) {
        this.nim = nim
        this.nama = nama
        this.jurusan = jurusan
        this.jenisKelamin = jenisKelamin
        this.alamat = alamat
    }
}