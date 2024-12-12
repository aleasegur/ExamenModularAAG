import java.time.Year;
import java.util.Scanner;
//ALEJANDRO ASENCIO GURAU
public class Main {
    //POR CIERTO EL MAIN ESTA ABAJO DEL TODO
    //Muestra el menu princiapl en el main(Ejercicio1)
    public static void menu(){
        Scanner sc=new Scanner(System.in);
        char opcion;
        boolean condicionBuclePrincipal=true;
        //BUCLE PRINCIPAL DONDE SE EJECUTARA EL MENU, HAGO VARIOS DO WHILES
        // PARA COMPROBAR QUE EL VALOR INTRODUCIDO ES CORRECTO
        while (condicionBuclePrincipal) {
            System.out.println("(a)Mayores de edad");
            System.out.println("(b)Calculadora de ingresos de canal de Youtube");
            System.out.println("(c)Calculo horario");
            System.out.println("(d)Salir");
            System.out.println("Elige una opcion: ");
            opcion=sc.next().toLowerCase().charAt(0);
            switch (opcion){
                case 'a':
                    //variables del caso A
                    int numeroPersonas,resultGetYears;
                    do {
                        System.out.println("Introduce el numero de personas: ");
                        numeroPersonas=sc.nextInt();
                        if (numeroPersonas<=0){
                            System.err.println("No puede ser menor o igual a 0");
                        }
                    }while (numeroPersonas<=0);
                    resultGetYears=nacimientoYear(numeroPersonas);
                    System.out.println("El numero total de personas de mayoria de edad son "+resultGetYears);
                    break;
                case 'b':
                    //VARIABLES DEL CASO B
                    int numVisitas;
                    double resultIngresos;
                    do {
                        System.out.println("Introduce el numero de visitas de tu canal de Youtube: ");
                        numVisitas=sc.nextInt();
                        if (numVisitas<=0){
                            System.err.println("No puede ser menor o igual a 0");
                        }
                    }while (numVisitas<=0);
                    resultIngresos=calcularIngresosYoutube(numVisitas);
                    System.out.println("La cantidad ingresada en su canal de Youtube es "+resultIngresos);
                    break;
                case 'c':
                    //variables del caso c
                    int hora,min,huso;
                    String cadena;

                    do {
                        System.out.println("Introduce la hora: ");
                        hora=sc.nextInt();
                        if (hora<0) {
                            System.err.println("EL valor no puede ser menor a 0");
                        }
                    }while (hora<0);

                    do {
                        System.out.println("Introduce los minutos: ");
                        min=sc.nextInt();
                        if (min<0) {
                            System.err.println("EL valor no puede ser menor a 0");
                        }
                    }while (min<0);

                    do {
                        System.out.println("Introduce el huso horario(entre -11 y +12): ");
                        huso=sc.nextInt();
                        if (huso<-11 || huso>12){
                            System.err.println("El huso no puede ser menor a -11 y mayor a 12");
                        }
                    }while (huso<-11 || huso>12);

                    do {
                        System.out.println("INtroduce el tipo de cadena(AM,PM): ");
                        sc.nextLine();
                        cadena=sc.nextLine().toUpperCase();
                        if (!cadena.equals("PM") && !cadena.equals("AM")){
                            System.out.println("NO PUEDES PONER ALGO DIFERENTE A AM Y PM");
                        }
                    }while (!cadena.equals("PM") && !cadena.equals("AM"));
                    calcularHusoHorario(hora,min,cadena,huso);
                    break;
                case 'd':
                    System.out.println("Saliendo...");
                    condicionBuclePrincipal=false;
                    break;
                default:
                    System.err.println("El valor introducido no existe.VUELVA A INTRODUCIRLO");
            }
        }
        sc.close();
    }

    //Ejercicio2 funcion de preguntar año y decir la cantidad de personas mayores de edad
    public static int nacimientoYear(int n){
        Scanner sc=new Scanner(System.in);
        int res=0;
        int year;
        for (int i = 1; i <=n ; i++) {
            //CONSIDERO QUE EL AÑO DE NACIMIENTO NO PUEDE SER NEGATIVO
            do {
                System.out.println("Introduce tu año de nacimiento, persona " + i + " :");
                year=sc.nextInt();
               //Lo que hago es que entre los años de nacimiento mayores a el año actual -18 (numero de mayoria de edad)
               if (year<=Year.now().getValue()-18){
                   //Y aqui hago que se acumule los años donde las personas son mayores de edad
                   res++;
               }
            }while (year<=0);
        }
        return res;
    }

    //Ejercicio3 calculo total de ingresos en un canal de youtube
    public static double calcularIngresosYoutube(int visitas){
        Scanner sc=new Scanner(System.in);
        double res=0,resultCPM=0,resIngresosVideo;
        String tematica;
        boolean cantidadVideos=true;
        //Pones todas las telematicas que quieras, cuidado si pones deporte en vez de deportes lo mismo con las demas
        while (cantidadVideos) {
            System.out.println("Tematica del video(Introduce fin, para fianalizar) :");
            tematica = sc.nextLine().toLowerCase();
            resultCPM += calcularCPM(tematica);
            if (tematica.equals("fin")){
                cantidadVideos=false;
            }
        }
        resIngresosVideo=(resultCPM*visitas)/1000;
        res+=resIngresosVideo;
        return res;
    }

    //Hago un metodo para calcular el CPM y lo gasto en el metodo de arriba calcularIngresosYoutube
    public static double calcularCPM(String tematica){
        double res=0;
        if (tematica.equals("asmr")){
            res+=0.1;
        }else if (tematica.equals("deportes")){
            res+=1.0;
        }else if (tematica.equals("animales")){
            res+=0.2;
        } else if (tematica.equals("tecnologia")) {
            res+=0.5;
        }else if (tematica.equals("bebes")){
            res+=0.5;
        }else if (tematica.equals("fin")) {//he puesto esto para que no saltase el mensaje de error, no afecta al prgrama
            res+=0;
        }else {
            System.err.println("LA TEMATICA QUE HAS ESCRITO NO EXISTE.VUELVA A INTRODUCIRLO");
        }
        return res;
    }

    //Ejercicio4 calcular huso horario(puede que no me salga bien)
    public static void calcularHusoHorario(int hora,int min,String cadena,int huso){
        //Lo que intento es sumar o restar segun el valor de uso
        //Para despues hacer un modular con la hora total de un dia que son en PM O AM que son 12
        int horasPM=(hora+huso)%12;
        int horasAM=(12*hora+huso)/12;
        int minPM=(min%60);
        int minAM=(min*60)/60;
        horasPM+=minPM;
        horasAM+=minAM;
        if (cadena.equals("PM")){
            System.out.println(horasAM+":"+minAM+" "+cadena+"("+huso+")");
        }else if (cadena.equals("AM")){
            System.out.println(horasPM+":"+minPM+" "+cadena+"("+huso+")");
        }else {
            System.err.println("LA CADENA INTRODUCIDA NO ES VALIDA");
        }
    }

    public static void main(String[] args) {
        menu();
    }
}