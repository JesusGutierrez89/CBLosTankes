package com.example.cblostankes;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cblostankes.BD.AppDatabaseEquipos;
import com.example.cblostankes.BD.ClaseEquipo;
import com.example.cblostankes.BD.ClaseJugador;
import com.example.cblostankes.BD.EquipoConJugadores;
import com.example.cblostankes.INTERFAZ.ComunicaMenu;
import com.example.cblostankes.INTERFAZ.EquipoDao;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ComunicaMenu {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabaseEquipos db = AppDatabaseEquipos.getDatabase(getApplicationContext());
                db.clearAllTables();
                //Borrar todas las tablas
                // 🔹 Insertar los equipos antes que los jugadores
                insertarEquiposIniciales();

                // 🔹 Esperar a que se inserten los equipos
                try {
                    Thread.sleep(5000); // Espera 3 segundos (ajustar según necesidad)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                insertarJugadoresIniciales();
               /* int equipo5 = R.drawable.equipo5;
                byte[] bytesImgEquipo5 = convertirImagenABytes(equipo5);*/

                // 🔹 Ahora insertar los jugadores
                /*ClaseJugador jugador1 = new ClaseJugador(
                        null, "Andrés", "Castillo", "Núñez", "Masculino",
                        "1997-01-10", 183, "357951852", "Pivot",
                        "andres@example.com", "Dominante en la pintura", 9, bytesImgEquipo5
                );
                ClaseJugador jugador2 = new ClaseJugador(
                        null, "Julia", "Gomez", "Lopez", "Femenina",
                        "1997-01-10", 183, "357951852", "Pivot",
                        "andres@example.com", "Dominante en la pintura", 9, bytesImgEquipo5
                );
                ClaseJugador jugador3 = new ClaseJugador(null, "Juan", "Pérez", "Gómez", "Masculino", "2000-05-14", 180, "123456789", "Pivot", "juan@example.com", "Capitán del equipo", 1, bytesImgEquipo5);
                ClaseJugador jugador4 = new ClaseJugador(null, "Luis", "Fernández", "Díaz", "Masculino", "1998-08-22", 175, "987654321", "Alero", "luis@example.com", "Buen juego aéreo", 8, bytesImgEquipo5);
                ClaseJugador jugador5 = new ClaseJugador(null, "Carlos", "Rodríguez", "Torres", "Masculino", "1995-12-10", 182, "456123789", "Base", "carlos@example.com", "Habilidoso con el balón", 8, bytesImgEquipo5);
                ClaseJugador jugador6 = new ClaseJugador(null, "María", "García", "López", "Femenino", "2002-03-05", 165, "741852963", "AlaPivot", "maria@example.com", "Goleadora del equipo", 1, bytesImgEquipo5);
                ClaseJugador jugador7 = new ClaseJugador(null, "Ana", "Martínez", "Ruiz", "Femenino", "1999-07-18", 170, "852963741", "Ala", "ana@example.com", "Rápida y fuerte", 1, bytesImgEquipo5);

                insertarJugador(db, jugador1);
                insertarJugador(db, jugador2);
                insertarJugador(db, jugador3);
                insertarJugador(db, jugador4);
                insertarJugador(db, jugador5);
                insertarJugador(db, jugador6);
                insertarJugador(db, jugador7);*/

            }
        }).start();
    }
    public void salir(View view){
        finish();
    }

    @Override
    public void menu(int queboton) {

    }
    private void insertarEquiposIniciales() {
        new Thread(() -> {
            AppDatabaseEquipos db = AppDatabaseEquipos.getDatabase(this);
           EquipoDao equipoDao = db.equipoDao();

            // Verificamos si la tabla tiene cargados los registros de prueba
            List<ClaseEquipo> equiposExistentes = (List<ClaseEquipo>) equipoDao.obtenerTodosLosEquipos();
            if (equiposExistentes == null || equiposExistentes.isEmpty()) {
                // Obtenemos los IDs de las imágenes desde drawable
                int equipo1 = R.drawable.equipo1;
                int equipo2 = R.drawable.equipo2;
                int equipo3 = R.drawable.equipo3;
                int equipo4 = R.drawable.equipo4;
                int equipo5 = R.drawable.equipo5;
                int equipo6 = R.drawable.equipo6;
                int equipo7 = R.drawable.equipo7;
                int equipo8 = R.drawable.equipo8;
                int equipo9 = R.drawable.equipo9;

                // Convertimos las imágenes a byte[]
                byte[] bytesImgEquipo1 = convertirImagenABytes(equipo1);
                byte[] bytesImgEquipo2 = convertirImagenABytes(equipo2);
                byte[] bytesImgEquipo3 = convertirImagenABytes(equipo3);
                byte[] bytesImgEquipo4 = convertirImagenABytes(equipo4);
                byte[] bytesImgEquipo5 = convertirImagenABytes(equipo5);
                byte[] bytesImgEquipo6 = convertirImagenABytes(equipo6);
                byte[] bytesImgEquipo7 = convertirImagenABytes(equipo7);
                byte[] bytesImgEquipo8 = convertirImagenABytes(equipo8);
                byte[] bytesImgEquipo9 = convertirImagenABytes(equipo9);

                // Creamos los equipos con las fotos en formato byte[]
                ClaseEquipo equipo1info = new ClaseEquipo(
                        1,
                        "Los Sayamans",
                        "Frezer S.L",
                        "Escuela",
                        "Masculino",
                        "SI",
                        "Sabado",
                        "12:00",
                        "Lunes y Miercoles",
                        bytesImgEquipo1,
                        "Vegeta",
                        "666666666",
                        1

                );
                ClaseEquipo equipo2info = new ClaseEquipo(
                        2,
                        "Los Aguiluchos",
                        "Piensos Hacendado",

                        "Cadete",
                        "Femenino",
                        "SI",
                        "Domingo",
                        "13:00",
                        "Martes y Miercoles",
                        bytesImgEquipo2,
                        "Hombre Pajaro",
                        "664566666",
                        2

                );

                ClaseEquipo equipo3info = new ClaseEquipo(
                        3,
                        "Los Lobos del Norte",
                        "Deportes Martinez",

                        "Junior",
                        "Masculino",
                        "NO",
                        "Sábado",
                        "16:00",
                        "Lunes y Jueves",
                        bytesImgEquipo3,
                        "Juan Fernández",
                        "612345678",
                        3

                );
                ClaseEquipo equipo4info = new ClaseEquipo(
                        4,
                        "Los Jockets",
                        "Circo",

                        "Infantil",
                        "Femenino",
                        "SI",
                        "Domingo",
                        "11:00",
                        "Miércoles y Viernes",
                        bytesImgEquipo4,
                        "Enigma",
                        "645678123",
                        4

                );
                ClaseEquipo equipo5info = new ClaseEquipo(
                        5,
                        "Espartanos Griegos",
                        "Gim Sparta",
                        "Senior",
                        "Femenino",
                        "NO",
                        "Viernes",
                        "19:30",
                        "Lunes y Miércoles",
                        bytesImgEquipo5,
                        "Kratos",
                        "698765432",
                        5

                );
                ClaseEquipo equipo6info = new ClaseEquipo(
                        6,
                        "La Mueteh",
                        "Restaurante La Parrilla",

                        "Alevín",
                        "Femenino",
                        "SI",
                        "Sábado",
                        "10:00",
                        "Martes y Jueves",
                        bytesImgEquipo6,
                        "Pedro Sanchez",
                        "678123456",
                        6

                );
                ClaseEquipo equipo7info = new ClaseEquipo(
                        7,
                        "Los Indefinidos Errantes",
                        "Talleres Rodríguez",
                        "Benjamín",
                        "Masculino",
                        "NO",
                        "Domingo",
                        "17:00",
                        "Lunes y Viernes",
                        bytesImgEquipo7,
                        "Gru",
                        "645987321",
                        7

                );
                ClaseEquipo equipo8info = new ClaseEquipo(
                        8,
                        "Los pescados Furiosos",
                        "Pescaderia Sylvia",
                        "Junior",
                        "Masculino",
                        "NO",
                        "Domingo",
                        "17:00",
                        "Lunes y Viernes",
                        bytesImgEquipo8,
                        "Pedro Martínez",
                        "645987321",
                        8

                );
                ClaseEquipo equipo9info = new ClaseEquipo(
                        9,
                        "Los arbustos Espinosos",
                        "Floristeria Jaime",
                        "Cadete",
                        "Femenino",
                        "NO",
                        "Jueves",
                        "19:00",
                        "Martes y Viernes",
                        bytesImgEquipo9,
                        "Juan Abonos",
                        "644569321",
                        9

                );

                // Insertamos los equipos en la base de datos
                equipoDao.insertarEquipo(equipo1info);
                equipoDao.insertarEquipo(equipo2info);
                equipoDao.insertarEquipo(equipo3info);
                equipoDao.insertarEquipo(equipo4info);
                equipoDao.insertarEquipo(equipo5info);
                equipoDao.insertarEquipo(equipo6info);
                equipoDao.insertarEquipo(equipo7info);
                equipoDao.insertarEquipo(equipo8info);
                equipoDao.insertarEquipo(equipo9info);
            }
        }).start();
    }
    private void insertarJugadoresIniciales() {
        new Thread(() -> {
            AppDatabaseEquipos db = AppDatabaseEquipos.getDatabase(this);
            EquipoDao equipoDao = db.equipoDao();

            int jugador1a = R.drawable.jugador1;
            int jugador2b = R.drawable.jugador2;
            int jugador3c = R.drawable.jugador3;
            int jugador4d = R.drawable.jugador4;
            int jugador5e = R.drawable.jugador5;

            byte[] byteJugador1 = convertirImagenABytes(jugador1a);
            byte[] byteJugador2 = convertirImagenABytes(jugador2b);
            byte[] byteJugador3 = convertirImagenABytes(jugador3c);
            byte[] byteJugador4 = convertirImagenABytes(jugador4d);
            byte[] byteJugador5 = convertirImagenABytes(jugador5e);

            // Verificamos si la tabla tiene cargados los registros de prueba
            List<ClaseJugador> jugadoresExistentes = (List<ClaseJugador>) equipoDao.obtenerTodosLosJugadores();
            if (jugadoresExistentes == null || jugadoresExistentes.isEmpty()) {

                // Equipo 1
                ClaseJugador jugador1 = new ClaseJugador(1, "Juan", "Pérez", "Gómez", "Masculino", "2000-05-14", 180, "123456789", "Pivot", "juan@example.com", "Capitán del equipo", 1, byteJugador1);
                ClaseJugador jugador2 = new ClaseJugador(2, "Luis", "Fernández", "Díaz", "Masculino", "1998-08-22", 175, "987654321", "Alero", "luis@example.com", "Buen juego aéreo", 1, byteJugador3);
                ClaseJugador jugador3 = new ClaseJugador(3, "Carlos", "Rodríguez", "Torres", "Masculino", "1995-12-10", 182, "456123789", "Base", "carlos@example.com", "Habilidoso con el balón", 1,byteJugador4);
                ClaseJugador jugador4 = new ClaseJugador(4, "María", "García", "López", "Femenino", "2002-03-05", 165, "741852963", "AlaPivot", "maria@example.com", "Goleadora del equipo", 1,byteJugador2);
                ClaseJugador jugador5 = new ClaseJugador(5, "Ana", "Martínez", "Ruiz", "Femenino", "1999-07-18", 170, "852963741", "Ala", "ana@example.com", "Rápida y fuerte", 1,byteJugador5);

                // Equipo 2
                ClaseJugador jugador6 = new ClaseJugador(6, "Sofía", "Hernández", "Santos", "Femenino", "2001-11-30", 160, "963852741", "Ala", "sofia@example.com", "Buena visión de juego", 2,byteJugador4);
                ClaseJugador jugador7 = new ClaseJugador(7, "Pedro", "López", "Morales", "Masculino", "1997-09-25", 178, "159357852", "Base", "pedro@example.com", "Gran capacidad de reacción", 2,byteJugador1);
                ClaseJugador jugador8 = new ClaseJugador(8, "Diego", "Ruiz", "Castillo", "Masculino", "2000-06-12", 176, "357159852", "Pivot", "diego@example.com", "Fuerte en los duelos", 2,byteJugador3);
                ClaseJugador jugador9 = new ClaseJugador(9, "Elena", "Vega", "Fernández", "Femenino", "2003-04-08", 167, "951753852", "Alero", "elena@example.com", "Creativa y técnica", 2,byteJugador5);
                ClaseJugador jugador10 = new ClaseJugador(10, "Ricardo", "Torres", "Jiménez", "Masculino", "1998-10-22", 180, "753951852", "AlaPivot", "ricardo@example.com", "Estratega del equipo", 2,byteJugador2);

                // Equipo 3
                ClaseJugador jugador11 = new ClaseJugador(11, "Gabriela", "Moreno", "Ramírez", "Femenino", "2002-12-25", 164, "852147963", "Ala", "gabriela@example.com", "Defensora implacable", 3,byteJugador5);
                ClaseJugador jugador12 = new ClaseJugador(12, "Miguel", "Navarro", "Ortega", "Masculino", "1996-03-09", 182, "963741258", "Base", "miguel@example.com", "Distribuye bien el balón", 3,byteJugador1);
                ClaseJugador jugador13 = new ClaseJugador(13, "Daniel", "Luna", "Reyes", "Masculino", "1999-07-04", 179, "147258369", "Alero", "daniel@example.com", "Gran velocidad", 3,byteJugador2);
                ClaseJugador jugador14 = new ClaseJugador(14, "Liliana", "Santos", "Gómez", "Femenino", "2001-05-17", 166, "369258147", "Pivot", "liliana@example.com", "Imponente en la pintura", 3,byteJugador4);
                ClaseJugador jugador15 = new ClaseJugador(15, "Pablo", "Cruz", "Martínez", "Masculino", "1997-08-13", 181, "258369147", "AlaPivot", "pablo@example.com", "Gran salto y defensa", 3,byteJugador3);

                // Equipo 4
                ClaseJugador jugador16 = new ClaseJugador(16, "Roberto", "Fernández", "Núñez", "Masculino", "2000-11-21", 176, "753951846", "Base", "roberto@example.com", "Rápido y preciso", 4,byteJugador4);
                ClaseJugador jugador17 = new ClaseJugador(17, "César", "Pineda", "Torres", "Masculino", "1998-09-14", 180, "951753468", "Pivot", "cesar@example.com", "Dominante en la zona", 4,byteJugador3);
                ClaseJugador jugador18 = new ClaseJugador(18, "Estefanía", "Rojas", "García", "Femenino", "2003-07-28", 165, "357159486", "Alero", "estefania@example.com", "Tiradora de larga distancia", 4,byteJugador1);
                ClaseJugador jugador19 = new ClaseJugador(19, "Lucía", "Reyes", "Navarro", "Femenino", "1999-10-10", 168, "258753159", "Ala", "lucia@example.com", "Inteligente en defensa", 4,byteJugador5);
                ClaseJugador jugador20 = new ClaseJugador(20, "Jorge", "Molina", "Suárez", "Masculino", "1996-06-23", 183, "159357258", "AlaPivot", "jorge@example.com", "Fuerza en el rebote", 4,byteJugador2);

                // Equipo 5
                ClaseJugador jugador21 = new ClaseJugador(21, "David", "Sánchez", "Herrera", "Masculino", "2001-02-19", 178, "654789123", "Base", "david@example.com", "Ágil con el balón", 5,byteJugador5);
                ClaseJugador jugador22 = new ClaseJugador(22, "Jessica", "Ortega", "Díaz", "Femenino", "2002-09-08", 167, "147369852", "Alero", "jessica@example.com", "Tiradora de triples", 5,byteJugador2);
                ClaseJugador jugador23 = new ClaseJugador(23, "Ángel", "Gómez", "Ramírez", "Masculino", "2000-04-25", 180, "951852753", "Pivot", "angel@example.com", "Gran capacidad de bloqueo", 5,byteJugador4);
                ClaseJugador jugador24 = new ClaseJugador(24, "Emilia", "Fernández", "López", "Femenino", "1999-12-15", 169, "753951852", "AlaPivot", "emilia@example.com", "Versátil en ataque", 5,byteJugador3);
                ClaseJugador jugador25 = new ClaseJugador(25, "Tomás", "Castillo", "Morales", "Masculino", "1998-07-30", 181, "357258159", "Ala", "tomas@example.com", "Buena defensa", 5,byteJugador1);

                // Equipo 6
                ClaseJugador jugador26 = new ClaseJugador(26, "Sebastián", "Martínez", "Gutiérrez", "Masculino", "1997-05-11", 183, "753951456", "Pivot", "sebastian@example.com", "Gran dominio del rebote", 6,byteJugador4);
                ClaseJugador jugador27 = new ClaseJugador(27, "Adriana", "Hernández", "Cruz", "Femenino", "2000-03-18", 165, "159357486", "Ala", "adriana@example.com", "Explosiva en el contraataque", 6,byteJugador3);
                ClaseJugador jugador28 = new ClaseJugador(28, "Matías", "López", "Núñez", "Masculino", "1999-11-09", 176, "258753951", "Base", "matias@example.com", "Habilidoso con el balón", 6,byteJugador2);
                ClaseJugador jugador29 = new ClaseJugador(29, "Renata", "Santos", "Pérez", "Femenino", "2002-06-24", 170, "357159258", "Alero", "renata@example.com", "Creativa y rápida", 6,byteJugador5);
                ClaseJugador jugador30 = new ClaseJugador(30, "Gustavo", "Vega", "Suárez", "Masculino", "1995-08-12", 180, "951753258", "AlaPivot", "gustavo@example.com", "Fuerte en los duelos", 6,byteJugador1);

                // Equipo 7
                ClaseJugador jugador31 = new ClaseJugador(31, "Fernando", "Suárez", "Molina", "Masculino", "1996-02-14", 185, "123789456", "Pivot", "fernando@example.com", "Líder en defensa", 7,byteJugador3);
                ClaseJugador jugador32 = new ClaseJugador(32, "Raúl", "Gómez", "Herrera", "Masculino", "1999-09-21", 177, "321654987", "Alero", "raul@example.com", "Gran tiro de media distancia", 7,byteJugador1);
                ClaseJugador jugador33 = new ClaseJugador(33, "Javier", "Díaz", "Pérez", "Masculino", "2002-12-02", 180, "987321654", "Base", "javier@example.com", "Rápido y ágil", 7,byteJugador4);
                ClaseJugador jugador34 = new ClaseJugador(34, "Laura", "Ramírez", "Ortega", "Femenino", "2001-07-17", 168, "741369852", "AlaPivot", "laura@example.com", "Defensora clave", 7,byteJugador2);
                ClaseJugador jugador35 = new ClaseJugador(35, "Patricia", "Santos", "Luna", "Femenino", "1998-05-30", 171, "852741963", "Ala", "patricia@example.com", "Excelente control de balón", 7,byteJugador5);

                ClaseJugador jugador36 = new ClaseJugador(36, "Andrés", "Castillo", "Núñez", "Masculino", "1997-01-10", 183, "357951852", "Pivot", "andres@example.com", "Dominante en la pintura", 8,byteJugador2);
                ClaseJugador jugador37 = new ClaseJugador(37, "Esteban", "Vega", "Martínez", "Masculino", "2000-08-19", 179, "456987123", "Alero", "esteban@example.com", "Gran capacidad ofensiva", 8,byteJugador3);
                ClaseJugador jugador38 = new ClaseJugador(38, "Hugo", "Ortega", "Hernández", "Masculino", "1999-03-25", 174, "951468357", "Base", "hugo@example.com", "Estratega del equipo", 8,byteJugador1);
                ClaseJugador jugador39 = new ClaseJugador(39, "Carmen", "López", "Suárez", "Femenino", "2002-11-11", 166, "789654321", "AlaPivot", "carmen@example.com", "Mucha resistencia física", 8,byteJugador4);
                ClaseJugador jugador40 = new ClaseJugador(40, "Natalia", "González", "Pineda", "Femenino", "2003-06-28", 169, "357654951", "Ala", "natalia@example.com", "Precisión en los pases", 8,byteJugador5);

                ClaseJugador jugador41 = new ClaseJugador(41, "Manuel", "Jiménez", "Torres", "Masculino", "1995-04-05", 186, "456123987", "Pivot", "manuel@example.com", "Poderoso en la pintura", 9,byteJugador3);
                ClaseJugador jugador42 = new ClaseJugador(42, "Francisco", "Mendoza", "Rojas", "Masculino", "2001-09-12", 181, "753159456", "Alero", "francisco@example.com", "Tirador de tres confiable", 9,byteJugador2);
                ClaseJugador jugador43 = new ClaseJugador(43, "Alejandro", "Reyes", "Castaño", "Masculino", "2000-07-03", 176, "159357486", "Base", "alejandro@example.com", "Rápido en contraataques", 9,byteJugador4);
                ClaseJugador jugador44 = new ClaseJugador(44, "Isabel", "Fernández", "Luna", "Femenino", "2003-02-18", 167, "357456852", "AlaPivot", "isabel@example.com", "Defensora incansable", 9,byteJugador1);
                ClaseJugador jugador45 = new ClaseJugador(45, "Valeria", "Navarro", "García", "Femenino", "1998-12-15", 172, "951357852", "Ala", "valeria@example.com", "Creativa y rápida", 9,byteJugador5);

                // Insertamos los equipos en la base de datos
                equipoDao.insertarJugador(jugador1);
                equipoDao.insertarJugador(jugador2);
                equipoDao.insertarJugador(jugador3);
                equipoDao.insertarJugador(jugador4);
                equipoDao.insertarJugador(jugador5);
                equipoDao.insertarJugador(jugador6);
                equipoDao.insertarJugador(jugador7);
                equipoDao.insertarJugador(jugador8);
                equipoDao.insertarJugador(jugador9);
                equipoDao.insertarJugador(jugador10);
                equipoDao.insertarJugador(jugador11);
                equipoDao.insertarJugador(jugador12);
                equipoDao.insertarJugador(jugador13);
                equipoDao.insertarJugador(jugador14);
                equipoDao.insertarJugador(jugador15);
                equipoDao.insertarJugador(jugador16);
                equipoDao.insertarJugador(jugador17);
                equipoDao.insertarJugador(jugador18);
                equipoDao.insertarJugador(jugador19);
                equipoDao.insertarJugador(jugador20);
                equipoDao.insertarJugador(jugador21);
                equipoDao.insertarJugador(jugador22);
                equipoDao.insertarJugador(jugador23);
                equipoDao.insertarJugador(jugador24);
                equipoDao.insertarJugador(jugador25);
                equipoDao.insertarJugador(jugador26);
                equipoDao.insertarJugador(jugador27);
                equipoDao.insertarJugador(jugador28);
                equipoDao.insertarJugador(jugador29);
                equipoDao.insertarJugador(jugador30);
                equipoDao.insertarJugador(jugador31);
                equipoDao.insertarJugador(jugador32);
                equipoDao.insertarJugador(jugador33);
                equipoDao.insertarJugador(jugador34);
                equipoDao.insertarJugador(jugador35);
                equipoDao.insertarJugador(jugador36);
                equipoDao.insertarJugador(jugador37);
                equipoDao.insertarJugador(jugador38);
                equipoDao.insertarJugador(jugador39);
                equipoDao.insertarJugador(jugador40);
                equipoDao.insertarJugador(jugador41);
                equipoDao.insertarJugador(jugador42);
                equipoDao.insertarJugador(jugador43);
                equipoDao.insertarJugador(jugador44);
                equipoDao.insertarJugador(jugador45);

            }
        }).start();
    }
    private byte[] convertirImagenABytes(int drawableId) {
        // Decodificamos la imagen del recurso drawable
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), drawableId);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        return outputStream.toByteArray();
    }
    public void insertarJugador(AppDatabaseEquipos db, ClaseJugador jugador) {
        List<ClaseEquipo> equiposExistentes = db.equipoDao().obtenerTodosLosEquipos();

        // Imprime los equipos en la base de datos para depuración
        for (ClaseEquipo eq : equiposExistentes) {
            System.out.println("Equipo en DB: ID=" + eq.getEquipoId() + ", Nombre=" + eq.getNombreEquipo());
        }

        EquipoConJugadores equipo = db.equipoDao().getEquipoConJugadores(jugador.getEquipoId());
        if (equipo != null) {
            db.equipoDao().insertarJugador(jugador);
        } else {
            throw new IllegalArgumentException("EquipoId " + jugador.getEquipoId() + " no existe en la tabla ClaseEquipo.");
        }
    }
}
