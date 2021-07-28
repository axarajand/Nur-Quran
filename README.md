[![axarajand](https://circleci.com/gh/axarajand/QuranApp.svg?style=shield)](https://circleci.com/gh/axarajand/QuranApp)

**Aplikasi untuk menampilkan Surat dan Ayat dari Al-Qur'an serta terjemahannya.**

Kriteria App :
  * Terdapat 3 Fitur Utama
    * Tema dan API yaitu Quran.
    * Terdapat halaman list item, detail item, dan list favorite (menggunakan database).

  * Menerapkan Modularization
    * 1 Android Library untuk core dan 1 dynamic feature untuk favorite.

  * Menerapkan Clean Architecture
    * Tidak melanggar dependency rule dari Clean Architecture.
    * Memisahkan model untuk domain dengan model untuk data (separation model).

  * Menerapkan Dependency Injection
    * Menggunakan Koin.
    * Menerapkan injection untuk semua fungsionalitas.

  * Menerapkan Reactive Programming
    * Menggunakan Coroutine Flow. 
    * Menerapkan untuk mengambil data dari network dan database.

  * Menerapkan Continuous Integration.
    * Menggunakan Tool CircleCI.
    * Melakukan test dan build APK dengan sukses (pass) pada proses terakhirnya. (https://app.circleci.com/pipelines/github/axarajand/Nur-Quran)

  * Memiliki performa yang baik
    * Menerapkan Leak Canary dan tidak ada memory leaks saat dianalisa.
    * Tidak ada issue terkait performance saat dilakukan Inspect Code.

  * Menerapkan Security
    * Menerapkan obfuscation dengan ProGuard. 
    * Menerapkan encryption pada database.
    * Menerapkan certificate pinning untuk koneksi ke server.

