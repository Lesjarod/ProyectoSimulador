import java.util.Scanner;

public class simulador {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String id_Proceso, nombre;
        int numProcesos, tamano, tEjecucion, tLlegada, memoria, quantum;
        ListaDinamica lista=new ListaDinamica();
        System.out.println("SIMULADOR PLANIFICADOR DE PROCESOS\n");
        System.out.println("****************************************************\n");
        System.out.println("Se simulará la política de planificación de Round Robin\n");
        System.out.print("Ingrese el tamaño de la memoria: ");
        memoria=sc.nextInt();
        System.out.print("Ingrese el valor del quantum: ");
        quantum=sc.nextInt();
        System.out.print("Ingresa el número de procesos a simular: ");
        numProcesos=sc.nextInt();
        System.out.println("\nIngresa los datos de los procesos:");
        for (int i=1; i<=numProcesos; i++){
            System.out.println("\nDatos del proceso " + i);
            System.out.print("\tId (numérico o alfabético): ");
            sc.nextLine();
            id_Proceso=sc.nextLine();
            while (lista.verificarId(id_Proceso)==1){
                System.out.print("Ya existe un proceso con ese identificador, ingrese un nuevo id: ");
                id_Proceso=sc.nextLine();
            }
            System.out.print("\tNombre: ");
            nombre=sc.nextLine();
            System.out.print("\tTamano: ");
            tamano=sc.nextInt();
            System.out.print("\tTiempo de llegada: ");
            tLlegada=sc.nextInt();
            System.out.print("\tTiempo que requiere para su ejecución: ");
            tEjecucion=sc.nextInt();
            Proceso proceso=new Proceso(id_Proceso, nombre, tamano, tEjecucion, tLlegada);
            lista.agregar(proceso);
            System.out.println("\nSe creó el proceso " + nombre);
            System.out.println("Cola de procesos listos:");
            lista.imprimir();
        }
    }
}
